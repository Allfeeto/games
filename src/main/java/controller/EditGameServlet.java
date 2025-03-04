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
import domain.Game;
import dao.EmpConnBuilder;

@WebServlet("/editgame")
public class EditGameServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    String select_game_by_id = "SELECT id, title, release_year, genre, system_requirements FROM games WHERE id = ?;";
    String update_game = "UPDATE games SET title = ?, release_year = ?, genre = ?, system_requirements = ? WHERE id = ?;";

    public EditGameServlet() {
        // Конструктор, если нужно дополнительное подключение
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        EmpConnBuilder builder = new EmpConnBuilder();
        
        // Получаем id игры из параметров запроса
        String gameId = request.getParameter("id");
        if (gameId == null || gameId.trim().isEmpty()) {
            response.sendRedirect(request.getContextPath() + "/games");
            return;
        }

        try (Connection conn = builder.getConnection()) {
            // Загружаем данные игры по id
            PreparedStatement stmt = conn.prepareStatement(select_game_by_id);
            stmt.setLong(1, Long.parseLong(gameId));
            ResultSet rs = stmt.executeQuery();

            if (rs != null && rs.next()) {
                // Извлекаем данные из базы
                long id = rs.getLong("id");
                String title = rs.getString("title");
                int releaseYear = rs.getInt("release_year");
                String genre = rs.getString("genre");
                String systemRequirements = rs.getString("system_requirements");

                // Создаем объект Game и передаем на JSP
                Game game = new Game(id, title, releaseYear, genre, systemRequirements);
                request.setAttribute("gameEdit", game); 
            } else {
                request.setAttribute("error", "Игра не найдена!");
            }
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Произошла ошибка при загрузке данных игры!");
        }

        // Перенаправляем на страницу редактирования
        request.getRequestDispatcher("/jspf/editgame.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EmpConnBuilder builder = new EmpConnBuilder();

        // Получаем параметры из формы
        String title = request.getParameter("title");
        String releaseYear = request.getParameter("releaseYear");
        String genre = request.getParameter("genre");
        String systemRequirements = request.getParameter("systemRequirements");
        String gameId = request.getParameter("id");

        // Проверка на пустые поля
        if (title == null || title.trim().isEmpty() || releaseYear == null || releaseYear.trim().isEmpty() ||
            genre == null || genre.trim().isEmpty() || systemRequirements == null || systemRequirements.trim().isEmpty()) {
            // Если одно из полей пустое, добавляем атрибут с ошибкой
            request.setAttribute("error", "Все поля должны быть заполнены!");
            request.getRequestDispatcher("/jspf/editgame.jsp").forward(request, response);  // Ожидаем отобразить на той же странице
            return;
        }

        // Проверка на корректность года выпуска
        int releaseYearInt = 0;
        try {
            releaseYearInt = Integer.parseInt(releaseYear);
        } catch (NumberFormatException e) {
            // Ошибка преобразования в число
            request.setAttribute("error", "Год выпуска должен быть числовым значением!");
            request.getRequestDispatcher("/jspf/editgame.jsp").forward(request, response);
            return;
        }

        // Обработка редактирования игры
        try (Connection conn = builder.getConnection()) {
            PreparedStatement preparedStatement = conn.prepareStatement(update_game);
            preparedStatement.setString(1, title);
            preparedStatement.setInt(2, releaseYearInt);
            preparedStatement.setString(3, genre);
            preparedStatement.setString(4, systemRequirements);
            preparedStatement.setLong(5, Long.parseLong(gameId));

            int rows = preparedStatement.executeUpdate();

            // Выводим результат выполнения
            if (rows > 0) {
                System.out.println("Игра обновлена успешно!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Произошла ошибка при обновлении игры!");
            request.getRequestDispatcher("/jspf/editgame.jsp").forward(request, response);
            return;
        }

        // После обновления перенаправляем обратно на страницу с играми
        response.sendRedirect(request.getContextPath() + "/games");
    }
}
