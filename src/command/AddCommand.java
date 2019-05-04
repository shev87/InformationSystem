package command;

import data.DataSource;
import data.Department;
import data.User;
import exception.WrongActionException;
import helper.ConsoleHelper;

import java.io.*;
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

        list = DataSource.readListUsersFromFile();
        User doubleUser = null;
        for (User u : list){
            if (u.getName().equalsIgnoreCase(user.getName())) {
                ConsoleHelper.writeMessage("Такой сотрудник уже есть! Пож-та, добавьте к ФИО уникальную информацию");
                doubleUser = u;
                break;
            }
        }
        if (doubleUser == null) {
            list.add(user);
            DataSource.writeListUsersToFile(list);
            ConsoleHelper.writeMessage("Добавление сотрудника произошло успешно!");
        }
    }

    private void addDepartment() throws Exception{
        list = DataSource.readListUsersFromFile();
        listDepartments = DataSource.readListDepartmentsFromFile();
        ConsoleHelper.writeMessage("");
        ConsoleHelper.writeMessage("***Добавление отдела***");
        ConsoleHelper.writeMessage("Введите ФИО начальника:");
        String name = ConsoleHelper.readString();
        User boss = null;
        for (User u : list){
            if (u.getName().equalsIgnoreCase(name)) {
                boss = u;
                listDepartments.add(new Department(u));
                DataSource.writeListDepartmentsToFile(listDepartments);
                break;
            }
        }
        if (boss != null) ConsoleHelper.writeMessage("Добавление отдела произошло успешно!");
        else ConsoleHelper.writeMessage("Введённое ФИО не найдено");
    }
}
