<%@ page language="java"  pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title>编辑需求分析信息</title>
<link rel="stylesheet" type="text/css" href="skin/css/base.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.4.1.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/moment.js"></script>
<script type="text/javascript">
	$(function () {
		var id = location.search.substring(1);
		$.ajax({
			url:"${pageContext.request.contextPath}/analysis/getAnalysisById/"+id,
			type:"get",
			success:function (msg) {
				$("#id").val(msg.id);
				$("#proname").text(msg.proname);
				$("#title").val(msg.title);
				$("#simpledis").text(msg.simpledis);
				$("#detaileddis").text(msg.detaileddis);
				$("#remark").text(msg.remark);
				var addtime = moment(msg.addtime).format('YYYY-MM-DD');
				$("#addtime").val(addtime);
			}
		})
	})
	function save() {
		var proname = $("#proname").text();
		$("input[name=proname]").val(proname);
		$.ajax({
			url:"${pageContext.request.contextPath}/analysis/updateAnalysis",
			type:"post",
			data:$("form[name=form2]").serialize(),
			success:function (msg) {
				if (msg.statusCode == 200){
					window.location.href="${pageContext.request.contextPath}/project-need.jsp";
				} else {
					alert("修改失败！");
				}
			}
		})
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
    当前位置:项目管理>>编辑需求分析信息
 </td>
 </tr>
</table>
</td>
</tr>
</table>

<form name="form2">
<input type="hidden" name="id" id="id">
<input type="hidden" name="_method" value="put">
<input type="hidden" name="addtime" id="addtime">
<input type="hidden" name="proname">
<table width="98%" border="0" cellpadding="2" cellspacing="1" bgcolor="#D1DDAA" align="center" style="margin-top:8px">
<tr bgcolor="#E7E7E7">
	<td height="24" colspan="2" background="skin/images/tbg.gif">&nbsp;编辑需求信息&nbsp;</td>
</tr>
<tr>
	<td align="right" bgcolor="#FAFAF1" height="22">项目：</td>
	<td id="proname" name="proname" align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22"></td>
</tr>
<tr>
	<td align="right" bgcolor="#FAFAF1" height="22">标题：</td>
	<td  align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
		<input id="title" name="title"/>
	</td>
</tr>
<tr>
	<td align="right" bgcolor="#FAFAF1" height="22">简单描述：</td>
	<td align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
		<textarea id="simpledis" name="simpledis" rows=10 cols=130></textarea>
	</td>
</tr>
<tr >
	<td align="right" bgcolor="#FAFAF1" height="22">详细描述：</td>
	<td align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
		<textarea id="detaileddis" name="detaileddis" rows=15 cols=130></textarea>
	</td>
</tr>

<tr >
	<td align="right" bgcolor="#FAFAF1" >备注：</td>
	<td colspan=3 align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" >
		<textarea id="remark" name="remark" rows=10 cols=130></textarea>
	</td>
</tr>


<tr bgcolor="#FAFAF1">
<td height="28" colspan=4 align=center>
	&nbsp;
	<a href="javascript:save()" class="coolbg">保存</a>
	<a href="project-need.jsp" class="coolbg">返回</a>
</td>
</tr>
</table>

</form>
  

</body>
</html>