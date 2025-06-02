package tn.hr.compteur.maintenace.enitite;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;

import jakarta.persistence.*;
import lombok.*;
import tn.hr.compteur.machine.enitite.Machine;
import tn.hr.compteur.technicien.enitite.Technicien;

import java.time.LocalDate;

@Entity
@Getter @Setter @AllArgsConstructor @NoArgsConstructor

@Table(name = "maintenances")
public class Maintenance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "technicien_id", nullable = false)
    private Technicien technicien;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "machine_id", nullable = false)
    private Machine machine;

    @Column(nullable = false)
    private LocalDate dateMaintenance;

    private String description;



}