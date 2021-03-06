package br.com.financeiro.avaliacaofullstackback.module.transaction.dto;

import br.com.financeiro.avaliacaofullstackback.module.transaction.model.Transaction;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransactionResponse {
	
	private Double amount;
	private String type;
	private String depositorAccount;
	private String receiverAccount;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
	private LocalDateTime transferDate;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
	private LocalDateTime createdAt;

	public static TransactionResponse of(Transaction transaction) {
        return TransactionResponse
        		.builder()
        		.amount(transaction.getAmount())
        		.type(transaction.getType().toString())
        		.depositorAccount(transaction.getDepositor().getNumber())
        		.receiverAccount(transaction.getReceiver().getNumber())
        		.transferDate(transaction.getTransferDate())
        		.createdAt(transaction.getCreatedAt())
        		.build();
    }

	public static List<TransactionResponse> of(List<Transaction> transactions) {
		List<TransactionResponse> responses = new ArrayList<>();
		for(Transaction transaction : transactions) {
			responses.add(
					TransactionResponse
					.builder()
					.amount(transaction.getAmount())
					.type(transaction.getType().toString())
					.depositorAccount(transaction.getDepositor().getNumber())
					.receiverAccount(transaction.getReceiver().getNumber())
					.transferDate(transaction.getTransferDate())
					.createdAt(transaction.getCreatedAt())
					.build()
			);
		}
		return responses;
	}
}
