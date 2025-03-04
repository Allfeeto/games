<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page import="domain.Developer"%>

<!DOCTYPE html>
<html>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/jspf/css/style.css">
<meta http-equiv="Content-Type" content="text/html" charset="UTF-8">
<title>Разработчики</title>
<head>
<meta charset="UTF-8">
<title>Developers</title>
</head>
<body>
	<jsp:include page="header.jsp" />
	
	    <div id="main">
        <section>
            <aside class="leftAside">
                <h3>Список разработчиков</h3>
                <table>
                    <thead>
                        <tr>
                            <th scope="col">ID</th>
                            <th scope="col">Имя</th>
                            <th scope="col">Рейтинг</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="developer" items="${developers}">
                            <tr>
                                <td>${developer.getId()}</td>
                                <td>${developer.getName()}</td>
                                <td>${developer.getRating()}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </aside>
        </section>

        <section>
            <article>
                <h3>Добавить разработчика</h3>
                <div class="text-article">
                        <!-- Выводим ошибку, если она есть -->
        <c:if test="${not empty error}">
            <div style="color: red; font-weight: bold;">
                ${error}
            </div>
        </c:if>
                    <form method="POST" action="developers">
                        <p>
                            <label for="name">Имя</label>
                            <input type="text" name="name" id="name" />
                        </p>
                        <p>
                            <label for="rating">Рейтинг</label>
                            <input type="text" name="rating" id="rating" />
                        </p>
                        <p>
                            <button type="submit">Добавить</button>
                        </p>
                    </form>
                </div>
            </article>
        </section>
    </div>
	
	<jsp:include page="footer.jsp" />
</body>
</html>
