<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page import="domain.Developer" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Удаление разработчика</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/jspf/css/style.css">
</head>
<body>
    <jsp:include page="header.jsp" />

    <div id="main">
        <section>
            <aside class="leftAside">
                <h3>Разработчик для удаления</h3>
                <table>
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Имя</th>
                            <th>Рейтинг</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td>${developerDelete.id}</td>
                            <td>${developerDelete.name}</td>
                            <td>${developerDelete.rating}</td>
                        </tr>
                    </tbody>
                </table>
            </aside>
        </section>

        <section>
            <article>
                <h3>Удаление разработчика</h3>
                <div class="text-article">
                    <!-- Вывод ошибки, если есть -->
                    <c:if test="${not empty error}">
                        <div style="color: red; font-weight: bold;">
                            ${error}
                        </div>
                    </c:if>

                    <form method="POST" action="${pageContext.request.contextPath}/deletedeveloper">
                        <input type="hidden" name="id" value="${developerDelete.id}" />

                        <p>Вы действительно хотите удалить разработчика <strong>${developerDelete.name}</strong>?</p>

                        <button type="submit" class="btn btn-danger">Удалить</button>
                        <a href='<c:url value="/developers" />' role="button" class="btn btn-secondary">Отмена</a>
                    </form>
                </div>
            </article>
        </section>
    </div>

    <jsp:include page="footer.jsp" />
</body>
</html>
