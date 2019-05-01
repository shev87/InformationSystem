import command.*;

import java.util.HashMap;
import java.util.Map;

public class CommandExecutor {
    private static final Map<Operation, Command> allKnownCommandsMap = new HashMap<>();

    static {
        allKnownCommandsMap.put(Operation.WATCH, new WatchCommand());
        allKnownCommandsMap.put(Operation.ADD, new AddCommand());
        allKnownCommandsMap.put(Operation.CHANGE, new ChangeCommand());
        allKnownCommandsMap.put(Operation.DELETE, new DeleteCommand());
        allKnownCommandsMap.put(Operation.EXIT, new ExitCommand());
    }

    private CommandExecutor() {
    }

    public static void execute(Operation operation) throws Exception {
        allKnownCommandsMap.get(operation).execute();
    }
}
