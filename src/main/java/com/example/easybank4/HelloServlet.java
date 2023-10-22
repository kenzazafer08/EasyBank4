package com.example.easybank4;

import java.io.*;
import java.util.List;
import java.util.Optional;


import com.example.easybank4.dto.Agency;
import com.example.easybank4.dto.Client;
import com.example.easybank4.services.ClientService;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import com.example.easybank4.services.AgencyService;

@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {
    private String message;
    private ClientService clientService;


    public void init() {
        message = "Hello World!";
        clientService = new ClientService();

    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>" + message + "</h1>");
        boolean found = clientService.deleteClient("Kep");
        if(found){
            out.println("Client deleted successfully ");
        }else{
            out.println("no client deleted");
        }
        out.println("</body></html>");
    }

    public void destroy() {
    }
}
