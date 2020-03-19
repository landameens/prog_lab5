package app.QueryBuilder;

import app.CommandName;
import app.CommandType;
import app.Exceptions.InputException;
import app.Query;
import java.util.HashMap;
import java.util.List;

/**
 * This class is responsible for making query of commands that has simple arguments.
 */
public class QueryBuilderForSimpleCommands extends QueryBuilder {

    /**
     * Makes query for simple commands by command name, command type and commandList that contains simple arguments.
     * @param name
     * @param type
     * @param commandList
     * @param arguments
     * @return
     * @throws InputException
     */
    @Override
    public Query buildQuery(CommandName name, CommandType type, List<String> commandList, HashMap<String,String> arguments) throws InputException {
        validator.validateSimpleCommandArguments(name, commandList);
        arguments = interpretator.interpretateSimpleCommandArguments(name, commandList);
        Query query = new Query(name, type, arguments);
        return query;
    }
}
