package netcracker;

import netcracker.data.DataSource;
import netcracker.exception.InterruptOperationException;
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
    public static final String DB_URL = "jdbc:h2:~/base";
//    public static final String DB_URL = "jdbc:mysql://localhost:3306/world";
    public static String name = "root";
    public static String pass = "1234";

    private static String user = "shev87";
    private static String password = "12345678";
    private static String urlOnline = "jdbc:mysql://db4free.net:3306/netcracker?useSSL=false";

//  public static final String DB_Driver = "org.h2.Driver";
//  public static final String DB_Driver = "com.mysql.cj.jdbc.Driver";
    public static final String DB_Driver = "com.mysql.jdbc.Driver";

    private static Solution solution;
    // Таблицы СУБД
    public Users users;
    public Departments departments;

    // Получить новое соединение с БД
    public static Connection getConnection() throws SQLException {
//        return DriverManager.getConnection(DB_URL, name, pass);
        return DriverManager.getConnection(urlOnline, user, password);
 //       return DriverManager.getConnection(DB_URL);
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
            }catch (InterruptOperationException e){
                ConsoleHelper.writeMessage("Вы вышли из текущего режима работы программы!");
            }
            catch (Exception e) {
                ConsoleHelper.writeMessage("Произошла ошибка. Попробуйте снова.");
                //e.printStackTrace();
            }

        } while (operation != Operation.EXIT);

    }


    public static int askMainOperation() throws IOException, InterruptOperationException {
        System.out.println("");
        ConsoleHelper.writeMessage("Выберите раздел:");
        ConsoleHelper.writeMessage("\t 1 - сотрудники");
        ConsoleHelper.writeMessage("\t 2 - отделы");
        ConsoleHelper.writeMessage("\t 3 - выйти из системы");

        return ConsoleHelper.readInt();
    }

    public static Operation askOperation() throws IOException, InterruptOperationException {
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
