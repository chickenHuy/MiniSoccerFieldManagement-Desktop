package minisoccerfieldmanagement.service;

import java.math.BigDecimal;
import java.sql.Time;
import java.util.List;
import minisoccerfieldmanagement.model.PriceList;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
/**
 *
 * @author trank
 */
public interface IPriceListService {
    Boolean add(PriceList model);

    Boolean update(PriceList model);

    Boolean softDelete(int id);

    List<PriceList> findByDateOfWeek(String date);

    List<PriceList> findAll();

    PriceList findById(int id);

    BigDecimal findPriceByTime(Time dateTimeIn, Time dateTimeOut, String date);
}
