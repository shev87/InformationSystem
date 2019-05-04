package command;

import data.DataSource;
import data.User;
import exception.WrongActionException;
import helper.ConsoleHelper;

import java.util.ArrayList;
import java.util.List;

public class DeleteCommand implements Command{
    private List<User> list = new ArrayList<>();
    @Override
    public void execute() throws Exception {
        ConsoleHelper.writeMessage("");
        ConsoleHelper.writeMessage("***УДАЛЕНИЕ пользователя***");
        ConsoleHelper.writeMessage("Введите ФИО для удаления:");
        String name = ConsoleHelper.readString();

        list = DataSource.readListUsersFromFile();
        User deleteUser = null;
        for (User u : list){
            if (u.getName().equalsIgnoreCase(name)) {
                deleteUser = u;
                break;
            }
        }
        if (deleteUser == null) throw new WrongActionException();
        else {
            list.remove(deleteUser);
            DataSource.writeListUsersToFile(list);
            ConsoleHelper.writeMessage("Удаление прошло успешно!");
        }
    }
}
