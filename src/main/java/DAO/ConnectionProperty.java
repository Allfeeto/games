package dao;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


public class ConnectionProperty {

    // Все настройки конфигурации задаются прямо в коде
    public static final String DB_DRIVER_CLASS = "org.postgresql.Driver";
    public static final String DB_URL = "jdbc:postgresql://localhost:5432/games";
    public static final String DB_LOGIN = "postgres";
    public static final String DB_PASSWORD = "123456";

    // Метод для получения настроек
    public static String getProperty(String property) {
        switch (property) {
            case "db.driver.class":
                return DB_DRIVER_CLASS;
            case "db.url":
                return DB_URL;
            case "db.login":
                return DB_LOGIN;
            case "db.password":
                return DB_PASSWORD;
            default:
                throw new IllegalArgumentException("Unknown property: " + property);
        }
    }

    // Пример использования, чтобы вывести настройки
    public static void printConfig() {
        System.out.println("DB Driver Class: " + DB_DRIVER_CLASS);
        System.out.println("DB URL: " + DB_URL);
        System.out.println("DB Login: " + DB_LOGIN);
        System.out.println("DB Password: " + DB_PASSWORD);
    }
}
