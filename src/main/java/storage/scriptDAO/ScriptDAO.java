package storage.scriptDAO;

import storage.exception.RecursionExeption;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class ScriptDAO implements IScriptDAO {
    private String path;

    public ScriptDAO(String path) {
        this.path = path;
    }

    @Override
    public List<String> getScript() throws IOException {
        File file = new File(path);
        FileReader fileReader = new FileReader(file);
        BufferedReader reader = new BufferedReader(fileReader);

        List<String> scriptInput = new ArrayList<>();
        scriptInput.add(reader.readLine());

        while (reader.ready()) {
            String command = reader.readLine();

            scriptInput.add(command);
        }

        return scriptInput;
    }
}
