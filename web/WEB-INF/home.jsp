<%-- 
    Document   : home
    Created on : 19-Feb-2021, 8:13:55 PM
    Author     : User
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home Page</title>
    </head>
    <body>
        <h1>Home Page</h1>
        <h3>Hello ${username}</h3>
        <div>
            <a href="login?logout">Log out</a>
        </div>
    </body>
</html>