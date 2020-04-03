package domain.commandsRepository;

import java.util.ArrayList;
import java.util.List;

//TODO: нейминг не дает никакого контекста для понимания для чего этот класс, если переименовать в HistoryRepository, то из контекста станет все ясно
public class ArrayListCommandsRepository implements ICommandsRepository {
    private List<CommandDTO> historyList;

    public ArrayListCommandsRepository() {
        this.historyList = new ArrayList<>();
    }

    @Override
    public void add(CommandDTO commandDTO) {
        historyList.add(commandDTO);
    }

    //TODO: Здесь и надо прокидовать hetRecords(int quantity) и в зависимость от прокидываемого значения возвращать запрошенное количество записей
    @Override
    public List<CommandDTO> getHistory() {
        return historyList;
    }

    //TODO: опять забыла что такое паттерн репозиторий - он лишь оперирует данными (добавляет, удаляет, обновляет , но не содержит ничего более)
    // то что здесь написано надо перенести непосредственно в HistoryCommand
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
