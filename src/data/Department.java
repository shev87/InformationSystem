package data;

public class Department {
    private String name;
    private User user;

    public Department(String name, User user) {
        this.name = name;
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public User getUser() {
        return user;
    }
}
