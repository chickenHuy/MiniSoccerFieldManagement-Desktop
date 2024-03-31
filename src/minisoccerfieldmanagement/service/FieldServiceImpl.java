package minisoccerfieldmanagement.service;

import java.util.List;
import minisoccerfieldmanagement.dao.FieldDAOImpl;
import minisoccerfieldmanagement.dao.IFieldDAO;
import minisoccerfieldmanagement.model.Field;

public class FieldServiceImpl implements IFieldService{

    IFieldDAO fieldDAO = new FieldDAOImpl();
    
    @Override
    public Boolean add(Field model) {
        return fieldDAO.add(model);
    }

    @Override
    public Boolean update(Field model) {
        return fieldDAO.update(model);
    }

    @Override
    public Boolean softDelete(int id) {
        return fieldDAO.softDelete(id);
    }

    @Override
    public Field findById(int id) {
        return fieldDAO.findById(id);
    }

    @Override
    public List<Field> findByStatus(String status) {
        return fieldDAO.findByStatus(status);
    }

    @Override
    public List<Field> findAll() {
        return fieldDAO.findAll();
    }

    @Override
    public List<Field> findAllCombinedField() {
        return fieldDAO.findAllCombinedField();
    }

    @Override
    public List<Field> findAllNormalFiled() {
        return fieldDAO.findAllNormalFiled();
    }
    
}
