<%@ page language="java"  pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title>编辑附件</title>
<link rel="stylesheet" type="text/css" href="skin/css/base.css">
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.4.1.js"></script>
	<script type="text/javascript">
		var id = window.location.search.substring(1);
		$(function () {
			$.ajax({
				url:"${pageContext.request.contextPath}/attachment/getAttachmentById/"+id,
				type:"get",
				success:function (msg) {
					$("#id").val(msg.id);
					$("#proFk").val(msg.proFk);
					$("#proname").text(msg.project.pname);
					$("#attname").val(msg.attname);
					$("#attdis").val(msg.attdis);
					$("#remark").val(msg.remark);
					var path = msg.path;
					var filename = path.split("_")[1];
					$("#filename").text(filename);
				}
			})
		})
		function save() {
			var formData = new FormData();
			var file = $("#file")[0].files[0];
			formData.append("file",file);
			formData.append("id",$("#id").val());
			formData.append("proFk",$("#proFk").val());
			formData.append("attname",$("#attname").val());
			formData.append("attdis",$("#attdis").val());
			formData.append("remark",$("#remark").val());
			formData.append("_method","put");
			$.ajax({
				url:"${pageContext.request.contextPath}/attachment/updateAttachment",
				type:"post",
				data:formData,
				processData:false,
				contentType:false,
				success:function (msg) {
					if (msg.statusCode == 200){
						window.location.href="${pageContext.request.contextPath}/project-file.jsp";
					} else {
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
    当前位置:项目管理>>编辑附件
 </td>
 </tr>
</table>
</td>
</tr>
</table>

<form name="form2" action="javascript:void(0)">
<input id="id" name="id" type="hidden">
<input id="proFk" name="proFk" type="hidden">
<table width="98%" border="0" cellpadding="2" cellspacing="1" bgcolor="#D1DDAA" align="center" style="margin-top:8px">
<tr bgcolor="#E7E7E7">
	<td height="24" colspan="2" background="skin/images/tbg.gif">&nbsp;编辑附件&nbsp;</td>
</tr>
<tr >
	<td align="right" bgcolor="#FAFAF1" height="22">所属项目：</td>
	<td id="proname" name="proname" align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">农业银行自助管理系统</td>
</tr>
<tr >
	<td align="right" bgcolor="#FAFAF1" height="22">附件名称：</td>
	<td  align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
		<input name="attname" id="attname"/>
	</td>
</tr>
<tr >
	<td align="right" bgcolor="#FAFAF1" height="22">附件信息描述：</td>
	<td  align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
		<input name="attdis" id="attdis"/>
	</td>
</tr>


<tr >
	<td align="right" bgcolor="#FAFAF1" height="22">附件1：</td>
	<td  align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
		<span id="filename" name="filename"></span>
		<input id="file" name="file" type="file"/>
</tr >
	
<tr >
	<td align="right" bgcolor="#FAFAF1" >备注：</td>
	<td colspan=3 align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" >
		<textarea id="remark" name="remark" rows=10 cols=130>暂无</textarea>
	</td>
</tr>


<tr bgcolor="#FAFAF1">
<td height="28" colspan=4 align=center>
	&nbsp;
	<a href="javascript:save()" class="coolbg">保存</a>
	<a href="project-file.jsp" class="coolbg">返回</a>
</td>
</tr>
</table>

</form>
  

</body>
</html>