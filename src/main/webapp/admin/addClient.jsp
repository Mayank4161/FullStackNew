<%@ include file="../navbar.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<html>
<head>
    <title>Add Client</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/cropper.min.css">
</head>
<body>
    <section class="admin-form-section">
        <h2>Add New Client</h2>
        <div id="client-message"></div>
        <form id="addClientForm" action="AddClientServlet" method="post" enctype="multipart/form-data" class="admin-form">
            <label>Client Name</label>
            <input type="text" name="name" required>
            <label>Description</label>
            <textarea name="description" required></textarea>
            <label>Designation</label>
            <input type="text" name="designation" required>
            <label>Image</label>
            <input type="file" id="clientImageInput" name="image" accept="image/*" required>
            <div id="clientImagePreview" style="margin-bottom:16px;"></div>
            <input type="submit" value="Add Client">
        </form>
        <!-- Cropping Modal -->
        <div id="clientCropModal" style="display:none;position:fixed;top:0;left:0;width:100vw;height:100vh;background:rgba(0,0,0,0.7);align-items:center;justify-content:center;z-index:1000;">
            <div style="background:#fff;padding:20px;border-radius:10px;max-width:90vw;max-height:90vh;">
                <img id="clientCropImage" style="max-width:60vw;max-height:60vh;">
                <div style="text-align:center;margin-top:10px;">
                    <button type="button" id="clientCropBtn">Crop & Use</button>
                    <button type="button" id="clientCancelCropBtn">Cancel</button>
                </div>
            </div>
        </div>
    </section>
    <script src="${pageContext.request.contextPath}/assets/js/cropper.min.js"></script>
    <script src="${pageContext.request.contextPath}/assets/js/main.js"></script>
    <script src="${pageContext.request.contextPath}/assets/js/client-cropper.js"></script>
</body>
</html> 