package model;

public class ContaPoupanca extends Conta {

	private double novoSaldo;

	// Método Construtor

	public ContaPoupanca(String numeroConta, String titular, String cpf, double saldo) {
		super(numeroConta, titular, cpf, saldo);
		// TODO Auto-generated constructor stub
	}

	// Método para transferir um valor da conta poupança para a conta corrente.

	public void resgatar(ContaCorrente destino, double valor) {
		novoSaldo = getSaldo() - valor;
		alterarSaldo(novoSaldo);
		destino.depositar(valor);

	}

	// Método serve para transferir um valor da conta corrente para a conta
	// poupanca.

	public void aplicar(ContaCorrente origem, double valor) {
		origem.alterarSaldo(origem.getSaldo() - valor);
		novoSaldo = getSaldo() + valor;
		alterarSaldo(novoSaldo);
	}

}
