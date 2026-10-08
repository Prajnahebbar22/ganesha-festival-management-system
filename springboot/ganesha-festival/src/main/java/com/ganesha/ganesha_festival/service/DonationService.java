package com.ganesha.ganesha_festival.service;

import com.ganesha.ganesha_festival.entity.Donation;
import com.ganesha.ganesha_festival.repository.DonationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DonationService {

    private final DonationRepository donationRepository;

    public DonationService(DonationRepository donationRepository) {
        this.donationRepository = donationRepository;
    }

    public List<Donation> getAllDonations() {
        return donationRepository.findAll();
    }

    public Donation saveDonation(Donation donation) {
        return donationRepository.save(donation);
    }
    public Donation updateDonation(Integer id, Donation donation) {
       donation.setDonationId(id);
       return donationRepository.save(donation);
}
}