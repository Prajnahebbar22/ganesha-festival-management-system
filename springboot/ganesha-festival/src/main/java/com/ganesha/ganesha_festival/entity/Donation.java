package com.ganesha.ganesha_festival.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "donations")
public class Donation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "donation_id")
    private Integer donationId;

    @Column(name = "donor_name")
    private String donorName;

    @Column(name = "amount")
    private java.math.BigDecimal amount;

    @Column(name = "donation_type")
    private String donationType;

    @Column(name = "payment_mode")
    private String paymentMode;

    @Column(name = "donation_date")
    private java.time.LocalDate donationDate;

    @Column(name = "festival_year")
    private Integer festivalYear;

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

    public java.math.BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(java.math.BigDecimal amount) {
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

    public java.time.LocalDate getDonationDate() {
        return donationDate;
    }

    public void setDonationDate(java.time.LocalDate donationDate) {
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