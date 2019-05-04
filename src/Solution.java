import command.ExitCommand;
import data.DataSource;
import exception.WrongActionException;
import helper.ConsoleHelper;

import java.io.IOException;


public class Solution {

    public static void main(String[] args) {
        Operation operation = null;
        do {
            try {
                //operation = askOperation();
                int mode = askMainOperation();
                switch (mode){
                    case 1:
                        DataSource.setMode(1);
                        operation = askOperation();
                        CommandExecutor.execute(operation);
                        break;
                    case 2:
                        DataSource.setMode(2);
                        operation = askOperation();
                        CommandExecutor.execute(operation);
                        break;
                    case 3:
                        operation = Operation.EXIT;
                        new ExitCommand().execute();
                        break;
                    default: throw new WrongActionException();
                }
            } catch (WrongActionException e) {
                ConsoleHelper.writeMessage("Что-то пошло не так. Проверьте введённые данные");
            } catch (Exception e) {
                ConsoleHelper.writeMessage("Произошла ошибка.");
                e.printStackTrace();
            }

        } while (operation != Operation.EXIT);

    }


    public static int askMainOperation() throws IOException {
        System.out.println("");
        ConsoleHelper.writeMessage("Выберите раздел:");
        ConsoleHelper.writeMessage("\t 1 - сотрудники");
        ConsoleHelper.writeMessage("\t 2 - отделы");
        ConsoleHelper.writeMessage("\t 3 - выйти из системы");

        return ConsoleHelper.readInt();
    }

    public static Operation askOperation() throws IOException {
        System.out.println("");
        ConsoleHelper.writeMessage("Выберите операцию:");
        ConsoleHelper.writeMessage(String.format("\t %d - просмотр данных", Operation.WATCH.ordinal()));
        ConsoleHelper.writeMessage(String.format("\t %d - добавить", Operation.ADD.ordinal()));
        ConsoleHelper.writeMessage(String.format("\t %d - изменить данные", Operation.CHANGE.ordinal()));
        ConsoleHelper.writeMessage(String.format("\t %d - удалить данные", Operation.DELETE.ordinal()));
        ConsoleHelper.writeMessage(String.format("\t %d - выйти из системы", Operation.EXIT.ordinal()));

        return Operation.values()[ConsoleHelper.readInt()];
    }
}
