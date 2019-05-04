package data;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class DataSource {
    private static List<User> listUsers = new ArrayList<>();
    private static List<Department> listDepartments = new ArrayList<>();
    public static List<User> getListUsers() {
        return listUsers;
    }

    public static List<User> readListUsersFromFile(){
        try(FileInputStream fileInputStream = new FileInputStream("dataList.dat");
            ObjectInputStream stream = new ObjectInputStream(fileInputStream)){
            listUsers = (List<User>)stream.readObject();
            } catch (FileNotFoundException e){
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return listUsers;
    }

    public static void writeListUsersToFile(List<User> list){
        try(FileOutputStream fileOutputStream = new FileOutputStream("dataList.dat");
            ObjectOutputStream stream = new ObjectOutputStream(fileOutputStream)){
            stream.writeObject(list);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
