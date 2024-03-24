package minisoccerfieldmanagement.service;

import java.util.List;
import minisoccerfieldmanagement.dao.IServiceItemsDAO;
import minisoccerfieldmanagement.dao.ServiceItemsDAOImpl;
import minisoccerfieldmanagement.model.ServiceItems;

public class ServiceItemsServiceImpl implements IServiceItemsService{

    IServiceItemsDAO serviceItemsDAO = new ServiceItemsDAOImpl();
    
    @Override
    public Boolean add(ServiceItems model) {
        return serviceItemsDAO.add(model);
    }

    @Override
    public ServiceItems findById(int id) {
        return serviceItemsDAO.findById(id);
    }

    @Override
    public List<ServiceItems> findByService(int serviceId) {
        return serviceItemsDAO.findByService(serviceId);
    }

    @Override
    public List<ServiceItems> findByServiceUsage(int serviceUsageId) {
        return serviceItemsDAO.findByServiceUsage(serviceUsageId);
    }

    @Override
    public Boolean softDelete(int id) {
        return serviceItemsDAO.softDelete(id);
    }

    @Override
    public Boolean updateQty(int id, int quantity) {
        return serviceItemsDAO.updateQty(id, quantity);
    }
    
}
