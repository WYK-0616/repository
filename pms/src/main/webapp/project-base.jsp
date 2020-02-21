<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java"  pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title>项目信息管理</title>
<link rel="stylesheet" type="text/css" href="skin/css/base.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.4.1.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/moment.js"></script>
</head>
<body leftmargin="8" topmargin="8" background='skin/images/allbg.gif'>

<!--  快速转换位置按钮  -->
<table width="98%" border="0" cellpadding="0" cellspacing="1" bgcolor="#D1DDAA" align="center">
<tr>
 <td height="26" background="skin/images/newlinebg3.gif">
  <table width="58%" border="0" cellspacing="0" cellpadding="0">
  <tr>
  <td >
    当前位置:项目管理>>基本信息管理
 </td>
  <td>
    <input type='button' class="coolbg np" onClick="location='${pageContext.request.contextPath}/project-base-add.jsp';" value='添加新项目' />
 </td>
 </tr>
</table>
</td>
</tr>
</table>

<!--  搜索表单  -->
<form id="form3" name='form3' action='javascript:void(0)' method='get'>
<table width='98%'  border='0' cellpadding='1' cellspacing='1' bgcolor='#CBD8AC' align="center" style="margin-top:8px">
  <tr bgcolor='#EEF4EA'>
    <td background='${pageContext.request.contextPath}/skin/images/wbg.gif' align='center'>
      <table border='0' cellpadding='0' cellspacing='0'>
        <tr>
          <td width='90' align='center'>搜索条件：</td>
          <td width='160'>
          <select id="cid" name='cid' style='width:150px'>
          <option value='0'>选择类型...</option>
          	<option value='1'>项目名称</option>
          	<option value='2'>项目经理</option>
          </select>
        </td>
        <td width='70'>
          关键字：
        </td>
        <td width='160'>
          	<input type='text' id="keyword" name='keyword' style='width:120px' />
        </td>
        <td width='110'>
    		<select id="orderby" name='orderby' style='width:120px'>
            <option value='0'>排序...</option>
            <option value='1'>立项时间</option>
            <option value='2'>计划完成时间</option>
      	</select>
        </td>
        <td>
          &nbsp;&nbsp;&nbsp;<input name="imageField" onclick="submitForm3()" type="image" src="${pageContext.request.contextPath}/skin/images/frame/search.gif" width="45" height="20" border="0" class="np" />
        </td>
       </tr>
      </table>
    </td>
  </tr>
</table>
</form>
<!--  内容列表   -->
<form name="form2">

<table width="98%" border="0" cellpadding="2" cellspacing="1" bgcolor="#D1DDAA" align="center" style="margin-top:8px">
<tr bgcolor="#E7E7E7">
	<td height="24" colspan="12" background="skin/images/tbg.gif">&nbsp;项目信息列表&nbsp;</td>
</tr>
<tr id="title" align="center" bgcolor="#FAFAF1" height="22">
	<td width="4%">选择</td>
	<td width="6%">序号</td>
	<td width="10%">项目名称</td>
	<td width="10%">客户公司名称</td>
	<td width="10%">客户方负责人</td>
	<td width="5%">项目经理</td>
	<td width="8%">开发人员数</td>
	<td width="6%">立项时间</td>
	<td width="8%">最近更新时间</td>
	<td width="8%">计划完成时间</td>
	<td width="8%">状态</td>
	<td width="10%">操作</td>
</tr>

<tr id="endtr" bgcolor="#FAFAF1">
<td height="28" colspan="12">
	<a href="javascript:checkAll()" class="coolbg">全选</a>
	<a href="javascript:cancelAll()" class="coolbg">反选</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	<a href="javascript:delchecked()" class="coolbg">&nbsp;删除&nbsp;</a>
	<a href="" class="coolbg">&nbsp;导出Excel&nbsp;</a>
</td>
</tr>
<tr align="right" bgcolor="#EEF4EA">
	<td height="36" colspan="12" align="center"><!--翻页代码 --></td>
</tr>
</table>

</form>

<script type="text/javascript">
    function submitForm3() {
        var cid = $("select[id=cid]").val();
        var keyword = $("#keyword").val();
        var orderby = $("select[id=orderby]").val();
        $.ajax({
            url:"${pageContext.request.contextPath}/pro/search",
            type:"get",
            data:{"cid":cid,"keyword":keyword,"orderby":orderby},
            success:function (msg) {
                $("tr[name=trs]").remove();
                $(msg).each(function (index,item) {
                    $("#endtr").before('<tr name="trs" align=\'center\' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor=\'#FCFDEE\';" onMouseOut="javascript:this.bgColor=\'#FFFFFF\';" height="22" >\n' +
                        '\t<td><input name="id" type="checkbox" value="'+item.pid+'" class="np" ></td>\n' +
                        '\t<td>'+(index+1)+'</td>\n' +
                        '\t<td align="left"><a href=\'\'><u>'+item.pname+'</u></a></td>\n' +
                        '\t<td>'+item.customer.comname+'</td>\n' +
                        '\t<td>'+item.comper+'</td>\n' +
                        '\t<td>'+item.employee.ename+'</td>\n' +
                        '\t<td>'+item.empcount+'</td>\n' +
                        '\t<td>'+moment(item.starttime).format('YYYY-MM-DD')+'</td>\n' +
                        '\t<td>'+moment(item.buildtime).format('YYYY-MM-DD')+'</td>\n' +
                        '\t<td>'+moment(item.endtime).format('YYYY-MM-DD')+'</td>\n' +
                        '\t<td>'+item.level+'</td>\n' +
                        '\t<td><a href="${pageContext.request.contextPath}/pro/getProjectById/'+item.pid+'">编辑</a> | <a href="${pageContext.request.contextPath}/pro/getProjectById2/'+item.pid+'">查看详情</a></td>\n' +
                        '</tr>')
                })
            }
        })
    }
    function checkAll() {
        $("[name='checks']").prop("checked",true);
    }
    function cancelAll() {
        $("[name='checks']").each(function (index,item) {
            var result = $(this).prop("checked");
            $(this).prop("checked",!result);
        })
    }
    function delchecked(){
        var ids = [];
        $.each($('input:checkbox'),function(){
            if(this.checked){
                ids.push($(this).val());
            }
        });
        if (ids.length == 0){
            alert("请选择要删除的客户");
        } else {
            if (confirm("确认要删除吗?")){
                $.ajax({
                    url:"${pageContext.request.contextPath}/pro/deleteProject",
                    type:"post",
                    data:{"_method":"delete","ids":ids},
                    success:function (msg) {
                        if (msg.statusCode == 200){
                            $("input[type='checkbox']").each(function () {
                                if ($(this).prop("checked")){
                                    $(this).parent().parent().remove();
                                }
                            })
                        } else {
                            alert("删除失败！");
                        }
                    }
                })
            } else {
                return;
            }
        }
    }
    $(function () {
        $.ajax({
            type:"GET",
            url:"${pageContext.request.contextPath}/pro/projetList",
            success:function (msg) {
                $(msg).each(function (index,item) {
                    $("#endtr").before('<tr name="trs" align=\'center\' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor=\'#FCFDEE\';" onMouseOut="javascript:this.bgColor=\'#FFFFFF\';" height="22" >\n' +
                        '\t<td><input name="id" type="checkbox" value="'+item.pid+'" class="np" ></td>\n' +
                        '\t<td>'+(index+1)+'</td>\n' +
                        '\t<td align="left"><a href=\'\'><u>'+item.pname+'</u></a></td>\n' +
                        '\t<td>'+item.customer.comname+'</td>\n' +
                        '\t<td>'+item.comper+'</td>\n' +
                        '\t<td>'+item.employee.ename+'</td>\n' +
                        '\t<td>'+item.empcount+'</td>\n' +
                        '\t<td>'+moment(item.starttime).format('YYYY-MM-DD')+'</td>\n' +
                        '\t<td>'+moment(item.buildtime).format('YYYY-MM-DD')+'</td>\n' +
                        '\t<td>'+moment(item.endtime).format('YYYY-MM-DD')+'</td>\n' +
                        '\t<td>'+item.level+'</td>\n' +
                        '\t<td><a href="${pageContext.request.contextPath}/pro/getProjectById/'+item.pid+'">编辑</a> | <a href="${pageContext.request.contextPath}/pro/getProjectById2/'+item.pid+'">查看详情</a></td>\n' +
                        '</tr>')
                })
            }
        })
    })
</script>
</body>
</html>