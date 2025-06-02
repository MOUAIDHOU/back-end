package tn.hr.compteur.machine.service;

import com.google.api.core.ApiFuture;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.hr.compteur.machine.enitite.Machine;
import tn.hr.compteur.machine.repository.MachineRepository;
import tn.hr.compteur.maintenace.enitite.Maintenance;
import tn.hr.compteur.piece.enitite.Piece;
import tn.hr.compteur.piece.repository.PieceRepository;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;

@Service
public class MachineService {
    @Autowired
    private final MachineRepository machineRepository;
    private final DatabaseReference firebaseDatabase;
    private final PieceRepository pieceRepository;

    @Autowired
    public MachineService(MachineRepository machineRepository, DatabaseReference firebaseDatabase, PieceRepository pieceRepository) {
        this.machineRepository = machineRepository;
        this.firebaseDatabase = firebaseDatabase;
        this.pieceRepository = pieceRepository;
    }


    public List<Machine> findAll() {
        return machineRepository.findAll();
    }

    public Machine findById(Long id) {
        return machineRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Machine non trouvée"));
    }

    public Machine save(Machine machine) {
        Machine savedMachine = machineRepository.save(machine)  ;
      //  testFirebaseWrite();
        return savedMachine;
    }

    private void createFirebaseMachineEntry(Machine machine) {
        System.out.println("je suis appelle, "+machine.getReference());
        DatabaseReference machineRef = firebaseDatabase.child("arduino").child(machine.getReference());

        Map<String, Object> machineData = new HashMap<>();
        machineData.put("nbpiecetravaille", 0);
        machineData.put("nbpiecetotale", 0);
        machineData.put("status", "stop");
        System.out.println("je suis appelle 2 ");
        // Use ApiFuture (Firebase Admin SDK async operation)
        machineRef.setValueAsync(machineData);
        System.out.println("je suis appelle 3 ");

    }

    public Machine update(Long id, Machine machineDetails) {
        Machine existingMachine = machineRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Machine non trouvée"));

        // Sauvegarder l'ancienne référence avant toute modification
        String oldReference = existingMachine.getReference();

        // Mettre à jour les champs
        existingMachine.setReference(machineDetails.getReference());
        // ... autres mises à jour de champs si nécessaire

        Machine updatedMachine = machineRepository.save(existingMachine);

        // Appeler avec les deux paramètres


        return updatedMachine;
    }
    public void delete(Long id) {
        machineRepository.findById(id).ifPresent(machine -> {
            deleteFirebaseMachineEntry(machine);
            machineRepository.delete(machine);
        });
    }

    public List<Maintenance> getMaintenancesByMachine(Long machineId) {
        Machine machine = findById(machineId);
        return machine.getMaintenances();
    }


    private void deleteFirebaseMachineEntry(Machine machine) {
        DatabaseReference machineRef = firebaseDatabase.child("arduino").child(machine.getReference());

        try {
            machineRef.removeValueAsync().get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException("Erreur Firebase lors de la suppression", e);
        }
    }

    public Machine assignPiece(Long machineId, Long pieceId) {
        Optional<Machine> optionalMachine = machineRepository.findById(machineId);
        Optional<Piece> optionalPiece = pieceRepository.findById(pieceId);

        if (optionalMachine.isPresent() && optionalPiece.isPresent()) {
            Machine machine = optionalMachine.get();
            machine.setPiece(optionalPiece.get());
            return machineRepository.save(machine);
        }

        return null;
    }

}