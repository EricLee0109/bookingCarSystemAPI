package com.bookingcar.web.rest;

import com.bookingcar.domain.Wallet;
import com.bookingcar.repository.WalletRepository;
import com.bookingcar.service.WalletService;
import com.bookingcar.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.bookingcar.domain.Wallet}.
 */
@RestController
@RequestMapping("/api")
public class WalletResource {

    private final Logger log = LoggerFactory.getLogger(WalletResource.class);

    private static final String ENTITY_NAME = "wallet";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final WalletService walletService;

    private final WalletRepository walletRepository;

    public WalletResource(WalletService walletService, WalletRepository walletRepository) {
        this.walletService = walletService;
        this.walletRepository = walletRepository;
    }

    /**
     * {@code POST  /wallets} : Create a new wallet.
     *
     * @param wallet the wallet to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new wallet, or with status {@code 400 (Bad Request)} if the wallet has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/wallets")
    public ResponseEntity<Wallet> createWallet(@Valid @RequestBody Wallet wallet) throws URISyntaxException {
        log.debug("REST request to save Wallet : {}", wallet);
        if (wallet.getId() != null) {
            throw new BadRequestAlertException("A new wallet cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Wallet result = walletService.save(wallet);
        return ResponseEntity
            .created(new URI("/api/wallets/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /wallets/:id} : Updates an existing wallet.
     *
     * @param id the id of the wallet to save.
     * @param wallet the wallet to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated wallet,
     * or with status {@code 400 (Bad Request)} if the wallet is not valid,
     * or with status {@code 500 (Internal Server Error)} if the wallet couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/wallets/{id}")
    public ResponseEntity<Wallet> updateWallet(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody Wallet wallet
    ) throws URISyntaxException {
        log.debug("REST request to update Wallet : {}, {}", id, wallet);
        if (wallet.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, wallet.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!walletRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Wallet result = walletService.update(wallet);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, wallet.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /wallets/:id} : Partial updates given fields of an existing wallet, field will ignore if it is null
     *
     * @param id the id of the wallet to save.
     * @param wallet the wallet to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated wallet,
     * or with status {@code 400 (Bad Request)} if the wallet is not valid,
     * or with status {@code 404 (Not Found)} if the wallet is not found,
     * or with status {@code 500 (Internal Server Error)} if the wallet couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/wallets/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<Wallet> partialUpdateWallet(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody Wallet wallet
    ) throws URISyntaxException {
        log.debug("REST request to partial update Wallet partially : {}, {}", id, wallet);
        if (wallet.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, wallet.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!walletRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<Wallet> result = walletService.partialUpdate(wallet);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, wallet.getId().toString())
        );
    }

    /**
     * {@code GET  /wallets} : get all the wallets.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of wallets in body.
     */
    @GetMapping("/wallets")
    public List<Wallet> getAllWallets() {
        log.debug("REST request to get all Wallets");
        return walletService.findAll();
    }

    /**
     * {@code GET  /wallets/:id} : get the "id" wallet.
     *
     * @param id the id of the wallet to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the wallet, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/wallets/{id}")
    public ResponseEntity<Wallet> getWallet(@PathVariable Long id) {
        log.debug("REST request to get Wallet : {}", id);
        Optional<Wallet> wallet = walletService.findOne(id);
        return ResponseUtil.wrapOrNotFound(wallet);
    }

    /**
     * {@code DELETE  /wallets/:id} : delete the "id" wallet.
     *
     * @param id the id of the wallet to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/wallets/{id}")
    public ResponseEntity<Void> deleteWallet(@PathVariable Long id) {
        log.debug("REST request to delete Wallet : {}", id);
        walletService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
