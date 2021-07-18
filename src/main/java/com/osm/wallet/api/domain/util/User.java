package com.osm.wallet.api.domain.util;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.Instant;

@Entity
@Table(name = "tbl_user")
@SequenceGenerator(name = "userSeq", sequenceName = "tbl_user_seq" , allocationSize = 1)
@NoArgsConstructor
@Getter
@Setter
public class User {

    private static final long serialVersionUID = 1840603817779318751L;

    @Id
    @GeneratedValue(generator = "userSeq", strategy = GenerationType.SEQUENCE)
    @Column(name = "user_id", nullable = false, unique = true)
    private Long id;

    @Column(name = "first_name", nullable = false, length = 30 )
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 30 )
    private String lastName;

    @Column(name = "phone_number", nullable = false, length = 18 )
    private String phoneNumber;

    @Column(name = "username", nullable = false, length = 25)
    private String username;

    @Column(name = "email_address", nullable = false, length = 35 )
    private String emailAddress;

    @Column(name="password", nullable = false)
    private String password;

    @Column(name = "phone_verification_ind", columnDefinition = "integer default '0'")
    private int phoneVerificationInd;

    @Column(name = "email_verification_ind", columnDefinition = "integer default '0'")
    private int emailVerificationInd;

    @Column(name = "bvn_verification_ind", columnDefinition = "integer default '0'")
    private int bvnVerificationInd;

    @Column(name = "date_registered")
    private Timestamp dateRegistered = Timestamp.from(Instant.now());


    @Transient private Long bvn;

    @Transient private String token;


}
