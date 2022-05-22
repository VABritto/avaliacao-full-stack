package br.com.financeiro.avaliacaofullstackback.module.transaction.service;

import br.com.financeiro.avaliacaofullstackback.config.exception.AccountException;
import br.com.financeiro.avaliacaofullstackback.config.exception.TransactionException;
import br.com.financeiro.avaliacaofullstackback.module.account.model.Account;
import br.com.financeiro.avaliacaofullstackback.module.account.service.AccountService;
import br.com.financeiro.avaliacaofullstackback.module.transaction.dto.TransactionRequest;
import br.com.financeiro.avaliacaofullstackback.module.transaction.dto.TransactionResponse;
import br.com.financeiro.avaliacaofullstackback.module.transaction.enumerator.FeeType;
import br.com.financeiro.avaliacaofullstackback.module.transaction.model.Transaction;
import br.com.financeiro.avaliacaofullstackback.module.transaction.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@Service
public class TransactionService {

	private static final int ZERO = 0;

	@Autowired
	private TransactionRepository transactionRepository;
	
	@Autowired
	private AccountService accountService;

	public TransactionResponse transfer(TransactionRequest request) throws TransactionException, AccountException {
		validateFields(request);
		Account depositor =  accountService.findByAccountNumber(request.getDepositorAccount());
		Account receiver = accountService.findByAccountNumber(request.getReceiverAccount());
		Double fee = applyFee(request);
		accountService.debitAmount(depositor, request.getAmount(), fee);
		Transaction transaction = transactionRepository.save(Transaction.of(request, depositor, receiver));
		return TransactionResponse.of(transaction);
	}

	public List<TransactionResponse> findAllByAccountNumber(String accountNumber) {
		Account depositor = accountService.findByAccountNumber(accountNumber);
		List<Transaction> transactions = transactionRepository.findAllByActiveTrueAndDepositorIs(depositor);
		return TransactionResponse.of(transactions);
	}

	private void validateFields(TransactionRequest request) throws TransactionException {
		String errorMessage = "";
		List<String> errorCollector = new ArrayList<>();

		if(request.getAmount() == null || request.getAmount() <= 0) {
			errorCollector.add("A quantia transferida deve ser maior do que zero.");
		}
		if(request.getTransferDate() == null || request.getTransferDate().isBefore(LocalDateTime.now().minusDays(1))) {
			errorCollector.add("A data de transferência deve ser hoje ou no futuro.");
		}
		if(request.getReceiverAccount() == null) {
			errorCollector.add("É necessário escolher uma conta para fazer a transferência.");
		}
		if(!errorCollector.isEmpty()) {
			errorMessage = String.join("; ", errorCollector);
		}
		
		if(StringUtils.hasText(errorMessage)) {
			throw new TransactionException("Erros encontrados: " + errorMessage);
		}
	}

	private Double applyFee(TransactionRequest request) throws TransactionException, AccountException {
		FeeType type = FeeType.valueOf(request.getType());
		Double fee = 0.0;
		switch (type) {
			case A:
				fee = applyFeeTypeA(request);
				break;
			case B:
				fee = applyFeeTypeB(request);
				break;
			case C:
				fee = applyFeeTypeC(request);
				break;
			case D:
				fee = applyFeeTypeD(request);
				break;
		}
		return fee;
	}

	private Double applyFeeTypeA(TransactionRequest request) throws TransactionException, AccountException {
		if(ChronoUnit.DAYS.between(request.getTransferDate().toLocalDate().atStartOfDay(), LocalDateTime.now().toLocalDate().atStartOfDay()) != ZERO) {
			throw new TransactionException("Operações do tipo A devem ser agendadas para o mesmo dia em que a operação foi realizada.");
		}
		return (request.getAmount() * 0.03) + 3;
	}

	private Double applyFeeTypeB(TransactionRequest request) throws TransactionException, AccountException {
		if(ChronoUnit.DAYS.between(request.getTransferDate(), LocalDateTime.now()) > 10) {
			throw new TransactionException("Operações do tipo B devem ser agendadas para até 10 (dez) dias contando a partir do dia no qual a operação foi realizada.");
		}
		return 12.0;
	}

	private Double applyFeeTypeC(TransactionRequest request) throws TransactionException, AccountException {
		long daysBetweenNowAndTransfer = ChronoUnit.DAYS.between(request.getTransferDate(), LocalDateTime.now());
		if(daysBetweenNowAndTransfer <= 10) {
			throw new TransactionException("Operações do tipo C devem ser agendadas para mais de 10 (dez) dias contando a partir do dia no qual a operação foi realizada.");
		}

		Double fee = 0.0;

		if(daysBetweenNowAndTransfer <= 20) {
			fee = request.getAmount() * 0.082;
		} else if(daysBetweenNowAndTransfer <= 30) {
			fee = request.getAmount() * 0.069;
		} else if(daysBetweenNowAndTransfer <= 40) {
			fee = request.getAmount() * 0.047;
		} else {
			fee = request.getAmount() * 0.017;
		}
		return fee;
	}

	private Double applyFeeTypeD(TransactionRequest request) throws AccountException, TransactionException {
		if(request.getAmount() > 2000) {
			return applyFeeTypeC(request);
		} else if(request.getAmount() <= 1000) {
			return applyFeeTypeA(request);
		} else {
			return applyFeeTypeB(request);
		}
	}

	public void executeTransactions() {
		List<Transaction> transactions = transactionRepository.findAllByActiveTrueAndTransferDateBefore(LocalDateTime.now());
		if(!transactions.isEmpty()) {
			for(Transaction transaction : transactions) {
				transaction.setActive(false);
				Double finalAmount = transaction.getReceiver().getAmount() + transaction.getAmount();
				transaction.getReceiver().setAmount(finalAmount);
			}
			transactionRepository.saveAll(transactions);
		}
	}
}
