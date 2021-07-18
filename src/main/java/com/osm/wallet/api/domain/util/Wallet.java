package com.osm.wallet.api.domain.util;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;

@Entity
@Table(name = "tbl_wallet")
@SequenceGenerator(name = "walletSeq", sequenceName = "tbl_wallet_seq" , allocationSize = 1)
@NoArgsConstructor
@Getter
@Setter
public class Wallet {

    private static final long serialVersionUID = 1840603817779318751L;

    @Id
    @GeneratedValue(generator = "walletSeq", strategy = GenerationType.SEQUENCE)
    @Column(name = "wallet_id", nullable = false, unique = true)
    private Long id;

    @Column(name = "wallet_number", nullable = false, length = 10 )
    private String walletNumber;

	@OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    protected User user;

    @Column(name = "wallet_balance", columnDefinition = "numeric(15,2) default '0.00'", nullable = false)
    private double walletBalance;

    @Column(name = "maximum_withdrawal", columnDefinition = "numeric(15,2) default '2000.00'", nullable = false)
    private double maximumWithdrawal;

    @Column(name = "date_created")
    private Timestamp dateRegistered = Timestamp.from(Instant.now());

}
