package minisoccerfieldmanagement.dao;

import java.util.List;
import minisoccerfieldmanagement.model.Service;

public interface IServiceDAO {
    
    Boolean add(Service model);

    Boolean update(Service model);

    Boolean softDelete(int id);
    
    Service findById(int id);
    
    List<Service> findByStatus(String status);
    
    List<Service> findAll();
    
    Boolean updateStatus(int id, String status);
    
    Boolean updateSoldAndQuantity(int id, int qty);
}
