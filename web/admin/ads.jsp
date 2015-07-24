<%@include file="templates/header.jsp" %>
<h1>Anuncios</h1>
<form>
        <input id="filterTable-input" data-type="search">
</form>
<table data-role="table" id="movie-table" data-filter="true" data-input="#filterTable-input" class="ui-responsive">
        <thead>
                <tr>
                        <th data-priority="1">Rank</th>
                        <th data-priority="persist">Movie Title</th>
                        <th data-priority="2">Year</th>
                        <th data-priority="3"><abbr title="Rotten Tomato Rating">Rating</abbr></th>
                        <th data-priority="4">Reviews</th>
                    </tr>
                </thead>
            <tbody>
                    <tr>
                            <th>1</th>
                            <td><a href="http://en.wikipedia.org/wiki/Citizen_Kane" data-rel="external">Citizen Kane</a></td>
                            <td>1941</td>
                            <td>100%</td>
                            <td>74</td>
                        </tr>
                    <tr>
                            <th>2</th>
                            <td><a href="http://en.wikipedia.org/wiki/Casablanca_(film)" data-rel="external">Casablanca</a></td>
                            <td>1942</td>
                            <td>97%</td>
                            <td>64</td>
                        </tr>
                    <tr>
                            <th>3</th>
                            <td><a href="http://en.wikipedia.org/wiki/The_Godfather" data-rel="external">The Godfather</a></td>
                            <td>1972</td>
                            <td>97%</td>
                            <td>87</td>
                        </tr>
                </tbody>
        </table>
<%@include file="templates/footer.jsp" %>