$(document).ready(function () {
    $.post("/aggregator/getQQ",
        {
            type: "国际"
        },
        function (data) {
            var article = "";
            var keywords = "";
            var move_text = "";
            var slider = "";
            for (var i = 5; i < 10; i++) {
                var title = data[i].title;
                var content = data[i].content.substr(0, 100) + "...";
                var type = data[i].type;
                var time = data[i].create;
                var url = data[i].url;
                var img = data[i].imageUrl.split(",")[0];
                for (var j = 0; j < data[i].keyword.split(" ").length; j++) {
                    var keyword = data[i].keyword.split(" ")[j];
                    if ("" != keyword) {
                        keywords += "<a class='tag' href='#'>" + keyword + "&nbsp;&nbsp;&nbsp;&nbsp;" + "</a>";
                    }
                }
                article += "<div class='article'>" +
                    "<div class='article-left'>" +
                    "<a href='" + url + "' target='view_window'><img src=" + img + "  style='height: 300px;'></a>" +
                    "</div>" +
                    "<div class='article-right'>" +
                    "<div class='article-title'>" +
                    "<p>" + time + "</p>" +
                    "<a class='title' href=" + url + " target='view_window'>" + title + "</a>" +
                    "</div>" +
                    "<div class='article-text'>" +
                    "<p>" + content + "</p>" +
                    "</div>" +
                    "</div><div class='clearfix'></div>" +
                    "</div>";
            }
            for (var i = 0; i < 2; i++) {
                var title = data[i].title;
                var url = data[i].url;
                var img = data[i].imageUrl.split(",")[0];
                move_text += "<div class='marquee" + (i + 1) + "'><a class='breaking' href=" + url + " target='view_window'>>>" + title + "</a></div>"
            }
            $(".js-marquee").html(move_text);
            for (var i = 0; i < 5; i++) {
                var title = data[i].title;
                var url = data[i].url;
                var img = data[i].imageUrl.split(",")[0];
                slider += "<li>" +
                    "<img src='" + img + "' class='callbacks_img'>" +
                    "<div class='caption'>" +
                    "<a href='" + url + "' target='view_window'>" + title + "</a>" +
                    "</div>" +
                    "</li>";
            }
            $(".articles .item").html(article);
            $(".tags .item").html(keywords);
            $("#slider").html(slider);
            $("#slider").responsiveSlides({
                auto: true,
                nav: true,
                speed: 500,
                namespace: "callbacks",
                pager: true,
            });
        }, 'json');
    $().UItoTop({easingType: 'easeOutQuart'});
});
$(document).ready(function () {
    $.post("/aggregator/getQQ",
        {
            type: "社会"
        },
        function (data) {
            var internation = ""
            for (var i = 0; i < 5; i = i + 2) {
                var title = data[i].title;
                var url = data[i].url;
                var img = data[i].imageUrl.split(",")[0];
                if (i % 2 == 0 && i < (data.length - 1)) {
                    internation += "<div class='internation-style-grids'>" +
                        "<div class='internation-style-left-grid'>" +
                        "<a href='" + url + "' target='view_window'><img src='" + img + "' alt=''  style='height: 300px;'/></a>" +
                        "<a class='title' href='" + url + "' target='view_window'>" + title + "</a>" +
                        "</div>" +
                        "<div class='internation-style-right-grid'>" +
                        "<a href='" + data[i + 1].url + "' target='view_window'><img src='" + data[i + 1].imageUrl.split(",")[0] + "' alt=''  style='height: 300px;'/></a>" +
                        "<a class='title' href='" + data[i + 1].url + "' target='view_window'>" + data[i + 1].title + "</a>" +
                        "</div>" +
                        "<div class='clearfix'></div></div>";
                }
            }
            $(".internation-item").html(internation);
        }, 'json');
});
$(document).ready(function () {
    $.post("/aggregator/getQQ", {
            type: "财经"
        },
        function (data) {
            var c_sports_main = ""
            for (var i = 0; i < 1; i++) {
                var title = data[i].title;
                var url = data[i].url;
                var img = data[i].imageUrl.split(",")[0];
                var time = data[i].create;
                c_sports_main += " <div class='c-image'>" +
                    "<a href='" + url + "' target='view_window'><img  src='" + img + "' alt='' style='height: 150px;'/></a>" +
                    "</div>" +
                    "<div class='c-text'>" +
                    "<a class='power' href='" + url + "' target='view_window'>" + title + "</a>" +
                    "<p class='date'>" + time + "</p>" +
                    "<a class='reu' href='" + url + "' target='view_window'><img src='images/more.png' alt=''/></a>" +
                    "<div class='clearfix'></div>" +
                    "</div>" +
                    "<div class='clearfix'></div>";
            }
            $(".s-grid-left .cricket .c-sports-main").html(c_sports_main);
        }, 'json');
});
$(document).ready(function () {
    $.post("/aggregator/getQQ", {
            type: "财经"
        },
        function (data) {
            var s_grid_small = ""
            for (var i = 1; i < 4; i++) {
                var title = data[i].title;
                var url = data[i].url;
                var img = data[i].imageUrl.split(",")[0];
                var time = data[i].create;
                s_grid_small += "  <div class='s-grid-small'>" +
                    "<div class='sc-image'>" +
                    "<a href='" + url + "' target='view_window'><img src='" + img + "' alt='' style='height: 120px;'/></a>" +
                    "</div>" +
                    "<div class='sc-text'>" +
                    "<a class='power' href='" + url + "' target='view_window'>" + title + "</a>" +
                    "<p class='date'>" + time + "</p>" +
                    "<a class='reu' href='" + url + "' target='view_window'><img src='images/more.png' alt=''/></a>" +
                    "<div class='clearfix'></div>" +
                    "</div>" +
                    "<div class='clearfix'></div>" +
                    "</div>";
            }
            $(".s-grid-left .cricket .item").html(s_grid_small);
        }, 'json');
});
$(document).ready(function () {
    $.post("/aggregator/getQQ", {
            type: "科技"
        },
        function (data) {
            var c_sports_main = ""
            for (var i = 0; i < 1; i++) {
                var title = data[i].title;
                var url = data[i].url;
                var img = data[i].imageUrl.split(",")[0];
                var time = data[i].create;
                c_sports_main += " <div class='c-image'>" +
                    "<a href='" + url + "' target='view_window'><img  src='" + img + "' alt='' style='height: 150px;'/></a>" +
                    "</div>" +
                    "<div class='c-text'>" +
                    "<a class='power' href='" + url + "' target='view_window'>" + title + "</a>" +
                    "<p class='date'>" + time + "</p>" +
                    "<a class='reu' href='" + url + "' target='view_window'><img src='images/more.png' alt=''/></a>" +
                    "<div class='clearfix'></div>" +
                    "</div>" +
                    "<div class='clearfix'></div>";
            }
            $(".s-grid-right .cricket .c-sports-main").html(c_sports_main);
        }, 'json');
});
$(document).ready(function () {
    $.post("/aggregator/getQQ", {
            type: "科技"
        },
        function (data) {
            var s_grid_small = ""
            for (var i = 1; i < 4; i++) {
                var title = data[i].title;
                var url = data[i].url;
                var img = data[i].imageUrl.split(",")[0];
                var time = data[i].create;
                s_grid_small += "  <div class='s-grid-small'>" +
                    "<div class='sc-image'>" +
                    "<a href='" + url + "' target='view_window'><img src='" + img + "' alt='' style='height: 120px;'/></a>" +
                    "</div>" +
                    "<div class='sc-text'>" +
                    "<a class='power' href='" + url + "' target='view_window'>" + title + "</a>" +
                    "<p class='date'>" + time + "</p>" +
                    "<a class='reu' href='" + url + "' target='view_window'><img src='images/more.png' alt=''/></a>" +
                    "<div class='clearfix'></div>" +
                    "</div>" +
                    "<div class='clearfix'></div>" +
                    "</div>";
            }
            $(".s-grid-right .cricket .item").html(s_grid_small);
        }, 'json');
});
$(document).ready(function () {
    $.post("/aggregator/getQQ", {
            type: "军事"
        },
        function (data) {
            var side_bar_article = ""
            for (var i = 0; i < 5; i++) {
                var title = data[i].title;
                var url = data[i].url;
                var img = data[i].imageUrl.split(",")[0];
                side_bar_article += "<div class='side-bar-article'>" +
                    "<a href='" + url + "' target='view_window'><img src='" + img + "' alt='' style='height: 300px;'/></a>" +
                    "<div class='side-bar-article-title'>" +
                    "<a href='" + url + "'>" + title + "</a>" +
                    "</div>" +
                    "</div>";
            }
            $(".side-bar-articles").html(side_bar_article);
        }, 'json');
});
$(document).ready(function () {
    $.post("/aggregator/getQQ",
        {
            type: "娱乐"
        },
        function (data) {
            var popular_grid = ""
            for (var i = 0; i < 5; i++) {
                var title = data[i].title;
                var url = data[i].url;
                var img = data[i].imageUrl.split(",")[0];
                var time = data[i].create;
                popular_grid += "<div class='popular-grid'>" +
                    "<a href='" + url + "' target='view_window'><img src='" + img + "' alt='' style='height: 300px;'/></a>" +
                    "<a class='title' href='" + url + "' target='view_window'>" + title + "</a>" +
                    "<p>" + time + "</p>" +
                    "</div>";
            }
            $(".popular-grids").html(popular_grid);
        }, 'json');
});