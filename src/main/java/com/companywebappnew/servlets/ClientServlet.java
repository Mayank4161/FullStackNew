package com.companywebappnew.servlets;

import com.companywebappnew.DBConnection;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.*;
import java.sql.*;
import org.json.*;
import jakarta.servlet.annotation.WebServlet;

@WebServlet("/admin/ClientServlet")
public class ClientServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json;charset=UTF-8");
        PrintWriter out = resp.getWriter();
        try (Connection con = DBConnection.getConnection()) {
            String sql = "SELECT id, name, description, designation, image_path FROM clients ORDER BY id DESC";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            JSONArray arr = new JSONArray();
            while (rs.next()) {
                JSONObject obj = new JSONObject();
                obj.put("id", rs.getInt("id"));
                obj.put("name", rs.getString("name"));
                obj.put("description", rs.getString("description"));
                obj.put("designation", rs.getString("designation"));
                obj.put("image_path", rs.getString("image_path"));
                arr.put(obj);
            }
            out.print(arr.toString());
        } catch (Exception e) {
            System.err.println("ClientServlet error: " + e.getMessage());
            out.print("[]");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/plain;charset=UTF-8");
        resp.getWriter().write("POST not supported. Use GET to retrieve clients.");
    }
} 
 