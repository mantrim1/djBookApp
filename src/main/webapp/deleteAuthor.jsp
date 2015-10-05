<%-- 
    Document   : deleteAuthor
    Created on : Sep 26, 2015, 7:00:40 PM
    Author     : Mark
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
         <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
        <link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap-theme.min.css">
        <title>Delete Author</title>
    </head>
    <body>
         <h3>Total rows affected: ${updated}</h3>
         <div id="form" class="well">
            <div class="panel panel-default">
                <div class="panel-heading"> <h1>Enter Author ID To Delete</h1></div>
                    <div class="panel-body">
                        <form  method="POST" action="AuthorController?action=delete"
                                  name="form1" id="form1" >
                            <div class="input-group">
                                <span class="input-group-addon" >ID Number</span>
                                 <input class="txt" required="true" type="number" id="inputName" name="inputId" />
                            </div>
            
                            <input class="btn btn-danger" type="submit" value="Delete" name="submit" id="submit"/>
                        </form>
                         <h2>${updated}</h2>
                    </div>
            </div>
             
        </div>
          
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
        <script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
    <script typ="javascript">
        $('#submit').on('click', function(){
           confirm("Are you sure you want to delete this author?"); 
        });
    </script>
    </body>
</html>
