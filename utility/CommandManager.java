package utility;

import java.util.ArrayList;
import java.util.List;

import commands.Command;
/**
 * Operates the commands.
 */
public class CommandManager {
    private final int COMMAND_HISTORY_SIZE = 8;

    private String[] commandHistory = new String[COMMAND_HISTORY_SIZE];
    private List<Command> commands = new ArrayList<>();
    private Command helpCommand;
    private Command infoCommand;
    private Command showCommand;
    private Command addCommand;
    private Command updateCommand;
    private Command removeByIdCommand;
    private Command clearCommand;
    private Command saveCommand;
    private Command executeCommand;
    private Command exitCommand;
    private Command removeFirstCommand;
    private Command addIfMaxCommand;
    private Command removeGreaterCommand;
    private Command groupCountingByCapacityCommand;
    private Command countLessThanFuelTypeCommand;
    private Command filterLessThanFuelConsumptionCommand;

    public CommandManager(Command helpCommand,Command infoCommand, Command showCommand,Command addCommand,
                        Command updateCommand, Command removeByIdCommand, Command clearCommand, Command saveCommand,
                        Command executeCommand, Command exitCommand, Command removeFirstCommand, Command addIfMaxCommand,
                        Command removeGreaterCommand, Command groupCountingByCapacityCommand,
                        Command countLessThanFuelTypeCommand, Command filterLessThanFuelConsumptionCommand) {
        this.helpCommand = helpCommand;
        this.infoCommand = infoCommand;
        this.showCommand = showCommand;
        this.addCommand = addCommand;
        this.updateCommand = updateCommand;
        this.removeByIdCommand = removeByIdCommand;
        this.clearCommand = clearCommand;
        this.saveCommand = saveCommand;
        this.executeCommand = executeCommand;
        this.exitCommand = exitCommand;
        this.removeFirstCommand = removeFirstCommand;
        this.addIfMaxCommand = addIfMaxCommand;
        this.removeGreaterCommand = removeGreaterCommand;
        this.groupCountingByCapacityCommand = groupCountingByCapacityCommand;
        this.countLessThanFuelTypeCommand = countLessThanFuelTypeCommand;
        this.filterLessThanFuelConsumptionCommand = filterLessThanFuelConsumptionCommand;

        commands.add(helpCommand);
        commands.add(infoCommand);
        commands.add(showCommand);
        commands.add(addCommand);
        commands.add(updateCommand);
        commands.add(removeByIdCommand);
        commands.add(clearCommand);
        commands.add(saveCommand);
        commands.add(executeCommand);
        commands.add(exitCommand);
        commands.add(removeFirstCommand);
        commands.add(addIfMaxCommand);
        commands.add(removeGreaterCommand);
        commands.add(groupCountingByCapacityCommand);
        commands.add(countLessThanFuelTypeCommand);
        commands.add(filterLessThanFuelConsumptionCommand);
    }

    public List<Command> getCommands() {
        return commands;
    }
    /**
     * Adds command to command history.
     * @param commandToStore Command to add.
     */
    public void addToHistory(String commandToStore) {

        for (Command command : commands) {
            if (command.getName().split(" ")[0].equals(commandToStore)) {
                for (int i = COMMAND_HISTORY_SIZE-1; i>0; i--) {
                    commandHistory[i] = commandHistory[i-1];
                }
                commandHistory[0] = commandToStore;
            }
        }
    }
    /**
     * Prints that command is not found.
     * @param command Comand, which is not found.
     * @return Command exit status.
     */
    public boolean noSuchCommand(String command) {
        Console.println("Command '" + command + "' is not exist. Type help for help");
        return false;
    }
    /**
     * Prints info about the all commands.
     * @param argument Its argument.
     * @return Command exit status.
     */
    public boolean help(String argument) {
        if (helpCommand.execute(argument)) {
            for (Command command : commands) {
                Console.printtable(command.getName(), command.getDescription());
            }
            return true;
        } else return false;
    }
    /**
     * Prints info about the all commands.
     * @param argument Its argument.
     * @return Command exit status.
     */
    public boolean info(String argument) {
        return infoCommand.execute(argument);
    }
    /**
     * Prints info about the all commands.
     * @param argument Its argument.
     * @return Command exit status.
     */
    public boolean show(String argument) {
        return showCommand.execute(argument);
    }
    /**
     * Prints info about the all commands.
     * @param argument Its argument.
     * @return Command exit status.
     */
    public boolean add(String argument) {
        return addCommand.execute(argument);
    }
    /**
     * Executes needed command.
     * @param argument Its argument.
     * @return Command exit status.
     */
    public boolean update(String argument) {
        return updateCommand.execute(argument);
    }
    /**
     * Executes needed command.
     * @param argument Its argument.
     * @return Command exit status.
     */
    public boolean removeById(String argument) {
        return removeByIdCommand.execute(argument);
    }
    /**
     * Executes needed command.
     * @param argument Its argument.
     * @return Command exit status.
     */
    public boolean clear(String argument) {
        return clearCommand.execute(argument);
    }
    /**
     * Executes needed command.
     * @param argument Its argument.
     * @return Command exit status.
     */
    public boolean save(String argument) {
        return saveCommand.execute(argument);
    }
    /**
     * Executes needed command.
     * @param argument Its argument.
     * @return Command exit status.
     */
    public boolean exit(String argument) {
        return exitCommand.execute(argument);
    }
    /**
     * Executes needed command.
     * @param argument Its argument.
     * @return Command exit status.
     */
    public boolean executeScript(String argument) {
        return executeCommand.execute(argument);
    }
    /**
     * Executes needed command.
     * @param argument Its argument.
     * @return Command exit status.
     */
    public boolean addIfMax(String argument) {
        return addIfMaxCommand.execute(argument);
    }
    /**
     * Executes needed command.
     * @param argument Its argument.
     * @return Command exit status.
     */
    public boolean removeGreater(String argument) {
        return removeGreaterCommand.execute(argument);
    }
    /**
     * Executes needed command.
     * @param argument Its argument.
     * @return Command exit status.
     */
    public boolean removeFirst(String argument) {
        return removeFirstCommand.execute(argument);
    }
    /**
     * Executes needed command.
     * @param argument Its argument.
     * @return Command exit status.
     */
    public boolean groupCountingByCapacity(String argument) {
        return groupCountingByCapacityCommand.execute(argument);
    }
    /**
     * Executes needed command.
     * @param argument Its argument.
     * @return Command exit status.
     */
    public boolean countLessThanFuelType(String argument) {
        return countLessThanFuelTypeCommand.execute(argument);
    }
    /**
     * Executes needed command.
     * @param argument Its argument.
     * @return Command exit status.
     */
    public boolean filterLessThanFuelConsumption(String argument) {
        return filterLessThanFuelConsumptionCommand.execute(argument);
    }
    
}
