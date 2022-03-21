package commands;

import Exception.CollectionIsEmptyException;
import Exception.WrongAmountOfElementsException;
import utility.Console;
import utility.VehicleData;

/**
 * Command 'remove_first'. Remove the first element from the collection
 */
public class RemoveFirstCommand extends AbstractCommand{
    private VehicleData vehicleData;

    public RemoveFirstCommand(VehicleData vehicleData) {
        super("remove_first", "Remove the first element from the collection");
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
            if(vehicleData.collectionSize() == 0) throw new CollectionIsEmptyException();

            vehicleData.removeFirst();

            Console.println("Remove first element succcess!");

        } catch (WrongAmountOfElementsException e) {
            Console.println("Usage: " + getName() + " ");
        } catch (CollectionIsEmptyException e) {
            Console.println("Collection is Empty!");
        }

        return false;
    }

    
}
