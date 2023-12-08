package org.example.controller;

import org.example.dao.PessoaFisicaDaoImplement;
import org.example.model.PessoaFisica;

import java.util.Scanner;
import java.util.UUID;

public class PessoaFisicaController {

    public static void getPessoaFisicaController() throws IllegalAccessException {
        PessoaFisicaDaoImplement pessoaFisicaDao = new PessoaFisicaDaoImplement();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nMenu Pessoa Física:");
            System.out.println("1. Cadastrar Pessoa Física");
            System.out.println("2. Consultar Pessoa Física por ID");
            System.out.println("3. Listar todas as Pessoas Físicas");
            System.out.println("4. Sair");
            System.out.print("Escolha uma opção: ");
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    cadastrarPessoaFisica(scanner, pessoaFisicaDao);
                    break;
                case 2:
                    consultarPessoaFisica(scanner, pessoaFisicaDao);
                    break;
                case 3:
                    listarPessoasFisicas(pessoaFisicaDao);
                    break;
                case 4:
                    System.out.println("Saindo...");
                    return;
                default:
                    System.out.println("Opção inválida.");
            }
        }
    }

    private static void cadastrarPessoaFisica(Scanner scanner, PessoaFisicaDaoImplement pessoaFisicaDao) throws IllegalAccessException {
        System.out.print("ID: ");
        UUID id = UUID.fromString(scanner.nextLine());
        System.out.print("RG: ");
        String cpf = scanner.nextLine();


        PessoaFisica pessoaFisica = new PessoaFisica(id, id, cpf);
        pessoaFisicaDao.insert(pessoaFisica);
        System.out.println("Pessoa Física cadastrada com sucesso!");
    }

    private static void consultarPessoaFisica(Scanner scanner, PessoaFisicaDaoImplement pessoaFisicaDao) {
        System.out.print("Digite o ID da Pessoa Física: ");
        UUID id = UUID.fromString(scanner.nextLine());
        pessoaFisicaDao.selectById(id).ifPresentOrElse(
                pessoaFisica -> System.out.println("Pessoa Física: " + pessoaFisica.getId() + " - " + pessoaFisica.getNome() + " - " + pessoaFisica.getCpf()),
                () -> System.out.println("Pessoa Física não encontrada.")
        );
    }

    private static void listarPessoasFisicas(PessoaFisicaDaoImplement pessoaFisicaDao) {
        System.out.println("Todas as Pessoas Físicas:");
        for (PessoaFisica pessoaFisica : pessoaFisicaDao.selectAll()) {
            System.out.println(pessoaFisica.getId() + " - " + pessoaFisica.getNome() + " - " + pessoaFisica.getCpf());
        }
    }
}
