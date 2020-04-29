<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <title>所有用户信息</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap-theme.css">
        <script src="${pageContext.request.contextPath}/js/jquery-2.1.0.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.js"></script>
    </head>
    <body>
        欢迎 <strong>${sessionScope.username}</strong

        <center><h3>查询所有用户信息</h3></center>
        <hr>
        <form class="form-inline" action="/findByConditionsServlet" method="post">
            用户名<input type="text" name="name" value="${pb.conditions['name'][0]}">
            年龄<input type="text" name="age" value="${pb.conditions['age'][0]}">
            性别<select name="gender">
            <option disabled selected>请选择</option>
            <option ${pb.conditions['gender'][0]=='男'?'selected':''} >男</option>
            <option ${pb.conditions['gender'][0]=='女'?'selected':''}}>女</option>
        </select>
            地区<select name="address">
            <option disabled selected>请选择</option>
            <option ${pb.conditions['address'][0]=='北京'?'selected':''} >北京</option>
            <option ${pb.conditions['address'][0]=='上海'?'selected':''} >上海</option>
            <option ${pb.conditions['address'][0]=='广州'?'selected':''} >广州</option>
        </select>
            <button type="submit" class="button btn-default btn-primary" value="">搜索</button>

            <a id="delSelected" href="javascript:void(0);" class="btn btn-default"
               style="float:right; margin-right: 5px">删除选中用户</a>
            <a id="add" href="${pageContext.request.contextPath}/pages/add.jsp" class="btn btn-default"
               style="float:right; margin-right: 5px">新增用户</a>
        </form>

        <form id="form" action="${pageContext.request.contextPath}/delSelectedServlet">
            <table class="table table-hover">
                <tr>
                    <th><input class="checkbox" type="checkbox" id="checkLeader"></th>
                    <th>编号</th>
                    <th>姓名</th>
                    <th>昵称</th>
                    <th>性别</th>
                    <th>年龄</th>
                    <th>地址</th>
                    <th>qq号码</th>
                    <th>邮箱</th>
                    <th colspan="2">操作</th>
                </tr>
                <c:forEach items="${pb.users}" var="u" varStatus="s">
                    <tr>
                        <td><input class="checkbox" type="checkbox" name="uid" value="${u.id}"></td>
                        <td>${s.count + (pb.pageCnt -1) * pb.row }</td>
                        <td>${u.name}</td>
                        <td>${u.username}</td>
                        <td>${u.gender}</td>
                        <td>${u.age}</td>
                        <td>${u.address}</td>
                        <td>${u.qq}</td>
                        <td>${u.email}</td>
                        <td>
                            <a class="btn btn-default"
                               href="${pageContext.request.contextPath}/findUserByIdServlet?id=${u.id}">修改</a>&nbsp;
                            <a class="btn btn-default" href="javascript:deleteUser(${u.id});">删除</a>
                        </td>
                    </tr>
                </c:forEach>
                <tr></tr>
            </table>
        </form>


        <nav aria-label="Page navigation">
            <ul class="pagination">
                <li class="${pb.pageCnt <= 1 || pb.pageCnt == null ?'disabled':''}">
                    <a class="btn ${pb.pageCnt <= 1 ||  pb.pageCnt == null ?'disabled':''}" href="${pageContext.request.contextPath}/findByConditionsServlet?curPage=${pb.pageCnt - 1}" aria-label="Previous">
                        <span aria-hidden="true">&laquo;</span>
                    </a>
                </li>
                <c:forEach begin="1" end="${pb.totalPage}" var="i">
                    <li class="${pb.pageCnt==i?'active':''}"
                    ><a href="${pageContext.request.contextPath}/findByConditionsServlet?curPage=${i}">${i}</a></li>
                </c:forEach>

                <li class="${pb.pageCnt >= pb.totalPage || pb.pageCnt == null?'disabled':''}">
                    <a  class="btn ${pb.pageCnt >= pb.totalPage || pb.pageCnt == null ?'disabled':''}" href="${pageContext.request.contextPath}/findByConditionsServlet?curPage=${pb.pageCnt + 1}" aria-label="Next">
                        <span aria-hidden="true">&raquo;</span>
                    </a>
                </li>
            </ul>

        </nav>
        共<${pb.totalPage == null?0:pb.totalPage}>页，每页<${pb.row == null? 0:pb.row}>条目,共${pb.totalCnt == null ? 0:pb.totalCnt}条目
    </body>

    <script>

        document.getElementById("delSelected").onclick = function () {
            if (confirm("您确认要删除选中用户?")) {
                var flg = false;
                var chks = document.getElementsByName("uid");
                for (var i = 0; i < chks.length; i++) {
                    if(chks[i].checked){
                        flg = true;
                        break;
                    }
                }
                if(flg){
                    document.getElementById("form").submit();
                }
            }
        }

        document.getElementById("checkLeader").onclick = function () {
            var chks = document.getElementsByName("uid");
            for (var i = 0; i < chks.length; i++) {
                chks[i].checked = this.checked;
            }
        }


        function deleteUser(id) {
            if (confirm("您确认要删除?")) {
                location.href = "${pageContext.request.contextPath}/deleteUserServlet?id=" + id;
            }
        }


    </script>
</html>
