package ptithcm.web.Service;

import ptithcm.web.Entity.MonAn;
import java.util.List;

public interface MonAnService {
    List<MonAn> getAllMonAn();
    MonAn getMonAnById(Long id);
    void saveMonAn(MonAn monAn);
    void deleteMonAn(Long id);
}
