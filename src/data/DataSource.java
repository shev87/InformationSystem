package data;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class DataSource {
    private static List<User> listUsers = new ArrayList<>();
    private static List<Department> listDepartments = new ArrayList<>();

    private static int mode;

    public static int getMode() {
        return mode;
    }

    public static void setMode(int mode) {
        DataSource.mode = mode;
    }

    public static List<User> getListUsers() {
        return listUsers;
    }

    public static List<User> readListUsersFromFile(){
        try(FileInputStream fileInputStream = new FileInputStream("dataListUsers.dat");
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
        try(FileOutputStream fileOutputStream = new FileOutputStream("dataListUsers.dat");
            ObjectOutputStream stream = new ObjectOutputStream(fileOutputStream)){
            stream.writeObject(list);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public static List<Department> readListDepartmentsFromFile(){
        try(FileInputStream fileInputStream = new FileInputStream("dataListDepartments.dat");
            ObjectInputStream stream = new ObjectInputStream(fileInputStream)){
            listDepartments = (List<Department>)stream.readObject();
        } catch (FileNotFoundException e){
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return listDepartments;
    }

    public static void writeListDepartmentsToFile(List<Department> list){
        try(FileOutputStream fileOutputStream = new FileOutputStream("dataListDepartments.dat");
            ObjectOutputStream stream = new ObjectOutputStream(fileOutputStream)){
            stream.writeObject(list);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
