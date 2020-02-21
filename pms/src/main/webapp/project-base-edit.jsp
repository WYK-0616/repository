<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java"  pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title>编辑项目信息</title>
<link rel="stylesheet" type="text/css" href="skin/css/base.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.4.1.js"></script>
<script type="text/javascript">
	$(function () {
		$.ajax({
			url:"${pageContext.request.contextPath}/cust/getCuscompanyList",
			type:"get",
			success:function (msg) {
				var comname = "${project.customer.comname}";
				$(msg).each(function (index,item) {
					if (comname == item.comname){
						$("#cuscompany").append("<option value='"+item.id+"' selected='selected'>"+item.comname+"</option>");
					} else {
						$("#cuscompany").append("<option value='"+item.id+"'>"+item.comname+"</option>");
					}
				})
			}
		})
		$.ajax({
			url:"${pageContext.request.contextPath}/emp/getManagerList",
			type:"get",
			success:function (msg) {
				var ename = "${project.employee.ename}";
				$(msg).each(function (index,item) {
					if (ename == item.ename){
						$("#manager").append("<option value='"+item.eid+"' selected='selected'>"+item.ename+"</option>");
					} else {
						$("#manager").append("<option value='"+item.eid+"'>"+item.ename+"</option>");
					}
				})
			}
		})
	})
	function changePerson() {
		var id = $("#cuscompany").val();
		$.ajax({
			url: "${pageContext.request.contextPath}/cust/getCompanyperson/"+id,
			type: "get",
			success:function (msg) {
				$("#principal").val(msg.companyperson);
			}
		})
	}
	function commit() {
		$("#form2").submit();
	}
</script>
</head>
<body leftmargin="8" topmargin="8" background='skin/images/allbg.gif'>

<!--  快速转换位置按钮  -->
<table width="98%" border="0" cellpadding="0" cellspacing="1" bgcolor="#D1DDAA" align="center">
<tr>
 <td height="26" background="${pageContext.request.contextPath}/skin/images/newlinebg3.gif">
  <table width="58%" border="0" cellspacing="0" cellpadding="0">
  <tr>
  <td >
    当前位置:项目管理>>编辑项目基本信息
 </td>
 </tr>
</table>
</td>
</tr>
</table>

<form id="form2" name="form2" action="${pageContext.request.contextPath}/pro/updateProject" method="post">
<input type="hidden" name="pid" value="${project.pid}">
<input type="hidden" name="_method" value="put">
<table width="98%" border="0" cellpadding="2" cellspacing="1" bgcolor="#D1DDAA" align="center" style="margin-top:8px">
<tr bgcolor="#E7E7E7">
	<td height="24" colspan="12" background="skin/images/tbg.gif">&nbsp;编辑项目信息&nbsp;</td>
</tr>
<tr >
	<td align="right" bgcolor="#FAFAF1" height="22">项目名称：</td>
	<td align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
		<input id="pname" name="pname" value="${project.pname}"/>
	</td>
	<td align="right" bgcolor="#FAFAF1" height="22">客户公司名称：</td>
	<td align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
		<select name="comname" id="cuscompany" onchange="changePerson()">
			<option>===请选择===</option>
		</select>
	</td>
</tr>
<tr >
	<td align="right" bgcolor="#FAFAF1" height="22">客户方负责人：</td>
	<td align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
		<input id="principal" name="comper" value="${project.customer.companyperson}"/>
	</td>
	<td align="right" bgcolor="#FAFAF1" height="22">项目经理：</td>
	<td align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
		<select id="manager" name="empFk">
			<option>===请选择===</option>
		</select>
	</td>
</tr>
<tr >
	<td align="right" bgcolor="#FAFAF1" height="22">开发人数：</td>
	<td align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
		<input id="countp" name="empcount" value="${project.empcount}">
	</td>
	<td align="right" bgcolor="#FAFAF1" height="22">开始时间：</td>
	<td align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
		<input value="<fmt:formatDate value='${project.starttime}'></fmt:formatDate>" id="sdate" name="starttime"/>
		<img onclick="WdatePicker({el:'sdate',minDate:'%y-%M-%d'})" src="${pageContext.request.contextPath}/js/My97DatePicker/skin/datePicker.gif" width="16" height="22" align="absmiddle">
	</td>
</tr>
<tr >
	<td align="right" bgcolor="#FAFAF1" height="22">立项时间：</td>
	<td align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
		<input value="<fmt:formatDate value='${project.buildtime}'></fmt:formatDate>" id="cdate" name="buildtime"/>
		<img onclick="WdatePicker({el:'cdate',minDate:'%y-%M-%d'})" src="${pageContext.request.contextPath}/js/My97DatePicker/skin/datePicker.gif" width="16" height="22" align="absmiddle">
	</td>
	<td align="right" bgcolor="#FAFAF1" height="22">预估成本：</td>
	<td align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
		<input value="${project.cost}" id="cost" name="cost"/>万
	</td>
</tr>
<tr >
	<td align="right" bgcolor="#FAFAF1" height="22">级别：</td>
	<td align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
		<select id="rank" name="level">
			<c:if test="${project.level == '紧急'}">
				<option value=紧急 selected="selected">紧急</option>
				<option value=一般>一般</option>
				<option value=暂缓>暂缓</option>
			</c:if>
			<c:if test="${project.level == '一般'}">
				<option value=紧急>紧急</option>
				<option value=一般 selected="selected">一般</option>
				<option value=暂缓>暂缓</option>
			</c:if>
			<c:if test="${project.level == '暂缓'}">
				<option value=紧急>紧急</option>
				<option value=一般>一般</option>
				<option value=暂缓 selected="selected">暂缓</option>
			</c:if>
		</select>
	</td>
	<td align="right" bgcolor="#FAFAF1" height="22">计划完成时间：</td>
	<td align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
		<input value="<fmt:formatDate value='${project.endtime}'></fmt:formatDate>" id="predate" name="endtime"/>
		<img onclick="WdatePicker({el:'predate',minDate:'%y-%M-%d'})" src="${pageContext.request.contextPath}/js/My97DatePicker/skin/datePicker.gif" width="16" height="22" align="absmiddle">
	</td>
</tr>

<tr >
	<td align="right" bgcolor="#FAFAF1" >备注：</td>
	<td colspan=3 align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" >
		<textarea rows=15 cols=130 name="remark" id="priremark">${project.remark}</textarea>
	</td>
</tr>


<tr bgcolor="#FAFAF1">
<td height="28" colspan=4 align=center>
	&nbsp;
	<a href="javascript:commit()" class="coolbg">保存</a>
	<a href="${pageContext.request.contextPath}/pro/projetList" class="coolbg">返回</a>
</td>
</tr>
</table>

</form>
  

</body>
</html>