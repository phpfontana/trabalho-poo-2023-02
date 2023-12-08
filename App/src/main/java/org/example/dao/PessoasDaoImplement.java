package org.example.dao;

import org.example.dao.CRUDDaoImplements;
import org.example.dao.interfaceDao.PessoaDao;
import org.example.model.Pessoa;

public class PessoasDaoImplement extends CRUDDaoImplements<Pessoa> implements PessoaDao {

    public PessoasDaoImplement() {
        super(Pessoa.class);
    }
}
