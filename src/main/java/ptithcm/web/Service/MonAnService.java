package ptithcm.web.Service;

import ptithcm.web.Entity.MonAn;
import java.util.List;

public interface MonAnService {
    List<MonAn> getAllMonAn();
    List<MonAn> getMonAnByTen(String ten);
    MonAn getMonAnById(Long id);
    void saveMonAn(MonAn monAn);
    void deleteMonAn(Long id);
}
