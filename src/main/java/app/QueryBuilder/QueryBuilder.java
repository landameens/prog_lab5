package app.QueryBuilder;

import app.*;
import app.Exceptions.InputException;

import java.util.HashMap;
import java.util.List;

/**
 * Abstraction of different QueryBuilders.
 */
public abstract class QueryBuilder {
    Validator validator;
    Interpretator interpretator;

    /**
     * Abstract method for creating a query.
     * @param name
     * @param type
     * @param commandList
     * @param arguments
     * @return
     * @throws InputException
     */
    public abstract Query buildQuery(CommandName name, CommandType type, List<String> commandList, HashMap<String,String> arguments) throws InputException;
}
