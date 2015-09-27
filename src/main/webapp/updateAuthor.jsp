<%-- 
    Document   : updateAuthor
    Created on : Sep 26, 2015, 7:26:38 PM
    Author     : Mark
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
         <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
        <link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap-theme.min.css">
        <title>Update Author</title>
    </head>
    <body>
         <div id="form" class="well">
            <div class="panel panel-default">
                <div class="panel-heading"> <h1>Update Author Information</h1></div>
                    <div class="panel-body">
                        <form  method="POST" action="AuthorController?action=update"
                                  name="form1" id="form1" >
                             <div class="input-group">
                                <span class="input-group-addon" >ID To Update</span>
                                 <input class="txt" required="true" type="number" id="inputName" name="inputId" />
                            </div>
                            <div class="input-group">
                                <span class="input-group-addon" >New Name Value</span>
                                 <input class="txt" required="true"id="inputName" name="inputName" />
                            </div>
            
                            <input class="btn btn-success" type="submit" value="Update" name="submit" id="submit"/>
                        </form>
                         <h2>${updated}</h2>
                    </div>
            </div>
             
        </div>
          
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
        <script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>

    </body>
</html>
