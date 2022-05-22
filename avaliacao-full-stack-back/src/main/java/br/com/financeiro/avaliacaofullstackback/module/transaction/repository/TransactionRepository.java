package br.com.financeiro.avaliacaofullstackback.module.transaction.repository;

import br.com.financeiro.avaliacaofullstackback.module.transaction.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    List<Transaction> findAll();

    List<Transaction> findAllByActiveTrueAndTransferDateBefore(LocalDateTime localDateTime);
}