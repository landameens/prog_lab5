package app.QueryBuilder;

import app.CommandName;
import app.CommandType;
import app.Exceptions.InputException;
import app.Query;
import app.QueryBuilder.QueryBuilder;
import app.Viewer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QueryBuilderForCompoundCommands extends QueryBuilder {

    @Override
    public Query buildQuery(CommandName name, CommandType type, List<String> commandList, HashMap<String,String> arguments) throws InputException {
        validator.validateSimpleArgumentsOfCompoundCommand(name, commandList);
        HashMap<String, String> simpleArguments = new HashMap<>();
        if (name.equals(CommandName.UPDATE)) {
            simpleArguments = interpretator.interpretateSimpleCommandArguments(name, commandList);
        }
        arguments.putAll(simpleArguments);
        Query query = new Query(name, type, arguments);
        return query;
    }
}
