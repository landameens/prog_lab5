package storage.scriptDAO;

import storage.exception.RecursionExeption;

import java.io.IOException;
import java.util.List;

public interface IScriptDAO {
    List<String> getScript() throws IOException, RecursionExeption;
}
