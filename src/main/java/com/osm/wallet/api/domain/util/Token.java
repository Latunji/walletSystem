package com.osm.wallet.api.domain.util;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "tbl_token")
@SequenceGenerator(name = "tokenSeq", sequenceName = "tbl_token_seq" , allocationSize = 1)
@NoArgsConstructor
@Getter
@Setter
public class Token {

    private static final long serialVersionUID = 1840603817779318751L;

    @Id
    @GeneratedValue(generator = "tokenSeq", strategy = GenerationType.SEQUENCE)
    @Column(name = "token_id", nullable = false, unique = true)
    private Long id;

    @Column(name = "email_address", nullable = false, length = 35 )
    private String emailAddress;

    @Column(name = "token", nullable = false, length = 6 )
    private String token;
}
