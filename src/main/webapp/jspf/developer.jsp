<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page import="domain.Developer"%>
<%
Developer dev1 = new Developer(1L, "John Doe", 4.5);
Developer dev2 = new Developer(2L, "Jane Smith", 4.7);
Developer dev3 = new Developer(3L, "Alice Johnson", 4.8);
Developer[] developers = new Developer[]{dev1, dev2, dev3};
pageContext.setAttribute("developers", developers);
%>
<!DOCTYPE html>
<html>
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/jspf/css/style.css">
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
<form method="POST" action="/employees/developers"> 
    <p>
        <label for="name">Имя разработчика</label>
        <input type="text" name="name" required />
    </p>
    <p>
        <label for="rating">Рейтинг</label>
        <input type="number" name="rating" min="0" max="5" step="0.1" required />
    </p>
    <button type="submit">Добавить</button> <!-- Кнопка отправки формы -->
</form>

</div>
</article>
</section>
</div>
<jsp:include page="footer.jsp" />
</body>
</html>
