package netcracker;

import netcracker.data.DataSource;
import netcracker.exception.WrongActionException;
import netcracker.exception.WrongIDException;
import netcracker.helper.ConsoleHelper;
import netcracker.sqlpackage.Departments;
import netcracker.sqlpackage.Users;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Solution {
// Блок объявления констант
    /**
     * DB_URL: Адрес нашей базы данных. Состоит из данных, разделённых двоеточием:
     * - Протокол=jdbc
     * - Вендор (производитель/наименование) СУБД=h2
     * - Расположение СУБД, в нашем случае путь до файла (c:/JavaPrj/SQLDemo/test.db/stockExchange).
     *   Для сетевых СУБД тут дополнительно указываются имена или IP адреса удалённых серверов,
     *   TCP/UDP номера портов и так далее.
     */
    public static final String DB_URL = "jdbc:h2:~/base";
    /**
     * DB_Driver: Здесь мы определили имя драйвера, которое можно узнать, например, кликнув
     * мышкой на подключенную библиотеку и развернув её структуру в директории test.lib текущего проекта.
     */
    public static final String DB_Driver = "org.h2.Driver";

    private static Solution solution;
    // Таблицы СУБД
    public Users users;
    public Departments departments;

    // Получить новое соединение с БД
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL);
    }

    // Инициализация
    private Solution() throws SQLException, ClassNotFoundException {
        Class.forName(DB_Driver);
        // Инициализируем таблицы
        users = new Users();
        departments = new Departments();
    }

    public static Solution getSolution() throws SQLException, ClassNotFoundException {
        if (solution == null) solution = new Solution();
        return solution;
    }

    // Создание всех таблиц и ключей между ними
    public void createTables() throws SQLException {
        users.createTable();
        departments.createTable();
    }
    public static void main(String[] args) {
        try {
            getSolution().createTables();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Operation operation = null;
        do {
            try {
                int mode = askMainOperation();
                //mode - режим работы базы: 1 - с сотрудниками, 2 - с отделами
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
                        CommandExecutor.execute(operation);
                        break;
                    default: throw new WrongActionException();
                }
            } catch (WrongActionException e) {
                ConsoleHelper.writeMessage("Что-то пошло не так. Проверьте введённые данные");
            } catch (WrongIDException e){
                ConsoleHelper.writeMessage("Вы неправильно ввели id");
            }
            catch (Exception e) {
                ConsoleHelper.writeMessage("Произошла ошибка. Попробуйте снова.");
                //e.printStackTrace();
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
