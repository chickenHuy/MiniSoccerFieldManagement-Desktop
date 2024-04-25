/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package minisoccerfieldmanagement.service;

import java.util.List;
import minisoccerfieldmanagement.model.ServiceUsage;

/**
 *
 * @author trank
 */
public interface IServiceUsageService {

    Boolean add(ServiceUsage serviceUsage);

    Boolean update(ServiceUsage serviceUsage);

    Boolean softDelete(int id);

    ServiceUsage findById(int id);

    ServiceUsage findByMatch(int matchId);

    List<ServiceUsage> findByCustomer(int customerId);

    int addServiceUsageWithReturnId(ServiceUsage serviceUsage);
}
