<%-- 
    Document   : index
    Created on : 10.12.2019, 12:33:53
    Author     : Hp
--%>

<%@page import="studentmanagementappdb.*"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
    <head>
        <title>Main Page JSP| Student Management App</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="studentcore.css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
        <script>
            function setIdUpdate(id) {
                document.getElementById("studentIdForUpdate").value = id;
            }
            function setIdDelete(id) {
                document.getElementById("studentIdForDelete").value = id;
            }
        </script>
    </head>
    <body>
        <%
            //parametrlerin goturulmesi
            String action = request.getParameter("action");

            String name = request.getParameter("name");
            name = name == null ? "" : name;
            String surname = request.getParameter("surname");
            surname = surname == null ? "" : surname;
            String ageStr = request.getParameter("age");
            ageStr = ageStr == null ? "" : ageStr;
            Integer age = null;
            if (ageStr != null && !ageStr.isEmpty()) {
                age = Integer.parseInt(ageStr);
            }
            String studentIdStr = request.getParameter("studentId");
                Integer studentId = null;
                if (studentIdStr != null && !studentIdStr.isEmpty()) {
                    studentId = Integer.parseInt(studentIdStr);
                }
                
            // actionun emal edilmesi
            if (action != null && !action.isEmpty()) {
               if (action.equalsIgnoreCase("delete")) {
                    StudentDatabase.delete(studentId);
                } else if (action.equalsIgnoreCase("update")) {
                    StudentDatabase.update(new Student(studentId, name, surname, age));
                } else if (action.equalsIgnoreCase("add")) {
                    StudentDatabase.add(new Student(null, name, surname, age));
                }

                if (!action.equalsIgnoreCase("search")) {
                    name = "";
                    surname = "";
                    ageStr = "";
                    response.sendRedirect("index.jsp"); //GET sorgusu ile qaytarir

                }
            }

            List<Student> students = StudentDatabase.getAll(name, surname, age);
        %>
        <div class="container">
            <div class="modal" id="deleteModal">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <form action="index.jsp">
                            <div class="modal-header">
                                <h4 class="modal-title">Delete Student</h4>
                                <button type="button" class="close" data-dism 
                                        iss="modal">&times;</button>
                            </div>
                            <div class="modal-body">
                                Are you sure?
                            </div>
                            <div class="modal-footer">
                                <input type="hidden" name="studentId" id="studentIdForDelete"/>
                                <input type="hidden" name="action" value="delete"/>
                                <input type="submit" class="btn btn-deafult" value="Yes"/>
                                <input type="submit" class="btn btn-danger" data-dismiss="modal" value="No"/>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>

        <div class="container">
            <div class="modal" id="updateModal">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <form action="index.jsp">
                            <div class="modal-header">
                                <h4 class="modal-title">Update Student</h4>
                                <button type="button" class="close" data-dismiss="modal">&times;</button>
                            </div>
                            <div class="modal-body">
                                <div class="form-group">
                                    <label for="name">Name</label>
                                    <input type="text" name="name" class="form-control" placeholder="name" >
                                </div>
                                <div class="form-group">
                                    <label for="Surname">Surname</label>
                                    <input type="text" name="surname" class="form-control" placeholder="surname" >
                                </div>
                                <div class="form-group">
                                    <label  for="age">Age</label>
                                    <input type="number" name="age" class="form-control" placeholder="age" >
                                </div>
                            </div>
                            <div class="modal-footer">
                                <input type="hidden" name="studentId" id="studentIdForUpdate"/>
                                <input type="hidden" name="action" value="update"/>
                                <input type="submit" class="btn btn-deafult" value="Ok"/>
                                <input type="submit" class="btn btn-danger" data-dismiss="modal" value="Cancel"/>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>

        <div class="row">
            <form class="col-md-3" action="index.jsp">
                <div class="form-group">
                    <label for="name">Name</label>
                    <input type="text" name="name" class="form-control" placeholder="name" value="<%=name%>">
                </div>
                <div class="form-group">
                    <label for="Surname">Surname</label>
                    <input type="text" name="surname" class="form-control" placeholder="surname" value="<%=surname%>">
                </div>
                <div class="form-group">
                    <label  for="age">Age</label>
                    <input type="number" name="age" class="form-control" placeholder="age" value="<%=age%>">
                </div>
                <button type="submit"  name="action" value="search" class="btn btn-primary">Search</button>
                <button type="submit" name="action" value="add" class="btn btn-success">Add</button>
            </form>
        </div>
        <br/>
        <br/>

        <table border="1" style="width:70%;">
            <thead>
                <tr>
                    <th>#</th>
                    <th>name</th>
                    <th>surname</th>
                    <th>age</th>
                    <th>actions</th>
                </tr>   
            </thead>
            <tbody>
                <%
                    for (int i = 0; i < students.size(); i++) {
                        Student s = students.get(i);
                %>
                <tr>
                    <td> 
                        <% out.print(i + 1);%> 
                    </td>
                    <td>
                        <%=s.getName()%>
                    </td>
                    <td>
                        <%=s.getSurname()%>
                    </td>
                    <td>
                        <%=s.getAge()%>
                    </td>
                    <td>
                        <button class="btn btn-warning" data-toggle="modal" data-target="#updateModal"
                                onclick="setIdUpdate('<%=s.getId()%>')">Update</button>
                        <button class="btn btn-danger" data-toggle="modal" data-target="#deleteModal"
                                onclick="setIdDelete('<%=s.getId()%>')" >Delete</button>
                    </td>
                </tr>
                <%
                    }
                %>
            </tbody>
        </table>
    </body>
</html>
