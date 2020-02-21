<%@ page language="java"  pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title>编辑模块信息</title>
<link rel="stylesheet" type="text/css" href="skin/css/base.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.4.1.js"></script>
<script type="text/javascript">
	$(function () {
		var id = window.location.search.substring(1);
		$.ajax({
			url:"${pageContext.request.contextPath}/module/getModuleById/"+id,
			type:"get",
			success:function (msg1) {
				$("#id").val(msg1.id);
				var proname = msg1.proname;
				var analysisFk = msg1.analysisFk;
				$.ajax({
					url:"${pageContext.request.contextPath}/pro/projetList",
					type:"get",
					success:function (msg2) {
						$(msg2).each(function (index,item) {
							if (item.pid == proname){
								$("#proname").append("<option selected='selected' value='"+item.pid+"'>"+item.pname+"</option>");
							} else {
								$("#proname").append("<option value='"+item.pid+"'>"+item.pname+"</option>");
							}
						})
					}
				})
				$.ajax({
					url:"${pageContext.request.contextPath}/analysis/getAnalysisById/"+analysisFk,
					type:"get",
					success:function (msg3) {
						$("option[name=option]").remove();
						$("#analysisFk").append("<option name='option' value='"+msg3.id+"'>"+msg3.title+"</option>");
					}
				})
				$("#modname").val(msg1.modname);
				$("#simpledis").text(msg1.simpledis);
				$("#detaileddis").text(msg1.detaileddis);
				$("#remark").text(msg1.remark);
				$("option[name=levels]").each(function () {
					if (msg1.level == $(this).val()){
						$(this).prop("selected",true);
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
				$("option[name=option]").remove();
				$("#analysisFk").append("<option name='option' value='"+msg.id+"'>"+msg.title+"</option>");
			}
		})
	}

	function save() {
		$.ajax({
			url:"${pageContext.request.contextPath}/module/updateModuleById/",
			type:"post",
			data:$("form[name=form2]").serialize(),
			success:function (msg) {
				if (msg.statusCode == 200){
					window.location.href="${pageContext.request.contextPath}/project-model.jsp"
				} else {
					alert("修改失败")
				}
			}
		})
	}
	function back() {
		window.location.href="${pageContext.request.contextPath}/project-model.jsp";
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
    当前位置:项目管理>>编辑模块信息
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
	<td height="24" colspan="2" background="skin/images/tbg.gif">&nbsp;编辑模块&nbsp;</td>
</tr>
<tr >
	<td align="right" bgcolor="#FAFAF1" height="22">选择项目：</td>
	<td  align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
		<select name="proname" id="proname" onchange="prochanged()">
			<option value=0>===请选择===</option>
		</select>
	</td>
</tr>
<tr >
	<td align="right" bgcolor="#FAFAF1" height="22">选择需求：</td>
	<td  align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
		<select name="analysisFk" id="analysisFk">

		</select>
	</td>
</tr>
<tr >
	<td align="right" bgcolor="#FAFAF1" height="22">模块名称：</td>
	<td  align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
		<input name="modname" id="modname"/>
	</td>
</tr>
<tr >
	<td align="right" bgcolor="#FAFAF1" height="22">优先级：</td>
	<td  align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
		<select name="level" id="level">
			<option name="levels" value="高">高</option>
			<option name="levels" value="中">中</option>
			<option name="levels" value="低">低</option>
			<option name="levels" value="暂缓">暂缓</option>
		</select>
	</td>
</tr>
<tr >
	<td align="right" bgcolor="#FAFAF1" height="22">简单描述：</td>
	<td align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
		<textarea name="simpledis" id="simpledis" rows=10 cols=130></textarea>
	</td>
</tr>
<tr >
	<td align="right" bgcolor="#FAFAF1" height="22">详细描述：</td>
	<td align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
		<textarea name="detaileddis" id="detaileddis" rows=15 cols=130></textarea>
	</td>
</tr>

<tr >
	<td align="right" bgcolor="#FAFAF1" >备注：</td>
	<td colspan=3 align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" >
		<textarea name="remark" id="remark" rows=10 cols=130></textarea>
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