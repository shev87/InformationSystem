package command;

import data.DataSource;
import data.User;
import exception.WrongActionException;
import helper.ConsoleHelper;

import java.util.ArrayList;
import java.util.List;

public class ChangeCommand implements Command{
    private List<User> list = new ArrayList<>();

    @Override
    public void execute() throws Exception {
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
            ConsoleHelper.writeMessage("Изменение данных прошло успешно!");
        }
    }
    }

