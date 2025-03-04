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

@WebServlet("/editdeveloper")
public class EditDeveloperServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    String select_developer_by_id = "SELECT id, name, rating FROM developers WHERE id = ?;";
    String update_developer = "UPDATE developers SET name = ?, rating = ? WHERE id = ?;";

    public EditDeveloperServlet() {
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
                request.setAttribute("developerEdit", developer); 
            } else {
                request.setAttribute("error", "Разработчик не найден!");
            }
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Произошла ошибка при загрузке данных разработчика!");
        }

        // Перенаправляем на страницу редактирования
        request.getRequestDispatcher("/jspf/editdeveloper.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EmpConnBuilder builder = new EmpConnBuilder();

        // Получаем параметры из формы
        String name = request.getParameter("name");
        String ratingStr = request.getParameter("rating");
        String developerId = request.getParameter("id");

        // Проверка на пустые поля
        if (name == null || name.trim().isEmpty() || ratingStr == null || ratingStr.trim().isEmpty()) {
            request.setAttribute("error", "Все поля должны быть заполнены!");
            request.getRequestDispatcher("/jspf/editdeveloper.jsp").forward(request, response);  // Ожидаем отобразить на той же странице
            return;
        }

        // Проверка на корректность рейтинга
        double rating = 0;
        try {
            rating = Double.parseDouble(ratingStr);
        } catch (NumberFormatException e) {
            // Ошибка преобразования в число
            request.setAttribute("error", "Рейтинг должен быть числовым значением!");
            request.getRequestDispatcher("/jspf/editdeveloper.jsp").forward(request, response);
            return;
        }

        // Обработка редактирования разработчика
        try (Connection conn = builder.getConnection()) {
            PreparedStatement preparedStatement = conn.prepareStatement(update_developer);
            preparedStatement.setString(1, name);
            preparedStatement.setDouble(2, rating);
            preparedStatement.setLong(3, Long.parseLong(developerId));

            int rows = preparedStatement.executeUpdate();

            // Выводим результат выполнения
            if (rows > 0) {
                System.out.println("Разработчик обновлен успешно!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Произошла ошибка при обновлении разработчика!");
            request.getRequestDispatcher("/jspf/editdeveloper.jsp").forward(request, response);
            return;
        }

        // После обновления перенаправляем обратно на страницу с разработчиками
        response.sendRedirect(request.getContextPath() + "/developers");
    }
}
