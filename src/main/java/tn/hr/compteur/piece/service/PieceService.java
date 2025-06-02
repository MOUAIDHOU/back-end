package tn.hr.compteur.piece.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import tn.hr.compteur.piece.enitite.Piece;
import tn.hr.compteur.piece.repository.PieceRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PieceService {

    private final PieceRepository pieceRepository;

    public Piece save(Piece piece) {
        return pieceRepository.save(piece);
    }

    public List<Piece> getAll() {
        return pieceRepository.findAll();
    }

    public Piece getById(Long id) {
        return pieceRepository.findById(id).orElse(null);
    }

    public void delete(Long id) {
        pieceRepository.deleteById(id);
    }

    public Piece update(Long id, Piece updatedPiece) {
        Optional<Piece> optionalPiece = pieceRepository.findById(id);
        if (optionalPiece.isPresent()) {
            Piece piece = optionalPiece.get();
            piece.setNom(updatedPiece.getNom());
            piece.setDescription(updatedPiece.getDescription());
            return pieceRepository.save(piece);
        } else {
            return null;
        }
    }
}
