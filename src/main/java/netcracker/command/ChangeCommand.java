package netcracker.command;

import netcracker.Solution;
import netcracker.data.DataSource;
import netcracker.data.Department;
import netcracker.data.User;
import netcracker.exception.WrongActionException;
import netcracker.helper.ConsoleHelper;

import java.util.ArrayList;
import java.util.List;

public class ChangeCommand implements Command{
    private List<User> list = new ArrayList<>();
    private List<Department> listDepartment = new ArrayList<>();

    @Override
    public void execute() throws Exception {
        switch (DataSource.getMode()){
            case 1:
                changeUser();
                break;
            case 2:
                changeDepartment();
                break;
            default: throw new WrongActionException();
        }
    }

    private void changeUser() throws Exception{
        ConsoleHelper.writeMessage("");
        ConsoleHelper.writeMessage("***ИЗМЕНЕНИЕ данных пользователя***");
        ConsoleHelper.writeMessage("Введите id для изменения:");
        int id = ConsoleHelper.readInt();
        ConsoleHelper.writeMessage("Введите ФИО:");
        String name = ConsoleHelper.readString();
        ConsoleHelper.writeMessage("Введите отдел:");
        String department = ConsoleHelper.readString();
        ConsoleHelper.writeMessage("Введите телефон:");
        String phone = ConsoleHelper.readString();
        ConsoleHelper.writeMessage("Введите зарплату:");
        int salary = ConsoleHelper.readInt();
        User user = new User(name, department, phone, salary);
        Solution.getSolution().users.changeTable(id, user);


    }

    private void changeDepartment() throws Exception{
        ConsoleHelper.writeMessage("");
        ConsoleHelper.writeMessage("***ИЗМЕНЕНИЕ начальника отдела***");
        ConsoleHelper.writeMessage("Введите id отдела, который надо изменить:");
        int idDep = ConsoleHelper.readInt();
        ConsoleHelper.writeMessage("Введите id нового начальника из списка сотрудников:");
        int id = ConsoleHelper.readInt();
        User user = Solution.getSolution().users.getUser(id);
        Solution.getSolution().departments.changeTable(idDep, user);

    }
    }

