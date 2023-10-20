package com.example.easybank4;

import java.io.*;
import java.util.List;
import java.util.Optional;


import com.example.easybank4.dto.Agency;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import com.example.easybank4.services.AgencyService;

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
        Agency agency = new Agency();
        agency.setCode("SYLFO");
        agency.setName("test");
        agency.setAddress("70 RUE EL OUMAM QUA HOPITAL SAFI");
        agency.setPhone("0634047964");
        agency.setDeleted(false);
        Optional<Agency> updated = agencyService.update(agency);
        if(updated.isPresent()){
            out.println(updated.get().getName());
        }else{
            out.println("no agency updated");
        }

        out.println("</body></html>");
    }

    public void destroy() {
    }
}
