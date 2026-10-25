package ma.ensaf.springbootbases.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ma.ensaf.springbootbases.dao.PersonneRepository;
import ma.ensaf.springbootbases.model.Personne;

@Controller
public class PersonneController {

    private static final List<String> CHAMPS_TRI = List.of("num", "nom", "prenom");

    @Autowired
    private PersonneRepository personneRepository;

    @GetMapping("/")
    public String home() { return "redirect:/showAll"; }

    @GetMapping(value = "/addPerson")
    public String addPerson() { return "addPerson"; }

    @PostMapping(value = "/addPerson")
    public ModelAndView addPerson(@RequestParam(value = "nom") String nom, @RequestParam(value = "prenom") String prenom) {
        Personne personne = new Personne(nom, prenom);
        personneRepository.save(personne);
        ModelAndView mv = new ModelAndView("confirm");
        mv.addObject("nom", nom);
        mv.addObject("prenom", prenom);
        return mv;
    }

    @GetMapping(value = "/showAll")
    public ModelAndView showAll() {
        List<Personne> personnes = personneRepository.findAll();
        ModelAndView mv = new ModelAndView("result");
        mv.addObject("personnes", personnes);
        return mv;
    }

    @GetMapping(value = "/showAllByPage/{i}/{j}")
    public ModelAndView showAllByPage(@PathVariable int i, @PathVariable int j) {
        int page = Math.max(i, 0);
        int taille = Math.max(j, 1);
        Page<Personne> personnes = personneRepository.findAll(PageRequest.of(page, taille));
        ModelAndView mv = new ModelAndView("result");
        mv.addObject("personnes", personnes.getContent());
        mv.addObject("pageCourante", page);
        mv.addObject("taille", taille);
        mv.addObject("totalPages", personnes.getTotalPages());
        return mv;
    }

    @GetMapping(value = "/showAllSorted/{v}")
    public ModelAndView showAllSorted(@PathVariable String v) {
        String champ = CHAMPS_TRI.contains(v) ? v : "nom";
        List<Personne> personnes = personneRepository.findAll(Sort.by(champ));
        ModelAndView mv = new ModelAndView("result");
        mv.addObject("personnes", personnes);
        return mv;
    }

    @GetMapping(value = "/search")
    public ModelAndView search(@RequestParam(value = "nom") String nom) {
        List<Personne> personnes = personneRepository.findByNomContainingIgnoreCase(nom);
        ModelAndView mv = new ModelAndView("result");
        mv.addObject("personnes", personnes);
        return mv;
    }
}