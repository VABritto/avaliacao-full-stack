package br.com.financeiro.avaliacaofullstackback.module.transaction.exception;

public class TransactionException extends Exception {
	private static final long serialVersionUID = -6339407022096653433L;

	public TransactionException(String message) {
		super(message);
	}
}
