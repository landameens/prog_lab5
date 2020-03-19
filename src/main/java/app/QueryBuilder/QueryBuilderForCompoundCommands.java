package app.QueryBuilder;

import app.CommandName;
import app.CommandType;
import app.Query;
import app.QueryBuilder.QueryBuilder;

import java.util.List;

public class QueryBuilderForCompoundCommands extends QueryBuilder {

    @Override
    public Query buildQuery(CommandName name, CommandType type, List<String> commandList) {
        return null;
    }
}
