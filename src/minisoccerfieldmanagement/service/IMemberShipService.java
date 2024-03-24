package minisoccerfieldmanagement.service;

import java.math.BigDecimal;
import java.util.List;
import minisoccerfieldmanagement.model.MemberShip;


public interface IMemberShipService {
    Boolean add(MemberShip model);

    List<MemberShip> findAll();

    MemberShip findById(int id);

    MemberShip findBySpendAmount(BigDecimal totalSpend);

    int findDiscountByCustomer(int customerId);

    Boolean softDelete(int id);

    Boolean update(MemberShip model);
}
