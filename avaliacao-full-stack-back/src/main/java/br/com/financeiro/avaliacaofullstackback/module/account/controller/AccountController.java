package br.com.financeiro.avaliacaofullstackback.module.account.controller;

import br.com.financeiro.avaliacaofullstackback.module.account.dto.AccountRequest;
import br.com.financeiro.avaliacaofullstackback.module.account.dto.AccountResponse;
import br.com.financeiro.avaliacaofullstackback.module.account.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping(value = "/account-sum", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAccountSum(@RequestBody AccountRequest request) {
        try {
            AccountResponse response = accountService.findByAccountNumber(request);
            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            log.debug(e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
