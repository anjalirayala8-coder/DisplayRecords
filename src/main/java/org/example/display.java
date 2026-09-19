package org.example;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
@WebServlet("/records")

public class display extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException {
        try {
            //load driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            //connection
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/stu",
                    "root",
                    "Anjalibs"
            );
            Statement st=con.createStatement();
            ResultSet rs=st.executeQuery("select * from stu");
            res.setContentType("text/html");
            PrintWriter out=res.getWriter();
            out.println("<html>");
            out.println("<body>");
            out.println("<table border=1>");
            out.println("<tr>");
            out.println("<th>ID</th>");
            out.println("<th>name</th>");
            out.println("<th>marks</th>");
            out.println("</tr>");
            while(rs.next()){
                out.println("<tr>");
                out.println("<td>"+rs.getInt("id")+"</td>");
                out.println("<td>"+rs.getString("name")+"</td>");
                out.println("<td>"+rs.getInt("marks")+"</td>");
                out.println("</tr>");
            }
            out.println("</table");
            out.println("</body");
            out.println("</html");
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}


