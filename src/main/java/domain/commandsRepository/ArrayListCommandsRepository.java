package domain.commandsRepository;

import java.util.ArrayList;
import java.util.List;

public class ArrayListCommandsRepository implements ICommandsRepository {
    private List<CommandDTO> historyList;

    public ArrayListCommandsRepository() {
        this.historyList = new ArrayList<>();
    }

    @Override
    public void add(CommandDTO commandDTO) {
        historyList.add(commandDTO);
    }

    @Override
    public List<CommandDTO> getHistory() {
        return historyList;
    }

    @Override
    public String getHistorytext() {
        StringBuilder answer = new StringBuilder();

        int number = 0;
        for(CommandDTO commandDTO : historyList){
            number++;
            answer.append(commandDTO.toString());

            if(number == 15){
                break;
            }
        }

        return answer.toString();
    }


}
