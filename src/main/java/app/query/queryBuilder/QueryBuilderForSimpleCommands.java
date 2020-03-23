package app.query.queryBuilder;

import app.CommandName;
import app.CommandType;
import app.Exceptions.InputException;
import app.query.Query;

import java.util.List;
import java.util.Map;

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
    public Query buildQuery(CommandName name, CommandType type, List<String> commandList, Map<String,String> arguments) throws InputException {
        validator.validateSimpleCommandArguments(name, commandList);
        arguments = interpretator.interpretateSimpleCommandArguments(name, commandList);
        return new Query(name.getName(), type.getName(), arguments);
    }
}
