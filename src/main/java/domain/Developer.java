package domain;

/**
* Класс данных о разработчиках
*/
public class Developer {
    // Идентификатор разработчика
    private Long id;
    // Название разработчика
    private String name;
    // Рейтинг разработчика
    private double rating;

    // Конструктор без параметров
    public Developer() {
    }

    // Конструктор с названием разработчика
    public Developer(String name) {
        this.name = name;
    }

    // Конструктор с идентификатором, названием и рейтингом разработчика
    public Developer(Long id, String name, double rating) {
        this.id = id;
        this.name = name;
        this.rating = rating;
    }

    // Геттер для id
    public Long getId() {
        return id;
    }

    // Сеттер для id
    public void setId(Long id) {
        this.id = id;
    }

    // Геттер для имени разработчика
    public String getName() {
        return name;
    }

    // Сеттер для имени разработчика
    public void setName(String name) {
        this.name = name;
    }

    // Геттер для рейтинга
    public double getRating() {
        return rating;
    }

    // Сеттер для рейтинга
    public void setRating(double rating) {
        this.rating = rating;
    }

    // Переопределение метода toString для отображения информации о разработчике
    @Override
    public String toString() {
        return "Developer {" +
               "Id = " + id +
               ", Name = '" + name + '\'' +
               ", Rating = " + rating +
               '}';
    }
}
