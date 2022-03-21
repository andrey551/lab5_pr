package commands;

import java.time.LocalDateTime;

import Exception.WrongAmountOfElementsException;
import utility.Console;
import utility.VehicleData;

/**
 * Command 'info'. Show information about collection
 */
public class InfoCommand extends AbstractCommand {
    private VehicleData vehicleData;

    public InfoCommand(VehicleData vehicleData) {
        super("info", "Show information about collection");
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
            LocalDateTime lastInitTime = vehicleData.getLastInitTime();
            String lastInitTimeString = (lastInitTime == null) ? "initialization has not yet taken place in this session" : 
            lastInitTime.toLocalDate().toString() + " " + lastInitTime.toLocalTime().toString();
        
            LocalDateTime lastSaveTime = vehicleData.getLastSaveTime();
            String lastSaveTimeString = (lastSaveTime == null) ? "initialization has not yet taken place in this session" : 
            lastSaveTime.toLocalDate().toString() + " " + lastSaveTime.toLocalTime().toString();

            Console.println("Detail about Collection:");
            Console.println("Type: " + vehicleData.collectionType());
            Console.println("Number of elements: " + vehicleData.collectionSize());
            Console.println("Last save day: " + lastSaveTimeString);
            Console.println("Last init day: " + lastInitTimeString);
        } catch (WrongAmountOfElementsException e) {
            Console.println("Usage: " + getName() + " ");
        }
        return false;
    }
    
}
