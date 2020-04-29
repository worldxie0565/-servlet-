<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>新增用户</title>
    </head>
    <body>
        <center><h3>新增用户</h3></center>
        <hr>
        <form action="${pageContext.request.contextPath}/addUserServlet" method="post">
            用户名<input type="text" name="name" placeholder="请输入用户名"><br>
            昵称<input type="text" name="username" placeholder="请输入昵称"><br>
            年龄<input type="text" name="age" placeholder="请输入年龄"><br>
            性别<select name="gender">
                    <option>请选择</option>
                    <option>男</option>
                    <option>女</option>
                </select><br>
            地址<select name="address">
            <option>请选择</option>
            <option>上海</option>
            <option>北京</option>
            <option>广州</option>
        </select>
            QQ<input type="text" name="qq" placeholder="请输入QQ"><br>
            email<input type="text" name="email" placeholder="请输入邮箱"><br>
            <button class="btn btn-default" type="submit">新增</button>
        </form>
    </body>
</html>
