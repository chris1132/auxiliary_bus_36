<#import "include/layout.ftl" as layout />

<@layout.iframe title="展示面板">
<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=kScef3hFzXqnSMGxyHTCY8ZZY6SwhDiN"></script>

<div class="wrap-fluid">
    <div class="container-fluid paper-wrap bevel tlbr">
        <!-- CONTENT -->
        <!--TITLE -->
        <div class="row">
            <div id="paper-top">
                <div class="col-lg-3">
                    <h2 class="tittle-content-header">
                        <i class="icon-window"></i>
                        <span>运行监控
                            </span>
                    </h2>
                </div>
            </div>
        </div>
        <!--/ TITLE -->
        <div id="paper-middle">
            <div id="mapContainer"></div>
        </div>

        <#--  折线图  -->
        <div class="content-wrap">
            <div class="row">
            <#--<div class="col-lg-6">-->
                <div class="chart-wrap">
                    <div class="chart-dash">
                        <!-- <div id="sparkline"></div> -->
                        <div id="placeholder" style="width:100%;height:200px;"></div>
                    </div>
                    <div class="row">
                        <div class="col-lg-8">
                            <div class="speed">
                                <h2>Speed Avarage</h2>
                                <h1>74
                                    <span>Km / hours</span>
                                </h1>
                            </div>
                        </div>
                        <div class="col-lg-4">
                            <div class="traffic">
                                <h2>Traffic per day</h2>
                                <h1>2.5874</h1>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-lg-4">
                            <div class="traffic-rates">
                                <h4>Traffic Rates</h4>
                                <h1>76 %
                                    <span>-1,2 %</span>
                                </h1>
                            </div>
                        </div>
                        <div class="col-lg-4">
                            <div class="traffic-rates">
                                <h4>Traffic Rates</h4>
                                <h1>25 %
                                    <span>-1,8 %</span>
                                </h1>
                            </div>
                        </div>
                        <div class="col-lg-4">
                            <div class="traffic-rates">
                                <h4>Traffic Rates</h4>
                                <h1>83 %
                                    <span>-1,9 %</span>
                                </h1>
                            </div>
                        </div>
                    </div>
                </div>
            <#--</div>-->

            </div>
            <!-- /END OF CONTENT -->

            <!-- FOOTER -->
            <div class="footer-space"></div>
            <div id="footer">
                <div class="devider-footer-left"></div>
                <div class="time">
                    <p id="spanDate">
                    <p id="clock">
                </div>

            </div>
            <!-- / END OF FOOTER -->

        </div>

        <#--  指标图-->
        <div class="content-wrap">
            <div class="row">
                <div class="col-lg-3">
                    <div class="profit" id="profitClose">
                        <div class="headline ">
                            <h3>
                                    <span>
                                        <i class="maki-ferry"></i>&#160;&#160;Ferry Arrival</span>
                            </h3>
                            <div class="titleClose">
                                <a href="#profitClose" class="gone">
                                    <span class="entypo-cancel"></span>
                                </a>
                            </div>
                        </div>

                        <div class="value">
                                <span class="pull-left"><i class="entypo-clock clock-position"></i>
                                </span>
                            <div id="getting-started">
                                <span>%M</span>

                                <span>%S</span>
                            </div>


                        </div>

                        <div class="progress-tinny">
                            <div style="width: 50%" class="bar"></div>
                        </div>
                        <div class="profit-line">
                            <i class="fa fa-caret-up fa-lg"></i>&#160;&#160; 50% &#160;From Last Hour</div>
                    </div>
                </div>
                <div class="col-lg-3">
                    <div class="revenue" id="revenueClose">
                        <div class="headline ">

                            <h3>
                                    <span>
                                        <i class="maki-aboveground-rail"></i>&#160;&#160;Train Speed</span>
                            </h3>

                            <div class="titleClose">
                                <a href="#revenueClose" class="gone">
                                    <span class="entypo-cancel"></span>
                                </a>
                            </div>
                        </div>
                        <div class="value">
                                <span class="pull-left"><i class="entypo-gauge gauge-position"></i>
                                </span>
                            <canvas id="canvas4" width="70" height="70"></canvas>
                            <i class="pull-right">/Km</i>

                        </div>

                        <div class="progress-tinny">
                            <div style="width: 25%" class="bar"></div>
                        </div>
                        <div class="profit-line">
                            <i class="fa fa-caret-down fa-lg"></i>&#160;&#160;Rate : 20 km/Hour</div>
                    </div>
                </div>
                <div class="col-lg-3">
                    <div class="order" id="orderClose">
                        <div class="headline ">
                            <h3>
                                    <span>
                                        <i class="maki-airport"></i>&#160;&#160;AIR PORT TRAFFIC</span>
                            </h3>
                            <div class="titleClose">
                                <a href="#orderClose" class="gone">
                                    <span class="entypo-cancel"></span>
                                </a>
                            </div>
                        </div>
                        <div class="value">
                                <span><i class="fa fa-plane fa-2x"></i>
                                </span><b id="speed"></b><b>Take Off</b>
                        </div>

                        <div class="progress-tinny">
                            <div style="width: 10%" class="bar"></div>
                        </div>
                        <div class="profit-line">
                            <i class="fa fa-caret-down fa-lg"></i>&#160;&#160;Rate : 20 Plane/Hour</div>
                    </div>
                </div>
                <div class="col-lg-3">
                    <div class=" member" id="memberClose">
                        <div class="headline ">
                            <h3>
                                    <span>
                                        <i class="fa fa-truck"></i>
                                        &#160;&#160;CARGO
                                    </span>
                            </h3>
                            <div class="titleClose">
                                <a href="#memberClose" class="gone">
                                    <span class="entypo-cancel"></span>
                                </a>
                            </div>
                        </div>
                        <div class="value">
                                <span><i class="maki-warehouse"></i>
                                </span>45<b>Sent</b>

                        </div>
                        <div class="progress-tinny">
                            <div style="width: 50%" class="bar"></div>
                        </div>
                        <div class="profit-line">
                            <span class="entypo-down-circled"></span>&#160;50% From Last Month</div>
                    </div>
                </div>
            </div>
        </div>

    </div>
</div>

<script type="text/javascript">
    //创建和初始化地图函数：
    var map = new BMap.Map("mapContainer");//在百度地图容器中创建一个地图

    var userlat = 30.770904;
    var userlng = 120.743517;

    var buslat = 30.765318;
    var buslng = 120.736665;

    <!-- var pointArr = [{lat:30.770904,lng:120.743517,message:"用户坐标"}, -->
    <!-- {lat:30.765318,lng:120.736465,message:"车辆坐标"}, -->
    <!-- {lat:30.771424,lng:120.745161,message:"起始点坐标"}, -->
    <!-- {lat:30.747956,lng:120.770816,message:"终点坐标"}] -->
    function initMap(){

        var point = new BMap.Point(120.759938,30.767817);//定义一个中心点坐标
        map.centerAndZoom(point,14);//设定地图的中心点和坐标并将地图显示在地图容器中

        map.enableDragging();//启用地图拖拽事件，默认启用(可不写)
        map.enableScrollWheelZoom();//启用地图滚轮放大缩小
        map.enableDoubleClickZoom();//启用鼠标双击放大，默认启用(可不写)
        map.enableKeyboard();//启用键盘上下左右键移动地图

//		addIcon("images/乘车辅助/location.png",userpt,new BMap.Size(30,28));
        var timesRun = 0;
        calculeBus2UserDis();
        var interval=setInterval(function(){
            deletePoint()
            var buslat = buslat+0.0004;
            var buslng = buslng+0.003;
            calculeBus2UserDis();
            timesRun +=1;
            if(timesRun== 5){
                clearInterval(interval)
            }
        },2000);
        drivingSearch();

    }

    function deletePoint(){
        var allOverlay = map.getOverlays();
        for(var i = 0;i<allOverlay.length;i++) {
            //删除指定经度的点
            if (allOverlay[i].point && allOverlay[i].point.lng == buslng) {
                map.removeOverlay(allOverlay[i]);
                return false;
            }
        }
    }

    function drivingSearch(){
        var p1 = new BMap.Point(120.745161,30.771424);
        var p2 = new BMap.Point(120.770816,30.747956);
        var driving = new BMap.DrivingRoute(map, {renderOptions:{map: map, autoViewport: true}});
        driving.search(p1, p2,{waypoints:[new BMap.Point(120.755833,30.768383),new BMap.Point(120.762049,30.750601)]});//waypoints表示途经点
    }

    function calculeBus2UserDis(){
        var buspt = new BMap.Point(buslng,buslat);
        var userpt = new BMap.Point(userlng,userlat);
        var searchComplete = function (results){
            if (transit.getStatus() != BMAP_STATUS_SUCCESS){
                return ;
            }
            var plan = results.getPlan(0);
            var busContent = "时间：" + plan.getDuration(true) + "   路程：" +plan.getDistance(true);

            var busMarker = addIcon("${staticFileDomain!''}/assets/img/bus_log.png",buspt,new BMap.Size(25,30));
            openInfoWin(busContent,buspt,busMarker)
        }

        var transit = new BMap.DrivingRoute(map, {onSearchComplete: searchComplete});
        transit.setSearchCompleteCallback(searchComplete)
        transit.search(buspt, userpt);

    }

    function openInfoWin(content,point,marker){
        var busInfoWindow = new BMap.InfoWindow(content, opts);  // 创建信息窗口对象
        marker.addEventListener("click", function(){
            map.openInfoWindow(busInfoWindow,point); //开启信息窗口
        });

    }

    function addIcon(url,point,mapSize){

        var myIcon = new BMap.Icon(url, mapSize,{
            // 指定定位位置。
            // 当标注显示在地图上时，其所指向的地理位置距离图标左上
            // 角各偏移10像素和30像素。
            // 图标中央下端的尖角位置。
            anchor: new BMap.Size(10, 30)});
        var marker = new BMap.Marker(point,{icon:myIcon});  // 创建标注
        map.addOverlay(marker);

        return marker;

    }
    var opts = {
        width : 200,     // 信息窗口宽度
        height: 100,     // 信息窗口高度
        title : "车辆位置" , // 信息窗口标题
    }

    initMap();//创建和初始化地图
</script>
</@layout.iframe>