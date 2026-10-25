package ma.ensaf.springbootbases.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ma.ensaf.springbootbases.model.Adresse;

public interface AdresseRepository extends JpaRepository<Adresse, Long> {
}