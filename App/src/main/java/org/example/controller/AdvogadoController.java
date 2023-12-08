package org.example.controller;


import org.example.dao.AdvogadoDaoImplement;
import org.example.model.Advogado;

import java.util.Scanner;
import java.util.UUID;

public class AdvogadoController {

    public static void getAdvogadoController() throws IllegalAccessException {
        AdvogadoDaoImplement advogadoDao = new AdvogadoDaoImplement();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Cadastrar Advogado");
            System.out.println("2. Consultar Advogado por ID");
            System.out.println("3. Listar todos os Advogados");
            System.out.println("4. Sair");
            System.out.print("Escolha uma opção: ");
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    cadastrarAdvogado(scanner, advogadoDao);
                    break;
                case 2:
                    consultarAdvogado(scanner, advogadoDao);
                    break;
                case 3:
                    listarAdvogados(advogadoDao);
                    break;
                case 4:
                    System.out.println("Saindo...");
                    return;
                default:
                    System.out.println("Opção inválida.");
            }
        }
    }

    private static void cadastrarAdvogado(Scanner scanner, AdvogadoDaoImplement advogadoDao) throws IllegalAccessException {
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Número da OAB: ");
        String numeroOAB = scanner.nextLine();
        // Pode adicionar outros campos específicos do Advogado aqui

        Advogado advogado = new Advogado(UUID.randomUUID(), nome, numeroOAB);
        advogadoDao.insert(advogado);
        System.out.println("Advogado cadastrado com sucesso!");
    }

    private static void consultarAdvogado(Scanner scanner, AdvogadoDaoImplement advogadoDao) {
        System.out.print("Digite o ID do Advogado: ");
        UUID id = UUID.fromString(scanner.nextLine());
        advogadoDao.selectById(id).ifPresentOrElse(
                advogado -> System.out.println("Advogado: " + advogado.getId() + " - " + advogado.getNumeroOAB()),
                () -> System.out.println("Advogado não encontrado.")
        );
    }

    private static void listarAdvogados(AdvogadoDaoImplement advogadoDao) {
        System.out.println("Todos os Advogados:");
        for (Advogado advogado : advogadoDao.selectAll()) {
            System.out.println(advogado.getId() + " - " + advogado.getNumeroOAB());
        }
    }
}
