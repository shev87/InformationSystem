package netcracker.helper;

import netcracker.exception.InterruptOperationException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleHelper {
    private static BufferedReader bis = new BufferedReader(new InputStreamReader(System.in));

    public static void writeMessage(String message) {
        System.out.println(message);
    }

    public static String readString() throws IOException, InterruptOperationException {

        String message = "";
        try {
            message = bis.readLine();
            if (message.equalsIgnoreCase("exit")) {
                throw new InterruptOperationException();
            }
        } catch (IOException ignored) {
        }
        return message;
    }

    public static int readInt() throws IOException, InterruptOperationException {
        String text = readString();
        return Integer.parseInt(text.trim());
    }
}
