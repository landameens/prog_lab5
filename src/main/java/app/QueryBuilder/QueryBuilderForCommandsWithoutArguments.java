package app.QueryBuilder;

import app.CommandName;
import app.CommandType;
import app.Query;
import app.QueryBuilder.QueryBuilder;

import java.util.HashMap;
import java.util.List;

/**
 * This class is responsible for making query of commands that has no arguments.
 */
public class QueryBuilderForCommandsWithoutArguments extends QueryBuilder {

    /**
     * Makes query for commands without arguments by command name and command type.
     * @param name
     * @param type
     * @param commandList
     * @param arguments
     * @return
     */
    @Override
    public Query buildQuery(CommandName name, CommandType type, List<String> commandList, HashMap<String, String> arguments) {
        arguments = new HashMap<>();
        Query query = new Query(name, type, arguments );
        return query;
    }
}
