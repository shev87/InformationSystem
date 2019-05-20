
package netcracker.data;

import java.io.Serializable;


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
        return String.format("ФИО: %-30s отдел: %-15s тел.: %-16s оклад: %5d", name, department, phone, salary);
    }

    @Override
    public int compareTo(User o) {
        return this.name.compareToIgnoreCase(o.name);
    }
}
