package br.com.financeiro.avaliacaofullstackback.module.account.model;

import br.com.financeiro.avaliacaofullstackback.module.user.model.User;
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
@Table(name = "ACCOUNT")
public class Account {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	
	@Column(name = "NUM", nullable = false)
	private String number;

	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="FK_ACCOUNT_USER", nullable=false)
	private User user;
	
	@Column(name = "AMOUNT", nullable = false)
	private Double amount;
	
	@Column(name = "ACTIVE", nullable = false)
	private boolean active;
	
	@Column(name = "CREATED_AT", nullable = false)
	private LocalDateTime createdAt;
	
	@Column(name = "LAST_MODIFIED", nullable = false)
	private LocalDateTime lastModified;
}
