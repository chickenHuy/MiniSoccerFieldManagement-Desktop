/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package minisoccerfieldmanagement.dao;

import java.math.BigDecimal;
import java.util.List;
import minisoccerfieldmanagement.model.MemberShip;

/**
 *
 * @author trank
 */
public interface IMembershipDAO {

    Boolean add(MemberShip model);

    List<MemberShip> findAll();

    MemberShip findById(int id);

    MemberShip findBySpendAmount(BigDecimal totalSpend);

    int findDiscountByCustomer(int customerId);

    Boolean softDelete(int id);

    Boolean update(MemberShip model);
    
    int findIdByName(String name);
    
}
