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
import java.sql.Statement;
import java.util.ArrayList;
import domain.Developer;
import dao.EmpConnBuilder;

@WebServlet("/developers")
public class DeveloperServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    String select_all_developers = "SELECT id, name, rating FROM developers";
    String insert_developer = "INSERT INTO developers (name, rating) VALUES (?, ?)";
    ArrayList<Developer> developers = new ArrayList<>();

    public DeveloperServlet() {
        // Конструктор, если нужно дополнительное подключение
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        EmpConnBuilder builder = new EmpConnBuilder();

        // Загрузка всех разработчиков
        try (Connection conn = builder.getConnection()) {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(select_all_developers);

            if (rs != null) {
                developers.clear();
                while (rs.next()) {
                    long id = rs.getLong("id");
                    String name = rs.getString("name");
                    double rating = rs.getDouble("rating");
                    developers.add(new Developer(id, name, rating));
                }
                rs.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Передаем информацию о разработчиках на JSP
        request.setAttribute("developers", developers);

        // Перенаправляем на страницу с разработчиками
        String userPath = request.getServletPath();
        if ("/developers".equals(userPath)) {
            request.getRequestDispatcher("/jspf/developer.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String ratingStr = request.getParameter("rating");
        double rating = 0;

        // Проверка, что поля не пустые
        if (name == null || name.trim().isEmpty() || ratingStr == null || ratingStr.trim().isEmpty()) {
            // Если одно из полей пустое, добавляем атрибут с ошибкой
            request.setAttribute("error", "Все поля должны быть заполнены!");
            // Возвращаемся на ту же страницу (например, добавление разработчика)
            request.getRequestDispatcher("/jspf/developer.jsp").forward(request, response);
            return; // Завершаем выполнение метода
        }

        try {
            rating = Double.parseDouble(ratingStr);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            // Если рейтинг не является числом, добавляем атрибут с ошибкой
            request.setAttribute("error", "Рейтинг должен быть числовым значением!");
            request.getRequestDispatcher("/jspf/developer.jsp").forward(request, response);
            return;
        }

        EmpConnBuilder builder = new EmpConnBuilder();

        try (Connection conn = builder.getConnection()) {
            PreparedStatement preparedStatement = conn.prepareStatement(insert_developer);
            preparedStatement.setString(1, name);
            preparedStatement.setDouble(2, rating);

            int rows = preparedStatement.executeUpdate();
            if (rows > 0) {
                // Успешно добавлен разработчик, перенаправляем на страницу с разработчиками
                response.sendRedirect(request.getContextPath() + "/developers");
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Произошла ошибка при добавлении разработчика!");
            request.getRequestDispatcher("/jspf/developer.jsp").forward(request, response);
        }
    }


}
