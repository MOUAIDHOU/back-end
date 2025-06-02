package tn.hr.compteur.piece.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import tn.hr.compteur.piece.enitite.Piece;
import tn.hr.compteur.piece.service.PieceService;

import java.util.List;

@RestController
@RequestMapping("/api/pieces")
@RequiredArgsConstructor
public class PieceController {

    private final PieceService pieceService;

    @PostMapping
    public ResponseEntity<Piece> create(@RequestBody Piece piece) {
        return ResponseEntity.ok(pieceService.save(piece));
    }

    @GetMapping
    public ResponseEntity<List<Piece>> getAll() {
        return ResponseEntity.ok(pieceService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Piece> getById(@PathVariable Long id) {
        Piece piece = pieceService.getById(id);
        return piece != null ? ResponseEntity.ok(piece) : ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Piece> update(@PathVariable Long id, @RequestBody Piece piece) {
        Piece updated = pieceService.update(id, piece);
        return updated != null ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        pieceService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
