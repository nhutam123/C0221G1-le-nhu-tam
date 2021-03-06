<%--
  Created by IntelliJ IDEA.
  User: LE NHU TAN
  Date: 29/05/2021
  Time: 8:32 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
  <jsp:include page="commond/style.jsp"/>
</head>
<body>
<div class="header">
    <div>
        <h1>Customer Management</h1>
        <h2>
            <form method="get" action="/customer">
                <input class="btn btn-success" type="submit" name="action" value="create">
            </form>
        </h2>
    </div>
    <div>
        <form method="post" action="/customer">
            <input class="search" type="text" name="name" id="search">
            <input class="btn btn-success" type="submit" name="action" value="search">
        </form>
    </div>
</div>

<div align="center">
    <table border="1" cellpadding="5" id="table" class="table table-striped table-bordered table-dark">
        <caption><h2>List of Customers</h2></caption>
        <thead>
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Email</th>
            <th>phoneNumber</th>
            <th>address</th>
            <th>edit</th>
            <th>delete</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="customer" items="${listCustomers}">
            <tr>
                <td><c:out value="${customer.id}"/></td>
                <td><c:out value="${customer.name}"/></td>
                <td><c:out value="${customer.email}"/></td>
                <td><c:out value="${customer.phoneNumber}"/></td>
                <td><c:out value="${customer.address}"/></td>
                <td>
                    <a href="/customer?action=edit&id=${customer.id}">Edit</a>
                </td>
                <td>
                    <button type="button" class="btn btn-danger"
                            onclick="sendDataToModal('${customer.id}','${customer.name}')" data-toggle="modal"
                            data-target="#exampleModalLong">
                        Delete
                    </button>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <div class="modal fade" id="exampleModalLong" tabindex="-1" role="dialog" aria-labelledby="exampleModalLongTitle"
         aria-hidden="true">
        <form action="/customer" method="get">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLongTitle">Modal title</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <input hidden type="text" name="id" id="idStudent">
                        <p>Bạn có muốn xóa sình viên tên :<span id="nameStudent"></span></p>
                    </div>
                    <div class="modal-footer d-flex justify-content-around">
                        <button type="button" class="btn btn-success" data-dismiss="modal">Cancel</button>
                        <input type="hidden" name="action" value="delete">
                        <button type="submit" class="btn btn-danger ">Delete</button>
                    </div>
                </div>
            </div>
        </form>
    </div>

    <br>

</div>
<script src="https://code.jquery.com/jquery-3.1.1.slim.min.js"
        integrity="sha384-A7FZj7v+d/sdmMqp/nOQwliLvUsJfDHW+k9Omg/a/EheAdgtzNs3hpfag6Ed950n"
        crossorigin="anonymous"></script>
<script src="https://cdn.datatables.net/1.10.24/js/jquery.dataTables.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/tether/1.4.0/js/tether.min.js"
        integrity="sha384-DztdAPBWPRXSA/3eYEEUWrWCy7G5KFbe8fFjk5JAIxUYHKkDx6Qin1DkWx51bBrb"
        crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/js/bootstrap.min.js"
        integrity="sha384-vBWWzlZJ8ea9aCX4pEW3rVHjgjt7zpkNpZk+02D9phzyeVkE+jo0ieGizqPLForn"
        crossorigin="anonymous"></script>

<script>
    $(document).ready(function x() {
        $('#table').dataTable({
            "dom": 'lrtip',
            "lengthChange": false,
            "pageLength": 5
        })
    });
    function sendDataToModal(id, name) {
        document.getElementById("idStudent").value = id;
        document.getElementById("nameStudent").innerText = name;
    }
</script>

</body>
</html>
