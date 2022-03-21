package commands;

import Exception.CollectionIsEmptyException;
import Exception.WrongAmountOfElementsException;
import utility.Console;
import utility.VehicleData;

/**
 * Command 'group_counting_by_capacity'. Group the elements of
 * the collection by the value of the field capacity, display the
 * number of elements in each group
 */
public class GroupCountingBycapacityCommand extends AbstractCommand{
    private VehicleData vehicleData;

    public GroupCountingBycapacityCommand(VehicleData vehicleData) {
        super("group_counting_by_capacity", "Group the elements of the collection by the value of the field capacity, display the number of elements in each group");
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
            Console.println(vehicleData.groupCoutingByCapacity());
            return true;
        } catch(WrongAmountOfElementsException e) {
            Console.println("Usage: " + getName() + " ");
        } catch(CollectionIsEmptyException e) {
            Console.println("Collection is empty!");
        }

        return true;
    }

    
}
