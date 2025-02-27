package controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import model.ContaCorrente;
import model.ContaPoupanca;

public class ContaControlador {

	private static Scanner scan = new Scanner(System.in);
	private static List<ContaCorrente> listaCC = new ArrayList<>();
	private static List<ContaPoupanca> listaCP = new ArrayList<>();
	private static ContaCorrente cc;
	private static ContaPoupanca cp;
	private static int opcao = 0;
	private static double valor = 0;
	private static String nome;
	private static String cpf = "";
	private static String numeroConta = "";

	// Méotodo para cadastrar uma nova conta

	public void iniciarSessao() {
		System.out.println("Olá, seja bem vindo(a) ao FucturaBank. Vamos realizar seu cadastro.");
		System.out.print("\nDigite seu nome completo: ");
		nome = scan.nextLine();

		String[] primeiroNome = nome.split(" ");

		System.out.print("\nOlá " + primeiroNome[0] + "! Agora digite seu CPF (somente números): ");
		cpf = scan.nextLine();

		// Condicional de erro para se caso o CPF digitado não tenha 11 dígitos

		while (cpf.length() != 11) {
			System.out.print("CPF Inválido. Digite novamente: ");
			cpf = scan.nextLine();
		}

		/*
		 * O usuário irá digitar que se quer abrir uma conta corrente ou conta poupanca
		 * Após escolher, o algoritmo irá verificar se o CPF digitado já está
		 * cadastrado. Se estiver, ele imprime os dados bancários já cadastrado, senão,
		 * ele cria uma nova conta bancária
		 */

		System.out.println("Você deseja abrir uma Conta Corrente ou uma Conta Poupança?");
		System.out.println("\n1. Conta Corrente\n2. Conta Poupança");
		System.out.print("\nEscolha a opção desejada: ");
		opcao = scan.nextInt();
		scan.nextLine();

		while (opcao > 2 || opcao < 1) {
			System.out.print("Opção inválida. Digite novamente: ");
			opcao = scan.nextInt();
			scan.nextLine();
		}

		switch (opcao) {
		case 1 -> {
			for (ContaCorrente buscar : listaCC) {
				if (buscar.getCpf().equalsIgnoreCase(cpf)) {
					System.out.println("Esse CPF já está cadastrado em Conta Corrente.");
					buscar.exibirDados();
					view.Principal.exibirMenu();
				}
			}
		}
		case 2 -> {
			for (ContaPoupanca achar : listaCP) {
				if (achar.getCpf().equalsIgnoreCase(cpf)) {
					System.out.println("Esse CPF já está cadastrado em Conta Poupança.");
					achar.exibirDados();
					view.Principal.exibirMenu();
				}
			}
		}
		}

		/*
		 * O usuário tem a opção de inserir um valor de deposito inicial ou não. Esse
		 * bloco de código também tem uma condicional que proíbe que o valor digitado
		 * seja menor que R$0
		 */

		System.out.print("\nInsira o valor do depósito incial. Caso não queira depositar, basta digitar 0: R$");
		switch (opcao) {
		case 1 -> {
			valor = scan.nextDouble();
			scan.nextLine();
			while (valor < 0) {
				System.out.print("Valor inválido. Digite novamente: R$");
				valor = scan.nextDouble();
				scan.nextLine();
			}

			cc = new ContaCorrente(ContaCorrente.gerarNumeroConta(), nome, cpf, valor);
			listaCC.add(cc);
			System.out.println("\n-----CONTA CORRENTE CADASTRADA COM SUCESSO-----");
		}
		case 2 -> {
			valor = scan.nextDouble();
			scan.nextLine();
			while (valor < 0) {
				System.out.print("Valor inválido. Digite novamente: R$");
				valor = scan.nextDouble();
				scan.nextLine();
			}

			cp = new ContaPoupanca(ContaPoupanca.gerarNumeroConta(), nome, cpf, valor);
			listaCP.add(cp);
			System.out.println("\n-----CONTA POUPANÇA CADASTRADA COM SUCESSO-----");
		}

		}

		view.Principal.exibirMenu();

	}

	/*
	 * Método para sacar dinheiro da Conta Corrente. Caso o valor solicitado mais a
	 * taxa de saque seja maior que o valor do saldo, o programa pedirá pra inserir
	 * um valor válido
	 */

	public void iniciarSaque() {
		System.out.print("\nDigite o numero da Conta Corrente: ");
		numeroConta = scan.next();

		for (ContaCorrente buscar : listaCC) {
			if (buscar.getNumeroConta().equalsIgnoreCase(numeroConta)) {
				System.out.print("Digite o valor a ser sacado (Será cobrado uma taxa de R$6,70): R$");
				valor = scan.nextDouble();

				while (valor <= 0) {
					System.out.print("Valor inválido. Digite novamente: R$");
					valor = scan.nextDouble();
				}
				buscar.sacar(valor);
			}
		}
		System.out.println("\n-----SAQUE REALIZADO COM SUCESSO-----");
		view.Principal.exibirMenu();
	}

	/*
	 * Método para exibir todas as contas cadastradas de um determinado CPF. Caso o
	 * CPF não tenha conta corrente e/ou conta pounpança cadastrada, o programa irpa
	 * notificar.
	 */

	public void iniciarExibicao() {
		System.out.println("\nDigite o seu CPF: ");
		cpf = scan.nextLine();

		for (ContaCorrente buscar : listaCC) {
			if (buscar.getCpf().equalsIgnoreCase(cpf)) {
				buscar.exibirDados();
				System.out.println("Tipo: Conta Corrente");
			} else {
				System.out.println("\nEsse CPF não possui conta corrente cadastrada");
			}
		}

		for (ContaPoupanca buscar : listaCP) {
			if (buscar.getCpf().equalsIgnoreCase(cpf)) {
				buscar.exibirDados();
				System.out.println("Tipo: Conta Poupança");
			} else {
				System.out.println("\nEsse CPF não possui conta corrente cadastrada");
			}
		}

		System.out.println("\n-----FIM DA EXIBIÇÃO-----");
		view.Principal.exibirMenu();
	}

	/*
	 * Método que transfere da Conta Corrente para a Conta Poupança (Cadastradas no
	 * mesmo CPF). Caso não tenha Conta Corrente e/ou Conta Poupança cadastradas, o
	 * programa irá notificar
	 */

	public void iniciarAplicacao() {
		System.out.println("\nDigite o número do CPF: ");
		String cpf = scan.next();

		boolean temContaCorrente = false;
		boolean temContaPoupanca = false;

		for (ContaPoupanca buscar : listaCP) {
			if (buscar.getCpf().equalsIgnoreCase(cpf)) {
				temContaPoupanca = true;
				for (ContaCorrente achar : listaCC) {
					if (achar.getCpf().equalsIgnoreCase(cpf)) {
						temContaCorrente = true;
						System.out.print("Digite o valor a ser aplicado: R$");
						double valor = scan.nextDouble();
						buscar.aplicar(achar, valor);
						System.out.println("\n-----DINHEIRO APLICADO COM SUCESSO-----");
						view.Principal.exibirMenu();
						return;
					}
				}
			}
		}
		if (temContaPoupanca && !temContaCorrente) {
			System.out.println("\nEsse CPF não possui Conta Corrente cadastrada.");
			view.Principal.exibirMenu();
		} else if (!temContaPoupanca) {
			System.out.println("\nEsse CPF não possui Conta Poupança cadastrada.");
			view.Principal.exibirMenu();
		}
	}

	/*
	 * Método que deposita dinheiro na Conta Corrente. Casoo valor seja menor ou
	 * igual a zero, o programa notificará um erro de valor inválido.
	 */

	public void iniciarDeposito() {
		System.out.print("\nDigite o número da sua Conta Corrente: ");
		numeroConta = scan.nextLine();

		for (ContaCorrente buscar : listaCC) {
			if (buscar.getNumeroConta().equalsIgnoreCase(numeroConta)) {
				System.out.print("Digite o valor a ser depositado: R$");
				valor = scan.nextDouble();
				while (valor <= 0) {
					System.out.print("Valor inválido. Digite novamente: R$");
					valor = scan.nextDouble();
				}
				buscar.depositar(valor);
			}
		}

		System.out.println("\n-----DEPOSITO REALIZADO COM SUCESSO-----");
		view.Principal.exibirMenu();
	}

	/*
	 * Método transfere o dinheiro da Conta Poupança para a Conta Corrente. Ele irá
	 * verificar se o CPF digitado possuí ambas as contas. Caso não tenha,
	 * notificará um erro.
	 */

	public void iniciarResgate() {
		System.out.print("\nDigite o numero do seu CPF: ");
		cpf = scan.next();

		for (ContaPoupanca buscar : listaCP) {
			if (buscar.getCpf().equalsIgnoreCase(cpf)) {
				for (ContaCorrente achar : listaCC) {
					if (achar.getCpf().equalsIgnoreCase(cpf)) {
						System.out.print("Digite o valor a ser resgatado: R$");
						valor = scan.nextDouble();
						buscar.resgatar(achar, valor);
					} else {
						System.out.println("\nEsse CPF não possui Conta Corrente cadastrada.");
						view.Principal.exibirMenu();
					}
				}
			} else {
				System.out.print("\nEsse CPF não possui Conta Poupança cadastrada.");
				view.Principal.exibirMenu();
			}
		}

		System.out.println("\n-----FIM DO RESGATE-----");
		view.Principal.exibirMenu();
	}

}
