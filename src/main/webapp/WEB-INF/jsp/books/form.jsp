<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>${pageTitle}</title>
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

    <main class="narrow">
        <div class="page-header">
            <div>
                <p class="eyebrow">Book record</p>
                <h1>${pageTitle}</h1>
            </div>
            <a class="secondary-button" href="<c:url value='/books' />">Back</a>
        </div>

        <form:form cssClass="form-panel" modelAttribute="book" method="post" action="${formAction}">
            <form:errors path="*" cssClass="alert error" element="div"/>

            <label>Title
                <form:input path="title"/>
                <form:errors path="title" cssClass="field-error"/>
            </label>

            <label>Genre
                <form:input path="genre"/>
                <form:errors path="genre" cssClass="field-error"/>
            </label>

            <label>Published Year
                <form:input path="publishedYear" type="number"/>
                <form:errors path="publishedYear" cssClass="field-error"/>
            </label>

            <label>ISBN
                <form:input path="isbn"/>
                <form:errors path="isbn" cssClass="field-error"/>
            </label>

            <label>Author
                <form:select path="author.id">
                    <form:option value="" label="Select an author"/>
                    <form:options items="${authors}" itemValue="id" itemLabel="name"/>
                </form:select>
                <form:errors path="author" cssClass="field-error"/>
            </label>

            <button class="button" type="submit">Save Book</button>
        </form:form>
    </main>
</div>
</body>
</html>
