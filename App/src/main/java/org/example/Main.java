package org.example;

import org.example.dao.PessoasDaoImplement;
import org.example.dao.interfaceDao.PessoaDao;
import org.example.enumModel.TipoPessoa;
import org.example.model.Pessoa;

import java.util.UUID;

public class Main {
    public static void main(String[] args) throws IllegalAccessException {
        PessoasDaoImplement pessoaDao = new PessoasDaoImplement();

        // Inserting a Pessoa
        Pessoa pessoa = new Pessoa(UUID.randomUUID(), "John Doe", "12345678912", TipoPessoa.PESSOAJURIDICA.getValue());
        pessoaDao.insert(pessoa);

        // Selecting all Pessoas
        System.out.println("All Pessoas:");
        for (Pessoa p : pessoaDao.selectAll()) {
            System.out.println(p.getId() + " - " + p.getNome() + " - " + p.getCpfCnpj());
        }

        // Updating a Pessoa by ID
        UUID idToUpdate = pessoa.getId();
        Pessoa pessoaToUpdate = new Pessoa(idToUpdate, "Updated Name", "12345678912", TipoPessoa.PESSOAFISICA.getValue());
        pessoaDao.updateById(idToUpdate, pessoaToUpdate);

        // Selecting a Pessoa by ID
        UUID idToSelect = pessoa.getId();
        pessoaDao.selectById(idToSelect).ifPresent(selectedPessoa -> {
            System.out.println("Selected Pessoa: " + selectedPessoa.getId() + " - " + selectedPessoa.getNome() + " - " + selectedPessoa.getCpfCnpj());
        });

        // Deleting a Pessoa by ID
        UUID idToDelete = pessoa.getId();
        boolean isDeleted = pessoaDao.deleteById(idToDelete);
        System.out.println("Is Pessoa deleted? " + isDeleted);
    }
}
