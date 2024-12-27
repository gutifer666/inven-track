package com.javiergutierrez.inven_track.modules.transactions.infrastructure.adapters;

import com.javiergutierrez.inven_track.modules.transactions.domain.Transaction;
import com.javiergutierrez.inven_track.modules.transactions.infrastructure.entities.TransactionEntity;
import com.javiergutierrez.inven_track.modules.transactions.infrastructure.mappers.TransactionMapper;
import com.javiergutierrez.inven_track.modules.transactions.infrastructure.repositories.IJpaTransactionRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Optional;

@AllArgsConstructor
@Slf4j
@Component
public class TransactionRepositoryAdapter {

	private final IJpaTransactionRepository iJpaTransactionRepository;
	private final TransactionMapper transactionMapper;

	public Optional<Transaction> createTransaction(Transaction transaction) {
		log.info("Call to createUsers {}", transaction);
		log.debug("Users to create: {}", transaction);

		TransactionEntity transactionEntity = transactionMapper.toEntity(transaction);
		TransactionEntity createdTransactionEntity = iJpaTransactionRepository.save(transactionEntity);

		log.info("Created transaction with ID: {}", transaction.getId());
		log.debug("Transaction created: {}", createdTransactionEntity);

		return Optional.of(transactionMapper.toModel(createdTransactionEntity));
	}
}
