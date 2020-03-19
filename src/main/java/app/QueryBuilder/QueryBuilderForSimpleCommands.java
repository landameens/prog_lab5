package app.QueryBuilder;

import app.CommandName;
import app.CommandType;
import app.Exceptions.InputException;
import app.Query;
import java.util.HashMap;
import java.util.List;

public class QueryBuilderForSimpleCommands extends QueryBuilder {

    @Override
    public Query buildQuery(CommandName name, CommandType type, List<String> commandList) throws InputException {
        validator.validateSimpleCommandArguments(name, commandList);
        HashMap<String, String> arguments = interpretator.interpretateSimpleCommandArguments(name, commandList);
        Query query = new Query(name, type, arguments);
        return query;
    }
}
