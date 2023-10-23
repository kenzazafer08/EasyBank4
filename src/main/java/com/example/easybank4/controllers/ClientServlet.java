package com.example.easybank4.controllers;

import com.example.easybank4.dto.Client;
import com.example.easybank4.dto.Person;
import com.example.easybank4.impl.ClientDAO;
import com.example.easybank4.services.ClientService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.IOException;
import java.util.List;
import java.util.Optional;


@WebServlet( urlPatterns = {"/clients", "/register", "/deleteClient", "/edit", "/client"})
public class ClientServlet extends HttpServlet {

    private static ClientService _clientService;

    @Override
    public void init() throws ServletException {
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        configuration.setProperty("hibernate.show_sql", "true");
        configuration.setProperty("hibernate.format_sql", "true");
        configuration.setProperty("hibernate.use_sql_comments", "true");
        SessionFactory _sessionFactory = configuration.buildSessionFactory();
        ClientDAO _clientDAO = new ClientDAO(_sessionFactory);
        _clientService = new ClientService(_clientDAO);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String choix = request.getServletPath();
        switch (choix){
            case "/clients" :
                clients(request,response);
                break;
            case "/client" :
                client(request,response);
                break;
            case "/register" :
                register(request,response);
                break;
            case "/deleteClient" :
                delete(request,response);
                break;
            case "/edit" :
                System.out.println("here");
                edit(request,response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }

    public void clients(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Person> clients = _clientService.findAll();

        if (clients.isEmpty()) {
            request.setAttribute("noClients", true);
        } else {
            request.setAttribute("clients", clients);
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/pages/client.jsp");
        dispatcher.forward(request, response);
    }

    public void client(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String clientIdParam = request.getParameter("code");

        if (clientIdParam != null && !clientIdParam.isEmpty()) {
            try {

                Optional<Person> client = _clientService.findById(clientIdParam);

                if (client.isPresent()) {
                    request.setAttribute("client", client.get());
                } else {
                    request.setAttribute("noClientFound", true);
                }
            } catch (NumberFormatException e) {
                request.setAttribute("invalidClientId", true);
            }
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/pages/client.jsp");
        dispatcher.forward(request, response);
    }


    public void register(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Client client = new Client();
        client.setFirstName(request.getParameter("firstName"));
        client.setLastName(request.getParameter("lastName"));
        client.setPhone(request.getParameter("phoneNumber"));
        client.setAddress(request.getParameter("address"));
        client.setDeleted(false);

        Optional<Person> success = _clientService.save(client);
        if (success.isPresent()) {
            try {
                System.out.println(success.get().getFirstName());
                response.sendRedirect(request.getContextPath() + "/clients");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            response.sendRedirect(request.getContextPath() + "/clients");
        }
    }

    public void edit(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
        Client client = new Client();
        client.setCode(request.getParameter("code"));
        client.setFirstName(request.getParameter("firstName"));
        client.setLastName(request.getParameter("lastName"));
        client.setPhone(request.getParameter("phone"));
        client.setAddress(request.getParameter("address"));
        client.setDeleted(false);

        if (client.getCode() != null && !client.getCode().isEmpty()) {
            try {

                Optional<Person> success = _clientService.update(client);

                if (success.isPresent()) {
                    response.sendRedirect(request.getContextPath() + "/clients");
                } else {
                    response.sendRedirect(request.getContextPath() + "/clients");
                }
            } catch (NumberFormatException e) {
                response.sendRedirect("error.jsp");
            }
        } else {
            response.sendRedirect("error.jsp");
        }
    }

    public void delete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String clientIdParam = request.getParameter("code");

        if (clientIdParam != null && !clientIdParam.isEmpty()) {
            try {
                boolean success = _clientService.delete(clientIdParam);

                if (success) {
                    response.sendRedirect(request.getContextPath() + "/clients?deleted=true");
                } else {
                    response.sendRedirect(request.getContextPath() + "/clients?deleted=false");
                }
            } catch (NumberFormatException e) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            }
        } else {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }
}