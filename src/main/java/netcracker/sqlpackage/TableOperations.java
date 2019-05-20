package netcracker.sqlpackage;

import netcracker.data.User;
import netcracker.exception.WrongIDException;

import java.sql.SQLException;

public interface TableOperations {
    void createTable() throws SQLException;
    void addToTable(User user) throws SQLException;
    void changeTable(int id, User user) throws SQLException, WrongIDException;
    void deleteFromTable(int id) throws SQLException, WrongIDException;
    void showTable() throws  SQLException;
}
