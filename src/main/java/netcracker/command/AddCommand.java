package netcracker.command;

import netcracker.Solution;
import netcracker.data.DataSource;
import netcracker.data.Department;
import netcracker.data.User;
import netcracker.exception.WrongActionException;
import netcracker.helper.ConsoleHelper;

import java.io.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AddCommand implements Command{
    private List<User> list = new ArrayList<>();
    private List<Department> listDepartments = new ArrayList<>();

    @Override
    public void execute() throws Exception {
        switch (DataSource.getMode()){
            case 1:
                addUser();
                break;
            case 2:
                addDepartment();
                break;
            default: throw new WrongActionException();
        }
    }

    private void addUser() throws IOException{
        ConsoleHelper.writeMessage("");
        ConsoleHelper.writeMessage("***Добавление сотрудника***");
        ConsoleHelper.writeMessage("Введите ФИО:");
        String name = ConsoleHelper.readString();
        ConsoleHelper.writeMessage("Введите отдел:");
        String department = ConsoleHelper.readString();
        ConsoleHelper.writeMessage("Введите телефон:");
        String phone = ConsoleHelper.readString();
        ConsoleHelper.writeMessage("Введите зарплату:");
        int salary = ConsoleHelper.readInt();
        User user = new User(name, department, phone, salary);

        try {
            Solution.getSolution().users.addToTable(user);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void addDepartment() throws Exception{
        list = DataSource.readListUsersFromFile();
        listDepartments = DataSource.readListDepartmentsFromFile();
        ConsoleHelper.writeMessage("");
        ConsoleHelper.writeMessage("***Добавление отдела***");
        ConsoleHelper.writeMessage("Введите id сотрудника, который будет начальком:");
        int id = ConsoleHelper.readInt();
        User user = Solution.getSolution().users.getUser(id);
        Solution.getSolution().departments.addToTable(user);

    }
}
