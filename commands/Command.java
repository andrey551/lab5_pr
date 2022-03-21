package commands;

/**
 * Interface Command
 */
public interface Command {
    String getDescription();
    String getName();
    Boolean execute(String argument);
}
