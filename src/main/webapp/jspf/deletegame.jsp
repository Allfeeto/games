<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page import="domain.Game"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Удаление игры</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/jspf/css/style.css">
</head>
<body>
    <jsp:include page="header.jsp" />

    <div id="main">
        <section>
            <aside class="leftAside">
                <h3>Игра для удаления</h3>
                <table>
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Название</th>
                            <th>Год выпуска</th>
                            <th>Жанр</th>
                            <th>Системные требования</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td>${gameDelete.id}</td>
                            <td>${gameDelete.title}</td>
                            <td>${gameDelete.releaseYear}</td>
                            <td>${gameDelete.genre}</td>
                            <td>${gameDelete.systemRequirements}</td>
                        </tr>
                    </tbody>
                </table>
            </aside>
        </section>

        <section>
            <article>
                <h3>Удаление игры</h3>
                <div class="text-article">
                    <!-- Вывод ошибки, если есть -->
                    <c:if test="${not empty error}">
                        <div style="color: red; font-weight: bold;">
                            ${error}
                        </div>
                    </c:if>

                    <form method="POST" action="${pageContext.request.contextPath}/deletegame">
                        <input type="hidden" name="id" value="${gameDelete.id}" />

                        <p>Вы действительно хотите удалить игру <strong>${gameDelete.title}</strong>?</p>

                        <button type="submit" class="btn btn-danger">Удалить</button>
                        <a href='<c:url value="/games" />' role="button" class="btn btn-secondary">Отмена</a>
                    </form>
                </div>
            </article>
        </section>
    </div>

    <jsp:include page="footer.jsp" />
</body>
</html>
