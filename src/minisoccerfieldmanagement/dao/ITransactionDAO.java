/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package minisoccerfieldmanagement.dao;
import java.sql.Timestamp;
import java.util.List;
import minisoccerfieldmanagement.model.Transaction;
/**
 *
 * @author trank
 */
public interface ITransactionDAO {
    Boolean add (Transaction transaction);
    Boolean update (Transaction transaction);
    Boolean softDelete (int id);
    Transaction findById (int id);
    List <Transaction> findByUser (int userId);
    Transaction findByServiceUsage (int serviceUsageId);
    void findByDate (Timestamp date);
    void findByCustomer (int customerId);
    void findByFieldId (int fieldId);
}
