package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
// Построитель соединения с базой данных
public class EmpConnBuilder implements ConnectionBuilder {
    public EmpConnBuilder() {
        try {
            // Настройка драйвера базы данных
            String driverClass = ConnectionProperty.getProperty("db.driver.class");
            if (driverClass == null || driverClass.isEmpty()) {
                throw new IllegalArgumentException("Driver class name is missing or empty in the configuration");
            }
            Class.forName(driverClass);
            System.out.println("Driver class loaded successfully: " + driverClass);
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
            throw new RuntimeException("Failed to load database driver", ex);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            throw e;
        }
    }

    // Получение соединения с базой данных
    @Override
    public Connection getConnection() throws SQLException {
        try {
            String url = ConnectionProperty.getProperty("db.url");
            String login = ConnectionProperty.getProperty("db.login");
            String password = ConnectionProperty.getProperty("db.password");
            return DriverManager.getConnection(url, login, password);
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new SQLException("Failed to get connection to the database", ex);
        }
    }
}
