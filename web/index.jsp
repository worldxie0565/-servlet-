<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>Title</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap-theme.css">
        <script src="${pageContext.request.contextPath}/js/jquery-2.1.0.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.js"></script>

    </head>
    <body>
        <div class="container" style="height: 500px; width: 300px;margin-top:100px">
            <form action="/loginServlet" method="post">
                <div class="form-group">
                    <label for="exampleInputUsername1">用户名</label>
                    <input type="text" name="username" class="form-control" id="exampleInputUsername1" placeholder="请输入用户名">
                </div>
                <div class="form-group">
                    <label for="exampleInputPassword1">密码</label>
                    <input type="password" name="password" class="form-control" id="exampleInputPassword1" placeholder="请输入密码">
                </div>
                <div class="form-inline">
                    <label for="exampleCheckCode1">验证码</label>
                    <input type="text" name="vcode" class="form-control" id="exampleCheckCode1" placeholder="请输入验证码" style="width: 120px">
                    <a href="javascript:updateVcode();">
                        <img id="vcode" src="/validateCodeServlet" alt="看不清,刷新试试">
                    </a>
                </div>
                <br>
                <button type="submit" class="btn btn-default">登录</button>
            </form>
            <br>
            <div id="alert_div" class="alert alert-warning alert-dismissible" role="alert"
            <c:if test="${empty local_msg}">style="display: none" </c:if>
                <button type="button" class="close" data-dismiss="alert" aria-label="Close"></button>
                ${local_msg}
            </div>
        </div>

        <script>

            function updateVcode() {
                var vcode = document.getElementById("vcode");
                vcode.src = "${pageContext.request.contextPath}/validateCodeServlet?" + new Date().getTime();
            }

            $(function () {
                $("input").focus(function () {
                    $("#alert_div").css("display", "none");
                })
            })

        </script>

    </body>
</html>