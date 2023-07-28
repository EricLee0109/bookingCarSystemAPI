package com.bookingcar.service;

import com.bookingcar.domain.Transaction;
import com.bookingcar.repository.TransactionRepository;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Transaction}.
 */
@Service
@Transactional
public class TransactionService {

    private final Logger log = LoggerFactory.getLogger(TransactionService.class);

    private final TransactionRepository transactionRepository;

    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    /**
     * Save a transaction.
     *
     * @param transaction the entity to save.
     * @return the persisted entity.
     */
    public Transaction save(Transaction transaction) {
        log.debug("Request to save Transaction : {}", transaction);
        return transactionRepository.save(transaction);
    }

    /**
     * Update a transaction.
     *
     * @param transaction the entity to save.
     * @return the persisted entity.
     */
    public Transaction update(Transaction transaction) {
        log.debug("Request to update Transaction : {}", transaction);
        return transactionRepository.save(transaction);
    }

    /**
     * Partially update a transaction.
     *
     * @param transaction the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<Transaction> partialUpdate(Transaction transaction) {
        log.debug("Request to partially update Transaction : {}", transaction);

        return transactionRepository
            .findById(transaction.getId())
            .map(existingTransaction -> {
                if (transaction.getIssueDate() != null) {
                    existingTransaction.setIssueDate(transaction.getIssueDate());
                }
                if (transaction.getAmount() != null) {
                    existingTransaction.setAmount(transaction.getAmount());
                }

                return existingTransaction;
            })
            .map(transactionRepository::save);
    }

    /**
     * Get all the transactions.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<Transaction> findAll() {
        log.debug("Request to get all Transactions");
        return transactionRepository.findAll();
    }

    /**
     * Get one transaction by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<Transaction> findOne(Long id) {
        log.debug("Request to get Transaction : {}", id);
        return transactionRepository.findById(id);
    }

    /**
     * Delete the transaction by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Transaction : {}", id);
        transactionRepository.deleteById(id);
    }
}
