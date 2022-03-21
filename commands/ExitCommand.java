package commands;

import Exception.WrongAmountOfElementsException;
import utility.Console;

/**
 * Command 'exit'. Finish program( without save data to file)
 */
public class ExitCommand  extends AbstractCommand{

    public ExitCommand() {
        super("exit","Finish program( without save data to file)" );
    }
    /**
     * Executes the command.
     * @return Command exit status.
     */
    @Override
    public Boolean execute(String argument) {
        try{
            if(!argument.isEmpty()) throw new WrongAmountOfElementsException();
            return true;
        } catch (WrongAmountOfElementsException e) {
            Console.println("Usage: " + getName() + " ");
        }

        return false;
    }
    
}
