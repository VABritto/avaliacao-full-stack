package br.com.financeiro.avaliacaofullstackback.module.account.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.financeiro.avaliacaofullstackback.module.account.model.Account;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin("http://localhost:4200")
public interface AccountRepository extends JpaRepository<Account, Long> {

	Account findByNumber(String number);
}
