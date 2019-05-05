package command;

import data.DataSource;
import data.Department;
import data.User;
import exception.WrongActionException;
import helper.ConsoleHelper;

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
        ConsoleHelper.writeMessage("Введите ФИО для удаления:");
        String name = ConsoleHelper.readString();

        list = DataSource.readListUsersFromFile();
        listDepartments = DataSource.readListDepartmentsFromFile();
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
            // если удалили начальника, то удаляем и его отдел из списка отделов
            Department deleteD = null;
            for (Department d : listDepartments){
                if (d.getUser().getName().equalsIgnoreCase(deleteUser.getName())) {
                    deleteD = d;
                    break;
                }
            }
            if (deleteD != null) listDepartments.remove(deleteD);
            DataSource.writeListDepartmentsToFile(listDepartments);
            ConsoleHelper.writeMessage("Удаление прошло успешно!");
        }
    }

    private void deleteDepartment() throws Exception{
        ConsoleHelper.writeMessage("");
        ConsoleHelper.writeMessage("***УДАЛЕНИЕ отдела***");
        ConsoleHelper.writeMessage("Введите ФИО начальника для удаления:");
        String name = ConsoleHelper.readString();

        listDepartments = DataSource.readListDepartmentsFromFile();
        Department deleteDepartment = null;
        for (Department d : listDepartments){
            if (d.getUser().getName().equalsIgnoreCase(name)) {
                deleteDepartment = d;
                break;
            }
        }
        if (deleteDepartment == null) throw new WrongActionException();
        else {
            listDepartments.remove(deleteDepartment);
            DataSource.writeListDepartmentsToFile(listDepartments);
            ConsoleHelper.writeMessage("Удаление прошло успешно!");
        }
    }
}
