import exception.WrongActionException;
import helper.ConsoleHelper;

import java.io.IOException;


public class Solution {

    public static void main(String[] args) {
        Operation operation = null;
        do {
            try {
                operation = askOperation();
                CommandExecutor.execute(operation);
            } catch (WrongActionException e) {
                ConsoleHelper.writeMessage("Что-то пошло не так.");
            } catch (Exception e) {
                ConsoleHelper.writeMessage("Произошла ошибка. Проверьте введенные данные.");
                e.printStackTrace();

            }

        } while (operation != Operation.EXIT);

    }

    public static Operation askOperation() throws IOException {
        System.out.println("");
        ConsoleHelper.writeMessage("Выберите операцию:");
        ConsoleHelper.writeMessage(String.format("\t %d - просмотр данных сотрудников", Operation.WATCH.ordinal()));
        ConsoleHelper.writeMessage(String.format("\t %d - добавить сотрудника", Operation.ADD.ordinal()));
        ConsoleHelper.writeMessage(String.format("\t %d - изменить данные сотрудника", Operation.CHANGE.ordinal()));
        ConsoleHelper.writeMessage(String.format("\t %d - удалить сотрудника из системы", Operation.DELETE.ordinal()));
        ConsoleHelper.writeMessage(String.format("\t %d - выйти из системы", Operation.EXIT.ordinal()));

        return Operation.values()[ConsoleHelper.readInt()];
    }
}
