<#macro iframe title="">
    <!DOCTYPE html>
    <html>
    <head>
        <meta charset="utf-8">
        <title>${title}</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="description" content="">
        <meta name="author" content="">
        <!-- Le styles -->
        <link rel="stylesheet" href="${staticFileDomain!''}/static/css/style.css">
        <link rel="stylesheet" href="${staticFileDomain!''}/static/css/loader-style.css">
        <link rel="stylesheet" href="${staticFileDomain!''}/static/css/bootstrap.css">
        <link rel="stylesheet" type="text/css" href="${staticFileDomain!''}/static/js/progress-bar/number-pb.css">
        <style type="text/css">
            canvas#canvas4 {
                position: relative;
                top: 20px;
            }
        </style>
        <!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
        <!--[if lt IE 9]>
        <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
        <![endif]-->
        <!-- Fav and touch icons -->
        <link rel="shortcut icon" href="${staticFileDomain!''}/static/ico/minus.png">
    </head>
    <body>

    <!-- Preloader -->
    <div id="preloader">
        <div id="status">&nbsp;</div>
    </div>
    <#--顶部栏目-->
    <#include "top_navbar_index.ftl">
    <#--/END OF 顶部栏目-->digital

    <#-- 左侧导航栏 -->
    <#include "side_menu.ftl">
    <#-- END OF 左侧导航栏 -->

    <#--  主页面部分 -->
    <#nested/>
    <#--  END OF 主页面部分 -->

    <#-- 右侧栏目 -->
    <#include "right_slider_content.ftl">
    <#-- END OF 右侧栏目-->
    <script type="text/javascript" src="${staticFileDomain!''}/static/js/jquery.js"></script>
    <script src="${staticFileDomain!''}/static/js/progress-bar/src/jquery.velocity.min.js"></script>
    <script src="${staticFileDomain!''}/static/js/progress-bar/number-pb.js"></script>
    <script src="${staticFileDomain!''}/static/js/progress-bar/progress-app.js"></script>

    <!-- MAIN EFFECT -->
    <script type="text/javascript" src="${staticFileDomain!''}/static/js/preloader.js"></script>
    <script type="text/javascript" src="${staticFileDomain!''}/static/js/bootstrap.js"></script>
    <script type="text/javascript" src="${staticFileDomain!''}/static/js/app.js"></script>
    <script type="text/javascript" src="${staticFileDomain!''}/static/js/load.js"></script>
    <script type="text/javascript" src="${staticFileDomain!''}/static/js/main.js"></script>
    <!-- GAGE -->
    <script type="text/javascript" src="${staticFileDomain!''}/static/js/chart/jquery.flot.js"></script>
    <script type="text/javascript" src="${staticFileDomain!''}/static/js/chart/jquery.flot.resize.js"></script>
    <script type="text/javascript" src="${staticFileDomain!''}/static/js/chart/realTime.js"></script>
    <script type="text/javascript" src="${staticFileDomain!''}/static/js/speed/canvasgauge-coustom.js"></script>
    <script type="text/javascript" src="${staticFileDomain!''}/static/js/countdown/jquery.countdown.js"></script>


    <script>
        var gauge4 = new Gauge("canvas4", {
            'mode': 'needle',
            'range': {
                'min': 0,
                'max': 90
            }
        });
        gauge4.draw(Math.floor(Math.random() * 90));
        var run = setInterval(function () {
            gauge4.draw(Math.floor(Math.random() * 90));
        }, 3500);
    </script>

    <script type="text/javascript">
        var output, started, duration, desired;

        // Constants
        duration = 5000;
        desired = '50';

        // Initial setup
        output = $('#speed');
        started = new Date().getTime();

        // Animate!
        animationTimer = setInterval(function () {
            // If the value is what we want, stop animating
            // or if the duration has been exceeded, stop animating
            if (output.text().trim() === desired || new Date().getTime() - started > duration) {
                console.log('animating');
                // Generate a random string to use for the next animation step
                output.text('' + Math.floor(Math.random() * 60)
                );
            } else {
                console.log('animating');
                // Generate a random string to use for the next animation step
                output.text('' + Math.floor(Math.random() * 120)
                );
            }
        }, 5000);
    </script>
    <script type="text/javascript">
        $('#getting-started').countdown('2015/01/01', function (event) {
            $(this).html(event.strftime(
                '<span>%M</span>' + '<span class="start-min">:</span>' + '<span class="start-min">%S</span>'));
        });
    </script>
    </body>
    </html>
</#macro>