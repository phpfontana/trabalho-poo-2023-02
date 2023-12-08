package org.example.dao;

import org.example.dao.interfaceDao.AdvogadoDao;
import org.example.model.Advogado;

public class AdvogadoDaoImplement extends CRUDDaoImplements<Advogado> implements AdvogadoDao {
    public AdvogadoDaoImplement() {
        super(Advogado.class);
    }
}
