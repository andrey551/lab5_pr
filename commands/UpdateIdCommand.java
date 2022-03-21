package commands;

import java.util.Date;

import Exception.CollectionIsEmptyException;
import Exception.IncorrectInputScriptException;
import Exception.MustBeNotEmptyException;
import Exception.VehicleNotFoundException;
import Exception.WrongAmountOfElementsException;
import Vehicle.Coordinates;
import Vehicle.FuelType;
import Vehicle.Vehicle;
import utility.Console;
import utility.VehicleAsker;
import utility.VehicleData;

/**
 * Command 'update id'. Update the value of the collection element whose id is equal to the given one
 */
public class UpdateIdCommand extends AbstractCommand{
    private VehicleData vehicleData;
    private VehicleAsker vehicleAsker;

    public UpdateIdCommand(VehicleData vehicleData, VehicleAsker vehicleAsker) {
        super("update_id", "Update the value of the collection element whose id is equal to the given one");
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

           Integer id = Integer.parseInt(argument);
           Vehicle oldVehicle = vehicleData.getById(id);
           if(oldVehicle == null) throw new VehicleNotFoundException();

           String name = oldVehicle.getName();
           Coordinates coordinates = oldVehicle.getCoordinates();
           Date creationDate = oldVehicle.getCreationDate();
           Double enginePower = oldVehicle.getEnginePower();
           Long capacity = oldVehicle.getCapacity();
           int fuelConsumption = oldVehicle.getFuelConsumption();
           FuelType fuelType = oldVehicle.getFuelType();

           vehicleData.removeFromCollection(oldVehicle);

           if(vehicleAsker.askQuestion("Wanna update name field ? ")) name = vehicleAsker.askName();
           if(vehicleAsker.askQuestion("Wanna update coordinates field ? ")) coordinates = vehicleAsker.askCoordinates();
           if(vehicleAsker.askQuestion("Wanna update engine power field ? ")) enginePower = vehicleAsker.askEnginePower();
           if(vehicleAsker.askQuestion("Wanna update capacity field ? ")) capacity = vehicleAsker.askCapacity();
           if(vehicleAsker.askQuestion("Wanna update fuel consumption field ? ")) 
                                                                        fuelConsumption = vehicleAsker.askFuelConsumption();
           if(vehicleAsker.askQuestion("Wanna update fuel type field ? ")) fuelType = vehicleAsker.askFuelType();

           vehicleData.addElement(new Vehicle(
               id, 
               name, 
               coordinates, 
               creationDate, 
               enginePower, 
               capacity, 
               fuelConsumption, 
               fuelType
            ));

           Console.println("Update vehilce successful!");
           return true;
       } catch (WrongAmountOfElementsException e) {
           Console.println("Usage: " + getName() + " ");
       } catch (CollectionIsEmptyException exception) {
            Console.printerror("Collection is empty!");
        } catch (NumberFormatException exception) {
            Console.printerror("Id must be a number!");
        } catch (VehicleNotFoundException exception) {
            Console.printerror("No such vehicle have this ID!");
        } catch (IncorrectInputScriptException | MustBeNotEmptyException exception) {}
        return false;
    }
    
}
