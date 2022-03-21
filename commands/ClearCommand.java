package commands;

import Exception.WrongAmountOfElementsException;
import utility.Console;
import utility.VehicleData;

/**
 * Command 'clear'. Clear collection
 */
public class ClearCommand extends AbstractCommand{
    private VehicleData vehicleData;

    public ClearCommand(VehicleData vehicleData) {
        super("clear", "Clear collection");
        this.vehicleData = vehicleData;
    }
    /**
     * Executes the command.
     * @return Command exit status.
     */
    @Override
    public Boolean execute(String argument) {
        try{
            if(!argument.isEmpty()) throw new WrongAmountOfElementsException();
            vehicleData.clearCollection();
            Console.println("Collection is deleted!");
            return true;
        } catch (WrongAmountOfElementsException e) {
            Console.println("Usage: " + getClass() + " ");
        }
        return false;
    }
    
}
