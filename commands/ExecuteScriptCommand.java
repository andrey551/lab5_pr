package commands;

import utility.Console;
import Exception.WrongAmountOfElementsException;
import java.util.Stack;

/**
 * Command 'execute_script'. Execute script from specified file
 */
public class ExecuteScriptCommand extends AbstractCommand{
    private static Stack<String> callStack = new Stack<>();

    public static void clearStack(){
        callStack.clear();
    }

    public ExecuteScriptCommand() {
        super("execute_script", "Execute script from specified file");
    }
    /**
     * Executes the command.
     * @return Command exit status.
     */
    @Override
    public Boolean execute(String argument) {
        try {
            if (argument.isEmpty()) throw new WrongAmountOfElementsException();
            Console.println("Executing script " + argument + "'...");
            return true;
        } catch (WrongAmountOfElementsException exception) {
            Console.println("Usage: " + getName() + "'");
        }
        return false;
    }

}
