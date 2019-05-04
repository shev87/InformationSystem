package command;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import data.DataSource;
import data.User;
import helper.ConsoleHelper;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class WatchCommand implements Command{
    @Override
    public void execute() throws Exception {
        ConsoleHelper.writeMessage("Просмотр пользователей:");
     //   FileReader reader = new FileReader("dataList.json");
        /*ObjectMapper mapper = new ObjectMapper();
        List<User> myObjects = mapper.readValue("dataList", new TypeReference<List<User>>(){});
        System.out.println(myObjects);*/
        List<User> list = DataSource.readListUsersFromFile();
        for (User u : list){
            ConsoleHelper.writeMessage(u.toString());
        }


    }
}
