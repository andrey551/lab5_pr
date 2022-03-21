package commands;

import Exception.WrongAmountOfElementsException;
import utility.Console;

/**
 * Command 'help'. Display help on available commands
 */
public class HelpCommand extends AbstractCommand{
    public HelpCommand() {
        super("help", "Display help on available commands");
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
        } catch (WrongAmountOfElementsException e){
            Console.println("Usage: " + getName() + " ");
        }
        return false;
    }
}
