package commands;

import java.util.Date;

import Exception.CollectionIsEmptyException;
import Exception.IncorrectInputScriptException;
import Exception.MustBeNotEmptyException;
import Exception.VehicleNotFoundException;
import Exception.WrongAmountOfElementsException;
import Vehicle.Vehicle;
import utility.VehicleAsker;
import utility.VehicleData;
import utility.Console;

/**
 * Command 'remove_greater'. Remove from the collection all elements greater than the given
 */
public class RemoveGreaterCommand extends AbstractCommand{
    private VehicleData vehicleData;
    private VehicleAsker vehicleAsker;

    public RemoveGreaterCommand(VehicleData vehicleData, VehicleAsker vehicleAsker) {
        super("remove_greater", "Remove from the collection all elements greater than the given");
        this.vehicleData = vehicleData;
        this.vehicleAsker = vehicleAsker;
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
           Vehicle vehicleToFind = new Vehicle(
               vehicleData.generateNextId(),
               vehicleAsker.askName(),
               vehicleAsker.askCoordinates(),
               new Date(),
               vehicleAsker.askEnginePower(),
               vehicleAsker.askCapacity(),
               vehicleAsker.askFuelConsumption(),
               vehicleAsker.askFuelType()
           );

           Vehicle vehicleFromCollection = vehicleData.getByValue(vehicleToFind);
           if(vehicleFromCollection == null) throw new VehicleNotFoundException();
           vehicleData.removeGreater(vehicleFromCollection);
           Console.println("Remove vehicle success!");
            return true;
       } catch (WrongAmountOfElementsException exception) {
        Console.println("Usage: '" + getName() + "'");
        } catch (CollectionIsEmptyException exception) {
            Console.printerror("Collection is empty!");
        } catch (VehicleNotFoundException exception) {
            Console.printerror("Not found vehicle such that in collection!");
        } catch (IncorrectInputScriptException exception) {

        } catch (MustBeNotEmptyException e) {
            Console.printerror("Collection should not be empty!");
        }
        return false;
    }
    
}
