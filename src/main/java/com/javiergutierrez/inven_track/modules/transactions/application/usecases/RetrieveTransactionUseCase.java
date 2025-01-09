package com.javiergutierrez.inven_track.modules.transactions.application.usecases;

import com.javiergutierrez.inven_track.modules.transactions.domain.Transaction;
import com.javiergutierrez.inven_track.modules.transactions.infrastructure.adapters.TransactionRepositoryAdapter;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Slf4j
@Component
public class RetrieveTransactionUseCase {

	private TransactionRepositoryAdapter transactionRepositoryAdapter;

	public Optional<List<Transaction>> findAllTransactions() {
		log.info("Call to findAllTransactions.");

		Optional<List<Transaction>> transactionList = transactionRepositoryAdapter.findAllTransactions();

		log.debug(transactionList.toString());

		return transactionList;
	}

}
