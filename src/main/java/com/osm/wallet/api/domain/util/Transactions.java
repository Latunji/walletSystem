package com.osm.wallet.api.domain.util;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.Instant;

@Entity
@Table(name = "tbl_transactions")
@SequenceGenerator(name = "transactionSeq", sequenceName = "tbl_transaction_seq" , allocationSize = 1)
@NoArgsConstructor
@Getter
@Setter
public class Transactions {

    private static final long serialVersionUID = 1840603817779318751L;

    @Id
    @GeneratedValue(generator = "transactionSeq", strategy = GenerationType.SEQUENCE)
    @Column(name = "transaction_id", nullable = false, unique = true)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "source_wallet_account")
    private Wallet sourceWallet;

    @ManyToOne
    @JoinColumn(name = "beneficiary_wallet_account")
    private Wallet beneficiaryWallet;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "source_user")
    private User sourceUser;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "beneficiary_user")
    private User beneficiaryUser;

    @Column(name = "amount", columnDefinition = "numeric(15,2) default '0.00'", nullable = false)
    private double amount;

    @Column(name = "date_created")
    private Timestamp dateCreated = Timestamp.from(Instant.now());

    @Transient String sourceEmail;

    @Transient String beneficiaryEmail;

    @Transient String sourceAccount;

    @Transient String beneficiaryAccount;
}
