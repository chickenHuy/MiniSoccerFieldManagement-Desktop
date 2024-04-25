/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package minisoccerfieldmanagement.service;

import java.util.List;
import minisoccerfieldmanagement.dao.IServiceUsageDAO;
import minisoccerfieldmanagement.dao.ServiceUsageDAOImpl;
import minisoccerfieldmanagement.model.ServiceUsage;

/**
 *
 * @author trank
 */
public class ServiceUsageServiceImpl implements IServiceUsageService {

    IServiceUsageDAO serviceUsageDAO;

    public ServiceUsageServiceImpl() {
        serviceUsageDAO = new ServiceUsageDAOImpl();
    }

    @Override
    public Boolean add(ServiceUsage serviceUsage) {
        return serviceUsageDAO.add(serviceUsage);
    }

    @Override
    public Boolean update(ServiceUsage serviceUsage) {
        return serviceUsageDAO.update(serviceUsage);
    }

    @Override
    public Boolean softDelete(int id) {
        return serviceUsageDAO.softDelete(id);
    }

    @Override
    public ServiceUsage findById(int id) {
        return serviceUsageDAO.findById(id);
    }

    @Override
    public ServiceUsage findByMatch(int matchId) {
        return serviceUsageDAO.findByMatch(matchId);
    }

    @Override
    public List<ServiceUsage> findByCustomer(int customerId) {
        return serviceUsageDAO.findByCustomer(customerId);
    }

    @Override
    public int addServiceUsageWithReturnId(ServiceUsage serviceUsage) {
        return serviceUsageDAO.addServiceUsageWithReturnId(serviceUsage);
    }
}
