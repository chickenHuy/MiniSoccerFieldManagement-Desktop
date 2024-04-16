package minisoccerfieldmanagement.service;

import java.util.List;
import minisoccerfieldmanagement.dao.FieldDAOImpl;
import minisoccerfieldmanagement.dao.IFieldDAO;
import minisoccerfieldmanagement.model.Field;

public class FieldServiceImpl implements IFieldService{

    IFieldDAO fieldDAO = new FieldDAOImpl();
    
    @Override
    public Boolean add5Field(Field model) {
        return fieldDAO.add5Field(model);
    }
    
    @Override
    public Boolean add7Field(Field model) {
        return fieldDAO.add7Field(model);
    }

    @Override
    public Boolean update7Field(Field model) {
        return fieldDAO.update7Field(model);
    }
    
    @Override
    public Boolean update5Field(Field model) {
        return fieldDAO.update5Field(model);
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

    @Override
    public List<Field> findAllDeleted() {
        return fieldDAO.findAllDeleted();
    }

    @Override
    public List<Field> findParent(int id) {
        return fieldDAO.findParent(id);
    }
    
    @Override
    public List<Field> findByNameALike(String str, boolean isFindDeleted) {
        return fieldDAO.findByNameALike(str, isFindDeleted);
    }
    
}
