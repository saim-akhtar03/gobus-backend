package com.tickets.GoBus.model;

import com.tickets.GoBus.domain.AccountStatus;
import com.tickets.GoBus.domain.USER_ROLE;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

@Table(name = "bus_operators")
public class BusOperators {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long operatorId;

    private String operatorName;
    private String contactNumber;
    private String email;
    @Embedded
    private BankDetails bankDetails = new BankDetails();
    @Embedded
    private BuisnessDetails buisnessDetails=new BuisnessDetails();
    private String password;
    private boolean isEmailVerified=false;
    @Enumerated(EnumType.STRING)
    private USER_ROLE role;
    private AccountStatus accountStatus= AccountStatus.PENDING_VERIFICATION;
}