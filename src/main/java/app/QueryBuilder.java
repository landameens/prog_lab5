package app;

public abstract class QueryBuilder {

    //TODO: не забывай про пакетирование) Логично засунуть все билдеры в один пакет
    public abstract Query buildQuery(CommandName name, CommandType type);
}
