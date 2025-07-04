<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ include file="navbar.jsp" %>
<html>
<head>
    <title>CompanyWebAppNew - Landing Page</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css">
</head>
<body>
    <div class="container">
        <section class="hero-section">
            <div class="hero-overlay">
                <h1>Learn more about our listing process, as well as our additional staging and design work.</h1>
                <button class="hero-btn">LEARN MORE</button>
            </div>
        </section>
        <section class="consultation-card">
            <h2>Get a Free Consultation</h2>
            <form class="consultation-form">
                <input type="text" placeholder="Full Name" required>
                <input type="email" placeholder="Enter Email Address" required>
                <input type="text" placeholder="Mobile Number" required>
                <input type="text" placeholder="Area, City" required>
                <button type="submit" class="consultation-btn">Get Quick Quote</button>
            </form>
        </section>
        <section class="projects-section">
            <h2>Our Projects</h2>
            <div id="projects" class="card-list"></div>
        </section>
        <section class="clients-section">
            <h2>Happy Clients</h2>
            <div id="clients" class="card-list"></div>
        </section>
        <section class="contact-section">
            <div class="contact-card">
                <h2><i class="fas fa-envelope"></i> Contact Us</h2>
                <form action="ContactServlet" method="post" class="contact-form">
                    <input type="text" name="full_name" placeholder="Full Name" required>
                    <input type="email" name="email" placeholder="Email Address" required>
                    <input type="text" name="mobile" placeholder="Mobile Number" required>
                    <input type="text" name="city" placeholder="City" required>
                    <button type="submit" class="contact-btn">Send Message</button>
                </form>
            </div>
        </section>
        <section class="newsletter-section">
            <div class="newsletter-card">
                <h2><i class="fas fa-paper-plane"></i> Subscribe to our Newsletter</h2>
                <form action="NewsletterServlet" method="post" class="newsletter-form">
                    <input type="email" name="email" placeholder="Enter your email address" required>
                    <button type="submit" class="newsletter-btn">Subscribe</button>
                </form>
            </div>
        </section>
    </div>
    <footer class="main-footer">
        <div class="footer-nav">
            <a href="#">Home</a>
            <a href="#">Services</a>
            <a href="#">Projects</a>
            <a href="#">Testimonials</a>
            <a href="#">Contact</a>
            <span class="footer-subscribe">
                <input type="email" placeholder="Enter Email Address">
                <button>Subscribe</button>
            </span>
        </div>
        <div class="footer-bottom">
            <span>All Rights Reserved 2023</span>
            <span class="footer-social">
                <a href="#" title="Facebook"><i class="fab fa-facebook-f"></i></a>
                <a href="#" title="Twitter"><i class="fab fa-twitter"></i></a>
                <a href="#" title="Instagram"><i class="fab fa-instagram"></i></a>
            </span>
        </div>
    </footer>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
    <script src="${pageContext.request.contextPath}/assets/js/main.js"></script>
</body>
</html> 