package view;

import java.util.Scanner;
import controller.ContaControlador;

public class Principal {

	static Scanner scan = new Scanner(System.in);
	int opcao = 0;

	public static void main(String[] args) {
		exibirMenu();
	}

	// Método para exibir o menu exibir as opções disponiveis com suas respectivas
	// chamadas

	public static void exibirMenu() {
		Scanner scanner = new Scanner(System.in);
		ContaControlador controlador = new ContaControlador();
		int opcao;

		do {
			System.out.println("\n-----MENU-----");
			System.out.println("1. Cadastrar Conta");
			System.out.println("2. Sacar");
			System.out.println("3. Depositar");
			System.out.println("4. Aplicar");
			System.out.println("5. Resgatar");
			System.out.println("6. Exibir Dados da Conta");
			System.out.println("0. Sair");
			System.out.print("\nDigite a opção desejada: ");
			opcao = scan.nextInt();
			scan.nextLine();

			while (opcao > 6 || opcao < 0) {

				System.out.print("Opção inválida. Digite novamente: ");
				opcao = scan.nextInt();
				scan.nextLine();
			}

			switch (opcao) {
			case 1 -> controlador.iniciarSessao();
			case 2 -> controlador.iniciarSaque();
			case 3 -> controlador.iniciarDeposito();
			case 4 -> controlador.iniciarAplicacao();
			case 5 -> controlador.iniciarResgate();
			case 6 -> controlador.iniciarExibicao();
			case 0 -> {
				System.out.println("\n-----FIM DA SESSÃO-----");
				System.exit(0);
			}
			default -> System.out.print("Opção inválida! Tente novamente: ");
			}
		} while (opcao != 0);

		scanner.close();
	}
}
