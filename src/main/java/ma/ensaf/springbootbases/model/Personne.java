package ma.ensaf.springbootbases.model;

import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class Personne {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long num;
    private String nom;
    private String prenom;

    @JsonIgnoreProperties("personnes")
    @ManyToMany(cascade = { CascadeType.MERGE, CascadeType.REFRESH }, fetch = FetchType.EAGER)
    private List<Adresse> adresses = new ArrayList<>();

    public Personne() {}
    public Personne(String nom, String prenom) { this.nom = nom; this.prenom = prenom; }

    // Getters w Setters
    public Long getNum() { return num; }
    public void setNum(Long num) { this.num = num; }
    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }
    public String getPrenom() { return prenom; }
    public void setPrenom(String prenom) { this.prenom = prenom; }
    public List<Adresse> getAdresses() { return adresses; }
    public void setAdresses(List<Adresse> adresses) { this.adresses = adresses; }
}