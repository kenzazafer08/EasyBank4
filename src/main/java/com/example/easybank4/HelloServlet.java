package com.example.easybank4;

import java.io.*;
import java.util.Optional;


import dto.Agency;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import services.AgencyService;

@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {
    private String message;
    private AgencyService agencyService; // Import and instantiate the AgencyService class


    public void init() {
        message = "Hello World!";
        agencyService = new AgencyService(); // Initialize the AgencyService

    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        // Hello
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>" + message + "</h1>");
        Optional<Agency> agency = agencyService.findAgency("15AEr");
        if(agency.isPresent()){
            out.println("Agency name :" +agency.get().getName());
        }else{
            out.println("No agency found");
        }

        out.println("</body></html>");
    }

    public void destroy() {
    }
}
