package command;


import data.User;
import helper.ConsoleHelper;

import java.io.FileWriter;
import java.io.StringWriter;

public class AddCommand implements Command{
    @Override
    public void execute() throws Exception {
        ConsoleHelper.writeMessage("");
        ConsoleHelper.writeMessage("Добавление пользователя");
        ConsoleHelper.writeMessage("Введите ФИО:");
        String name = ConsoleHelper.readString();
        ConsoleHelper.writeMessage("Введите отдел:");
        String department = ConsoleHelper.readString();
        ConsoleHelper.writeMessage("Введите телефон:");
        String phone = ConsoleHelper.readString();
        ConsoleHelper.writeMessage("Введите зарплату:");
        int salary = ConsoleHelper.readInt();
        User user = new User(name, department, phone, salary);
        FileWriter writer = new FileWriter("dataList");
        O
    }
}
