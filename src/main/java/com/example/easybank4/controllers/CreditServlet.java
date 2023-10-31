package com.example.easybank4.controllers;

import com.example.easybank4.dto.*;
import com.example.easybank4.helpers.helper;
import com.example.easybank4.services.ClientService;
import com.example.easybank4.services.CreditService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@WebServlet( urlPatterns = {"/credits"  ,"/credit" , "/registerCredit"})
public class CreditServlet extends HttpServlet {

    CreditService creditService;

    @Override
    public void init() throws ServletException {
        creditService = new CreditService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String choix = request.getServletPath();
        switch (choix){
            case "/credits" :
                credits(request,response);
                break;
            case "/credit" :
                credit(request,response);
                break;
            case "/registerCredit" :
                register(request,response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }

    public void credits(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Credit> credits = creditService.ShowList();

        if (credits.isEmpty()) {
            request.setAttribute("noCredits", true);
        } else {
            request.setAttribute("credits", credits);
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/pages/credit.jsp");
        dispatcher.forward(request, response);
    }

    public void credit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String clientIdParam = request.getParameter("code");

        if (clientIdParam != null && !clientIdParam.isEmpty()) {
            try {

                Optional<Credit> credit = creditService.SearchByCode(clientIdParam);

                if (credit.isPresent()) {
                    request.setAttribute("credit", credit.get());
                } else {
                    request.setAttribute("noCreditFound", true);
                }
            } catch (NumberFormatException e) {
                request.setAttribute("invalidCreditId", true);
            }
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/pages/credit.jsp");
        dispatcher.forward(request, response);
    }


    public void register(HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println("here");
        Employee e = new Employee();
        e.setNumber(request.getParameter("employeeNumber"));
        Client c = new Client();
        c.setCode(request.getParameter("clientCode"));
        Agency a = new Agency();
        a.setCode(request.getParameter("agencyCode"));
        Credit credit = new Credit();
        credit.setCode(helper.generate(5));
        credit.setAmount(Double.parseDouble(request.getParameter("amount")));
        credit.setDuration(Integer.parseInt(request.getParameter("duration")));
        credit.setDescription(request.getParameter("description"));
        credit.setStatus(CreditStatus.pending);
        credit.setAgency(a);
        credit.setClient(c);
        credit.setEmployee(e);
        credit.setMonthly(creditService.Simulation(credit));
        Optional<Credit> success = creditService.add(credit);

        if (success.isPresent()) {
            try {
                response.sendRedirect(request.getContextPath() + "/simulation");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        } else {
            response.sendRedirect(request.getContextPath() + "/simulation");
        }
    }

}
