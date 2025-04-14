package com.tickets.GoBus.model;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "buses")
public class Buses {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long busId;
    @Column(unique = true)
    private String busNumber;
    private int capacity;
    private String busDriverName;
    private StringBuilder busDriverNumber;
    @OneToMany(mappedBy = "buses", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Seats> seats;
    @ManyToOne
    @JoinColumn(name = "operator_id")
    private BusOperators busOperators;
}
