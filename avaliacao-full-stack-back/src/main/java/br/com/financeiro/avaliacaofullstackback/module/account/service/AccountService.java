package br.com.financeiro.avaliacaofullstackback.module.account.service;

import br.com.financeiro.avaliacaofullstackback.config.exception.AccountException;
import br.com.financeiro.avaliacaofullstackback.config.exception.TransactionException;
import br.com.financeiro.avaliacaofullstackback.module.account.dto.AccountRequest;
import br.com.financeiro.avaliacaofullstackback.module.account.dto.AccountResponse;
import br.com.financeiro.avaliacaofullstackback.module.account.model.Account;
import br.com.financeiro.avaliacaofullstackback.module.account.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

	@Autowired
	private AccountRepository accountRepository;
	
	public Account findByAccountNumber(String accountNumber) {
		return accountRepository.findByNumber(accountNumber);
	}

	public AccountResponse findByAccountNumber(AccountRequest request) {
		Account account = accountRepository.findByNumber(request.getAccountNumber());
		return AccountResponse.of(account);
	}

	public void debitAmount(Account account, Double amount, Double fee) throws AccountException, TransactionException {
		Double finalAmount = account.getAmount() - (amount + fee);
		if(finalAmount < 0) {
			throw new AccountException("Quantia excede depósito em caixa. Quantia: R$" + dotToCommaConverter(amount) + "; Taxa: " + dotToCommaConverter(fee));
		}
		account.setAmount(finalAmount);
		accountRepository.save(account);
	}

	public void depositAmount(Account account, Double amount) {
		amount += account.getAmount();
		account.setAmount(amount);
		accountRepository.save(account);
	}

	private String dotToCommaConverter(Double amount) {
		return amount.toString().replace(".", ",");
	}
}