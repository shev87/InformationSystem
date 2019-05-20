package netcracker.command;

import netcracker.Solution;
import netcracker.data.DataSource;
import netcracker.data.Department;
import netcracker.data.User;
import netcracker.exception.WrongActionException;
import netcracker.helper.ConsoleHelper;

import java.util.ArrayList;
import java.util.List;

public class DeleteCommand implements Command{
    private List<User> list = new ArrayList<>();
    private List<Department> listDepartments = new ArrayList<>();

    @Override
    public void execute() throws Exception {
        switch (DataSource.getMode()){
            case 1:
                deleteUser();
                break;
            case 2:
                deleteDepartment();
                break;
            default: throw new WrongActionException();
        }
    }

    private void deleteUser() throws Exception{
        ConsoleHelper.writeMessage("");
        ConsoleHelper.writeMessage("***УДАЛЕНИЕ сотрудника***");
        ConsoleHelper.writeMessage("Введите id сотрудника для удаления:");
        int id = ConsoleHelper.readInt();
        Solution.getSolution().users.deleteFromTable(id);

    }

    private void deleteDepartment() throws Exception{
        ConsoleHelper.writeMessage("");
        ConsoleHelper.writeMessage("***УДАЛЕНИЕ отдела***");
        ConsoleHelper.writeMessage("Введите id отдела для удаления:");
        int id = ConsoleHelper.readInt();
        Solution.getSolution().departments.deleteFromTable(id);


    }
}
