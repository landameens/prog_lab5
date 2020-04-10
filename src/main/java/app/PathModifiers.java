package app;

public enum PathModifiers {
    ABSOLUTE("absolute"),
    RELATIVE("relative");

    private String name;
    PathModifiers(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static PathModifiers getPathModifiers(String name) {
        PathModifiers[] modifiers = PathModifiers.values();
        for (PathModifiers modifier : modifiers) {
            if (name.equals(modifier.getName())) {
                return modifier;
            }
        }
        return null;
    }
}
