package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import domain.Developer;
import dao.EmpConnBuilder;

@WebServlet("/deletedeveloper")
public class DeleteDeveloperServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    String select_developer_by_id = "SELECT id, name, rating FROM developers WHERE id = ?;";
    String delete_developer_by_id = "DELETE FROM developers WHERE id = ?;";

    public DeleteDeveloperServlet() {
        // Конструктор, если нужно дополнительное подключение
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        EmpConnBuilder builder = new EmpConnBuilder();
        
        // Получаем id разработчика из параметров запроса
        String developerId = request.getParameter("id");
        if (developerId == null || developerId.trim().isEmpty()) {
            response.sendRedirect(request.getContextPath() + "/developers");
            return;
        }

        try (Connection conn = builder.getConnection()) {
            // Загружаем данные разработчика по id
            PreparedStatement stmt = conn.prepareStatement(select_developer_by_id);
            stmt.setLong(1, Long.parseLong(developerId));
            ResultSet rs = stmt.executeQuery();

            if (rs != null && rs.next()) {
                // Извлекаем данные из базы
                long id = rs.getLong("id");
                String name = rs.getString("name");
                double rating = rs.getDouble("rating");

                // Создаем объект Developer и передаем на JSP
                Developer developer = new Developer(id, name, rating);
                request.setAttribute("developerDelete", developer); 
            } else {
                request.setAttribute("error", "Разработчик не найден!");
            }
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Произошла ошибка при загрузке данных разработчика!");
        }

        // Перенаправляем на страницу удаления
        request.getRequestDispatcher("/jspf/deletedeveloper.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EmpConnBuilder builder = new EmpConnBuilder();

        // Получаем id разработчика
        String developerId = request.getParameter("id");
        if (developerId == null || developerId.trim().isEmpty()) {
            response.sendRedirect(request.getContextPath() + "/developers");
            return;
        }

        // Удаляем разработчика из базы
        try (Connection conn = builder.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(delete_developer_by_id);
            stmt.setLong(1, Long.parseLong(developerId));
            int rows = stmt.executeUpdate();

            if (rows > 0) {
                System.out.println("Разработчик успешно удален!");
            } else {
                request.setAttribute("error", "Разработчик не найден!");
                request.getRequestDispatcher("/jspf/deletedeveloper.jsp").forward(request, response);
                return;
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Произошла ошибка при удалении разработчика!");
            request.getRequestDispatcher("/jspf/deletedeveloper.jsp").forward(request, response);
            return;
        }

        // После удаления перенаправляем обратно на страницу с разработчиками
        response.sendRedirect(request.getContextPath() + "/developers");
    }
}
