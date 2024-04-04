package minisoccerfieldmanagement.service;

import java.util.List;
import minisoccerfieldmanagement.model.Field;

public interface IFieldService {
    
    Boolean add7Field(Field model);

    Boolean add5Field(Field model);
    
    Boolean update(Field model);

    Boolean softDelete(int id);

    Field findById(int id);

    List<Field> findByStatus(String status);

    List<Field> findAll();

    List<Field> findAllCombinedField();

    List<Field> findAllNormalFiled();
    
    List<Field> findAllDeleted();
}
