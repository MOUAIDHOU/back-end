package tn.hr.compteur.machine.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.hr.compteur.machine.enitite.Machine;
import tn.hr.compteur.machine.service.MachineService;
import tn.hr.compteur.maintenace.enitite.Maintenance;

import java.util.List;

@RestController
@RequestMapping("/api/machines")
public class MachineController {
    @Autowired
    private MachineService machineService;

    @GetMapping
    public List<Machine> getAllMachines() {
        return machineService.findAll();
    }

    @GetMapping("/{id}")
    public Machine getMachineById(@PathVariable Long id) {
        return machineService.findById(id);
    }

    @PostMapping
    public Machine createMachine(@RequestBody Machine machine) {
        return machineService.save(machine);
    }

    @PutMapping("/{id}")
    public Machine updateMachine(@PathVariable Long id, @RequestBody Machine machineDetails) {
        return machineService.update(id, machineDetails);
    }

    @DeleteMapping("/{id}")
    public void deleteMachine(@PathVariable Long id) {
        machineService.delete(id);
    }

    @GetMapping("/{id}/maintenances")
    public List<Maintenance> getMaintenancesByMachine(@PathVariable Long id) {
        return machineService.getMaintenancesByMachine(id);
    }
    @PutMapping("/{machineId}/piece/{pieceId}")
    public ResponseEntity<Machine> assignPieceToMachine(
            @PathVariable Long machineId,
            @PathVariable Long pieceId
    ) {
        Machine updated = machineService.assignPiece(machineId, pieceId);
        return updated != null ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
    }

}
