package app;

/**
 * This enum contains types of commands.
 * A simple command is a command that has only non-composite arguments, that user writes in the same line as a command name.
 * A compound command is a command what has compound arguments (it consist of many fields)
 */
public enum CommandType {
    COMMAND_WITHOUT_ARGUMENTS, SIMPLE_COMMAND, COMPOUND_COMMAND;
}
