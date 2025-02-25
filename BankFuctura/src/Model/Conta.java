package Model;

import java.util.Random;
import java.util.Scanner;

public abstract class Conta {

	Random r = new Random();
	Scanner scan = new Scanner(System.in);

	private String numeroConta;
	private String titular;
	private double saldo;
	private String cpf;

	// Método construtor.

	public Conta(String numeroConta, String titular, String cpf, double saldo) {
		this.numeroConta = numeroConta;
		this.titular = titular;
		this.cpf = cpf;
		this.saldo = saldo;
	}

	public String getNumeroConta() {
		return numeroConta;
	}

	public String getTitular() {
		return titular;
	}

	public void setTitular(String titular) {
		this.titular = titular;
	}

	public double getSaldo() {
		return saldo;
	}

	// Método protegido para alterar o saldo.

	protected void alterarSaldo(double valor) {
		this.saldo = valor;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	// Metodo para gerar de forma aleatória o número da conta com 4 dígitos.

	public static String gerarNumeroConta() {
		Random r = new Random();
		int numero = r.nextInt(9999);
		String num = String.format("%04d", numero);
		return num;
	}

	// Método para exibir todos os dados de uma determinada conta solicitada.

	public void exibirDados() {
		System.out.println("\nN° da Conta: " + numeroConta);
		System.out.println("Nome Completo: " + titular);
		System.out.println("CPF: " + cpf);
		System.out.println("Saldo: R$" + String.format("%.2f", saldo));
	}

	// Método para depositar um valor na conta.

	public void depositar(double valor) {
		saldo += valor;
	}

}
