package com.ganesha.ganesha_festival.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "donations")
public class Donation {
	 @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "donation_id")
    private Integer donationId;

        @NotBlank(message = "Donor name is required")
    @Size(max = 100, message = "Donor name cannot exceed 100 characters")
    @Column(name = "donor_name")
    private String donorName;

    @NotNull(message = "Donation amount is required")
    @Positive(message = "Donation amount must be greater than zero")
    @Column(name = "amount")
    private BigDecimal amount;

    @NotBlank(message = "Donation type is required")
    @Column(name = "donation_type")
    private String donationType;

    @NotBlank(message = "Payment mode is required")
    @Column(name = "payment_mode")
    private String paymentMode;

    @NotNull(message = "Donation date is required")
    @Column(name = "donation_date")
    private LocalDate donationDate = LocalDate.now();

    @NotNull(message = "Festival year is required")
    @Min(value = 2000, message = "Festival year must be 2000 or later")
    @Column(name = "festival_year")
    private Integer festivalYear;

    @Size(max = 500, message = "Remarks cannot exceed 500 characters")
    @Column(name = "remarks")
    private String remarks;
    

    public Integer getDonationId() {
        return donationId;
    }

    public void setDonationId(Integer donationId) {
        this.donationId = donationId;
    }

    public String getDonorName() {
        return donorName;
    }

    public void setDonorName(String donorName) {
        this.donorName = donorName;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getDonationType() {
        return donationType;
    }

    public void setDonationType(String donationType) {
        this.donationType = donationType;
    }

    public String getPaymentMode() {
        return paymentMode;
    }

    public void setPaymentMode(String paymentMode) {
        this.paymentMode = paymentMode;
    }

    public LocalDate getDonationDate() {
        return donationDate;
    }

    public void setDonationDate(LocalDate donationDate) {
        this.donationDate = donationDate;
    }

    public Integer getFestivalYear() {
        return festivalYear;
    }

    public void setFestivalYear(Integer festivalYear) {
        this.festivalYear = festivalYear;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}