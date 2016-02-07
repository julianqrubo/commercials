$(document).on("pageload", function () {

});




$(document).on("pagecreate", function () {
    setup_autocomplete("country");
    setup_autocomplete("department");
    setup_autocomplete("city");
    setup_delete("country");
    setup_delete("department");
    setup_delete("city");
    $('#fileupload').fileupload();
    $('#fileupload').fileupload(
            'option',
            'redirect',
            window.location.href.replace(
                    /\/[^\/]*$/,
                    '/cors/result.html?%s'
                    )
            );
    $('#fileupload').fileupload({
        autoUpload: true,
        done: function (e, data) {
            for (var i = 0; i<data.result.length; i++){
                $( "#" + $(this).attr('path') ).val(data.result[i].path);
                console.log(data.result[i]);
            }
        },
        progressall: function (e, data) {
            var progress = parseInt(data.loaded / data.total * 100, 10);
            $('#progress .progress-bar').css(
                'width',
                progress + '%'
            );
        },
        change: function (){
          $('#progress .progress-bar').css(
                'width',
                '0%'
            );  
        }
    });
});
//fns

var setup_delete = function (delete_id) {
    $('#delete_' + delete_id).on('click', function (e) {
        confirmDialog("Seguro desea eliminar este elemento?", {title: "Eliminar", callback: function () {
                var form = e.target.form;
                $.ajax({url: form.action + "/delete/" + form.id.value, type: 'POST'})
                        .then(function (response) {
                            $(":mobile-pagecontainer").pagecontainer("change", form.action, {reload: true});
                        });
            }});
    });
};
var setup_autocomplete = function (autocomplete_id) {
    $("#" + autocomplete_id + '-list').on("filterablebeforefilter", function (e, data) {
        var $ul = $(this),
                $input = $(data.input),
                value = $input.val(),
                html = "";
        $ul.html("");
        if (value && value.length > 2) {
            $ul.html("<li><div class='ui-loader'><span class='ui-icon ui-icon-loading'></span></div></li>");
            $ul.listview("refresh");
            $.ajax({
                url: _app_root + $ul.attr('service'),
                data: {
                    q: $input.val()
                }
            })
                    .then(function (response) {
                        $ul.show();
                        $.each(response, function (i, val) {
                            var $li = $("<li>" + val.name + "</li>");
                            $li.attr('code', val.code);
                            $li.attr('id', val.id);
                            $li.on('click', function (e) {
                                var _li = $(e.target);
                                var parentUl = $(_li.parent());
                                var hidden_id = "#" + parentUl.attr('id').replace('-list', '');
                                $(hidden_id).val(_li.attr('id'));
                                $(hidden_id + "-label").val(_li.html());
                                parentUl.hide();
                            });
                            $li.appendTo($ul);
                        });
                        $ul.listview("refresh");
                        $ul.trigger("updatelayout");
                    });
        }
    });
};
var confirmDialog = function (text, options) {
    var options = options || {
        title: "Confirmación",
        callback: function () {
            alert("Confirmado");
        }};
    var popupDialogId = 'popupDialog';
    $('<div data-role="popup" id="' + popupDialogId + '" data-confirmed="no" data-transition="pop" data-overlay-theme="b" data-theme="b" data-dismissible="false" style="max-width:500px;"> \
                        <div data-role="header" data-theme="a">\
                            <h1>' + options.title + '</h1>\
                        </div>\
                        <div role="main" class="ui-content">\
                            <h3 class="ui-title">' + text + '</h3>\
                            <a href="#" class="ui-btn ui-corner-all ui-shadow ui-btn-inline ui-btn-b optionConfirm" data-rel="back">Si</a>\
                            <a href="#" class="ui-btn ui-corner-all ui-shadow ui-btn-inline ui-btn-b optionCancel" data-rel="back" data-transition="flow">No</a>\
                        </div>\
                    </div>')
            .appendTo($.mobile.pageContainer);
    var popupDialogObj = $('#' + popupDialogId);
    popupDialogObj.trigger('create');
    popupDialogObj.popup({
        afterclose: function (event, ui) {
            popupDialogObj.find(".optionConfirm").first().off('click');
            var isConfirmed = popupDialogObj.attr('data-confirmed') === 'yes' ? true : false;
            $(event.target).remove();
            if (isConfirmed && options.callback) {
                options.callback();
            }
        }
    });
    popupDialogObj.popup('open');
    popupDialogObj.find(".optionConfirm").first().on('click', function () {
        popupDialogObj.attr('data-confirmed', 'yes');
    });
}