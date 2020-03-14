package app;

import java.util.HashMap;

public class QueryBuilderForCommandsWithoutArguments extends QueryBuilder{

    @Override
    public Query buildQuery(CommandName name, CommandType type) {
        Query query = new Query(name, type, new HashMap<>() );
        return query;
    }
}
