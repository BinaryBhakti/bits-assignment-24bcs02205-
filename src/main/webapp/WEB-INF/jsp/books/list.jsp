<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Books</title>
    <link rel="stylesheet" href="<c:url value='/css/styles.css' />">
</head>
<body>
<div class="shell">
    <nav class="topbar">
        <a class="brand" href="<c:url value='/books' />">Library Manager</a>
        <div class="nav-links">
            <a class="active" href="<c:url value='/books' />">Books</a>
            <a href="<c:url value='/authors' />">Authors</a>
        </div>
    </nav>

    <main>
        <div class="page-header">
            <div>
                <p class="eyebrow">Inner join result</p>
                <h1>Books with Authors</h1>
            </div>
            <a class="button" href="<c:url value='/books/new' />">Add Book</a>
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
                    <th>Title</th>
                    <th>Author</th>
                    <th>Genre</th>
                    <th>Year</th>
                    <th>ISBN</th>
                    <th>Country</th>
                    <th>Action</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="row" items="${bookAuthorDetails}">
                    <tr>
                        <td>${row.title}</td>
                        <td>
                            <span class="primary-text">${row.authorName}</span>
                            <span class="muted">${row.authorEmail}</span>
                        </td>
                        <td>${row.genre}</td>
                        <td>${row.publishedYear}</td>
                        <td>${row.isbn}</td>
                        <td>${row.country}</td>
                        <td><a class="text-link" href="<c:url value='/books/${row.bookId}/edit' />">Edit</a></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </section>
    </main>
</div>
</body>
</html>
