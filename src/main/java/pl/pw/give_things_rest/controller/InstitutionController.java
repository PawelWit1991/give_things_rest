package pl.pw.give_things_rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import pl.pw.give_things_rest.model.Institution;
import pl.pw.give_things_rest.service.InstitutionService;

import java.util.List;

@RestController
@RequestMapping("/api/institution")
public class InstitutionController {

    @Autowired
    private InstitutionService institutionService;


    @GetMapping("all")
    public Page<Institution> all(Pageable pageable) {
        return institutionService.all(pageable);
    }

    @GetMapping("/{id}")
    public Institution findById(@PathVariable Long id) {
        return institutionService.findByInstitutionId(id);
    }

    @PostMapping
    public Institution create(@RequestBody Institution institution) {
        return institutionService.save(institution);
    }

    @PutMapping
    public Institution update(@RequestBody Institution institution) {
        return institutionService.update(institution);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        institutionService.delete(id);
    }
}
