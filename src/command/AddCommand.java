package command;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SequenceWriter;
import data.DataSource;
import data.User;
import helper.ConsoleHelper;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class AddCommand implements Command{
    private List<User> list = new ArrayList<>();

    @Override
    public void execute() throws Exception {
        ConsoleHelper.writeMessage("");
        ConsoleHelper.writeMessage("***Добавление пользователя***");
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
        list.add(user);
        DataSource.writeListUsersToFile(list);
    }
}
