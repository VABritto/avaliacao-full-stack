package br.com.financeiro.avaliacaofullstackback.module.transaction.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class TransactionListRequest {

    @JsonProperty("account_number")
    private String accountNumber;
}
