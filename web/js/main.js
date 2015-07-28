$(document).on("pageload", function () {
    
});

$(document).on("pagecreate", function () {
    $("#autocomplete").on("filterablebeforefilter", function (e, data) {
        var $ul = $(this),
                $input = $(data.input),
                value = $input.val(),
                html = "";
        $ul.html("");
        if (value && value.length > 2) {
            $ul.html("<li><div class='ui-loader'><span class='ui-icon ui-icon-loading'></span></div></li>");
            $ul.listview("refresh");
            $.ajax({
                url: _app_root + "/admin/countries/list",
                data: {
                    q: $input.val()
                }
            })
                    .then(function (response) {
                        $.each(response, function (i, val) {
                            html += "<li>" + val.name + "</li>";
                        });
                        $ul.html(html);
                        $ul.listview("refresh");
                        $ul.trigger("updatelayout");
                    });
        }
    });
});