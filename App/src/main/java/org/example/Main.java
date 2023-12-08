package org.example;

import org.example.controller.AdvogadoController;
import org.example.controller.PessoaController;
import org.example.controller.PessoaFisicaController;
import org.example.controller.PessoaJuridicaController;
import org.example.dao.PessoasDaoImplement;
import org.example.enumModel.TipoPessoa;
import org.example.model.Pessoa;
import org.example.model.PessoaFisica;

import java.util.Scanner;
import java.util.UUID;

public class Main {
    public static void main(String[] args) throws IllegalAccessException {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Advogados");
            System.out.println("2. Pessoas Físicas");
            System.out.println("3. Pessoas Jurídicas");
            System.out.println("4. Pessoa");
            System.out.println("5. Sair");
            System.out.print("Escolha uma opção: ");
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    AdvogadoController.getAdvogadoController();
                    break;
                case 2:
                    PessoaFisicaController.getPessoaFisicaController();
                    break;
                case 3:
                    PessoaJuridicaController.getPessoaJuridicaController();
                    break;
                case 4:
                    PessoaController.getPessoaControler();
                    break;
                case 5:
                    System.out.println("Saindo...");
                    return;
                default:
                    System.out.println("Opção inválida.");
            }
        }

    }
}
