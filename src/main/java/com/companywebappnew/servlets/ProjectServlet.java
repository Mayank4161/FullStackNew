package com.companywebappnew.servlets;

import com.companywebappnew.DBConnection;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.*;
import java.sql.*;
import org.json.*;

@WebServlet("/admin/ProjectServlet")
public class ProjectServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json;charset=UTF-8");
        PrintWriter out = resp.getWriter();
        try (Connection con = DBConnection.getConnection()) {
            String sql = "SELECT id, name, description, image_path FROM projects ORDER BY id DESC";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            JSONArray arr = new JSONArray();
            while (rs.next()) {
                JSONObject obj = new JSONObject();
                obj.put("id", rs.getInt("id"));
                obj.put("name", rs.getString("name"));
                obj.put("description", rs.getString("description"));
                obj.put("image_path", rs.getString("image_path"));
                arr.put(obj);
            }
            out.print(arr.toString());
        } catch (Exception e) {
            System.err.println("ProjectServlet error: " + e.getMessage());
            out.print("[]");
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/plain;charset=UTF-8");
        resp.getWriter().write("POST not supported. Use GET to retrieve projects.");
    }
} 