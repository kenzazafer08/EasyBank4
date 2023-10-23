package com.example.easybank4.controllers;

import com.example.easybank4.dto.Agency;
import com.example.easybank4.dto.Client;
import com.example.easybank4.services.AgencyService;
import com.example.easybank4.services.ClientService;

import com.google.gson.JsonObject;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@WebServlet(urlPatterns = {"/simulation", "/simulationSearch"})
public class SimulationServlet extends HttpServlet {

    ClientService clientService;
    AgencyService agencyService;

    @Override
    public void init() throws ServletException {
        clientService = new ClientService();
        agencyService = new AgencyService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String choix = req.getServletPath();
        switch (choix){
            case "/simulation":
                simulation(req,resp);
                break;
            case "/simulationSearch":
                client(req,resp);
                break;
        }

    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
    public void client(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String clientIdParam = request.getParameter("code");
        JsonObject jsonObject = new JsonObject();
        if (clientIdParam != null && !clientIdParam.isEmpty()) {
            try {
                Optional<Client> client = clientService.getClientByCode(clientIdParam);
                if(client.isPresent()){
                    String clientName = client.get().getFirstName() + " " + client.get().getLastName();
                    jsonObject.addProperty("name", clientName);
                    jsonObject.addProperty("phone", client.get().getPhone());
                    jsonObject.addProperty("address", client.get().getAddress());
                } else {
                    jsonObject.addProperty("name", "");
                    jsonObject.addProperty("phone", "");
                    jsonObject.addProperty("address", "");
                }

            } catch (NumberFormatException e) {
                request.setAttribute("invalidClientId", true);
            }
        }
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(jsonObject.toString());
    }

    public void simulation(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Agency> agencies = agencyService.list();

        if (agencies.isEmpty()) {
            request.setAttribute("noAgencies", true);
        } else {
            request.setAttribute("agencies", agencies);
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/pages/simulation.jsp");
        dispatcher.forward(request, response);
    }


}
