package commands;

import Exception.WrongAmountOfElementsException;
import utility.Console;
import utility.VehicleData;

/**
 * Command 'save'. Save collection to file
 */
public class SaveCommand extends AbstractCommand{
    private VehicleData vehicleData;

    public SaveCommand(VehicleData vehicleData) {
        super("save", "Save collection to file");
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
            vehicleData.saveCollection();
            return true;
        } catch(WrongAmountOfElementsException e) {
            Console.println("Usage: " + getName() + " ");
        }

        return false;
    }
    
}
