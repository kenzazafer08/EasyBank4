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
        client.setFirstName("kenza");
        client.setLastName("zafer");
        client.setAddress("youcode");
        client.setPhone("911");
        client.setDeleted(false);
        client.setCode("Kep");
        Optional<Client> updated = clientService.updateClient(client);
        if(updated.isPresent()){
            out.println("client updated successfully " + updated.get().getLastName());
        }else{
            out.println("No client updated");
        }
        out.println("</body></html>");
    }

    public void destroy() {
    }
}
