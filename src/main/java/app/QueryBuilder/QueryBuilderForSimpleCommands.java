package app.QueryBuilder;

import app.CommandName;
import app.CommandType;
import app.Query;
import app.QueryBuilder.QueryBuilder;

public class QueryBuilderForSimpleCommands extends QueryBuilder {

    @Override
    public Query buildQuery(CommandName name, CommandType type) {
        return null;
    }
}
