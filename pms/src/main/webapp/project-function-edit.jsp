<%@ page language="java"  pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title>编辑功能信息</title>
<link rel="stylesheet" type="text/css" href="skin/css/base.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.4.1.js"></script>
<script type="text/javascript">
	var id = window.location.search.substring(1);
	$(function () {
		$.ajax({
			url:"${pageContext.request.contextPath}/function/getFunctionById/"+id,
			type:"get",
			success:function (msg1) {
				var proname = msg1.proname;
				var analysisname = msg1.analysisname;
				var modeleFk = msg1.modeleFk;
				var functionname = msg1.functionname;
				$("#functionname").val(functionname);
				var level = msg1.level;
				$("option[name=lopts]").each(function () {
					if ($(this).val() == level){
						$(this).prop("selected",true);
					}
				})
				var simpledis = msg1.simpledis;
				$("#simpledis").val(simpledis);
				var detaileddis = msg1.detaileddis;
				$("#detaileddis").val(detaileddis);
				var remark = msg1.remark;
				$("#remark").val(remark);
				var id = msg1.id;
				$("#id").val(id);
				$.ajax({
					url:"${pageContext.request.contextPath}/pro/projetList",
					type:"get",
					success:function (msg2) {
						$(msg2).each(function (index,item) {
							if (proname == item.pid){
								$("#proname").append("<option selected='selected' value='"+item.pid+"'>"+item.pname+"</option>");
							} else {
								$("#proname").append("<option value='"+item.pid+"'>"+item.pname+"</option>");
							}
						})
					}
				})
				$.ajax({
					url:"${pageContext.request.contextPath}/analysis/getAnalysisById/"+analysisname,
					type:"get",
					success:function (msg3) {
						$("option[name=alaoption]").remove();
						$("#analysisname").append("<option name='alaoption' value='"+msg3.id+"'>"+msg3.title+"</option>");
					}
				})

				$.ajax({
					url:"${pageContext.request.contextPath}/module/getModulesByAnalysisFk/"+analysisname,
					type:"get",
					success:function (msg4) {
						$("option[name=modoption]").remove();
						$(msg4).each(function (index,item) {
							if (modeleFk == item.id){
								$("#modeleFk").append("<option selected='selected' name='modoption' value='"+item.id+"'>"+item.modname+"</option>");
							}else {
								$("#modeleFk").append("<option name='modoption' value='"+item.id+"'>"+item.modname+"</option>");
							}
						})
					}
				})

			}
		})
	})
	function prochanged() {
		var analysisFk = $("#proname").val();
		$.ajax({
			url:"${pageContext.request.contextPath}/analysis/getAnalysisById/"+analysisFk,
			type:"get",
			success:function (msg) {
				$("option[name=alaoption]").remove();
				$("#analysisname").append("<option name='alaoption' value='"+msg.id+"'>"+msg.title+"</option>");
			}
		})
		$.ajax({
			url:"${pageContext.request.contextPath}/module/getModulesByAnalysisFk/"+analysisFk,
			type:"get",
			success:function (msg) {
				$("option[name=modoption]").remove();
				$(msg).each(function (index,item) {
					$("#modeleFk").append("<option name='modoption' value='"+item.id+"'>"+item.modname+"</option>");
				})
			}
		})
	}
	function save() {
		$.ajax({
			url:"${pageContext.request.contextPath}/function/updateFunction",
			type:"post",
			data:$("form[name=form2]").serialize(),
			success:function (msg) {
				if (msg.statusCode == 200){
					window.location.href="${pageContext.request.contextPath}/project-function.jsp";
				}else {
					alert("修改失败")
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
    当前位置:项目管理>>编辑功能信息
 </td>
 </tr>
</table>
</td>
</tr>
</table>

<form name="form2">
<input type="hidden" name="id" id="id">
<input type="hidden" name="_method" value="put">
<table width="98%" border="0" cellpadding="2" cellspacing="1" bgcolor="#D1DDAA" align="center" style="margin-top:8px">
<tr bgcolor="#E7E7E7">
	<td height="24" colspan="2" background="skin/images/tbg.gif">&nbsp;编辑功能&nbsp;</td>
</tr>
<tr >
	<td align="right" bgcolor="#FAFAF1" height="22">选择项目：</td>
	<td  align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
		<select id="proname" name="proname" onchange="prochanged()">
			<option value=0>===请选择===</option>
		</select>
	</td>
</tr>
<tr >
	<td align="right" bgcolor="#FAFAF1" height="22">选择需求：</td>
	<td  align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
		<select name="analysisname" id="analysisname">

		</select>
	</td>
</tr>
<tr >
	<td align="right" bgcolor="#FAFAF1" height="22">选择模块：</td>
	<td  align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
		<select id="modeleFk" name="modeleFk">

		</select>
	</td>
</tr>
<tr >
	<td align="right" bgcolor="#FAFAF1" height="22">功能名称：</td>
	<td  align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
		<input name="functionname" id="functionname"/>
	</td>
</tr>
<tr >
	<td align="right" bgcolor="#FAFAF1" height="22">优先级：</td>
	<td  align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
		<select id="level" name="level">
			<option name="lopts" value="高">高</option>
			<option name="lopts" value="中">中</option>
			<option name="lopts" value="低">低</option>
			<option name="lopts" value="暂缓">暂缓</option>
		</select>
	</td>
</tr>
<tr >
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
	<a href="project-function.jsp" class="coolbg">返回</a>
</td>
</tr>
</table>

</form>
  

</body>
</html>