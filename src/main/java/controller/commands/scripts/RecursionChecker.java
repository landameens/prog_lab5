package controller.commands.scripts;

import java.util.Set;
import java.util.TreeSet;

public class RecursionChecker {
    private Set<Integer> scripts = new TreeSet<>();

    public boolean check(int script){
        return scripts.add(script);
    }
}
