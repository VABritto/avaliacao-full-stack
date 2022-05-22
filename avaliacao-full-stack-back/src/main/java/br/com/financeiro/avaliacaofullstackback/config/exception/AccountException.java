package br.com.financeiro.avaliacaofullstackback.config.exception;

public class AccountException  extends Exception {
    private static final long serialVersionUID = -3523708272483156940L;

    public AccountException(String message) {
        super(message);
    }
}
