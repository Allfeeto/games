<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page import="domain.Game"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Игры</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/jspf/css/style.css">
    <style>
        th:nth-child(1), td:nth-child(1) {
            display: none;
        }
    </style>
</head>
<body>
    <jsp:include page="header.jsp" />
    <div id="main">
        <section>
            <aside class="leftAside">
                <h3>Список игр</h3>
                <table>
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Название</th>
                            <th>Год выпуска</th>
                            <th>Жанр</th>
                            <th>Системные требования</th>
                            <th scope="col">Редактировать</th>
							<th scope="col">Удалить</th>
                            
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="game" items="${games}">
                            <tr>
                                <td>${game.id}</td>
                                <td>${game.title}</td>
                                <td>${game.releaseYear}</td>
                                <td>${game.genre}</td>
                                <td>${game.systemRequirements}</td>
                                <td width="20"><a href='<c:url value="/editgame?id=${game.getId()}" />'
                                role="button" class="btn btn-outline-primary edit-delete">
								<img alt="Редактировать" class="edit-delete"  src="${pageContext.request.contextPath}/jspf/images/icon-edit.png"></a></td> 
								<td width="20"><a href='<c:url value="/deletegame?id=${game.getId()}"/>'
								role="button" class="btn btn-outline-primary "> <img
								alt="Удалить" class="edit-delete" src="${pageContext.request.contextPath}/jspf/images/icon-delete.png"></a></td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </aside>
        </section>
        <section>
            <article>
                <h3>Добавить новую игру</h3>
                
                <div class="text-article">
                        <!-- Выводим ошибку, если она есть -->
        <c:if test="${not empty error}">
            <div style="color: red; font-weight: bold;">
                ${error}
            </div>
        </c:if>
                    <form method="POST" action="">
                        <p>
                            <label for="title">Название</label>
                            <input type="text" name="title" />
                        </p>
                        <p>
                            <label for="releaseYear">Год выпуска</label>
                            <input type="number" name="releaseYear" />
                        </p>
                        <p>
                            <label for="genre">Жанр</label>
                            <input type="text" name="genre" />
                        </p>
                        <p>
                            <label for="systemRequirements">Системные требования</label>
                            <input type="text" name="systemRequirements" />
                        </p>
                        <button type="submit">Добавить</button>
                    </form>
                </div>
            </article>
        </section>
    </div>
    <jsp:include page="footer.jsp" />
</body>
</html>
