package com.bookingcar.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * A Wallet.
 */
@Entity
@Table(name = "wallet")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Wallet implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "balance", nullable = false)
    private Long balance;

    @OneToMany(mappedBy = "wallet")
    @JsonIgnoreProperties(value = { "wallet" }, allowSetters = true)
    private Set<Transaction> transactions = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Wallet id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBalance() {
        return this.balance;
    }

    public Wallet balance(Long balance) {
        this.setBalance(balance);
        return this;
    }

    public void setBalance(Long balance) {
        this.balance = balance;
    }

    public Set<Transaction> getTransactions() {
        return this.transactions;
    }

    public void setTransactions(Set<Transaction> transactions) {
        if (this.transactions != null) {
            this.transactions.forEach(i -> i.setWallet(null));
        }
        if (transactions != null) {
            transactions.forEach(i -> i.setWallet(this));
        }
        this.transactions = transactions;
    }

    public Wallet transactions(Set<Transaction> transactions) {
        this.setTransactions(transactions);
        return this;
    }

    public Wallet addTransaction(Transaction transaction) {
        this.transactions.add(transaction);
        transaction.setWallet(this);
        return this;
    }

    public Wallet removeTransaction(Transaction transaction) {
        this.transactions.remove(transaction);
        transaction.setWallet(null);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Wallet)) {
            return false;
        }
        return id != null && id.equals(((Wallet) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Wallet{" +
            "id=" + getId() +
            ", balance=" + getBalance() +
            "}";
    }
}
