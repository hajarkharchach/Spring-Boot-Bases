package ma.ensaf.springbootbases.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ma.ensaf.springbootbases.dao.AdresseRepository;
import ma.ensaf.springbootbases.dao.PersonneRepository;
import ma.ensaf.springbootbases.model.Adresse;
import ma.ensaf.springbootbases.model.Personne;

@RestController
public class PersonneRestController {

    private static final String JSON = MediaType.APPLICATION_JSON_VALUE;
    private static final String XML = MediaType.APPLICATION_XML_VALUE;

    @Autowired
    private PersonneRepository personneRepository;
    @Autowired
    private AdresseRepository adresseRepository;

    @GetMapping(value = "/personnes", produces = { JSON, XML })
    public List<Personne> getPersonnes() { return personneRepository.findAll(); }

    @GetMapping(value = "/personnes/{id}", produces = { JSON, XML })
    public Personne getPersonne(@PathVariable("id") long id) {
        return personneRepository.findById(id).orElse(null);
    }

    @PostMapping(value = "/personnes", consumes = { JSON, XML }, produces = { JSON, XML })
    public Personne addPersonne(@RequestBody Personne personne) {
        List<Adresse> adresses = personne.getAdresses();
        if (adresses != null) {
            for (int k = 0; k < adresses.size(); k++) {
                Adresse adresse = adresses.get(k);
                if (adresse.getId() != null) {
                    Long idAdresse = adresse.getId();
                    adresses.set(k, adresseRepository.findById(idAdresse).orElseThrow(
                            () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Adresse introuvable")));
                } else {
                    adresses.set(k, adresseRepository.save(adresse));
                }
            }
        }
        return personneRepository.saveAndFlush(personne);
    }
}