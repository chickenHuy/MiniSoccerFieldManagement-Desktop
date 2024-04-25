/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package minisoccerfieldmanagement.service;

import java.sql.Timestamp;
import java.util.List;
import minisoccerfieldmanagement.model.Transaction;

/**
 *
 * @author trank
 */
public interface ITransactionService {

    Boolean add(Transaction transaction);

    Boolean update(Transaction transaction);

    Boolean softDelete(int id);

    Transaction findById(int id);

    List<Transaction> findByUser(int userId);

    Transaction findByServiceUsage(int serviceUsageId);

    List<Transaction> findByDate(Timestamp date);

    List<Transaction> findByCustomer(int customerId);

    List<Transaction> findByFieldId(int fieldId);

    List<Transaction> findAll();

    List<Transaction> findByFilter(String search, String type, String order, Timestamp date);

    int addTransactionWithReturnId(Transaction transaction);
}
