
const API_URL = "http://localhost:8080/api/donations";

const donationForm = document.getElementById("donationForm");
const donationList = document.getElementById("donationList");

// Load and display all existing donations
async function loadDonations() {
    try {
        const response = await fetch(API_URL);

        if (!response.ok) {
            throw new Error("Failed to load donations.");
        }

        const donations = await response.json();

        console.log("All donations:", donations);

        // Clear old rows before displaying fresh data
        donationList.innerHTML = "";

        donations.forEach(function (donation) {
            const row = document.createElement("tr");

            const values = [
                donation.donationId,
                donation.donorName,
                `₹${donation.amount}`,
                donation.donationType,
                donation.paymentMode,
                donation.festivalYear,
                donation.donationDate ?? ""
            ];

            values.forEach(function (value) {
                const cell = document.createElement("td");
                cell.textContent = value ?? "";
                row.appendChild(cell);
            });

            donationList.appendChild(row);
        });

    } catch (error) {
        console.error("Error loading donations:", error);
        alert("Could not load donations. Check whether the backend is running.");
    }
}

// Save a new donation when the form is submitted
donationForm.addEventListener("submit", async function (event) {
    event.preventDefault();

    const donorName = document.getElementById("donorName").value.trim();
    const amount = Number(document.getElementById("amount").value);
    const donationType = document.getElementById("donationType").value;
    const paymentMode = document.getElementById("paymentMode").value;
    const festivalYear = Number(document.getElementById("festivalYear").value);
    const remarks = document.getElementById("remarks").value.trim();

    // Basic frontend validation
    if (!donorName || !Number.isFinite(amount) || amount <= 0) {
        alert("Please enter a donor name and an amount greater than zero.");
        return;
    }

    const donation = {
        donorName: donorName,
        amount: amount,
        donationType: donationType,
        paymentMode: paymentMode,
        festivalYear: festivalYear,
        remarks: remarks
    };

    console.log("Donation to save:", donation);

    try {
        const response = await fetch(API_URL, {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(donation)
        });

        if (!response.ok) {
            const errorText = await response.text();
            throw new Error(
                `Save failed (${response.status}): ${errorText}`
            );
        }

        const savedDonation = await response.json();

        console.log("Donation saved:", savedDonation);

        alert("Donation saved successfully!");

        donationForm.reset();

        // Refresh the table to include the newly saved donation
        await loadDonations();

    } catch (error) {
        console.error("Error saving donation:", error);
        alert("Could not save the donation. Check the backend and the error in the browser console.");
    }
});

// Load existing donations when the page opens
loadDonations();
