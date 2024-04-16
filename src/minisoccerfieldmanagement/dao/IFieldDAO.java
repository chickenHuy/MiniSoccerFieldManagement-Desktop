package minisoccerfieldmanagement.dao;

import java.util.List;
import minisoccerfieldmanagement.model.Field;

public interface IFieldDAO {

    Boolean add7Field(Field model);

    Boolean add5Field(Field model);
    
    Boolean update7Field(Field model);
    
    Boolean update5Field(Field model);

    Boolean softDelete(int id);

    Field findById(int id);

    List<Field> findByStatus(String status);

    List<Field> findAll();

    List<Field> findAllCombinedField();

    List<Field> findAllNormalFiled();

    List<Field> findAllDeleted();
    List<Field> findParent(int id);
    List<Field> findByNameALike(String str, boolean isFindDeleted);
}
