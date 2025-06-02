package tn.hr.compteur.technicien.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.hr.compteur.maintenace.enitite.Maintenance;
import tn.hr.compteur.technicien.enitite.Technicien;
import tn.hr.compteur.technicien.repository.TechnicienRepository;

import java.util.List;
import java.util.Optional;

@Service
public class TechnicienService {
    @Autowired
    private TechnicienRepository technicienRepository;

    public List<Technicien> findAll() {
        return technicienRepository.findAll();
    }

    public Optional<Technicien> login(String nom, String password) {
        return technicienRepository.findByNomAndPassword(nom, password);
    }

    public Optional<Technicien> findByNom(String nom) {
        return technicienRepository.findByNom(nom);
    }


    public Technicien findById(Long id) {
        return technicienRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Technicien non trouvé"));
    }

    public Technicien save(Technicien technicien) {
        return technicienRepository.save(technicien);
    }

    public void delete(Long id) {
        technicienRepository.deleteById(id);
    }

    public List<Maintenance> getMaintenancesByTechnicien(Long technicienId) {
        Technicien technicien = findById(technicienId);
        return technicien.getMaintenances();
    }
}