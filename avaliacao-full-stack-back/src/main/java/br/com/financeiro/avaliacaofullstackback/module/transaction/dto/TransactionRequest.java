package br.com.financeiro.avaliacaofullstackback.module.transaction.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TransactionRequest {
	
	private Double amount;
	private String type;
	private String depositorAccount;
	private String receiverAccount;
	private LocalDateTime transferDate;
}
