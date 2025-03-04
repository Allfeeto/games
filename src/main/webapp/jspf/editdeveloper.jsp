<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page import="domain.Developer"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Редактирование разработчика</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/jspf/css/style.css">
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
                            <th>ID</th>
                            <th>Имя</th>
                            <th>Рейтинг</th>
                        </tr>
                    </thead>
                    <tbody>
                            <tr>
                                <td>${developerEdit.id}</td>
                                <td>${developerEdit.name}</td>
                                <td>${developerEdit.rating}</td>
                            </tr>

                    </tbody>
                </table>
            </aside>
        </section>

        <section>
            <article>
                <h3>Редактирование разработчика</h3>
                <div class="text-article">
                    <!-- Выводим ошибку, если она есть -->
                    <c:if test="${not empty error}">
                        <div style="color: red; font-weight: bold;">
                            ${error}
                        </div>
                    </c:if>

                    <form method="POST" action="">
                        <div class="mb-3 row">
                            <label for="id" class="col-sm-4 col-form-label">ID разработчика</label>
                            <div class="col-sm-6">
                                <input type="text" class="form-control" readonly value="${developerEdit.id}" />
                            </div>
                        </div>
                        <div class="mb-3 row">
                            <label for="name" class="col-sm-4 col-form-label">Имя</label>
                            <div class="col-sm-6">
                                <input type="text" class="form-control" name="name" value="${developerEdit.name}" />
                            </div>
                        </div>
                        <div class="mb-3 row">
                            <label for="rating" class="col-sm-4 col-form-label">Рейтинг</label>
                            <div class="col-sm-6">
                                <input type="number" class="form-control" name="rating" value="${developerEdit.rating}" />
                            </div>
                        </div>
                        <button type="submit" class="btn btn-primary">Редактировать</button>
                        <a href='<c:url value="/developers" />' role="button" class="btn btn-secondary">Отменить/Возврат</a>
                    </form>
                </div>
            </article>
        </section>
    </div>

    <jsp:include page="footer.jsp" />
</body>
</html>
