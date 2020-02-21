<%@ page language="java"  pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <script type="text/javascript" src="js/echarts.min.js"></script>
    <script type="text/javascript" src="js/jquery-3.4.1.js"></script>
    <script type="text/javascript">
        $(function () {
            var names=[];
            var scores=[];
            $.ajax({
                type:"GET",
                url:"${pageContext.request.contextPath}/comparision/list",
                success:function (msg) {
                    $(msg).each(function (index,item) {
                        names.push(item.company);
                        scores.push(item.targetmoney);
                    })
                    <!-- 3.初始化图表  -->
                    var myChart= echarts.init(document.getElementById("main"));
                    // 5.指定图表的配置项和数据
                    var option = {
                        title: {
                            text: '各品牌手机销售金额展示'
                        },
                        tooltip: {},
                        legend: {
                            data:['销售金额']
                        },
                        xAxis: {
                            data: names
                        },
                        yAxis: {
                            type:'value',
                            axisLabel:{
                                formatter:'{value}万'
                            }
                        },
                        series: [{
                            name: '销售金额',
                            type: 'bar',
                            data: scores
                        }]
                    };
                    <!-- 4.往图表中设置数据 -->
                    myChart.setOption(option);
                }
            });
        });
    </script>
</head>
<body>
<div id="main" style="width: 500px;height: 400px"></div>
</body>
</html>