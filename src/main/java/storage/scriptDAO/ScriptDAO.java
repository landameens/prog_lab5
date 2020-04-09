package storage.scriptDAO;

import storage.exception.RecursionExeption;
import storage.scriptDAO.IScriptDAO;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ScriptDAO implements IScriptDAO {
    private String path;

    public ScriptDAO(String path) {
        this.path = path;
    }

    private String firstCommand = "execute_script " + path;

    @Override
    public List<String> getScript() throws IOException, RecursionExeption {
        String thisCommand = "execute_script " + path;

        File file = new File(path);
        FileReader fileReader = new FileReader(file);
        BufferedReader reader = new BufferedReader(fileReader);


        List<String> scriptInput = new ArrayList<>();
        scriptInput.add(reader.readLine());

        while (reader.ready()) {
            String command = reader.readLine();

            if (command.toLowerCase().equals(thisCommand) || command.toLowerCase().equals(firstCommand)){
                throw new RecursionExeption("Рекурсия!");
            }

            scriptInput.add(command);
        }

        return scriptInput;
    }
}
