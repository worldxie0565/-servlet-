<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>编辑用户</title>
    </head>
    <body>
        <center><h3>编辑用户</h3></center>
        <form class="container" action="${pageContext.request.contextPath}/editUserServlet" method="post">
            <div class="form-group">
            <label for="exampleInputID1">用户ID</label>
            <input readonly type="text" name="id" value="${user.id}" class="form-control" id="exampleInputID1">
        </div>
            <br>
            <div class="form-group">
                <label for="exampleInputName1">用户名</label>
                <input type="text" name="name" value="${user.name}" class="form-control" id="exampleInputName1">
            </div>
            <br>
            <div class="form-group">
                <label for="exampleInputName1">地址</label>
                <select name="address">
                    <option disabled selected>请选择</option>
                    <option ${user.address == '北京'?'selected':''}>北京</option>
                    <option ${user.address == '上海'?'selected':''}>上海</option>
                    <option ${user.address == '广州'?'selected':''}>广州</option>
                </select>
            </div>
            <button type="submit" class="btn btn-default">确认修改</button>
        </form>
    </body>
</html>
