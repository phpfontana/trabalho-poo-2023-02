package org.example.dao;

import org.example.dao.interfaceDao.PessoaFisicaDao;
import org.example.dao.interfaceDao.PessoaJuridicaDao;
import org.example.model.PessoaFisica;
import org.example.model.PessoaJuridica;

public class PessoaFisicaDaoImplement extends CRUDDaoImplements<PessoaFisica> implements PessoaFisicaDao {
    protected PessoaFisicaDaoImplement() {
        super(PessoaFisica.class);
    }
}

