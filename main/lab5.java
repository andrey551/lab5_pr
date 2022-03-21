package main;

import java.util.Scanner;

import commands.*;
import utility.*;

/**
 * @author Tuan Anh Dau
 * @Group: P3114
 */
public class lab5 {

    public static final String PS1 = "$ ";
    public static final String PS2 = "> ";

    public static void main(String[] args) {
        try(Scanner userScanner = new Scanner(System.in)){
            final String path = "data.xml";
            VehicleAsker vehicleAsker = new VehicleAsker(userScanner);
            FileManager fileManager = new FileManager(path);
            VehicleData vehicleData = new VehicleData(fileManager);
            CommandManager commandManager = new CommandManager(
                new HelpCommand(),
                new InfoCommand(vehicleData),
                new ShowCommand(vehicleData),
                new AddCommand(vehicleData, vehicleAsker),
                new UpdateIdCommand(vehicleData, vehicleAsker),
                new RemoveByIdCommand(vehicleData),
                new ClearCommand(vehicleData),
                new SaveCommand(vehicleData),
                new ExecuteScriptCommand(),
                new ExitCommand(),
                new RemoveGreaterCommand(vehicleData, vehicleAsker),
                new AddIfMaxCommand(vehicleData, vehicleAsker),
                new RemoveGreaterCommand(vehicleData, vehicleAsker),
                new GroupCountingBycapacityCommand(vehicleData),
                new CountLessThanFuelTypeCommand(vehicleData, vehicleAsker),
                new FilterLessThanFuelConsumptionCommand(vehicleData, vehicleAsker)
                );

                Console console = new Console(commandManager, userScanner, vehicleAsker);

                console.interactiveMode();
        } catch (NullPointerException e) {
            e.printStackTrace();
            Console.println("Collection is null");
        }
    }
}