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

    @GetMapping(value = "/list-all-transactions", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> listAllTransactions() {
        try {
            List<TransactionResponse> responses = transactionService.findAll();
            return ResponseEntity.ok().body(responses);
        } catch (Exception e) {
            log.debug(e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
