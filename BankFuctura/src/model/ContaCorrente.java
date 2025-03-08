package model;

public class ContaCorrente extends Conta implements Tarifavel {

	private double novoSaldo = 0;
	private static final double taxa = 6.70;

	// Método Construtor.

	public ContaCorrente(String numeroConta, String titular, String cpf, double saldo) {
		super(numeroConta, titular, cpf, saldo);
		// TODO Auto-generated constructor stub
	}

	// Método para sacar um determinado valor da conta.

	public void sacar(double valor) {
		while (valor + taxa > getSaldo()) {
			System.out.print("Valor inválido. Digite novamente: R$");
			valor = scan.nextDouble();
		}
		aplicarTaxaSaque(taxa);
		novoSaldo = getSaldo() - valor;
		alterarSaldo(novoSaldo);
	}

	/*
	 * Método requerido pela interface Tarifavel para aplicar uma taxa para cada
	 * saque realizado.
	 */

	@Override
	public void aplicarTaxaSaque(double valor) {
		novoSaldo = getSaldo() - valor;
		alterarSaldo(novoSaldo);

	}

}
