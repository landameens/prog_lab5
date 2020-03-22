package app.query.queryBuilder;

import app.Exceptions.InputException;
import app.query.Query;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class is responsible for making query of commands that has compound arguments.
 */
public class QueryBuilderForCompoundCommands extends QueryBuilder {

    /**
     * Makes query for compound commands by command name, command type,
     * commandList that contains simple arguments and hashMap of complex arguments.
     * @param name
     * @param type
     * @param commandList
     * @param arguments
     * @return
     * @throws InputException
     */
    @Override
    public Query buildQuery(String name,
                            String type,
                            List<String> commandList,
                            Map<String,String> arguments) throws InputException {
        validator.validateSimpleArgumentsOfCompoundCommand(name, commandList);

        Map<String, String> simpleArguments = new HashMap<>();
        if (name.equals("update")) {
            simpleArguments = interpretator.interpretateSimpleCommandArguments(name, commandList);
        }
        arguments.putAll(simpleArguments);

        return new Query(name, type, arguments);
    }
}
