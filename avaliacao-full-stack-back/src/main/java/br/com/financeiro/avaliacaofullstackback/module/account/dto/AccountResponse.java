package br.com.financeiro.avaliacaofullstackback.module.account.dto;

import br.com.financeiro.avaliacaofullstackback.module.account.model.Account;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountResponse {

    private String number;
    private String name;
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
