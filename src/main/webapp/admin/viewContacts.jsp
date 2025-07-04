<%@ include file="../navbar.jsp" %>
<%@ page import="java.sql.*,com.companywebappnew.DBConnection" %>
<html>
<head><title>View Contacts</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css">
</head>
<body>
<section class="admin-table-section">
<h2>Contact Form Responses</h2>
<div class="table-container">
<table class="admin-table">
    <tr><th>Full Name</th><th>Email</th><th>Mobile</th><th>City</th></tr>
<%
    try (Connection con = DBConnection.getConnection()) {
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM contacts");
        while (rs.next()) {
%>
    <tr>
        <td><%= rs.getString("full_name") %></td>
        <td><%= rs.getString("email") %></td>
        <td><%= rs.getString("mobile") %></td>
        <td><%= rs.getString("city") %></td>
    </tr>
<%
        }
    } catch (Exception e) { out.print("<tr><td colspan='4'>Error</td></tr>"); }
%>
</table>
</div>
</section>
</body>
</html> 