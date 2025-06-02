package tn.hr.compteur.piece.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import tn.hr.compteur.piece.enitite.Piece;

public interface PieceRepository extends JpaRepository<Piece, Long> {
}
