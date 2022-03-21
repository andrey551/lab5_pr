package commands;

import Exception.CollectionIsEmptyException;
import Exception.IncorrectInputScriptException;
import Exception.WrongAmountOfElementsException;
import utility.Console;
import utility.VehicleAsker;
import utility.VehicleData;

/**
 * Command 'count_less_than_fuel_type fuelType'. Print the number of
 * elements whose fuelType field value is less than the given one
 */
public class CountLessThanFuelTypeCommand extends AbstractCommand{
    private VehicleData vehicleData;
    private VehicleAsker vehicleAsker;

    public CountLessThanFuelTypeCommand(VehicleData vehicleData, VehicleAsker vehicleAsker) {
        super("count_less_than_fuel_type", "Print the number of elements whose fuelType field value is less than the given one");
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

            Console.println(vehicleData.CountLessThanFuelType(vehicleAsker.askFuelType()));
            return true;
        } catch (WrongAmountOfElementsException e) {
            Console.println("Usage: " + getName() + " ");
        } catch (CollectionIsEmptyException e) {
            Console.println("Collection is empty!");
        } catch (IncorrectInputScriptException e) {
            Console.println("Input is not legal!");
        }

        return false;
    }
}
