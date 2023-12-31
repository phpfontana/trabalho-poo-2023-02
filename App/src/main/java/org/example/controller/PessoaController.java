package org.example.controller;

import org.example.dao.PessoaFisicaDaoImplement;
import org.example.dao.PessoasDaoImplement;
import org.example.enumModel.TipoPessoa;
import org.example.model.Pessoa;

import java.util.Scanner;
import java.util.UUID;

public class PessoaController {

    public static void getPessoaControler() throws IllegalAccessException {
    PessoasDaoImplement pessoaDao = new PessoasDaoImplement();
    Scanner scanner = new Scanner(System.in);

        while (true) {
        System.out.println("\nMenu:");
        System.out.println("1. Cadastrar Pessoa");
        System.out.println("2. Consultar Pessoa por ID");
        System.out.println("3. Listar todas as Pessoas");
        System.out.println("4. Deletar Pessoas Jurídicas por id");
        System.out.println("5. Sair");
        System.out.print("Escolha uma opção: ");
        int choice = Integer.parseInt(scanner.nextLine());

        switch (choice) {
            case 1:
                cadastrarPessoa(scanner, pessoaDao);
                break;
            case 2:
                consultarPessoa(scanner, pessoaDao);
                break;
            case 3:
                listarPessoas(pessoaDao);
                break;
            case 4:
                deletarPessoa(scanner, pessoaDao);
                break;
            case 5:
                System.out.println("Saindo...");
                return;
            default:
                System.out.println("Opção inválida.");
        }
    }
}

    public static void deletarPessoa(Scanner scanner, PessoasDaoImplement pessoaDao){
        System.out.print("ID: ");
        UUID id = UUID.fromString(scanner.nextLine());

        pessoaDao.deleteById(id);
        System.out.println("Pessoa deletada com sucesso!");
    }
    private static void cadastrarPessoa(Scanner scanner, PessoasDaoImplement pessoaDao) throws IllegalAccessException {
        System.out.print("Nome: ");
        String nome = scanner.nextLine(); // Lê o nome composto completo
        System.out.print("CPF/CNPJ: ");
        String cpfCnpj = scanner.nextLine(); // Lê o CPF/CNPJ completo
        System.out.print("Tipo (1 para Pessoa Física, 2 para Pessoa Jurídica): ");
        int tipo = Integer.parseInt(scanner.nextLine()); // Lê o tipo completo
        TipoPessoa tipoPessoa = (tipo == 1) ? TipoPessoa.PESSOAFISICA : TipoPessoa.PESSOAJURIDICA;

        Pessoa pessoa = new Pessoa(UUID.randomUUID(), nome, cpfCnpj, tipoPessoa.getValue());
        pessoaDao.insert(pessoa);
        System.out.println("Pessoa cadastrada com sucesso!");
    }

    private static void consultarPessoa(Scanner scanner, PessoasDaoImplement pessoaDao) {
        System.out.print("Digite o ID da pessoa: ");
        UUID id = UUID.fromString(scanner.nextLine());
        pessoaDao.selectById(id).ifPresentOrElse(
                pessoa -> System.out.println("Pessoa: " + pessoa.getId() + " - " + pessoa.getNome() + " - " + pessoa.getCpfCnpj()),
                () -> System.out.println("Pessoa não encontrada.")
        );
    }

    private static void listarPessoas(PessoasDaoImplement pessoaDao) {
        System.out.println("Todas as Pessoas:");
        for (Pessoa p : pessoaDao.selectAll()) {
            System.out.println(p.getId() + " - " + p.getNome() + " - " + p.getCpfCnpj());
        }
    }
}

