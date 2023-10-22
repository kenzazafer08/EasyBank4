package com.example.easybank4.controllers;

import com.example.easybank4.dto.Agency;
import com.example.easybank4.dto.Client;
import com.example.easybank4.services.AgencyService;
import com.example.easybank4.services.ClientService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;
import java.util.Optional;


@WebServlet( urlPatterns = {"/agencies" ,  "/registerAgency","/deleteAgency" , "/editAgency", "/Agency"})
public class AgencyServlet extends HttpServlet {

    AgencyService agencyService;
    @Override
    public void init() throws ServletException {
        agencyService = new AgencyService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String choix = request.getServletPath();
        switch (choix){
            case "/agencies" :
                agencies(request,response);
                break;
            case "/Agency" :
                agency(request,response);
                break;
            case "/registerAgency" :
                register(request,response);
                break;
            case "/deleteAgency" :
                delete(request,response);
                break;
            case "/editAgency" :
                edit(request,response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }

    public void agencies(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Agency> agencies = agencyService.list();

        if (agencies.isEmpty()) {
            request.setAttribute("noAgencies", true);
        } else {
            request.setAttribute("agencies", agencies);
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/pages/agency.jsp");
        dispatcher.forward(request, response);
    }

    public void agency(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String agencyIdParam = request.getParameter("code");

        if (agencyIdParam != null && !agencyIdParam.isEmpty()) {
            try {

                Optional<Agency> agency = agencyService.findAgency(agencyIdParam);

                if (agency.isPresent()) {
                    request.setAttribute("agency", agency.get());
                } else {
                    request.setAttribute("noAgencyFound", true);
                }
            } catch (NumberFormatException e) {
                request.setAttribute("invalidAgencyId", true);
            }
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/pages/agency.jsp");
        dispatcher.forward(request, response);
    }


    public void register(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Agency agency = new Agency();
        agency.setName(request.getParameter("name"));
        agency.setAddress(request.getParameter("address"));
        agency.setPhone(request.getParameter("phone"));
        agency.setDeleted(false);

        Optional<Agency> success = agencyService.AddAgency(agency);
        if (success.isPresent()) {
            try {
                response.sendRedirect(request.getContextPath() + "/agencies");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            response.sendRedirect(request.getContextPath() + "/agencies");
        }
    }

    public void edit(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
        Agency agency = new Agency();
        agency.setCode(request.getParameter("code"));
        agency.setName(request.getParameter("name"));
        agency.setAddress(request.getParameter("address"));
        agency.setPhone(request.getParameter("phone"));
        agency.setDeleted(false);

        if (agency.getCode() !=null && !agency.getCode().isEmpty()) {
            try {

                Optional<Agency> success = agencyService.update(agency);

                if (success.isPresent()) {
                    response.sendRedirect(request.getContextPath() + "/agencies");
                } else {
                    response.sendRedirect(request.getContextPath() + "/agencies");
                }
            } catch (NumberFormatException e) {
                response.sendRedirect("error.jsp");
            }
        } else {
            response.sendRedirect("error.jsp");
        }
    }

    public void delete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String agencyIdParam = request.getParameter("code");

        if (agencyIdParam != null && !agencyIdParam.isEmpty()) {
            try {
                boolean success = agencyService.deleteAgency(agencyIdParam);

                if (success) {
                    response.sendRedirect(request.getContextPath() + "/agencies");
                } else {
                    response.sendRedirect(request.getContextPath() + "/agencies");
                }
            } catch (NumberFormatException e) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            }
        } else {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }
}