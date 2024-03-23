/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package minisoccerfieldmanagement.dao;

import minisoccerfieldmanagement.model.PriceList;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;
/**
 *
 * @author trank
 */
public interface IPriceListDAO {
    Boolean add(PriceList model);

    Boolean update(PriceList model);

    Boolean softDelete(int id);

    List<PriceList> findByDateOfWeek(String date);

    List<PriceList> findAll();

    PriceList findById(int id);

    BigDecimal findPriceByTime(Timestamp dateTimeIn, Timestamp dateTimeOut, String date);
    
}
