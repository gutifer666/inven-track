package com.javiergutierrez.inven_track.modules.transactions.application.service;

import com.javiergutierrez.inven_track.modules.transactions.application.usecases.CreateTransactionUseCase;
import com.javiergutierrez.inven_track.modules.transactions.domain.Transaction;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor
@Slf4j
@Service
public class TransactionService {

	private CreateTransactionUseCase createTransactionUseCase;

	public Optional<Transaction> createTransaction(Transaction transaction) {
		log.info("Call to createTransaction.");
		log.debug(transaction.toString());

		return createTransactionUseCase.createTransaction(transaction);

	}
}
