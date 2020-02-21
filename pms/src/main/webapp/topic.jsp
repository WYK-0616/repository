<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/2/11
  Time: 15:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en-US">
<head>
    <meta charset="UTF-8">
    <title>ueditor demo</title>
</head>
<body>
<!-- 加载编辑器的容器 -->
<form action="${pageContext.request.contextPath}/tz/save" method="post">
    标题:<input type="text" name="title">
    <script id="container" name="content" type="text/plain" style="width: 1024px;height: 500px"></script>
    <input type="submit" value="提交">
</form>
<!-- 配置文件 -->
<script type="text/javascript" src="js/ueditor/ueditor.config.js"></script>
<!-- 编辑器源码文件 -->
<script type="text/javascript" src="js/ueditor/ueditor.all.js"></script>
<!-- 实例化编辑器 -->
<script type="text/javascript">
    var ue = UE.getEditor('container');
</script>
</body>
</html>
