package commands;

import java.util.Date;

import Exception.IncorrectInputScriptException;
import Exception.MustBeNotEmptyException;
import Exception.WrongAmountOfElementsException;
import Vehicle.Vehicle;
import utility.*;

/**
 * Command add. Add a new element to the collection
 */
public class AddCommand extends AbstractCommand{
    private VehicleData vehicleData;
    private VehicleAsker vehicleAsker;

    public AddCommand(VehicleData vehicleData, VehicleAsker vehicleAsker) {
        super("add", "Add a new element to the collection");
        this.vehicleAsker = vehicleAsker;
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
            vehicleData.addElement(new Vehicle(
                vehicleData.generateNextId(),
                vehicleAsker.askName(),
                vehicleAsker.askCoordinates(),
                new Date(),
                vehicleAsker.askEnginePower(),
                vehicleAsker.askCapacity(),
                vehicleAsker.askFuelConsumption(),
                vehicleAsker.askFuelType()));

                Console.println("Vehicle added success!");
                return true;
        } catch (WrongAmountOfElementsException e) {
            Console.println("Usage : " + getName() + " ");
        } catch (IncorrectInputScriptException e) {
            Console.println("Input is not accepted");
        } catch (MustBeNotEmptyException e) {}

        return false;
    }
}
