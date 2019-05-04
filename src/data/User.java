
package data;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import java.io.Serializable;

@JsonAutoDetect
public class User implements Serializable, Comparable<User> {
    private static final long serialVersionUID = 6405239208607886158L;

    private String name;
    private String department;
    private String phone;
    private int salary;

    public User(String name, String department, String phone, int salary) {
        this.name = name;
        this.department = department;
        this.phone = phone;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public String getDepartment() {
        return department;
    }

    public String getPhone() {
        return phone;
    }

    public int getSalary() {
        return salary;
    }

    @Override
    public String toString() {
        return "ФИО: " + name +
                "\t отдел: " + department +
                "\t тел.: " + phone +
                "\t оклад: " + salary;
    }

    @Override
    public int compareTo(User o) {
        return this.name.compareToIgnoreCase(o.name);
    }
}
