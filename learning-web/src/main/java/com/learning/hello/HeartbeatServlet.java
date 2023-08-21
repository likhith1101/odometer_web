package com.learning.hello;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.GenericServlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;

public class HeartbeatServlet extends GenericServlet {

    @Override
    public void init() {
        System.out.println("This line is printed when a servlet inits");
    }

    @Override
    public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html"); 

        PrintWriter out = response.getWriter(); 
        out.println("<html>");
        out.println("<head><title>Heartbeat Servlet</title></head>");
        out.println("<body>");
        out.println("<h1>I am alive!</h1>"); 
        out.println("</body>");
        out.println("</html>");
    }

    @Override
    public void destroy() {
        System.out.println("This is called when the servlet is destroyed");
    }
}
