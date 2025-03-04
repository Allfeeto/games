<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/jspf/css/style.css">
    <meta http-equiv="Content-Type" content="text/html" charset="UTF-8">
    <title>Главная страница</title>
    <style>
        #main {
        	    display: flex;
        	    justify-content: normal;
        	    flex-direction: column;
        	    gap: 0px;
        }
    </style>
</head>
<body>
    <jsp:include page="header.jsp" />
    <div id="main">
        <h2>Функции системы</h2>
        <nav>
		    <ul>
		        <li><a href="<c:url value='/developers'/>">Разработчики</a></li>
		        <li><a href="<c:url value='/games'/>">Игры</a></li>
		    </ul>

		    
        </nav>
    </div>
    <jsp:include page="footer.jsp" />
</body>
</html>
