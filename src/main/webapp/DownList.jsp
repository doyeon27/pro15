<%@ page import="java.io.UnsupportedEncodingException" %>
<%@ page import="java.net.URLEncoder" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<ul>

<%--    <c:forEach var="filename" items="${fileNames}">--%>
<%--        <li><a href="${pageContext.request.contextPath}/DownloadFile.do?fileName=${filename}">${filename}</a></li>--%>
<%--    </c:forEach>--%>
    <c:forEach var="filename" items="${fileNames}">
        <c:set var="downloadPath" value="${pageContext.request.contextPath}/DownloadFile.do?fileName=${filename}" />
            <li>
                <a href="<c:out value='${downloadPath}'/>" download="${filename}">${filename}</a>
            </li>
    </c:forEach>
</ul>

</body>
</html>
