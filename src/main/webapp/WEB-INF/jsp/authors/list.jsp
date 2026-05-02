<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Authors</title>
    <link rel="stylesheet" href="<c:url value='/css/styles.css' />">
</head>
<body>
<div class="shell">
    <nav class="topbar">
        <a class="brand" href="<c:url value='/books' />">Library Manager</a>
        <div class="nav-links">
            <a href="<c:url value='/books' />">Books</a>
            <a class="active" href="<c:url value='/authors' />">Authors</a>
        </div>
    </nav>

    <main>
        <div class="page-header">
            <div>
                <p class="eyebrow">Author table</p>
                <h1>Authors</h1>
            </div>
            <a class="button" href="<c:url value='/authors/new' />">Add Author</a>
        </div>

        <c:if test="${not empty successMessage}">
            <div class="alert success">${successMessage}</div>
        </c:if>
        <c:if test="${not empty errorMessage}">
            <div class="alert error">${errorMessage}</div>
        </c:if>

        <section class="table-panel">
            <table>
                <thead>
                <tr>
                    <th>Name</th>
                    <th>Email</th>
                    <th>Country</th>
                    <th>Birth Year</th>
                    <th>Action</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="author" items="${authors}">
                    <tr>
                        <td>${author.name}</td>
                        <td>${author.email}</td>
                        <td>${author.country}</td>
                        <td>${author.birthYear}</td>
                        <td><a class="text-link" href="<c:url value='/authors/${author.id}/edit' />">Edit</a></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </section>
    </main>
</div>
</body>
</html>
