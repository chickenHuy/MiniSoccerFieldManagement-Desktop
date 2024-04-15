package minisoccerfieldmanagement.service;

import java.util.List;
import minisoccerfieldmanagement.dao.IServiceDAO;
import minisoccerfieldmanagement.dao.ServiceDAOImpl;
import minisoccerfieldmanagement.model.Service;

public class ServiceServiceImpl implements IServiceService {

    IServiceDAO serviceDAO = new ServiceDAOImpl();

    @Override
    public Boolean add(Service model) {
        return serviceDAO.add(model);
    }

    @Override
    public Boolean update(Service model) {
        return serviceDAO.update(model);
    }

    @Override
    public Boolean softDelete(int id) {
        return serviceDAO.softDelete(id);
    }

    @Override
    public Service findById(int id) {
        return serviceDAO.findById(id);
    }

    @Override
    public List<Service> findByStatus(String status) {
        return serviceDAO.findByStatus(status);
    }

    @Override
    public List<Service> findAll() {
        return serviceDAO.findAll();
    }

    @Override
    public Boolean updateStatus(int id, String status) {
        return serviceDAO.updateStatus(id, status);
    }

    @Override
    public Boolean updateSoldAndQuantity(int id, int qty) {
        Service service = serviceDAO.findById(id);
        if (service != null) {
            if (service.getQuantity() >= qty) {
                return serviceDAO.updateSoldAndQuantity(id, qty);
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    @Override
    public List<Service> loadDataIntoJTable(String keyword, String status, int limit, int offset, String orderBy, String field) {
        return serviceDAO.loadDataIntoJTable(keyword, status, limit, offset, orderBy, field);
    }

    @Override
    public List<String> loadServiceNameByKeyword(String keyword, String status) {
        return serviceDAO.loadServiceNameByKeyword(keyword, status);
    }
}
