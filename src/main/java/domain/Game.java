package domain;

/**
* Класс для данных о компьютерной игре
*/
public class Game {
    // Идентификатор игры
    private Long id;

    // Название игры
    private String title;

    // Год выпуска
    private int releaseYear;

    // Жанр игры
    private String genre;

    // Системные требования игры
    private String systemRequirements;

    // Конструктор без параметров
    public Game() {
    }

    // Конструктор с названием игры, годом выпуска, жанром и системными требованиями
    public Game(String title, int releaseYear, String genre, String systemRequirements) {
        this.title = title;
        this.releaseYear = releaseYear;
        this.genre = genre;
        this.systemRequirements = systemRequirements;
    }

    // Конструктор с id, названием игры, годом выпуска, жанром и системными требованиями
    public Game(Long id, String title, int releaseYear, String genre, String systemRequirements) {
        this.id = id;
        this.title = title;
        this.releaseYear = releaseYear;
        this.genre = genre;
        this.systemRequirements = systemRequirements;
    }

    // Геттеры и сеттеры для всех полей
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getSystemRequirements() {
        return systemRequirements;
    }

    public void setSystemRequirements(String systemRequirements) {
        this.systemRequirements = systemRequirements;
    }

    // Переопределение метода toString для вывода информации о игре
    @Override
    public String toString() {
        return "Game {" +
               "Id = " + id +
               ", Title = '" + title + '\'' +
               ", ReleaseYear = " + releaseYear +
               ", Genre = '" + genre + '\'' +
               ", SystemRequirements = '" + systemRequirements + '\'' +
               '}';
    }
}
