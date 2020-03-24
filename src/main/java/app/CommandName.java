package app;

/**
 * This enum contains all command names
 */
public enum CommandName {
    HELP("help"),
    INFO("info"),
    SHOW("show"),
    ADD("add"),
    UPDATE("update"),
    REMOVE_BY_ID("removeById"),
    CLEAR("clear"),
    SAVE("save"),
    EXECUTE_SCRIPT("executeScript"),
    EXIT("exit"),
    ADD_IF_MIN("addIfMin"),
    REMOVE_LOWER("removeLover"),
    HISTORY("history"),
    COUNT_BY_GROUP_ADMIN("countByGroupAdmin"),
    FILTER_BY_SHOULD_BE_EXPELLED("filterByShouldBeExpelled"),
    FILTER_LESS_THEN_SHOULD_BE_EXPELLED("filterLessThanShouldBeExpelled"),
    TEST("test");

    private String name;

    CommandName(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public static CommandName getCommandNameEnum(String name){
        CommandName[] commandNames = CommandName.values();

        for (CommandName commandName : commandNames){
            if(name.equals(commandName.getName())){
                return commandName;
            }
        }

        return null;
    }

}
