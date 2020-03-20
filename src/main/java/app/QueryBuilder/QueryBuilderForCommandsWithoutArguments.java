package app.QueryBuilder;

import app.CommandName;
import app.CommandType;
import app.Query;
import app.QueryBuilder.QueryBuilder;

import java.util.HashMap;
import java.util.List;

public class QueryBuilderForCommandsWithoutArguments extends QueryBuilder {

    @Override
    public Query buildQuery(CommandName name, CommandType type, List<String> commandList, HashMap<String, String> arguments) {
        arguments = new HashMap<>();
        Query query = new Query(name, type, arguments );
        return query;
    }
}
