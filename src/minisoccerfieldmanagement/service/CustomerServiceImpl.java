package minisoccerfieldmanagement.service;

import java.math.BigDecimal;
import java.util.List;
import minisoccerfieldmanagement.dao.CustomerDAOImpl;
import minisoccerfieldmanagement.dao.ICustomerDAO;
import minisoccerfieldmanagement.model.Customer;
import minisoccerfieldmanagement.model.MemberShip;

public class CustomerServiceImpl implements ICustomerService{
    
    ICustomerDAO customerDAO = new CustomerDAOImpl();
    
    @Override
    public Boolean add(Customer model) {
        return customerDAO.add(model);
    }

    @Override
    public Customer findById(int id) {
        return customerDAO.findById(id);
    }

    @Override
    public List<Customer> findByMemberShip(int memberShipId) {
        return customerDAO.findByMemberShip(memberShipId);
    }

    @Override
    public Customer findByPhoneNumber(String phoneNumber) {
       return customerDAO.findByPhoneNumber(phoneNumber);
    }

    @Override
    public Boolean softDelete(int id) {
        return customerDAO.softDelete(id);
    }

    @Override
    public Boolean update(Customer model) {
        return customerDAO.update(model);
    }

    @Override
    public Boolean updateTotalSpend(int id, BigDecimal increment) {        
        Customer customer = customerDAO.findById(id);
        IMemberShipService memberShipService = new MemberShipServiceImpl();
        if (customer == null)
            return false;
        customer.setTotalSpend(customer.getTotalSpend().add(increment));
        MemberShip newMemberShip = memberShipService.findBySpendAmount(customer.getTotalSpend());
        customer.setMemberShipId(newMemberShip.getId());
        return update(customer);
    }
    
}
