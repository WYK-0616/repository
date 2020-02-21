<%@ page language="java"  pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title>添加需求分析信息</title>
<link rel="stylesheet" type="text/css" href="skin/css/base.css">
<script type="text/javascript" src="js/jquery-3.4.1.js"></script>
	<script type="text/javascript">
		$(function () {
			$.ajax({
				url:"${pageContext.request.contextPath}/pro/projetListWithNotAnalysis",
				type:"get",
				success:function (msg) {
					$(msg).each(function (index,item) {
						var option = "<option value="+item.pid +","+ item.pname+">"+item.pname+"</option>"
						$("#pname").append(option);
					})
				}
			})
		})
		function save() {
			var pid = $("#pname").val().split(",")[0];
			var pname = $("#pname").val().split(",")[1];
			var title = $("#title").val();
			var simpledis = $("#simpledis").val();
			var detaileddis = $("#detaileddis").val();
			var remark = $("#remark").val();
			$.ajax({
				url:"${pageContext.request.contextPath}/analysis/addAnalysis",
				type:"post",
				data:{"id":pid,"proname":pname,"title":title,"simpledis":simpledis,"detaileddis":detaileddis,"remark":remark},
				success:function (msg) {
					if(msg.statusCode == 200){
						window.location.href="${pageContext.request.contextPath}/project-need.jsp";
					} else if (msg.statusCode == 499) {
						alert("每个项目只能对应一个需求！")
					} else if (msg.statusCode == 500) {
						alert("添加失败")
					}
				}
			})
		}
		function back() {
			window.location.href="${pageContext.request.contextPath}/project-need.jsp";
		}
	</script>
</head>
<body leftmargin="8" topmargin="8" background='skin/images/allbg.gif'>

<!--  快速转换位置按钮  -->
<table width="98%" border="0" cellpadding="0" cellspacing="1" bgcolor="#D1DDAA" align="center">
<tr>
 <td height="26" background="skin/images/newlinebg3.gif">
  <table width="58%" border="0" cellspacing="0" cellpadding="0">
  <tr>
  <td >
    当前位置:项目管理>>添加需求分析基本信息
 </td>
 </tr>
</table>
</td>
</tr>
</table>

<form name="form2">

<table width="98%" border="0" cellpadding="2" cellspacing="1" bgcolor="#D1DDAA" align="center" style="margin-top:8px">
<tr bgcolor="#E7E7E7">
	<td height="24" colspan="2" background="skin/images/tbg.gif">&nbsp;添加新需求&nbsp;</td>
</tr>
<tr>
	<td align="right" bgcolor="#FAFAF1" height="22">选择项目：</td>
	<td  align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
		<select id="pname">
			<option value=0>===请选择===</option>
		</select>
	</td>
</tr>
<tr >
	<td align="right" bgcolor="#FAFAF1" height="22">标题：</td>
	<td  align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
		<input id="title"/>
	</td>
</tr>
<tr >
	<td align="right" bgcolor="#FAFAF1" height="22">简单描述：</td>
	<td align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
		<textarea rows=10 cols=130 id="simpledis"></textarea>
	</td>
</tr>
<tr>
	<td align="right" bgcolor="#FAFAF1" height="22">详细描述：</td>
	<td align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
		<textarea rows=15 cols=130 id="detaileddis"></textarea>
	</td>
</tr>

<tr >
	<td align="right" bgcolor="#FAFAF1" >备注：</td>
	<td colspan=3 align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" >
		<textarea rows=10 cols=130 id="remark"></textarea>
	</td>
</tr>


<tr bgcolor="#FAFAF1">
<td height="28" colspan=4 align=center>
	&nbsp;
	<a href="javascript:save()" class="coolbg">保存</a>
	<a href="javascript:back()" class="coolbg">返回</a>
</td>
</tr>
</table>

</form>
  

</body>
</html>