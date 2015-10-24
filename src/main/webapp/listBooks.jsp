<%-- 
    Document   : listBooks
    Created on : Oct 24, 2015, 1:11:03 PM
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
        <title>JSP Page</title>
    </head>
    <body>
         <table style="width: 75%;" border="1" cellspacing="0" cellpadding="4">
                <tr style="background-color: black;color:white;">
                    <th align="left" class="tableHead">ID</th>
                    <th align="left" class="tableHead">Title</th>
                    <th align="right" class="tableHead">ISBN</th>
                    <th align="right" class="tableHead">Author</th>
                </tr>
                <c:forEach var="b" items="${books}" varStatus="rowCount">
                    <c:choose>
                        <c:when test="${rowCount.count % 2 == 0}">
                            <tr style="background-color: white;">
                            </c:when>
                            <c:otherwise>
                            <tr style="background-color: #ccffff;">
                            </c:otherwise>
                        </c:choose>
                        <td><input type="checkbox" name="bookId" value="${b.bookId}" /></td>
                        <td align="left">${b.title}</td>
                        <td align="left">${b.isbn}</td>
                        <td align="left">
                            <c:choose>
                                <c:when test="${not empty b.authorId}">
                                    ${b.authorId.authorName}
                                </c:when>
                                <c:otherwise>
                                    None
                                </c:otherwise>
                            </c:choose>
                    </td>
                    </tr>
                </c:forEach>
            </table>
    </body>
</html>
