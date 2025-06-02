package tn.hr.compteur.maintenace.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.hr.compteur.machine.enitite.Machine;
import tn.hr.compteur.machine.service.MachineService;
import tn.hr.compteur.maintenace.enitite.Maintenance;
import tn.hr.compteur.maintenace.repository.MaintenanceRepository;
import tn.hr.compteur.technicien.enitite.Technicien;
import tn.hr.compteur.technicien.service.TechnicienService;

import java.util.List;

@Service
public class MaintenanceService {
    @Autowired
    private MaintenanceRepository maintenanceRepository;

    @Autowired
    private TechnicienService technicienService;

    @Autowired
    private MachineService machineService;

    public Maintenance createMaintenance(Long technicienId, Long machineId, Maintenance maintenance) {
        Technicien technicien = technicienService.findById(technicienId);
        Machine machine = machineService.findById(machineId);

        maintenance.setTechnicien(technicien);
        maintenance.setMachine(machine);

        Maintenance savedMaintenance = maintenanceRepository.save(maintenance);

        technicien.getMaintenances().add(savedMaintenance);
        machine.getMaintenances().add(savedMaintenance);

        technicienService.save(technicien);
        machineService.save(machine);

        return savedMaintenance;
    }

    public List<Maintenance> findAll() {
        return maintenanceRepository.findAll();
    }

    public Maintenance findById(Long id) {
        return maintenanceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Maintenance non trouvée"));
    }

    public void delete(Long id) {
        maintenanceRepository.deleteById(id);
    }
}