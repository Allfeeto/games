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

@WebServlet("/deletegame")
public class DeleteGameServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    String select_game_by_id = "SELECT id, title, release_year, genre, system_requirements FROM games WHERE id = ?;";
    String delete_game_by_id = "DELETE FROM games WHERE id = ?;";

    public DeleteGameServlet() {
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
                request.setAttribute("gameDelete", game); 
            } else {
                request.setAttribute("error", "Игра не найдена!");
            }
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Произошла ошибка при загрузке данных игры!");
        }

        // Перенаправляем на страницу удаления
        request.getRequestDispatcher("/jspf/deletegame.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EmpConnBuilder builder = new EmpConnBuilder();

        // Получаем id игры
        String gameId = request.getParameter("id");
        if (gameId == null || gameId.trim().isEmpty()) {
            response.sendRedirect(request.getContextPath() + "/games");
            return;
        }

        // Удаляем игру из базы
        try (Connection conn = builder.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(delete_game_by_id);
            stmt.setLong(1, Long.parseLong(gameId));
            int rows = stmt.executeUpdate();

            if (rows > 0) {
                System.out.println("Игра успешно удалена!");
            } else {
                request.setAttribute("error", "Игра не найдена!");
                request.getRequestDispatcher("/jspf/deletegame.jsp").forward(request, response);
                return;
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Произошла ошибка при удалении игры!");
            request.getRequestDispatcher("/jspf/deletegame.jsp").forward(request, response);
            return;
        }

        // После удаления перенаправляем обратно на страницу со списком игр
        response.sendRedirect(request.getContextPath() + "/games");
    }
}
