package storage;

import storage.exception.RecursionExeption;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ScriptDAO implements IScriptDAO {
    private String path;

    public ScriptDAO(String path) {
        this.path = path;
    }

    @Override
    public List<String> getScript() throws IOException, RecursionExeption {
        String thisCommand = "execute_script " + path;

        File file = new File(path);
        FileReader fr = new FileReader(file);
        BufferedReader reader = new BufferedReader(fr);


        List<String> scriptInput = new ArrayList<>();
        scriptInput.add(reader.readLine());

        while (reader.ready()) {
            String command = reader.readLine();

            if (command.toLowerCase().equals(thisCommand)){
                throw new RecursionExeption("Рекурсия!");
            }

            scriptInput.add(command);
        }

        return scriptInput;
    }
}
