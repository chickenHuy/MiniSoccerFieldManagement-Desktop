package minisoccerfieldmanagement.service;

import java.util.List;
import minisoccerfieldmanagement.model.Service;

public interface IServiceService {

    Boolean add(Service model);

    Boolean update(Service model);

    Boolean softDelete(int id);

    Service findById(int id);

    List<Service> findByStatus(String status);

    List<Service> findAll();

    Boolean updateStatus(int id, String status);

    Boolean updateSoldAndQuantity(int id, int qty);

    List<Service> loadDataIntoJTable(String keyword, String status, int limit, int offset, String orderBy, String field);

    List<String> loadServiceNameByKeyword(String keyword, String status);
}
