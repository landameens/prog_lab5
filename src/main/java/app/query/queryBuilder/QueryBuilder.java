package app.query.queryBuilder;

import app.*;
import app.Exceptions.InputException;
import app.query.Query;

import java.util.List;
import java.util.Map;

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
    public abstract Query buildQuery(String name,
                                     String type,
                                     List<String> commandList,
                                     Map<String,String> arguments) throws InputException;
}
