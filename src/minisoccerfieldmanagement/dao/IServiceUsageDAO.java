/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package minisoccerfieldmanagement.dao;

import minisoccerfieldmanagement.model.ServiceUsage;
import java.util.List;
/**
 *
 * @author trank
 */
public interface IServiceUsageDAO {
    Boolean add (ServiceUsage serviceUsage);
    Boolean update (ServiceUsage serviceUsage);
    Boolean softDelete (int id);
    ServiceUsage findById (int id);
    ServiceUsage findByMatch (int matchId);
    List <ServiceUsage> findByCustomer (int customerId);
    
}
