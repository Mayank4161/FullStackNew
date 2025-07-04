package com.companywebappnew.servlets;

import com.companywebappnew.DBConnection;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.WebServlet; // यही mapping जरूरी है!
import java.io.*;
import java.sql.*;
import java.util.regex.Pattern;

@WebServlet("/ContactServlet") // यही mapping जरूरी है!
public class ContactServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static final Pattern EMAIL_PATTERN = Pattern.compile("^[A-Za-z0-9+_.-]+@(.+)$");
	private static final Pattern MOBILE_PATTERN = Pattern.compile("^[0-9]{10}$");
	
	public  ContactServlet() {
		System.out.println("Object created ContactServlet");
	}
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/plain;charset=UTF-8");
        PrintWriter out = resp.getWriter();
        
        try {
            // Input validation
            String fullName = req.getParameter("full_name");
            String email = req.getParameter("email");
            String mobile = req.getParameter("mobile");
            String city = req.getParameter("city");
            
            // Check for null or empty values
            if (fullName == null || fullName.trim().isEmpty() ||
                email == null || email.trim().isEmpty() ||
                mobile == null || mobile.trim().isEmpty() ||
                city == null || city.trim().isEmpty()) {
                out.write("Error: All fields are required");
                return;
            }
            
            // Sanitize inputs
            fullName = fullName.trim();
            email = email.trim().toLowerCase();
            mobile = mobile.trim();
            city = city.trim();
            
            // Validate email format
            if (!EMAIL_PATTERN.matcher(email).matches()) {
                out.write("Error: Please enter a valid email address");
                return;
            }
            
            // Validate mobile number (10 digits)
            if (!MOBILE_PATTERN.matcher(mobile).matches()) {
                out.write("Error: Please enter a valid 10-digit mobile number");
                return;
            }
            
            // Validate name length
            if (fullName.length() < 2 || fullName.length() > 50) {
                out.write("Error: Name must be between 2 and 50 characters");
                return;
            }
            
            // Validate city length
            if (city.length() < 2 || city.length() > 30) {
                out.write("Error: City must be between 2 and 30 characters");
                return;
            }
            
            try (Connection con = DBConnection.getConnection()) {
                String sql = "INSERT INTO contacts (full_name, email, mobile, city) VALUES (?, ?, ?, ?)";
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setString(1, fullName);
                ps.setString(2, email);
                ps.setString(3, mobile);
                ps.setString(4, city);
                ps.executeUpdate();
                out.write("Thank you! Your message has been sent successfully.");
            } catch (Exception e) {
                System.err.println("Database error: " + e.getMessage());
                out.write("Error: Could not save your message. Please try again later.");
            }
            
        } catch (Exception e) {
            System.err.println("ContactServlet error: " + e.getMessage());
            out.write("Error: An unexpected error occurred. Please try again later.");
        }
    }
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/plain;charset=UTF-8");
        resp.getWriter().write("GET not supported. Please use the contact form to submit data via POST.");
    }
}