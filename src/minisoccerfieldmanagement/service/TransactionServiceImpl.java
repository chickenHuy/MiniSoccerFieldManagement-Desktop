/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package minisoccerfieldmanagement.service;

import java.sql.Timestamp;
import java.util.List;
import minisoccerfieldmanagement.dao.ITransactionDAO;
import minisoccerfieldmanagement.dao.TransactionDAOImpl;
import minisoccerfieldmanagement.model.Transaction;

/**
 *
 * @author trank
 */
public class TransactionServiceImpl implements ITransactionService{
    ITransactionDAO transactionDAO;

    public TransactionServiceImpl() {
        transactionDAO = new TransactionDAOImpl();
    }
    

    @Override
    public Boolean add(Transaction transaction) {
        return transactionDAO.add(transaction);
    }

    @Override
    public Boolean update(Transaction transaction) {
        return  transactionDAO.update(transaction);
    }

    @Override
    public Boolean softDelete(int id) {
        return  transactionDAO.softDelete(id);
    }

    @Override
    public Transaction findById(int id) {
        return transactionDAO.findById(id);
    }

    @Override
    public List<Transaction> findByUser(int userId) {
        return transactionDAO.findByUser(userId);
    }

    @Override
    public Transaction findByServiceUsage(int serviceUsageId) {
        return transactionDAO.findByServiceUsage(serviceUsageId);
    }

    @Override
    public List<Transaction> findByDate(Timestamp date) {
        return transactionDAO.findByDate(date);
    }

    @Override
    public List<Transaction> findByCustomer(int customerId) {
        return transactionDAO.findByCustomer(customerId);
    }

    @Override
    public List<Transaction> findByFieldId(int fieldId) {
        return transactionDAO.findByFieldId(fieldId);
    }

    @Override
    public List<Transaction> findAll() {
        return  transactionDAO.findAll();
    }

    @Override
    public List<Transaction> findByFilter(String search, String type, String order, Timestamp date) {
        return  transactionDAO.findByFilter(search, type, order, date);
    }

    @Override
    public int addTransactionWithReturnId(Transaction transaction){
        return transactionDAO.addTransactionWithReturnId(transaction);
    }
}
