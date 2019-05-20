package netcracker.sqlpackage;

import netcracker.data.Department;
import netcracker.data.User;
import netcracker.exception.WrongIDException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Departments extends BaseTable implements TableOperations {
    public Departments() throws SQLException {
        super("departments");
    }

    @Override
    public void createTable() throws SQLException {
        super.executeSqlStatement("CREATE TABLE IF NOT EXISTS departments(" +
                "id INT AUTO_INCREMENT PRIMARY KEY," +
                "department VARCHAR(16) NOT NULL," +
                "name VARCHAR(30) NOT NULL," +
                "phone VARCHAR(15) NOT NULL)");
    }

    @Override
    public void addToTable(User user) throws SQLException {
        String name = user.getName();
        String department = user.getDepartment();
        String phone = user.getPhone();
        String sql = String.format("INSERT INTO departments (department, name, phone) "
                + "VALUES ('%s', '%s', '%s')", department, name, phone);
        super.executeSqlStatement(sql, "Добавление отдела прошло успешно!");
    }

    @Override
    public void changeTable(int id, User user) throws SQLException, WrongIDException {
        Department dep = getDepartment(id);
        if (dep == null) throw new WrongIDException();
        String name = user.getName();
        String department = user.getDepartment();
        String phone = user.getPhone();
        String sql = String.format("UPDATE departments SET department='%s', name='%s', phone='%s'"
                + "WHERE id=%d", department, name, phone, id);
        super.executeSqlStatement(sql, "Изменение данных отдела прошло успешно!");
    }

    @Override
    public void deleteFromTable(int id) throws SQLException, WrongIDException {
        Department dep = getDepartment(id);
        if (dep == null) throw new WrongIDException();
        String sql = "DELETE FROM departments WHERE id=" + id;
        super.executeSqlStatement(sql, "Удаление отдела прошло успешно");
    }

    @Override
    public void showTable() throws SQLException {
        reopenConnection();
        String sql = "Select * from departments";
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        while (rs.next()) {
            int id = rs.getInt(1);
            String department = rs.getString(2);
            String name = rs.getString(3);
            String phone = rs.getString(4);
            System.out.println(String.format("id: %d Отдел: %-15s начальник: %-30s тел.: %-16s",
                    id, department, name, phone));
        }
        System.out.println("");
    }

    public Department getDepartment(int id) throws SQLException{
        reopenConnection();
        String sql = "Select * from departments WHERE id=" + id;
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        while (rs.next()) {
            int i = rs.getInt(1);
            String department = rs.getString(2);
            String name = rs.getString(3);
            String phone = rs.getString(4);
            return new Department(new User(name, department, phone, 0));
        }
        return null;
    }
}
