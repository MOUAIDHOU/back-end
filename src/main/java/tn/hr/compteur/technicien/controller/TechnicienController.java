package tn.hr.compteur.technicien.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.hr.compteur.maintenace.enitite.Maintenance;
import tn.hr.compteur.technicien.enitite.LoginRequest;
import tn.hr.compteur.technicien.enitite.Technicien;
import tn.hr.compteur.technicien.service.TechnicienService;

import java.util.List;
import java.util.Optional;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/techniciens")
public class TechnicienController {
    @Autowired
    private TechnicienService technicienService;


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest credentials) {
        String nom = credentials.getNom();
        String password = credentials.getPassword();

        Optional<Technicien> technicien = technicienService.login(nom, password);
        if (technicien.isPresent()) {
            return ResponseEntity.ok(technicien.get());
        } else {
            return ResponseEntity.status(401).body("Nom ou mot de passe invalide");
        }
    }




    @GetMapping
    public List<Technicien> getAllTechniciens() {
        return technicienService.findAll();
    }

    @GetMapping("/{id}")
    public Technicien getTechnicienById(@PathVariable Long id) {
        return technicienService.findById(id);
    }

    @PostMapping
    public Technicien createTechnicien(@RequestBody Technicien technicien) {
        return technicienService.save(technicien);
    }

    @DeleteMapping("/{id}")
    public void deleteTechnicien(@PathVariable Long id) {
        technicienService.delete(id);
    }

    @GetMapping("/{id}/maintenances")
    public List<Maintenance> getMaintenancesByTechnicien(@PathVariable Long id) {
        return technicienService.getMaintenancesByTechnicien(id);
    }
}