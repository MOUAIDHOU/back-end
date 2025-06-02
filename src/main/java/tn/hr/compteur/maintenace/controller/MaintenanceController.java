package tn.hr.compteur.maintenace.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.hr.compteur.maintenace.enitite.Maintenance;
import tn.hr.compteur.maintenace.service.MaintenanceService;

import java.util.List;

@RestController
@RequestMapping("/api/maintenances")
public class MaintenanceController {
    @Autowired
    private MaintenanceService maintenanceService;

    @PostMapping("/technicien/{technicienId}/machine/{machineId}")
    public Maintenance createMaintenance(
            @PathVariable Long technicienId,
            @PathVariable Long machineId,
            @RequestBody Maintenance maintenance) {
        return maintenanceService.createMaintenance(technicienId, machineId, maintenance);
    }

    @GetMapping
    public List<Maintenance> getAllMaintenances() {
        return maintenanceService.findAll();
    }

    @GetMapping("/{id}")
    public Maintenance getMaintenanceById(@PathVariable Long id) {
        return maintenanceService.findById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteMaintenance(@PathVariable Long id) {
        maintenanceService.delete(id);
    }
}