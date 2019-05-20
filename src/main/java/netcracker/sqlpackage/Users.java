package netcracker.sqlpackage;

import netcracker.data.User;
import netcracker.exception.WrongIDException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Users extends BaseTable implements TableOperations {
    public Users() throws SQLException {
        super("users");
    }

    @Override
    public void createTable() throws SQLException {
        super.executeSqlStatement("CREATE TABLE IF NOT EXISTS users(" +
                        "id INT AUTO_INCREMENT PRIMARY KEY," +
                        "name VARCHAR(30) NOT NULL," +
                        "department VARCHAR(16) NOT NULL," +
                        "phone VARCHAR(15) NOT NULL," +
                        "salary INTEGER NOT NULL)");
    }

    @Override
    public void addToTable(User user) throws SQLException {
        String name = user.getName();
        String department = user.getDepartment();
        String phone = user.getPhone();
        int salary = user.getSalary();
        String sql = String.format("INSERT INTO users (name, department, phone, salary) "
                + "VALUES ('%s', '%s', '%s', %d)", name, department, phone, salary);
        super.executeSqlStatement(sql, "Добавление сотрудника прошло успешно!");
    }

    @Override
    public void showTable() throws SQLException {
        reopenConnection();
        String sql = "Select * from users";
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        while (rs.next()) {
            int id = rs.getInt(1);
            String name = rs.getString(2);
            String department = rs.getString(3);
            String phone = rs.getString(4);
            int salary = rs.getInt(5);
            System.out.println(String.format("id: %2d ФИО: %-30s отдел: %-15s тел.: %-16s оклад: %5d",
                    id, name, department, phone, salary));
        }
        System.out.println("");
    }

    @Override
    public void changeTable(int id, User user) throws SQLException, WrongIDException {
        User checkUser = getUser(id);
        if (checkUser == null) throw new WrongIDException();
        String name = user.getName();
        String department = user.getDepartment();
        String phone = user.getPhone();
        int salary = user.getSalary();
        String sql = String.format("UPDATE users SET name='%s', department='%s', phone='%s', salary=%d "
                + "WHERE id=%d", name, department, phone, salary, id);
        super.executeSqlStatement(sql, "Изменение данных сотрудника прошло успешно!");

    }

    @Override
    public void deleteFromTable(int id) throws SQLException, WrongIDException {
        User checkUser = getUser(id);
        if (checkUser == null) throw new WrongIDException();
        String sql = "DELETE FROM users WHERE id=" + id;
        super.executeSqlStatement(sql, "Удаление сотрудника прошло успешно");
    }

    public User getUser(int id) throws SQLException{
        reopenConnection();
        String sql = "Select * from users WHERE id=" + id;
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        while (rs.next()) {
            int i = rs.getInt(1);
            String name = rs.getString(2);
            String department = rs.getString(3);
            String phone = rs.getString(4);
            int salary = rs.getInt(5);
            return new User(name, department, phone, salary);
        }
        return null;
    }
}
