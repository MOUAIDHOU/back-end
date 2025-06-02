package tn.hr.compteur.machine.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.hr.compteur.machine.enitite.Machine;



public interface MachineRepository extends JpaRepository<Machine, Long> {

}
