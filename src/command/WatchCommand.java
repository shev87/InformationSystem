package command;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import data.DataSource;
import data.Department;
import data.User;
import exception.WrongActionException;
import helper.ConsoleHelper;

import java.util.Collections;
import java.util.List;

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
        List<User> list = DataSource.readListUsersFromFile();
        Collections.sort(list);
        for (User u : list){
            ConsoleHelper.writeMessage(u.toString());
        }
    }

    private void watchDepartment(){
        ConsoleHelper.writeMessage("***Просмотр отделов***");
        List<Department> list = DataSource.readListDepartmentsFromFile();
        Collections.sort(list);
        for (Department d : list){
            ConsoleHelper.writeMessage(d.toString());
        }
    }
}
