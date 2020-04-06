package domain.commandsRepository;

import java.util.ArrayList;
import java.util.List;

public class HistoryRepository implements ICommandsRepository {
    private List<Record> historyList;

    public HistoryRepository() {
        this.historyList = new ArrayList<>();
    }

    @Override
    public void add(Record commandDTO) {
        historyList.add(commandDTO);
    }

    @Override
    public List<Record> getRecords(int quantity) {

        List<Record> answer = new ArrayList<>();

        int number = 0;
        for(Record commandDTO : historyList){
            number++;
            answer.add(commandDTO);

            if(number == quantity){
                break;
            }
        }

        return answer;
    }
}
