<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page import="domain.Game"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Редактирование игры</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/jspf/css/style.css">
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
                        </tr>
                    </thead>
                    <tbody>
                        
                            <tr>
                                <td>${gameEdit.id}</td>
                                <td>${gameEdit.title}</td>
                                <td>${gameEdit.releaseYear}</td>
                                <td>${gameEdit.genre}</td>
                                <td>${gameEdit.systemRequirements}</td>
                            </tr>
                        
                    </tbody>
                </table>
            </aside>
        </section>

        <section>
            <article>
                <h3>Редактирование игры</h3>
                <div class="text-article">
                    <!-- Выводим ошибку, если она есть -->
                    <c:if test="${not empty error}">
                        <div style="color: red; font-weight: bold;">
                            ${error}
                        </div>
                    </c:if>

                    <form method="POST" action="">
                        <div class="mb-3 row">
                            <label for="id" class="col-sm-4 col-form-label">ID игры</label>
                            <div class="col-sm-6">
                                <input type="text" class="form-control" readonly value="${gameEdit.id}" />
                            </div>
                        </div>
                        <div class="mb-3 row">
                            <label for="title" class="col-sm-4 col-form-label">Название</label>
                            <div class="col-sm-6">
                                <input type="text" class="form-control" name="title" value="${gameEdit.title}" />
                            </div>
                        </div>
                        <div class="mb-3 row">
                            <label for="releaseYear" class="col-sm-4 col-form-label">Год выпуска</label>
                            <div class="col-sm-6">
                                <input type="number" class="form-control" name="releaseYear" value="${gameEdit.releaseYear}" />
                            </div>
                        </div>
                        <div class="mb-3 row">
                            <label for="genre" class="col-sm-4 col-form-label">Жанр</label>
                            <div class="col-sm-6">
                                <input type="text" class="form-control" name="genre" value="${gameEdit.genre}" />
                            </div>
                        </div>
                        <div class="mb-3 row">
                            <label for="systemRequirements" class="col-sm-4 col-form-label">Системные требования</label>
                            <div class="col-sm-6">
                                <input type="text" class="form-control" name="systemRequirements" value="${gameEdit.systemRequirements}" />
                            </div>
                        </div>
                        <button type="submit" class="btn btn-primary">Редактировать</button>
                        <a href='<c:url value="/games" />' role="button" class="btn btn-secondary">Отменить/Возврат</a>
                    </form>
                </div>
            </article>
        </section>
    </div>

    <jsp:include page="footer.jsp" />
</body>
</html>
