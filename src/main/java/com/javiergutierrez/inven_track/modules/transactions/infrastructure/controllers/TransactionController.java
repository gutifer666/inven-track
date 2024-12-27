package com.javiergutierrez.inven_track.modules.transactions.infrastructure.controllers;

import com.javiergutierrez.inven_track.modules.products.domain.Product;
import com.javiergutierrez.inven_track.modules.transactions.application.service.TransactionService;
import com.javiergutierrez.inven_track.modules.transactions.domain.Transaction;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/api/transactions")
@Slf4j
@Validated
@RestController
public class TransactionController {

	private final TransactionService transactionService;

	@PostMapping
	public ResponseEntity<Transaction> createTransaction(@Valid @RequestBody Transaction transaction) {
		log.info("Call to createTransaction {}", transaction);

		Transaction createdTransaction = transactionService.createTransaction(transaction).orElseThrow(() -> {
			log.error("Failed to create transaction.");
			return new IllegalStateException("Failed to create transaction.");
		});
		log.info("Transaction created: {}", createdTransaction);

		return new ResponseEntity<>(createdTransaction, HttpStatus.CREATED);
	}
}
