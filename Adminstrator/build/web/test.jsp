<%-- 
    Document   : test
    Created on : Apr 1, 2014, 7:12:46 AM
    Author     : beshoy
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>File Uploading Form</title>
    </head>
    <body>
        <h3>File Upload:</h3>
        Select a file to upload: <br />
        <form action="servletAddProduct" method="post"
              enctype="multipart/form-data">
            <input type="file" name="file" size="50" />
            <br />
            <input type="submit" value="Upload File" />
        </form>
    </body>
</html>
