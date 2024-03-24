package minisoccerfieldmanagement.service;

import java.util.List;
import minisoccerfieldmanagement.model.ServiceItems;

public interface IServiceItemsService {
    Boolean add(ServiceItems model);

    ServiceItems findById(int id);

    List<ServiceItems> findByService(int serviceId);

    List<ServiceItems> findByServiceUsage(int serviceUsageId);

    Boolean softDelete(int id);

    Boolean updateQty(int id,int quantity);
}
