package ma.ensaf.springbootbases.model;

import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class Adresse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String rue;
    private String codePostal;
    private String ville;

    @JsonIgnoreProperties("adresses")
    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "adresses")
    private List<Personne> personnes = new ArrayList<>();

    public Adresse() {}
    public Adresse(String rue, String codePostal, String ville) {
        this.rue = rue; this.codePostal = codePostal; this.ville = ville;
    }

    // Getters w Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getRue() { return rue; }
    public void setRue(String rue) { this.rue = rue; }
    public String getCodePostal() { return codePostal; }
    public void setCodePostal(String codePostal) { this.codePostal = codePostal; }
    public String getVille() { return ville; }
    public void setVille(String ville) { this.ville = ville; }
    public List<Personne> getPersonnes() { return personnes; }
    public void setPersonnes(List<Personne> personnes) { this.personnes = personnes; }
}