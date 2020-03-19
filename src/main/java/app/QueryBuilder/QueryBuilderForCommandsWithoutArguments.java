package app.QueryBuilder;

import app.CommandName;
import app.CommandType;
import app.Query;
import app.QueryBuilder.QueryBuilder;

import java.util.HashMap;
import java.util.List;

public class QueryBuilderForCommandsWithoutArguments extends QueryBuilder {

    @Override
    public Query buildQuery(CommandName name, CommandType type, List<String> commandList) {
        Query query = new Query(name, type, new HashMap<>() );
        return query;
    }
}
