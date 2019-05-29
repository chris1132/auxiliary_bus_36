<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <!-- 引入 ECharts 文件 -->
    <script src="../../static/js/echarts.min.js"></script>
</head>
<body>
<!-- 为 ECharts 准备一个具备大小（宽高）的 DOM -->
<div id="main" style="width: 800px;height:400px;top: 100px"></div>
<div id="main2" style="width: 800px;height:400px;"></div>


<script type="text/javascript">
    // 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById('main'));
    var myChart2 = echarts.init(document.getElementById('main2'));

    option = {
        title: {
            text: '乘车统计',
            subtext: '',
            left: 'center'
        },
        tooltip: {
            trigger: 'item',
            formatter: "{a} <br/>{b} : {c} ({d}%)",
        },
        legend: {
            // orient: 'vertical',
            // top: 'middle',
            bottom: 10,
            left: 'center',
            data: ['不愿意', '愿意']
        },
        series: [
            {
                type: 'pie',
                radius: '65%',
                center: ['50%', '50%'],
                selectedMode: 'single',
                data: [
                    {
                        value: 1856,
                        name: '愿意2077（94.67%）',
                        label: {
                            normal: {
                                formatter: [
                                    '{title|{b}}{abg|}',
                                    '  {weatherHead|现有方式}             {valueHead|数量}{rateHead|占比}',
                                    '{hr|}',
                                    ' ',
                                    '  私家车                {value|1710}{rate|82.3%}',
                                    ' ',
                                    '  公交车                    {value|108}{rate|5.2%}',
                                    ' ',
                                    '  电瓶车|自行车       {value|161}{rate|7.7%}',
                                    ' ',
                                    '  步行                       {value|98}{rate|4.7%}',
                                    ' '
                                ].join('\n'),
                                textStyle: {
                                    color: '#333'
                                },
                                backgroundColor: '#eee',
                                borderColor: '#777',
                                borderWidth: 1,
                                borderRadius: 4,
                                rich: {
                                    title: {
                                        color: '#eee',
                                        align: 'center'
                                    },
                                    abg: {
                                        backgroundColor: '#333',
                                        width: '100%',
                                        align: 'right',
                                        height: 25,
                                        borderRadius: [4, 4, 0, 0]
                                    },
                                    Sunny: {
                                        height: 30,
                                        align: 'left'
                                    },
                                    Cloudy: {
                                        height: 30,
                                        align: 'left'
                                    },
                                    Showers: {
                                        height: 30,
                                        align: 'left'
                                    },
                                    weatherHead: {
                                        color: '#333',
                                        height: 24,
                                        align: 'left'
                                    },
                                    hr: {
                                        borderColor: '#777',
                                        width: '100%',
                                        borderWidth: 0.5,
                                        height: 0
                                    },
                                    value: {
                                        width: 20,
                                        padding: [0, 20, 0, 30],
                                        align: 'left'
                                    },
                                    valueHead: {
                                        color: '#333',
                                        width: 20,
                                        padding: [0, 20, 0, 30],
                                        align: 'center'
                                    },
                                    rate: {
                                        width: 40,
                                        align: 'right',
                                        padding: [0, 10, 0, 0]
                                    },
                                    rateHead: {
                                        color: '#333',
                                        width: 40,
                                        align: 'center',
                                        padding: [0, 10, 0, 0]
                                    }
                                }
                            }
                        }
                    },
                    {
                        value: 99, name: '不愿意 117（5.33%）',
                        label: {
                            normal: {
                                formatter: [
                                    ' ',
                                    ' ',
                                    '{title|{b}}{abg|}',
                                    '  {weatherHead|原因}     {valueHead|数量}',
                                    '{hr|}',
                                    ' ',
                                    '乘车不安全  {value|24}',
                                    ' ',
                                    '换乘次数多  {value|23}',
                                    ' ',
                                    '乘坐时间长  {value|51}',
                                    ' ',
                                    '其他的原因  {value|58}'
                                ].join('\n'),
                                textStyle: {
                                    color: '#333'
                                },
                                backgroundColor: '#eee',
                                borderColor: '#777',
                                borderWidth: 1,
                                borderRadius: 4,
                                rich: {
                                    title: {
                                        color: '#eee',
                                        align: 'center'
                                    },
                                    abg: {
                                        backgroundColor: '#333',
                                        width: '100%',
                                        align: 'right',
                                        height: 25,
                                        borderRadius: [4, 4, 0, 0]
                                    },
                                    weatherHead: {
                                        color: '#333',
                                        height: 24,
                                        align: 'left'
                                    },
                                    hr: {
                                        borderColor: '#777',
                                        width: '100%',
                                        borderWidth: 0.5,
                                        height: 0
                                    },
                                    value: {
                                        width: 20,
                                        padding: [0, 20, 0, 30],
                                        align: 'left'
                                    },
                                    valueHead: {
                                        color: '#333',
                                        width: 20,
                                        padding: [0, 20, 0, 30],
                                        align: 'center'
                                    },
                                    rate: {
                                        width: 40,
                                        align: 'right',
                                        padding: [0, 10, 0, 0]
                                    },
                                    rateHead: {
                                        color: '#333',
                                        width: 40,
                                        align: 'center',
                                        padding: [0, 10, 0, 0]
                                    }
                                }
                            }
                        }
                    }
                ],
                itemStyle: {
                    emphasis: {
                        shadowBlur: 10,
                        shadowOffsetX: 0,
                        shadowColor: 'rgba(0, 0, 0, 0.5)'
                    }
                }
            }
        ]
    };


    radaroption = {
        title: {
            text: '影响因素'
        },
        legend: {
            data: ['愿意', '不愿意']
        },
        radar: [
            {
                indicator: [],
                center: ['25%', '50%'],
                radius: 120,

            },
            {
                indicator: [
                    {text: '乘车安全', max: 5},
                    {text: '运行时间', max: 5},
                    {text: '步行距离', max: 5},
                    {text: '乘坐舒适度', max: 5},
                    {text: '车辆票价', max: 5}
                ],
                center: ['25%', '50%'],
                radius: 120
            }
        ],
        series: [
            {
                name: '成绩单',
                type: 'radar',
                radarIndex: 1,
                data: [
                    {
                        value: [4.26, 3.52, 2.73, 1.59, 0.91],
                        name: '愿意',
                        label: {
                            normal: {
                                show: true,
                                formatter: function (params) {
                                    return params.value;
                                }
                            }
                        }
                    },
                    {
                        value: [4.06, 3.82, 4.73, 2.59, 1.91],
                        name: '不愿意',
                        label: {
                            normal: {
                                show: true,
                                formatter: function (params) {
                                    return params.value;
                                }
                            }
                        }
                    }
                ]
            }
        ]
    }


    //   myChart2.setOption(radaroption);
    myChart.setOption(option);
</script>
</body>


</html>