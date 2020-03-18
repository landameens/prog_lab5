package app.QueryBuilder;

import app.CommandName;
import app.CommandType;

public class QueryBuilderFactory {
    public QueryBuilder getQueryBuilder (CommandType type){
        switch (type){
            case SIMPLE_COMMAND:
                return new QueryBuilderForSimpleCommands();
            case COMMAND_WITHOUT_ARGUMENTS:
                return new QueryBuilderForCommandsWithoutArguments();
            case COMPOUND_COMMAND:
                return new QueryBuilderForCompoundCommands();
            default:
                throw new IllegalArgumentException("QueryBuilder can not be created. Current CommandType is " + type);

        }
    }
}
