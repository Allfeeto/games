package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import domain.Developer;

@WebServlet("/developers")
public class DeveloperServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //ArrayList<Developer> authors = DeveloperDB.select();
        //request.setAttribute("developers", developers);

        getServletContext().getRequestDispatcher("/jspf/developer.jsp").forward(request, response);
    }


}