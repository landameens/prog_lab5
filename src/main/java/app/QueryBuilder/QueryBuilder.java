package app.QueryBuilder;

import app.*;
import app.Exceptions.InputException;

import java.util.HashMap;
import java.util.List;

public abstract class QueryBuilder {
    Validator validator;
    Interpretator interpretator;

    public abstract Query buildQuery(CommandName name, CommandType type, List<String> commandList, HashMap<String,String> arguments) throws InputException;
}
