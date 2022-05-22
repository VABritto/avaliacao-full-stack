package br.com.financeiro.avaliacaofullstackback.module.transaction.scheduler;

import br.com.financeiro.avaliacaofullstackback.module.transaction.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
public class TransactionScheduler {

    @Autowired
    private TransactionService transactionService;

    @Scheduled(fixedDelay = 2000)
    public void executeTransactions() {
        transactionService.executeTransactions();
    }
}
