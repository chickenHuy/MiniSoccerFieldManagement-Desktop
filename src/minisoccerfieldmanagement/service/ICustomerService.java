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
}
