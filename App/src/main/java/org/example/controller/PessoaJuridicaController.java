package org.example.controller;


import org.example.dao.PessoaJuridicaDaoImplement;
import org.example.model.PessoaJuridica;

import java.util.Scanner;
import java.util.UUID;

public class PessoaJuridicaController {

    public static void getPessoaJuridicaController() throws IllegalAccessException {
        PessoaJuridicaDaoImplement pessoaJuridicaDao = new PessoaJuridicaDaoImplement();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nMenu Pessoa Jurídica:");
            System.out.println("1. Cadastrar Pessoa Jurídica");
            System.out.println("2. Consultar Pessoa Jurídica por ID");
            System.out.println("3. Listar todas as Pessoas Jurídicas");
            System.out.println("4. Sair");
            System.out.print("Escolha uma opção: ");
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    cadastrarPessoaJuridica(scanner, pessoaJuridicaDao);
                    break;
                case 2:
                    consultarPessoaJuridica(scanner, pessoaJuridicaDao);
                    break;
                case 3:
                    listarPessoasJuridicas(pessoaJuridicaDao);
                    break;
                case 4:
                    System.out.println("Saindo...");
                    return;
                default:
                    System.out.println("Opção inválida.");
            }
        }
    }

    private static void cadastrarPessoaJuridica(Scanner scanner, PessoaJuridicaDaoImplement pessoaJuridicaDao) throws IllegalAccessException {
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("CNPJ: ");
        String cnpj = scanner.nextLine();

        PessoaJuridica pessoaJuridica = new PessoaJuridica(UUID.randomUUID(), nome, cnpj);
        pessoaJuridicaDao.insert(pessoaJuridica);
        System.out.println("Pessoa Jurídica cadastrada com sucesso!");
    }

    private static void consultarPessoaJuridica(Scanner scanner, PessoaJuridicaDaoImplement pessoaJuridicaDao) {
        System.out.print("Digite o ID da Pessoa Jurídica: ");
        UUID id = UUID.fromString(scanner.nextLine());
        pessoaJuridicaDao.selectById(id).ifPresentOrElse(
                pessoaJuridica -> System.out.println("Pessoa Jurídica: " + pessoaJuridica.getId() + " - " + pessoaJuridica.getNome() + " - " + pessoaJuridica.getCnpj()),
                () -> System.out.println("Pessoa Jurídica não encontrada.")
        );
    }

    private static void listarPessoasJuridicas(PessoaJuridicaDaoImplement pessoaJuridicaDao) {
        System.out.println("Todas as Pessoas Jurídicas:");
        for (PessoaJuridica pessoaJuridica : pessoaJuridicaDao.selectAll()) {
            System.out.println(pessoaJuridica.getId() + " - " + pessoaJuridica.getNome() + " - " + pessoaJuridica.getCnpj());
        }
    }
}
