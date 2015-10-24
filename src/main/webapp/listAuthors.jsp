<%-- 
    Document   : listAuthors.jsp
    Created on : Sep 23, 2015, 8:43:42 PM
    Author     : Mark
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
       <jsp:include page="style.jsp" />
        <title>List Of Authors</title>
    </head>
    <body>
        <h1>Author List</h1>
        <p>${count} Records Found</p>
        <table width="500" border="1" cellspacing="0" cellpadding="4">
            <tr style="background-color: black;color:white;">
                <th align="left" class="tableHead">ID</th>
                <th align="left" class="tableHead">Author Name</th>
                <th align="left" class="tableHead">Date Added</th>
                <th align="left" class="tableHead">Books</th>
                <th align="left">Edit Selected Book</th>
                <th align="left">Add A Book</th>
                <th align='left'>Delete A Book</th>
            </tr>
        <c:forEach var="a" items="${authors}" varStatus="rowCount">
            <c:choose>
                <c:when test="${rowCount.count % 2 == 0}">
                    <tr style="background-color: white;">
                </c:when>
                <c:otherwise>
                    <tr style="background-color: #ccffff;">
                </c:otherwise>
            </c:choose>
            <td align="left">${a.authorId}</td>
            <td align="left">${a.authorName}</td>
            <td align="right">
                <fmt:formatDate pattern="M/d/yyyy" value="${a.createDate}"></fmt:formatDate>
            </td>
<td>
                      <c:choose>
                    <c:when test="${ not empty a.bookCollection }">
            
                <select id="${a.authorId}" class="bookSelect">
                    <option sel="true" value=""></option>
                <c:forEach var="b" items="${a.bookCollection}" varStatus="rowCount">
                    <option value="${b.bookId}">${b.title}</option >   
                </c:forEach>
                </select>
               
                      
                    
                    </c:when>
                    <c:otherwise>
                        No books
                    </c:otherwise>
                    </c:choose> 
    </td>  
    <td>
        <input type="button" id="editLink_${a.authorId}" class='edit' value='Edit Selected From Dialog' ></input>
    </td>
    <td><input type="button" onclick="$('#form1').attr('action', 'BookController?action=addEdit&authorId=${a.authorId}'); $('#form1').submit();"
               class="add" id="addLink_${a.authorId}" value="Add New From Dialog"/> </td>
    <td><a id='deleteLink_${a.authorId}' href=''>Delete Selected</a></td>
        </tr>
            
        </c:forEach>
        </table>
        <div id="dialog">
            <h3> Enter Book Info Here </h3>
           <form  method="POST" 
               name="form1" id="form1">
            <div>
                <b>Title</b>
            <input type="text" name="inputTitle" id="inputTitle" />
            </div>
            <div>
                <b>ISBN</b>
            <input name="inputIsbn" type="text" id="inputIsbn" />
            </div>
            <div>
                <b>Change Author Author(Edit Only)</b>
                <select id="inputAuthorId">
                    <option value=""></option>
                    <c:forEach var="a" items="${authors}" varStatus="rowCount">
                        <option value="${a.authorId}">${a.authorName}</option>
                    </c:forEach>
                </select>
            </div>
           </form>
        </div>
        <input type="button" onclick="$('#dialog').dialog('open');" value="Editor"/>
        <a href="../bookApp/" >Go Back</a>
        
        <jsp:include page="jscript.jsp" />
        <script>
             $('.edit').on('click', function(){
                 var id = $(this).attr('id');
                 var authId = id.split('_',2)[1];
                
                 var book = $('#'+authId).val();
                 alert(book)
                if(book!==""&&book!==null){
                      $('#form1').attr('action', 'BookController?action=addEdit&&bookId='+book);
                      $('#form1').submit();
                  }
                
                
            });
            $('.bookSelect').on('change', function(){
               var id = $(this).attr('id');
               var value = $(this).val();
               alert(id+', '+value)
               if(value!==""&&value!==null){
                    $('#deleteLink_'+id).attr('href','BookController?action=delete&&bookId='+value);
               }
              
            });
            $(function (){
              $('#dialog').dialog();
            });
        </script>
    </body>
</html>
