
package data;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect
public class User {
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
}
