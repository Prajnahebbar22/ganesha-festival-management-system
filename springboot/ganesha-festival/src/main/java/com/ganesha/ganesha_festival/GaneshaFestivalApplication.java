package com.ganesha.ganesha_festival;

import java.util.List;
import com.ganesha.ganesha_festival.entity.Donation;
import org.springframework.context.ConfigurableApplicationContext;
import com.ganesha.ganesha_festival.repository.DonationRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class GaneshaFestivalApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context =
        SpringApplication.run(GaneshaFestivalApplication.class, args);
		DonationRepository donationRepository =
        context.getBean(DonationRepository.class);
		List<Donation> donations = donationRepository.findAll();

		for (Donation donation : donations) {
                     System.out.println(donation.getDonationId() + " | "
            + donation.getDonorName() + " | "
            + donation.getAmount());
		}
	}

}
