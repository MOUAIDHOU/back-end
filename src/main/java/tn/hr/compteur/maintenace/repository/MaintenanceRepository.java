package tn.hr.compteur.maintenace.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.hr.compteur.maintenace.enitite.Maintenance;

import java.util.List;

public interface MaintenanceRepository extends JpaRepository<Maintenance, Long> {
    List<Maintenance> findByTechnicienId(Long technicienId);
    List<Maintenance> findByMachineId(Long machineId);
}
