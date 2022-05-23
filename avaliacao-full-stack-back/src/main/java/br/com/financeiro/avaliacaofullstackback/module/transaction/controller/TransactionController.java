package br.com.financeiro.avaliacaofullstackback.module.transaction.controller;

import br.com.financeiro.avaliacaofullstackback.module.transaction.dto.TransactionRequest;
import br.com.financeiro.avaliacaofullstackback.module.transaction.dto.TransactionResponse;
import br.com.financeiro.avaliacaofullstackback.module.transaction.service.TransactionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/transaction")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @CrossOrigin(origins = {"http://localhost:4200"}, exposedHeaders = "Access-Control-Allow-Origin")
    @PostMapping(value = "/make-transaction", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> makeTransaction(@RequestBody TransactionRequest request) {
        try {
            TransactionResponse response = transactionService.transfer(request);
            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            log.debug(e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @CrossOrigin(origins = {"http://localhost:4200"}, exposedHeaders = "Access-Control-Allow-Origin")
    @GetMapping(value = "/list-all-transactions/{account_number}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> listAllTransactions(@PathVariable("account_number") String accountNumber) {
        try {
            List<TransactionResponse> responses = transactionService.findAllByAccountNumber(accountNumber);
            return ResponseEntity.ok().body(responses);
        } catch (Exception e) {
            log.debug(e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
