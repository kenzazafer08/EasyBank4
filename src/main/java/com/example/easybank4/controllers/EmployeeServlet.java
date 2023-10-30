package com.example.easybank4.controllers;

import com.example.easybank4.dto.Client;
import com.example.easybank4.dto.Employee;
import com.example.easybank4.services.ClientService;
import com.example.easybank4.services.EmployeeService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;
import java.util.Optional;


@WebServlet( urlPatterns = {"/employees"  ,"/registerEmployee","/deleteEmployee" ,  "/editEmployee", "/Employee"})
public class EmployeeServlet extends HttpServlet {

    EmployeeService employeeService;
    @Override
    public void init() throws ServletException {
        employeeService = new EmployeeService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String choix = request.getServletPath();
        switch (choix){
            case "/employees" :
                employees(request,response);
                break;
            case "/Employee" :
                Employee(request,response);
                break;
            case "/registerEmployee" :
                register(request,response);
                break;
            case "/deleteEmployee" :
                delete(request,response);
                break;
            case "/editEmployee" :
                System.out.println("here");
                edit(request,response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }

    public void employees(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Employee> employees = employeeService.getEmployeeList();

        if (employees.isEmpty()) {
            request.setAttribute("noEmployees", true);
        } else {
            request.setAttribute("employees", employees);
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/pages/employee.jsp");
        dispatcher.forward(request, response);
    }

    public void Employee(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String clientIdParam = request.getParameter("code");

        if (clientIdParam != null && !clientIdParam.isEmpty()) {
            try {

                Optional<Employee> employee = employeeService.getEmployeeByCode(clientIdParam);

                if (employee.isPresent()) {
                    request.setAttribute("employee", employee.get());
                } else {
                    request.setAttribute("noEmployeeFound", true);
                }
            } catch (NumberFormatException e) {
                request.setAttribute("invalidEmployeeId", true);
            }
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/pages/employee.jsp");
        dispatcher.forward(request, response);
    }


    public void register(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Employee employee = new Employee();
        employee.setFirstName(request.getParameter("firstName"));
        employee.setLastName(request.getParameter("lastName"));
        employee.setPhone(request.getParameter("phoneNumber"));
        employee.setAddress(request.getParameter("address"));
        employee.setDeleted(false);

        Optional<Employee> success = employeeService.addEmployee(employee);
        if (success.isPresent()) {
            try {
                System.out.println(success.get().getFirstName());
                response.sendRedirect(request.getContextPath() + "/employees");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            response.sendRedirect(request.getContextPath() + "/employees");
        }
    }

    public void edit(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
        Employee employee = new Employee();
        employee.setNumber(request.getParameter("code"));
        employee.setFirstName(request.getParameter("firstName"));
        employee.setLastName(request.getParameter("lastName"));
        employee.setPhone(request.getParameter("phoneNumber"));
        employee.setAddress(request.getParameter("address"));
        employee.setDeleted(false);

        if (employee.getNumber() != null && !employee.getNumber().isEmpty()) {
            try {

                Optional<Employee> success = employeeService.updateEmployee(employee);

                if (success.isPresent()) {
                    response.sendRedirect(request.getContextPath() + "/employees");
                } else {
                    response.sendRedirect(request.getContextPath() + "/employees");
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
                boolean success = employeeService.deleteEmployee(clientIdParam);

                if (success) {
                    response.sendRedirect(request.getContextPath() + "/employees?deleted=true");
                } else {
                    response.sendRedirect(request.getContextPath() + "/employees?deleted=false");
                }
            } catch (NumberFormatException e) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            }
        } else {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }
}