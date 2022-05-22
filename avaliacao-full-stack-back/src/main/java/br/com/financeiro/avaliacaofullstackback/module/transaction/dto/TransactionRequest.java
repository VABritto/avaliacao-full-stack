package br.com.financeiro.avaliacaofullstackback.module.transaction.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TransactionRequest {
	
	private Double amount;
	@JsonProperty("fee_type")
	private String type;
	@JsonProperty("depositor_account")
	private String depositorAccount;
	@JsonProperty("receiver_account")
	private String receiverAccount;
	@JsonProperty("transfer_date")
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
	private LocalDateTime transferDate;
}
