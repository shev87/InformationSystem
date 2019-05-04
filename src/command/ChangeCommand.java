package command;

import data.DataSource;
import data.Department;
import data.User;
import exception.WrongActionException;
import helper.ConsoleHelper;

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
        ConsoleHelper.writeMessage("Введите ФИО для изменения:");
        String name = ConsoleHelper.readString();

        list = DataSource.readListUsersFromFile();
        User changeUser = null;
        for (User u : list){
            if (u.getName().equalsIgnoreCase(name)) {
                changeUser = u;
                break;
            }
        }
        if (changeUser == null) throw new WrongActionException();
        else {
            list.remove(changeUser);
            ConsoleHelper.writeMessage("Введите отдел:");
            String department = ConsoleHelper.readString();
            ConsoleHelper.writeMessage("Введите телефон:");
            String phone = ConsoleHelper.readString();
            ConsoleHelper.writeMessage("Введите зарплату:");
            int salary = ConsoleHelper.readInt();
            User user = new User(name, department, phone, salary);
            list.add(user);
            DataSource.writeListUsersToFile(list);
            //изменённый сотрудник мб начальником
            listDepartment = DataSource.readListDepartmentsFromFile();
            Department changeDepart = null;
            for (Department d : listDepartment){
                if (d.getUser().getName().equalsIgnoreCase(user.getName())) {
                    changeDepart = d;
                    break;
                }
            }
            if (changeDepart != null){
                listDepartment.remove(changeDepart);
                listDepartment.add(new Department(user));
                DataSource.writeListDepartmentsToFile(listDepartment);
            }

            ConsoleHelper.writeMessage("Изменение данных прошло успешно!");
        }
    }

    private void changeDepartment() throws Exception{
        ConsoleHelper.writeMessage("");
        ConsoleHelper.writeMessage("***ИЗМЕНЕНИЕ начальника отдела***");
        ConsoleHelper.writeMessage("Введите ФИО нового начальника:");
        String name = ConsoleHelper.readString();
        User newBoss = null;
        list = DataSource.readListUsersFromFile();
        for (User u : list){
            if (u.getName().equalsIgnoreCase(name)){
                newBoss = u;
                break;
            }
        }
        if (newBoss == null) throw new WrongActionException();
        listDepartment = DataSource.readListDepartmentsFromFile();
        Department changeDepart = null;
        for (Department d : listDepartment){
            if (d.getUser().getDepartment().equalsIgnoreCase(newBoss.getDepartment())) {
                changeDepart = d;
                break;
            }
        }
        if (changeDepart == null) throw new WrongActionException();
        else {
            listDepartment.remove(changeDepart);
            listDepartment.add(new Department(newBoss));
            DataSource.writeListDepartmentsToFile(listDepartment);
            ConsoleHelper.writeMessage("Изменение данных прошло успешно!");
        }
    }
    }

