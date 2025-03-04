package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import domain.Game;
import dao.EmpConnBuilder;

@WebServlet("/games")
public class GameServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    String select_all_games = "SELECT id, title, release_year, genre, system_requirements FROM games;";
    ArrayList<Game> games = new ArrayList<>();

    public GameServlet() {
        // Конструктор, если нужно дополнительное подключение
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        EmpConnBuilder builder = new EmpConnBuilder();

        // Загрузка всех игр
        try (Connection conn = builder.getConnection()) {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(select_all_games);

            if (rs != null) {
                games.clear();
                while (rs.next()) {
                    long id = rs.getLong("id");
                    String title = rs.getString("title");
                    int release_year = rs.getInt("release_year");
                    String genre = rs.getString("genre");
                    String system_requirements = rs.getString("system_requirements");
                    games.add(new Game(id, title, release_year, genre, system_requirements));
                }
                rs.close();
            } else {
               System.out.println("ResultSet is null.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Передаем информацию о играх на JSP
        request.setAttribute("games", games);

        // Перенаправляем на страницу с играми
        String userPath = request.getServletPath();
        if ("/games".equals(userPath)) {
            request.getRequestDispatcher("/jspf/game.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
