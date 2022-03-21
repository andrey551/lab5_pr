package commands;

import java.util.Date;

import Exception.IncorrectInputScriptException;
import Exception.MustBeNotEmptyException;
import Exception.WrongAmountOfElementsException;
import Vehicle.Vehicle;
import utility.Console;
import utility.VehicleAsker;
import utility.VehicleData;

/**
 * Command 'add_if_max'.Add a new element to the collection
 * if its value is greater than the value of the
 * largest element in this collection
 */
public class AddIfMaxCommand extends AbstractCommand{
    private VehicleData vehicleData;
    private VehicleAsker vehicleAsker;

    public AddIfMaxCommand(VehicleData vehicleData, VehicleAsker vehicleAsker) {
        super("add_if_max", "Add a new element to the collection if its value is greater than the value of the largest element in this collection");
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
            Vehicle vehicleToAdd = new Vehicle(
                vehicleData.generateNextId(),
                vehicleAsker.askName(),
                vehicleAsker.askCoordinates(),
                new Date(),
                vehicleAsker.askEnginePower(),
                vehicleAsker.askCapacity(),
                vehicleAsker.askFuelConsumption(),
                vehicleAsker.askFuelType()
                );
            
                if(vehicleData.collectionSize() == 0 || vehicleToAdd.compareTo(vehicleData.getLast()) > 0) {
                    vehicleData.addElement(vehicleToAdd);
                    Console.println("Add element success!");
                    return true;
                } else Console.printerror("Element to add is smaller than max element!");
        } catch(WrongAmountOfElementsException e) {
            Console.println("Usage: " + getName() + " ");
        } catch (IncorrectInputScriptException|MustBeNotEmptyException e) {}
        return false;
    }
    
}
