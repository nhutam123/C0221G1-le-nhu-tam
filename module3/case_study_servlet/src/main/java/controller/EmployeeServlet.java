package controller;

import model.bean.*;
import model.service.DegreeService;
import model.service.DepartmentSevice;
import model.service.EmployeeService;
import model.service.PositionService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "EmployeeServlet" ,urlPatterns = {"/employee"})
public class EmployeeServlet extends HttpServlet {
    EmployeeService service=new EmployeeService();
    DepartmentSevice departmentSevice=new DepartmentSevice();
    PositionService positionService=new PositionService();
    DegreeService degreeService=new DegreeService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "create":
                insertEmployee(request, response);
                break;
            case "edit":
                    updateEmployee(request, response);
                break;
            case "search":
                    searchUser(request,response);
                    break;
        }

    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }

        switch (action) {
            case "create":
                showNewForm(request, response);
                break;
            case "edit":
                showEditForm(request, response);
                break;
            case "delete":
                delete(request, response);
                break;
            case "sort":
//                sort(request,response);
                break;
            default:
                listEmployee(request, response);
                break;
        }

    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response) {
        List<Department> list=departmentSevice.selectAll();
        List<Position> positionList=positionService.selectAll();
        List<Degree> degreeList=degreeService.selectAll();
        request.setAttribute("listDepartment",list);
        request.setAttribute("listPosition",positionList);
        request.setAttribute("listDegree",degreeList);
        RequestDispatcher dispatcher=request.getRequestDispatcher("furama/employee/create.jsp");
        try {
            dispatcher.forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void searchUser(HttpServletRequest request, HttpServletResponse response) {
        String name=request.getParameter("name");
        List<Employee> employees= service.search(name);
        RequestDispatcher dispatcher ;
        if (employees==null){
            dispatcher=request.getRequestDispatcher("furama/error.jsp");
            try {
                dispatcher.forward(request,response);
            } catch (ServletException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else {
            request.setAttribute("employees" ,employees);
            try {
                dispatcher=request.getRequestDispatcher("furama/employee/search.jsp");
                dispatcher.forward(request,response);
            } catch (ServletException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }


    private void updateEmployee(HttpServletRequest request, HttpServletResponse response) {
        int id=Integer.parseInt(request.getParameter("id"));
        String name=request.getParameter("name");
        Degree degree=service.selectDegree(Integer.parseInt(request.getParameter("degree")));
        Department department=service.selectDepartment(Integer.parseInt(request.getParameter("department")));
        Position position=service.selectPositon(Integer.parseInt(request.getParameter("position")));

        String birthday=request.getParameter("birthday");
        String card =request.getParameter("card");
        String phoneNumber=request.getParameter("phoneNumber");
        String email=request.getParameter("email");
        String address=request.getParameter("address");
        Employee employee=new Employee(id,name,degree,department,position,birthday,card,phoneNumber,email,address);
        service.update(employee);
        Employee employee1=service.select(id);
        List<Degree> degrees=degreeService.selectAll();
        List<Department> departments=departmentSevice.selectAll();
        List<Position> positions=positionService.selectAll();

        request.setAttribute("employee",employee1);
        request.setAttribute("listPosition",positions);
        request.setAttribute("listDegree",degrees);
        request.setAttribute("listDepartment",departments);
        RequestDispatcher dispatcher=request.getRequestDispatcher("furama/employee/edit.jsp");
        try {
            dispatcher.forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));
        Employee employee = service.select(id);
        List<Department> list=departmentSevice.selectAll();
        List<Position> positionList=positionService.selectAll();
        List<Degree> degreeList=degreeService.selectAll();

        request.setAttribute("employee", employee);
        request.setAttribute("listDepartment",list);
        request.setAttribute("listPosition",positionList);
        request.setAttribute("listDegree",degreeList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("furama/employee/edit.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void delete(HttpServletRequest request, HttpServletResponse response) {
        int id=Integer.parseInt(request.getParameter("id"));
        service.delete(id);
        List<Employee> list=service.selectAll();
        request.setAttribute("listEmployee",list);
        RequestDispatcher dispatcher=request.getRequestDispatcher("furama/employee/list.jsp");
        try {
            dispatcher.forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void insertEmployee(HttpServletRequest request, HttpServletResponse response) {
        String name=request.getParameter("name");
        Degree degree=service.selectDegree(Integer.parseInt(request.getParameter("degree")));
        Department department=service.selectDepartment(Integer.parseInt(request.getParameter("department")));
        Position position=service.selectPositon(Integer.parseInt(request.getParameter("position")));
        String birthday=request.getParameter("birthday");
        String card =request.getParameter("card");
        String phoneNumber=request.getParameter("phoneNumber");
        String email=request.getParameter("email");
        String address=request.getParameter("address");
        Employee employee=new Employee(name,degree,department,position,birthday,card,phoneNumber,email,address);
        try {
            service.insert(employee);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("furama/employee/create.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    private void listEmployee(HttpServletRequest request, HttpServletResponse response) {
        List<Employee> list= service.selectAll();
        request.setAttribute("listEmployee" , list);
        RequestDispatcher dispatcher=request.getRequestDispatcher("/furama/employee/list.jsp");
        try {
            dispatcher.forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
