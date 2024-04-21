package minisoccerfieldmanagement.service;

import java.math.BigDecimal;
import java.util.List;
import minisoccerfieldmanagement.model.Customer;

public interface ICustomerService {
    
    Boolean add(Customer model);

    Customer findById(int id);

    List<Customer> findByMemberShip(int memberShipId);

    Customer findByPhoneNumber(String phoneNumber);

    Boolean softDelete(int id);

    Boolean update(Customer model);

    Boolean updateTotalSpend(int id, BigDecimal increment);
    
    List<Customer> findAll();
    
    Boolean checkPhoneNumberExist(String phoneNumber);
    
    Boolean checkPhoneNumberExistExceptCurrent(int id, String phoneNumber);
    
    List<Customer> findAllAndFilter(String content, int membershipId, int displayType);
    
}
