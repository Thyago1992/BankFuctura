package model;

public class ContaCorrente extends Conta implements Tarifavel {

	private double novoSaldo;

	// Método Construtor.

	public ContaCorrente(String numeroConta, String titular, String cpf, double saldo) {
		super(numeroConta, titular, cpf, saldo);
		// TODO Auto-generated constructor stub
	}

	// Método para sacar um determinado valor da conta.

	public void sacar(double valor) {
		// double dinheiroTotal = aplicarTaxaSaque(6.70)+valor;
		while (valor + 6.70 > getSaldo()) {
			System.out.print("Valor inválido. Digite novamente: R$");
			valor = scan.nextDouble();
		}
		aplicarTaxaSaque(6.70);
		novoSaldo = getSaldo() - valor;
		alterarSaldo(novoSaldo);
	}

	// Método serve para transferir um valor da conta corrente para a conta
	// poupanca.

	public void aplicar(ContaPoupanca destino, double valor) {
		sacar(valor);
		destino.depositar(valor);
	}

	/*
	 * Método requerido pela interface Tarifavel para aplicar uma taxa para cada
	 * saque realizado.
	 */

	@Override
	public void aplicarTaxaSaque(double valor) {
		alterarSaldo(getSaldo() - valor);

	}

}
