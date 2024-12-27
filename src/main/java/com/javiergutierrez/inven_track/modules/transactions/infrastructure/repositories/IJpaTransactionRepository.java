package com.javiergutierrez.inven_track.modules.transactions.infrastructure.repositories;

import com.javiergutierrez.inven_track.modules.transactions.infrastructure.entities.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IJpaTransactionRepository extends JpaRepository<TransactionEntity, Long> {
}
