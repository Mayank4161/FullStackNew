<%@ include file="../navbar.jsp" %>
<%@ page import="java.sql.*,com.companywebappnew.DBConnection" %>
<html>
<head><title>View Subscribers</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css">
</head>
<body>
<section class="admin-table-section">
<h2>Newsletter Subscribers</h2>
<div class="table-container">
<table class="admin-table">
    <tr><th>Email</th></tr>
<%
    try (Connection con = DBConnection.getConnection()) {
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM newsletter");
        while (rs.next()) {
%>
    <tr>
        <td><%= rs.getString("email") %></td>
    </tr>
<%
        }
    } catch (Exception e) { out.print("<tr><td>Error</td></tr>"); }
%>
</table>
</div>
</section>
</body>
</html> 