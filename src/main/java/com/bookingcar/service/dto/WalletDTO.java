package com.bookingcar.service.dto;

import java.io.Serializable;
import java.util.Objects;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link com.bookingcar.domain.Wallet} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class WalletDTO implements Serializable {

    private Long id;

    @NotNull
    private Long balance;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBalance() {
        return balance;
    }

    public void setBalance(Long balance) {
        this.balance = balance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof WalletDTO)) {
            return false;
        }

        WalletDTO walletDTO = (WalletDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, walletDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "WalletDTO{" +
            "id=" + getId() +
            ", balance=" + getBalance() +
            "}";
    }
}
