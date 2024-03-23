/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package minisoccerfieldmanagement.dao;

import java.math.BigDecimal;
import java.util.List;
import minisoccerfieldmanagement.model.Customer;

/**
 *
 * @author trank
 */
public interface ICustomerDAO {

    Boolean add(Customer model);

    Customer findById(int id);

    List<Customer> findByMemberShip(int memberShipId);

    Customer findByPhoneNumber(String phoneNumber);

    Boolean softDelete(int id);

    Boolean update(Customer model);

    Boolean updateTotalSpend(int id, BigDecimal increment);
    
}
