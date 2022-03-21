package commands;

import Exception.CollectionIsEmptyException;
import Exception.WrongAmountOfElementsException;
import Exception.WrongIdException;
import Vehicle.Vehicle;
import utility.Console;
import utility.VehicleData;

/**
 * Command 'remove_by_id'. Delete element from colletion by ID
 */
public class RemoveByIdCommand extends AbstractCommand{
    private VehicleData vehicleData;

    public RemoveByIdCommand(VehicleData vehicleData) {
        super("remove_by_id", "Delete element from collection by ID");
        this.vehicleData = vehicleData;
    }
    /**
     * Executes the command.
     * @return Command exit status.
     */
    @Override
    public Boolean execute(String argument) {
        try{
            if(argument.isEmpty()) throw new WrongAmountOfElementsException();
            if(vehicleData.collectionSize() == 0) throw new CollectionIsEmptyException();
            Integer id = Integer.parseInt(argument);
            Vehicle vehicleToRemove = vehicleData.getById(id);
            if(vehicleToRemove == null) throw new WrongIdException();
            vehicleData.removeFromCollection(vehicleToRemove);
            Console.println("Deleted vehicle!");
        } catch(CollectionIsEmptyException e) {
            Console.println("Empty collection");
        } catch(WrongAmountOfElementsException e) {
            Console.println("Usage: " + getName() + " ");
        } catch(NumberFormatException e) {
            Console.println("ID must be a Integer number");
        } catch (WrongIdException e) {
            Console.println("ID not found!");
        }

        return false;
    }
    
}
