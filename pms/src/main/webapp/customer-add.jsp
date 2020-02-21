<%@ page language="java"  pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title>添加客户信息</title>
<link rel="stylesheet" type="text/css" href="skin/css/base.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.4.1.js"></script>
</head>
<body leftmargin="8" topmargin="8" background='skin/images/allbg.gif'>

<!--  快速转换位置按钮  -->
<table width="98%" border="0" cellpadding="0" cellspacing="1" bgcolor="#D1DDAA" align="center">
<tr>
 <td height="26" background="skin/images/newlinebg3.gif">
  <table width="58%" border="0" cellspacing="0" cellpadding="0">
  <tr>
  <td >
    当前位置:客户信息管理>>添加客户信息
 </td>
 </tr>
</table>
</td>
</tr>
</table>

<form name="form2" id="form2" action="${pageContext.request.contextPath}/cust/addcustomer" method="post">

<table width="98%" border="0" cellpadding="2" cellspacing="1" bgcolor="#D1DDAA" align="center" style="margin-top:8px">
<tr bgcolor="#E7E7E7">
	<td height="24" colspan="2" background="skin/images/tbg.gif">&nbsp;添加客户&nbsp;</td>
</tr>
<tr >
	<td align="right" bgcolor="#FAFAF1" height="22">公司名称：</td>
	<td  align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
		<input name="comname" id="comname"/>
		<span id="comnamemsg"></span>
	</td>
</tr>
<tr >
	<td align="right" bgcolor="#FAFAF1" height="22">公司联系人：</td>
	<td  align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
		<input name="companyperson" id="companyperson"/>
		<span id="companypersonmsg"></span>
	</td>
</tr>
<tr >
	<td align="right" bgcolor="#FAFAF1" height="22">公司地址：</td>
	<td  align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
		<input size="60" name="comaddress" id="comaddress"/>
		<span id="comaddressmsg"></span>
	</td>
</tr>
<tr >
	<td align="right" bgcolor="#FAFAF1" height="22">联系电话：</td>
	<td  align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
		<input name="comphone" id="comphone"/>
		<span id="comphonemsg"></span>
	</td>
</tr>
<tr >
	<td align="right" bgcolor="#FAFAF1" height="22">座机：</td>
	<td  align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
		<input name="camera" id="camera"/>
		<span id="cameramsg"></span>
	</td>
</tr>
<tr >
	<td align="right" bgcolor="#FAFAF1" height="22">公司简介：</td>
	<td align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
		<textarea rows=15 cols=130 name="present" id="present"></textarea>
		<span id="presentmsg"></span>
	</td>
</tr>

<tr >
	<td align="right" bgcolor="#FAFAF1" >备注：</td>
	<td colspan=3 align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" >
		<textarea rows=10 cols=130 name="remark" id="remark"></textarea>
		<span id="remarkmsg"></span>
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

<script type="text/javascript">
		<!-- 提交form表单 -->
		function save() {

			$("#form2").submit();
		}
		<!-- 返回上一页 -->
		function back() {
			window.location.href="${pageContext.request.contextPath}/cust/list";
		}
		<!-- 公司非空验证及唯一验证 -->
		$("#comname").blur(function () {
			var comname = $("#comname").val();
			if (comname == null || comname == ""){
				$("#comnamemsg").text("✘").css({"color":"red"});
			} else {
				$.ajax({
					url:"${pageContext.request.contextPath}/cust/validateComname",
					type:"get",
					data:{"comname":comname},
					success:function (msg) {
						if (msg.result == 200){
							$("#comnamemsg").text("✔").css({"color":"green"});
						} else {
							$("#comnamemsg").text("该公司已存在客户列表！").css({"color":"red"});
						}
					}
				})
			}
		})
		<!-- 公司联系人非空验证 -->
		$("#companyperson").blur(function () {
			var companyperson = $("#companyperson").val();
			if (companyperson == null || companyperson == ""){
				$("#companypersonmsg").text("✘").css({"color":"red"});
			} else {
				$("#companypersonmsg").text("✔").css({"color":"green"});
			}
		})
		<!-- 公司地址非空验证 -->
		$("#comaddress").blur(function () {
			var comaddress = $("#comaddress").val();
			if (comaddress == null || comaddress == ""){
				$("#comaddressmsg").text("✘").css({"color":"red"});
			} else {
				$("#comaddressmsg").text("✔").css({"color":"green"});
			}
		})
		<!-- 手机号非空验证及正则验证 -->
		$("#comphone").blur(function () {
			var comphone = $("#comphone").val();
			if (comphone == null || comphone == ""){
				$("#comphonemsg").text("✘").css({"color":"red"});
			} else {
				var myreg = /^[1][3,4,5,7,8,9][0-9]{9}$/;
				if (!myreg.test(comphone)) {
					$("#comphonemsg").text("手机号格式不正确").css({"color":"red"});
				} else {
					$("#comphonemsg").text("✔").css({"color":"green"});
				}
			}
		})
		<!-- 座机号非空验证及正则验证 -->
		$("#camera").blur(function () {
			var camera = $("#camera").val();
			if (camera == null || camera == ""){
				$("#cameramsg").text("✘").css({"color":"red"});
			} else {
				var myreg = /^(([0\+]\d{2,3}-)?(0\d{2,3})-)(\d{7,8})(-(\d{3,}))?$/;
				if (!myreg.test(camera)) {
					$("#cameramsg").text("电话号格式不正确").css({"color":"red"});
				} else {
					$("#cameramsg").text("✔").css({"color":"green"});
				}
			}
		})
		<!-- 公司简介非空验证 -->
		$("#present").blur(function () {
			var present = $("#present").val();
			if (present == null || present == ""){
				$("#presentmsg").text("✘").css({"color":"red"});
			} else {
				$("#presentmsg").text("✔").css({"color":"green"});
			}
		})
		<!-- 备注非空验证 -->
		$("#remark").blur(function () {
			var remark = $("#remark").val();
			if (remark == null || remark == ""){
				$("#remarkmsg").text("✘").css({"color":"red"});
			} else {
				$("#remarkmsg").text("✔").css({"color":"green"});
			}
		})
</script>
</body>
</html>