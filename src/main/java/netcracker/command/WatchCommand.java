package netcracker.command;

import netcracker.Solution;
import netcracker.data.DataSource;;
import netcracker.exception.WrongActionException;
import netcracker.helper.ConsoleHelper;
import java.sql.SQLException;


public class WatchCommand implements Command{
    @Override
    public void execute() throws Exception {
        switch (DataSource.getMode()){
            case 1:
                watchUser();
                break;
            case 2:
                watchDepartment();
                break;
            default: throw new WrongActionException();
        }
    }

    private void watchUser(){
        ConsoleHelper.writeMessage("***Просмотр сотрудников***");
        try {
            Solution.getSolution().users.showTable();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void watchDepartment(){
        ConsoleHelper.writeMessage("***Просмотр отделов***");
        try {
            Solution.getSolution().departments.showTable();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
