package br.com.financeiro.avaliacaofullstackback.module.account.dto;

import br.com.financeiro.avaliacaofullstackback.module.account.model.Account;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountResponse {

    @JsonProperty("account_number")
    private String number;
    private String name;
    @JsonProperty("account_sum")
    private Double amount;

    public static AccountResponse of(Account account) {
        return AccountResponse
                .builder()
                .number(account.getNumber())
                .name(account.getUser().getName())
                .amount(account.getAmount())
                .build();
    }
}
