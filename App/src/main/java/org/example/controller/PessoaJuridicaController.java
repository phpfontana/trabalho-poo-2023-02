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
            System.out.println("4. Deletar Pessoas Jurídicas por id");
            System.out.println("5. Sair");
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
                    deletarPessoaJuridica(scanner,pessoaJuridicaDao);
                    break;
                case 5:
                    System.out.println("Saindo...");
                    return;
                default:
                    System.out.println("Opção inválida.");
            }
        }
    }

    public static void deletarPessoaJuridica(Scanner scanner,PessoaJuridicaDaoImplement pessoaJuridicaDao){
        System.out.print("ID: ");
        UUID id = UUID.fromString(scanner.nextLine());

        pessoaJuridicaDao.deleteById(id);
        System.out.println("Pessoa Juridica deletada com sucesso!");
    }

    private static void cadastrarPessoaJuridica(Scanner scanner, PessoaJuridicaDaoImplement pessoaJuridicaDao) throws IllegalAccessException {
        System.out.print("ID: ");
        UUID id = UUID.fromString(scanner.nextLine());
        System.out.print("Razão Social: ");
        String razao_social = scanner.nextLine();
        System.out.print("Nome Fantasia: ");
        String nome_fantasia = scanner.nextLine();
        System.out.print("Inscrição Estadual: ");
        String inscricao_estadual = scanner.nextLine();

        PessoaJuridica pessoaJuridica = new PessoaJuridica(id, razao_social, nome_fantasia, inscricao_estadual);
        pessoaJuridicaDao.insert(pessoaJuridica);
        System.out.println("Pessoa Jurídica cadastrada com sucesso!");
    }

    private static void consultarPessoaJuridica(Scanner scanner, PessoaJuridicaDaoImplement pessoaJuridicaDao) {
        System.out.print("Digite o ID da Pessoa Jurídica: ");
        UUID id = UUID.fromString(scanner.nextLine());
        pessoaJuridicaDao.selectById(id).ifPresentOrElse(
                pessoaJuridica -> System.out.println("Pessoa Jurídica: " + pessoaJuridica.getId() + " - " + pessoaJuridica.getRazaoSocial() + " - " + pessoaJuridica.getNomeFantasia()+ " - " + pessoaJuridica.getInscricaoEstadual()),
                () -> System.out.println("Pessoa Jurídica não encontrada.")
        );
    }

    private static void listarPessoasJuridicas(PessoaJuridicaDaoImplement pessoaJuridicaDao) {
        System.out.println("Todas as Pessoas Jurídicas:");
        for (PessoaJuridica pessoaJuridica : pessoaJuridicaDao.selectAll()) {
            System.out.println(pessoaJuridica.getId() + " - " + pessoaJuridica.getRazaoSocial() + " - " + pessoaJuridica.getNomeFantasia()+ " - " + pessoaJuridica.getInscricaoEstadual());
        }
    }
}
