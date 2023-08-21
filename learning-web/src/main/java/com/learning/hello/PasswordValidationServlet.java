package com.learning.hello;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;


public class PasswordValidationServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private List<String> loadBannedPasswords() {
        List<String> bannedPasswords = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("banned_passwords.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                bannedPasswords.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bannedPasswords;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();

        String password = request.getParameter("password");
        List<String> bannedPasswords = loadBannedPasswords();

        if (bannedPasswords.contains(password)) {
            out.println("Given password is banned.");
        } else {
            out.println("Given password is not banned.");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();

        String password = request.getParameter("password");
        List<String> bannedPasswords = loadBannedPasswords();

        if (bannedPasswords.contains(password)) {
            out.println("{\"success\": false}");
        } else {
            out.println("{\"success\": true}");
        }
    }
}
