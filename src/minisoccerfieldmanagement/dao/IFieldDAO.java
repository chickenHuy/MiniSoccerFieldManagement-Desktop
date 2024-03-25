package minisoccerfieldmanagement.dao;

import java.util.List;
import minisoccerfieldmanagement.model.Field;

public interface IFieldDAO {

    Boolean add(Field model);

    Boolean update(Field model);

    Boolean softDelete(int id);

    Field findById(int id);

    List<Field> findByStatus(String status);

    List<Field> findAll();

    List<Field> findAllCombinedField();

    List<Field> findAllNormalFiled();
}
