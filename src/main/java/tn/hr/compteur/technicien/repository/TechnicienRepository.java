package tn.hr.compteur.technicien.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.hr.compteur.technicien.enitite.Technicien;

import java.util.Optional;

public interface TechnicienRepository extends JpaRepository<Technicien, Long> {
    Optional<Technicien> findByNomAndPassword(String nom, String password);
    Optional<Technicien> findByNom(String nom);
}
