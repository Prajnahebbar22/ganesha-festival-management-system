package com.ganesha.ganesha_festival.controller;

import com.ganesha.ganesha_festival.entity.Donation;
import com.ganesha.ganesha_festival.service.DonationService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:5500")
@RestController
@RequestMapping("/api/donations")

public class DonationController {

    private final DonationService donationService;

    public DonationController(DonationService donationService) {
        this.donationService = donationService;
    }

    @GetMapping
    public List<Donation> getAllDonations() {
        return donationService.getAllDonations();
    }

    @PostMapping
    public Donation saveDonation(@Valid @RequestBody Donation donation) {
        return donationService.saveDonation(donation);
    }
    @PutMapping("/{id}")
    public Donation updateDonation(
            @PathVariable Integer id,
            @Valid @RequestBody Donation donation) {

        return donationService.updateDonation(id, donation);
    }
    @DeleteMapping("/{id}")
    public void deleteDonation(@PathVariable Integer id) {
           donationService.deleteDonation(id);
}
}