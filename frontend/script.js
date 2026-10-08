const donationForm = document.getElementById("donationForm");

donationForm.addEventListener("submit", function (event) {

    event.preventDefault();

    const donorName = document.getElementById("donorName").value;
    const amount = Number(document.getElementById("amount").value);
    const donationType = document.getElementById("donationType").value;
    const paymentMode = document.getElementById("paymentMode").value;
    const festivalYear = Number(document.getElementById("festivalYear").value);
    const remarks = document.getElementById("remarks").value;

    // Create donation object FIRST
    const donation = {
        donorName: donorName,
        amount: amount,
        donationType: donationType,
        paymentMode: paymentMode,
        festivalYear: festivalYear,
        remarks: remarks
    };

    console.log(donation);

    // Send donation to Spring Boot
    fetch("http://localhost:8080/api/donations", {
        method: "POST",

        headers: {
            "Content-Type": "application/json"
        },

        body: JSON.stringify(donation)
    })
        .then(response => response.json())
        .then(data => {
            console.log("Donation saved:", data);
        })
        .catch(error => {
            console.error("Error:", error);
        });

});
const donationList = document.getElementById("donationList");

fetch("http://localhost:8080/api/donations")
    .then(response => response.json())
    .then(data => {

        console.log("All donations:", data);

        data.forEach(function (donation) {

            const donationItem = document.createElement("p");

            donationItem.textContent =
                donation.donorName + " - ₹" + donation.amount;

            donationList.appendChild(donationItem);

        });
    })
    .catch(error => {
        console.error("Error loading donations:", error);
    });
