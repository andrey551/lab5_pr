package commands;

import Exception.WrongAmountOfElementsException;
import utility.Console;
import utility.VehicleData;

/**
 * Command 'show'. Show all elements of collection
 */
public class ShowCommand  extends AbstractCommand{
    private VehicleData vehicleData;

    public ShowCommand(VehicleData vehicleData) {
        super("show", "Show all elements of collection");
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
            Console.println(vehicleData);
            return true;
        } catch(WrongAmountOfElementsException e) {
            Console.println("Usage: " + getName() + " ");
        }
        return false;
    }
    
}
