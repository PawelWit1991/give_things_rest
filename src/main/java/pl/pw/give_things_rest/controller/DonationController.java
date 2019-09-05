package pl.pw.give_things_rest.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import pl.pw.give_things_rest.model.Donation;
import pl.pw.give_things_rest.service.DonationService;



@RestController
@RequestMapping("/api/donation")
public class DonationController {

    @Autowired
    private DonationService donationService;

    @PostMapping
    public Donation add(@RequestBody Donation donation){
        return donationService.save(donation);
    }

    @GetMapping("{id}")
    public Donation read(@PathVariable Long id){
        return donationService.getById(id);
    }

    @GetMapping("/all")
    public Page<Donation> all(Pageable pageable){
        return donationService.findAll(pageable);
    }
}
