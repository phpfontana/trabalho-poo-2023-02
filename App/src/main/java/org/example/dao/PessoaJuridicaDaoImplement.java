package org.example.dao;

import org.example.dao.interfaceDao.PessoaJuridicaDao;
import org.example.model.PessoaJuridica;

public class PessoaJuridicaDaoImplement extends CRUDDaoImplements<PessoaJuridica> implements PessoaJuridicaDao {
    public PessoaJuridicaDaoImplement() {
        super(PessoaJuridica.class);
    }
}

