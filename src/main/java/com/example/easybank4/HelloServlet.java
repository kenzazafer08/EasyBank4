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

        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>" + message + "</h1>");
        Boolean agency = agencyService.deleteAgency("XOcRY");
        if(agency){
            out.println("Agency deleted successfully");
        }else{
            out.println("No agency found");
        }

        out.println("</body></html>");
    }

    public void destroy() {
    }
}
