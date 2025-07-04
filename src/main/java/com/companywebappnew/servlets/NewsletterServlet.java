package com.companywebappnew.servlets;

import com.companywebappnew.DBConnection;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.*;
import java.sql.*;
import java.util.regex.Pattern;

@WebServlet("/NewsletterServlet")
public class NewsletterServlet extends HttpServlet {
    
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[A-Za-z0-9+_.-]+@(.+)$");
    
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/plain;charset=UTF-8");
        PrintWriter out = resp.getWriter();
        
        try {
            String email = req.getParameter("email");
            
            // Check for null or empty value
            if (email == null || email.trim().isEmpty()) {
                out.write("Error: Email address is required");
                return;
            }
            
            // Sanitize input
            email = email.trim().toLowerCase();
            
            // Validate email format
            if (!EMAIL_PATTERN.matcher(email).matches()) {
                out.write("Error: Please enter a valid email address");
                return;
            }
            
            // Validate email length
            if (email.length() > 100) {
                out.write("Error: Email address is too long");
                return;
            }
            
            try (Connection con = DBConnection.getConnection()) {
                // Check if email already exists
                String checkSql = "SELECT COUNT(*) FROM newsletter WHERE email = ?";
                PreparedStatement checkPs = con.prepareStatement(checkSql);
                checkPs.setString(1, email);
                if (checkPs.executeQuery().next() && checkPs.executeQuery().getInt(1) > 0) {
                    out.write("This email is already subscribed to our newsletter!");
                    return;
                }
                
                String sql = "INSERT INTO newsletter (email) VALUES (?)";
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setString(1, email);
                ps.executeUpdate();
                out.write("Thank you! You have been successfully subscribed to our newsletter.");
            } catch (Exception e) {
                System.err.println("Database error: " + e.getMessage());
                out.write("Error: Could not subscribe to newsletter. Please try again later.");
            }
            
        } catch (Exception e) {
            System.err.println("NewsletterServlet error: " + e.getMessage());
            out.write("Error: An unexpected error occurred. Please try again later.");
        }
    }
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/plain;charset=UTF-8");
        resp.getWriter().write("GET not supported. Please use the newsletter form to subscribe via POST.");
    }
}
