package app;

public abstract class QueryBuilder {

    public abstract Query buildQuery(CommandName name, CommandType type);
}
