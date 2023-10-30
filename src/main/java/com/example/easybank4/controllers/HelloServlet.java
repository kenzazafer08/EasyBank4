package com.example.easybank4.controllers;

import java.io.*;
import java.util.List;
import java.util.Optional;


import com.example.easybank4.dto.*;
import com.example.easybank4.helpers.helper;
import com.example.easybank4.services.CreditService;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import com.example.easybank4.services.AgencyService;

@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {
    private String message;
    private CreditService creditService; // Import and instantiate the AgencyService class


    public void init() {
        message = "Hello World!";
        creditService = new CreditService(); // Initialize the AgencyService

    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>" + message + "</h1>");
        Employee e = new Employee();
        e.setNumber("cnXyl");
        Client c = new Client();
        c.setCode("LKy");
        Agency a = new Agency();
        a.setCode("SKd2e");
        Credit credit = new Credit();
        credit.setCode(helper.generate(5));
        credit.setAmount(1000);
        credit.setDuration(24);
        credit.setDescription("credit");
        credit.setStatus(CreditStatus.pending);
        credit.setAgency(a);
        credit.setClient(c);
        credit.setEmployee(e);
        credit.setMonthly(creditService.Simulation(credit));
        Optional<Credit> added = creditService.add(credit);
        if(added.isPresent()){
            out.println(added.get().getCode());
        }else{
            out.println("no credit added");
        }

        out.println("</body></html>");
    }

    public void destroy() {
    }
}
