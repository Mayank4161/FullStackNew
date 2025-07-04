<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ include file="../navbar.jsp" %>
<html>
<head>
    <title>Admin Dashboard</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css">
</head>
<body>
    <section class="admin-dashboard">
        <h2>Admin Dashboard</h2>
        <div class="dashboard-cards">
            <a href="addProject.jsp" class="dashboard-card">
                <span class="card-icon">📁</span>
                <span>Add Project</span>
            </a>
            <a href="addClient.jsp" class="dashboard-card">
                <span class="card-icon">👤</span>
                <span>Add Client</span>
            </a>
            <a href="viewContacts.jsp" class="dashboard-card">
                <span class="card-icon">📬</span>
                <span>View Contacts</span>
            </a>
            <a href="viewSubscribers.jsp" class="dashboard-card">
                <span class="card-icon">📰</span>
                <span>View Subscribers</span>
            </a>
        </div>
    </section>
</body>
</html> 