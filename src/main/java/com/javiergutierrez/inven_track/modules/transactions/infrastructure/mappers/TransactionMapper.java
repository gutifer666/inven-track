package com.javiergutierrez.inven_track.modules.transactions.infrastructure.mappers;

import com.javiergutierrez.inven_track.common.GenericMapper;
import com.javiergutierrez.inven_track.modules.transactions.domain.Transaction;
import com.javiergutierrez.inven_track.modules.transactions.infrastructure.entities.TransactionEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TransactionMapper extends GenericMapper<TransactionEntity, Transaction> {
}
