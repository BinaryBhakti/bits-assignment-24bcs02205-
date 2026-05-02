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
            <a href="<c:url value='/books' />">Books</a>
            <a class="active" href="<c:url value='/authors' />">Authors</a>
        </div>
    </nav>

    <main class="narrow">
        <div class="page-header">
            <div>
                <p class="eyebrow">Author record</p>
                <h1>${pageTitle}</h1>
            </div>
            <a class="secondary-button" href="<c:url value='/authors' />">Back</a>
        </div>

        <form:form cssClass="form-panel" modelAttribute="author" method="post" action="${formAction}">
            <form:errors path="*" cssClass="alert error" element="div"/>

            <label>Name
                <form:input path="name"/>
                <form:errors path="name" cssClass="field-error"/>
            </label>

            <label>Email
                <form:input path="email" type="email"/>
                <form:errors path="email" cssClass="field-error"/>
            </label>

            <label>Country
                <form:input path="country"/>
                <form:errors path="country" cssClass="field-error"/>
            </label>

            <label>Birth Year
                <form:input path="birthYear" type="number"/>
                <form:errors path="birthYear" cssClass="field-error"/>
            </label>

            <button class="button" type="submit">Save Author</button>
        </form:form>
    </main>
</div>
</body>
</html>
