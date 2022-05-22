package br.com.financeiro.avaliacaofullstackback.module.account.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class AccountRequest {

    @JsonProperty("account_number")
    private String accountNumber;
}
