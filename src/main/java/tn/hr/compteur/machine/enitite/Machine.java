package tn.hr.compteur.machine.enitite;

import jakarta.persistence.*;
import lombok.*;
import tn.hr.compteur.maintenace.enitite.Maintenance;
import tn.hr.compteur.piece.enitite.Piece;


import java.util.ArrayList;
import java.util.List;
@Data
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@Entity
@Table(name = "machines")
public class Machine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String reference;

    private String type;
    private String marque;

    @OneToMany(mappedBy = "machine", cascade = CascadeType.ALL)
    private List<Maintenance> maintenances = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "piece_id") // clé étrangère dans la table "machines"
    private Piece piece;

}
