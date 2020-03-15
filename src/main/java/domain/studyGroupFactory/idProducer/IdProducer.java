package domain.studyGroupFactory.idProducer;

import java.util.LinkedList;
import java.util.List;

public class IdProducer {
        private List<Long> idList;

        public IdProducer(){
           idList = initList();
        }

        public long getId(){
            int k = 0;
            Long resultId = idList.get(k);
            idList.remove(k);
            return resultId;
        }

        private List<Long> initList(){
            List<Long> list = new LinkedList<>();
            for (long i = 1; i < 100; i++){
                list.add(i);
            }
            return list;
        }
}
