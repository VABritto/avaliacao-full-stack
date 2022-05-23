package br.com.financeiro.avaliacaofullstackback.module.account.controller;

import br.com.financeiro.avaliacaofullstackback.module.account.dto.AccountResponse;
import br.com.financeiro.avaliacaofullstackback.module.account.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @CrossOrigin(origins = {"http://localhost:4200"}, exposedHeaders = "Access-Control-Allow-Origin")
    @GetMapping(value = "/account-sum/{account_number}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAccountSum(@PathVariable("account_number") String accountNumber) {
        try {
            AccountResponse response = accountService.findResponseByAccountNumber(accountNumber);
            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            log.debug(e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
