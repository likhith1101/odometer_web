package com.learning.hello;

import java.io.IOException;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.WebApplicationTemplateResolver;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/odometer")
public class OdometerServlet extends HttpServlet {

    private Odometer odometer;
    private JakartaServletWebApplication application;
    private TemplateEngine templateEngine;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        odometer = new Odometer(0); // Initialize with an initial reading of 0
        
        application = JakartaServletWebApplication.buildApplication(getServletContext());
        final WebApplicationTemplateResolver templateResolver =
                new WebApplicationTemplateResolver(application);
        templateResolver.setTemplateMode(TemplateMode.HTML);
        templateResolver.setPrefix("/WEB-INF/templates/");
        templateResolver.setSuffix(".html");
        templateEngine = new TemplateEngine();
        templateEngine.setTemplateResolver(templateResolver);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final IWebExchange webExchange = this.application.buildExchange(req, resp);
        final WebContext ctx = new WebContext(webExchange);

        ctx.setVariable("currentReading", odometer.getCurrentReading());
        ctx.setVariable("nextReading", odometer.getNextReading());
        ctx.setVariable("previousReading", odometer.getPreviousReading());

        templateEngine.process("odometer", ctx, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        String inputNumber = req.getParameter("inputNumber");
        
        if (inputNumber != null && !inputNumber.isEmpty()) {
            int newReading = Integer.parseInt(inputNumber);
            odometer = new Odometer(newReading); // Update the odometer reading
        } else if ("next".equals(action)) {
            odometer.getNextReading();
        } else if ("previous".equals(action)) {
            odometer.getPreviousReading();
        }

        resp.sendRedirect(req.getContextPath() + "/odometer");
    }

}
