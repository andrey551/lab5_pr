package commands;

import Exception.CollectionIsEmptyException;
import Exception.IncorrectInputScriptException;
import Exception.WrongAmountOfElementsException;
import Vehicle.Vehicle;
import utility.Console;
import utility.VehicleAsker;
import utility.VehicleData;

import java.util.ArrayDeque;

/**
 * Command 'filter_less_than_fuel_consumption'. Display elements
 * whose fuelConsumption field value is less than the given one
 */
public class FilterLessThanFuelConsumptionCommand extends AbstractCommand{
    private VehicleData vehicleData;
    private VehicleAsker vehicleAsker;

    public FilterLessThanFuelConsumptionCommand(VehicleData vehicleData, VehicleAsker vehicleAsker) {
        super("filter_less_than_fuel_consumption", "Display elements whose fuelConsumption field value is less than the given one" );
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
            Console.println("List of vehicle that have fuel consumption that small than the given: ");
            int consumptionValue = Integer.parseInt(argument);
            ArrayDeque<Vehicle> result = vehicleData.FilterLessThanFuelConsumption(consumptionValue);
            if(result.isEmpty()) Console.println("none");
            else Console.println(result);
            
            return true;
        } catch (WrongAmountOfElementsException e) {
            Console.println("Usage: " + getName() + " ");
        } catch (CollectionIsEmptyException e) {
            Console.println("Collection is empty!");
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        return false;
    }

    
}
