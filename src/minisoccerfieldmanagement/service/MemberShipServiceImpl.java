package minisoccerfieldmanagement.service;

import java.math.BigDecimal;
import java.util.List;
import minisoccerfieldmanagement.dao.IMembershipDAO;
import minisoccerfieldmanagement.dao.MembershipDAOImpl;
import minisoccerfieldmanagement.model.MemberShip;

public class MemberShipServiceImpl implements IMemberShipService{

    IMembershipDAO memberShipDAO = new MembershipDAOImpl();
    
    @Override
    public Boolean add(MemberShip model) {
        return memberShipDAO.add(model);
    }

    @Override
    public List<MemberShip> findAll() {
        return memberShipDAO.findAll();
    }

    @Override
    public MemberShip findById(int id) {
        return memberShipDAO.findById(id);
    }

    @Override
    public MemberShip findBySpendAmount(BigDecimal totalSpend) {
        return memberShipDAO.findBySpendAmount(totalSpend);
    }

    @Override
    public int findDiscountByCustomer(int customerId) {
        return memberShipDAO.findDiscountByCustomer(customerId);
    }

    @Override
    public Boolean softDelete(int id) {
        return memberShipDAO.softDelete(id);
    }

    @Override
    public Boolean update(MemberShip model) {
        return memberShipDAO.update(model);
    }
    
}
