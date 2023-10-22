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
        Client client = new Client();
        client.setFirstName("kniza");
        client.setLastName("jaafar");
        client.setPhone("0634047964");
        client.setAddress("70 RUE ELOUMAM QUA HOPITAL SAFI");
        Optional<Client> created = clientService.addClient(client);
        if(created.isPresent()){
            out.println("Client created successfully :"+created.get().getCode());
        }else{
            out.println("no client created");
        }

        out.println("</body></html>");
    }

    public void destroy() {
    }
}
