package by.gurinovich.cryptologos.gpsolutionstechtask.entity;

import jakarta.persistence.*;

import java.sql.Time;

@Entity
@Table(name = "arrival_times")
public class ArrivalTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "check_in")
    private Time checkIn;

    @Column(name = "check_out")
    private Time checkOut;
}