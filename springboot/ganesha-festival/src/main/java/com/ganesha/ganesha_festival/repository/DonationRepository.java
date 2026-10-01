package com.ganesha.ganesha_festival.repository;

import com.ganesha.ganesha_festival.entity.Donation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DonationRepository extends JpaRepository<Donation, Integer> {

}