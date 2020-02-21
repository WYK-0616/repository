<%@ page language="java"  pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title>需求分析管理</title>
<link rel="stylesheet" type="text/css" href="skin/css/base.css">
<script type="text/javascript" src="js/moment.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.4.1.js"></script>
<script type="text/javascript">
    $(function () {
        $.ajax({
            url:"${pageContext.request.contextPath}/analysis/list",
            type:"get",
            success:function (msg) {
                $(msg).each(function (index,item) {
                    var str = "<tr name='trs' align='center' bgcolor='#FFFFFF' onMouseMove="+"javascript:this.bgColor='#FCFDEE';"+"onMouseOut=javascript:this.bgColor='#FFFFFF';"+" height="+22+" >" +
                        "\t<td><input name='id' type='checkbox' id='id' value='"+item.id+"' class='np'></td>" +
                        "<td>"+(index+1)+"</td>" +
                        "<td>"+item.title+"</td>" +
                        "<td align='center'><a href=''><u>"+item.proname+"</u></a></td>" +
                        "<td>"+moment(item.addtime).format('YYYY-MM-DD')+"</td>" +
                        "<td>"+moment(item.updatetime).format('YYYY-MM-DD')+"</td>" +
                        "<td><a href='project-need-edit.jsp?"+item.id+"'>编辑</a> | <a href='project-need-look.jsp?"+item.id+"'>查看详情</a></td>" +
                        "</tr>";
                    $("#endstr").before(str);
                })
            }
        })
    })
    function checkAll() {
        $("input[name=id]").prop("checked",true);
    }
    function cancelAll() {
        $("input[name=id]").each(function () {
            if ($(this).prop("checked")){
                $(this).prop("checked",false);
            } else {
                $(this).prop("checked",true);
            }
        })
    }
    function removechecked() {
        var ids = [];
        $("input[name=id]").each(function () {
            if ($(this).prop("checked")){
                ids.push($(this).val());
            }
        })
        if (confirm("确定要删除吗？")){
            $.ajax({
                url:"${pageContext.request.contextPath}/analysis/deleteByIds",
                type:"post",
                data:{"ids":ids,"_method":"delete"},
                success:function (msg) {
                    if (msg.statusCode == 200){
                        $("input[name=id]").each(function () {
                            if ($(this).prop("checked")){
                                $(this).parent().parent().remove();
                            }
                        })
                    } else {
                        alert("删除失败");
                    }
                }
            })
        }
    }
    function search() {
        var cid = $("#cid").val();
        var keyword = $("#keyword").val();
        var orderby = $("#orderby").val();
        $.ajax({
            url:"${pageContext.request.contextPath}/analysis/search",
            type:"get",
            data:{"cid":cid,"keyword":keyword,"orderby":orderby},
            success:function (msg) {
                $("tr[name=trs]").remove();
                $(msg).each(function (index,item) {
                    var str = "<tr name='trs' align='center' bgcolor='#FFFFFF' onMouseMove="+"javascript:this.bgColor='#FCFDEE';"+"onMouseOut=javascript:this.bgColor='#FFFFFF';"+" height="+22+" >" +
                        "<td><input name='id' type='checkbox' id='id' value='"+item.id+"' class='np'></td>" +
                        "<td>"+(index+1)+"</td>" +
                        "<td>"+item.title+"</td>" +
                        "<td align='center'><a href=''><u>"+item.proname+"</u></a></td>" +
                        "<td>"+moment(item.addtime).format('YYYY-MM-DD')+"</td>" +
                        "<td>"+moment(item.updatetime).format('YYYY-MM-DD')+"</td>" +
                        "<td><a href='project-need-edit.jsp?"+item.id+"'>编辑</a> | <a href='project-need-look.jsp?"+item.id+"'>查看详情</a></td>" +
                        "</tr>";
                    $("#endstr").before(str);
                })
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
    当前位置:项目管理>>需求分析管理
 </td>
  <td>
    <input type='button' class="coolbg np" onClick="location='${pageContext.request.contextPath}/project-need-add.jsp';" value='添加新项目需求' />
 </td>
 </tr>
</table>
</td>
</tr>
</table>

<!--  搜索表单  -->
<form name='form3' action='javascript:void(0)' method='get'>
<input type='hidden' name='dopost' value='' />
<table width='98%'  border='0' cellpadding='1' cellspacing='1' bgcolor='#CBD8AC' align="center" style="margin-top:8px">
  <tr bgcolor='#EEF4EA'>
    <td background='skin/images/wbg.gif' align='center'>
      <table border='0' cellpadding='0' cellspacing='0'>
        <tr>
          <td width='90' align='center'>搜索条件：</td>
          <td width='160'>
          <select id="cid" name='cid' style='width:150px'>
          <option value='0'>选择类型...</option>
          	<option value='1'>项目名称</option>
          	<option value='2'>标题</option>
          </select>
        </td>
        <td width='70'>
          关键字：
        </td>
        <td width='160'>
          	<input type='text' id="keyword" name='keyword' value='' style='width:120px' />
        </td>
        <td width='110'>
    		<select id="orderby" name='orderby' style='width:120px'>
            <option value='0'>排序...</option>
            <option value='1'>添加时间</option>
            <option value='2'>修改时间</option>
      	</select>
        </td>
        <td>
          &nbsp;&nbsp;&nbsp;<input name="imageField" type="image" onclick="search()" src="skin/images/frame/search.gif" width="45" height="20" border="0" class="np" />
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
	<td height="24" colspan="12" background="skin/images/tbg.gif">&nbsp;需求列表&nbsp;</td>
</tr>
<tr align="center" bgcolor="#FAFAF1" height="22">
	<td width="4%">选择</td>
	<td width="6%">序号</td>
	<td width="10%">标题</td>
	<td width="10%">项目名称</td>
	<td width="8%">添加时间</td>
	<td width="8%">修改时间</td>
	<td width="10%">操作</td>
</tr>


<tr id="endstr" bgcolor="#FAFAF1">
<td height="28" colspan="12">
	&nbsp;
	<a href="javascript:checkAll()" class="coolbg">全选</a>
	<a href="javascript:cancelAll()" class="coolbg">反选</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	<a href="javascript:removechecked()" class="coolbg">&nbsp;删除&nbsp;</a>
	<a href="" class="coolbg">&nbsp;导出Excel&nbsp;</a>
</td>
</tr>
<tr align="right" bgcolor="#EEF4EA">
	<td height="36" colspan="12" align="center"><!--翻页代码 --></td>
</tr>
</table>

</form>
  

</body>
</html>