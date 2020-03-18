package app.QueryBuilder;

import app.CommandName;
import app.CommandType;
import app.Query;

public abstract class QueryBuilder {

    public abstract Query buildQuery(CommandName name, CommandType type);
}
