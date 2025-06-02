package tn.hr.compteur.piece.enitite;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import tn.hr.compteur.machine.enitite.Machine;

import java.util.ArrayList;
import java.util.List;

@Data
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@Entity
@Table(name = "pieces")
public class Piece {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nom;

    private String description;

    @JsonIgnore
    @OneToMany(mappedBy = "piece", cascade = CascadeType.ALL)
    private List<Machine> machines = new ArrayList<>();
}
