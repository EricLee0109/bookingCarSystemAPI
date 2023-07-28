package com.bookingcar.service.mapper;

import com.bookingcar.domain.Transaction;
import com.bookingcar.domain.Wallet;
import com.bookingcar.service.dto.TransactionDTO;
import com.bookingcar.service.dto.WalletDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Transaction} and its DTO {@link TransactionDTO}.
 */
@Mapper(componentModel = "spring")
public interface TransactionMapper extends EntityMapper<TransactionDTO, Transaction> {
    @Mapping(target = "wallet", source = "wallet", qualifiedByName = "walletId")
    TransactionDTO toDto(Transaction s);

    @Named("walletId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    WalletDTO toDtoWalletId(Wallet wallet);
}
