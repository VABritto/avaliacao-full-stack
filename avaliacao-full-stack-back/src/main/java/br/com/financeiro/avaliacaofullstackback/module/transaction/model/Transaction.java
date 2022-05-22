package br.com.financeiro.avaliacaofullstackback.module.transaction.model;

import br.com.financeiro.avaliacaofullstackback.module.account.model.Account;
import br.com.financeiro.avaliacaofullstackback.module.transaction.dto.TransactionRequest;
import br.com.financeiro.avaliacaofullstackback.module.transaction.enumerator.FeeType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "TRANSACTION")
public class Transaction {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "TYPE", nullable = false)
	private FeeType type;
	
	@Column(name = "AMOUNT", nullable = false)
	private Double amount;
	
	@Column(name = "TRANSFER_DATE", nullable = false)
	private LocalDateTime transferDate;

	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="FK_DEPOSITOR", nullable=false)
	private Account depositor;

	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="FK_RECEIVER", nullable=false)
	private Account receiver;

	@Column(name = "ACTIVE", nullable = false)
	private boolean active;
	
	@Column(name = "CREATED_AT", nullable = false)
	private LocalDateTime createdAt;
	
	public static Transaction of(TransactionRequest request, Account depositor, Account receiver) {
        return Transaction
        		.builder()
        		.type(FeeType.valueOf(request.getType().trim().toUpperCase()))
        		.amount(request.getAmount())
        		.depositor(depositor)
        		.receiver(receiver)
        		.transferDate(request.getTransferDate())
				.active(true)
        		.createdAt(LocalDateTime.now())
        		.build();
    }
}
