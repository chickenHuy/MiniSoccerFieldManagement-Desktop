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
    Boolean softDelete (String id);
    Transaction findById (String id);
    List <Transaction> findByUser (String userId);
    Transaction findByServiceUsage (String serviceUsageId);
    void findByDate (Timestamp date);
    void findByCustomer (String customerId);
    void findByFieldId (String fieldId);
}
