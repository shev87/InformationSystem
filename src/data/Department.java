package data;

import java.io.Serializable;

public class Department implements Serializable, Comparable<Department> {
    private static final long serialVersionUID = 6311262742523405623L;

    private User user;


    public Department(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    @Override
    public String toString() {
        /*return "отдел: " + user.getDepartment() +
                "\t начальник: " + user.getName() +
                "\t телефон: " + user.getPhone();*/
        return String.format("отдел: %-15s начальник: %-30s телефон: %-15s", user.getDepartment(), user.getName(), user.getPhone());
    }

    @Override
    public int compareTo(Department o) {
        return this.getUser().getDepartment().compareToIgnoreCase(o.getUser().getDepartment());
    }
}
