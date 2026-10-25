package ma.ensaf.springbootbases.dao;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ma.ensaf.springbootbases.model.Personne;

public interface PersonneRepository extends JpaRepository<Personne, Long> {
    List<Personne> findByNomAndPrenom(String nom, String prenom);
    List<Personne> findByNom(String nom);
    List<Personne> findByNomContainingIgnoreCase(String nom);

    @Query("select p from Personne p where p.prenom = ?1")
    List<Personne> chercherSelonLePrenom(String prenom);
}