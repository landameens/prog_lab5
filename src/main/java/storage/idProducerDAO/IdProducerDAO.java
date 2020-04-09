package storage.idManagerDAO;

import java.util.List;

public class IdManagerDAO implements IIdManagerDAO{
    private String path;

    public IdManagerDAO(String path) {
        this.path = path;
    }

    @Override
    public List<Integer> getDefaultIdList() {

    }
}
