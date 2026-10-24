
const API_URL = "http://localhost:8080/api/donations";

const donationForm = document.getElementById("donationForm");
const donationList = document.getElementById("donationList");

let allDonations = [];

const money = amount =>
    Number(amount || 0).toLocaleString("en-IN", {
        style: "currency",
        currency: "INR",
        minimumFractionDigits: 2
    });

function showMessage(message) {
    const toast = document.getElementById("toast");

    if (toast) {
        toast.textContent = message;
        toast.classList.add("show");

        setTimeout(() => toast.classList.remove("show"), 3000);
    } else {
        alert(message);
    }
}

function updateDashboard() {
    const total = allDonations.reduce(
        (sum, d) => sum + Number(d.amount || 0), 0
    );

    const festival = allDonations
        .filter(d => d.donationType === "FESTIVAL")
        .reduce((sum, d) => sum + Number(d.amount || 0), 0);

    const food = allDonations
        .filter(d => d.donationType === "FOOD")
        .reduce((sum, d) => sum + Number(d.amount || 0), 0);

    const arti = allDonations
        .filter(d => d.donationType === "ARTI")
        .reduce((sum, d) => sum + Number(d.amount || 0), 0);

    document.getElementById("totalDonations").textContent = money(total);
    document.getElementById("festivalDonations").textContent = money(festival);
    document.getElementById("foodDonations").textContent = money(food);

    // Arti is included in total donations.
    // Show its separate amount in the browser console for now.
    console.log("Arti donations:", money(arti));

    // Combine all donations by donor name.
    const donorTotals = new Map();

    allDonations.forEach(d => {
        const name = String(d.donorName || "").trim();
        const key = name.toLowerCase();

        if (!key) return;

        const existing = donorTotals.get(key) || {
            name,
            total: 0
        };

        existing.total += Number(d.amount || 0);
        donorTotals.set(key, existing);
    });

    const eligibleDonors = [...donorTotals.values()]
        .filter(d => d.total > 200)
        .sort((a, b) => a.name.localeCompare(b.name));

    document.getElementById("eligibleDonors").textContent =
        eligibleDonors.length;

    document.getElementById("invitationCount").textContent =
        eligibleDonors.length;

    const invitationList = document.getElementById("invitationList");
    invitationList.replaceChildren();

    if (eligibleDonors.length === 0) {
        const empty = document.createElement("p");
        empty.className = "empty-message";
        empty.textContent = "No eligible donors yet.";
        invitationList.appendChild(empty);
    } else {
        eligibleDonors.forEach(donor => {
            const item = document.createElement("div");
            item.className = "invitation-item";

            const name = document.createElement("strong");
            name.textContent = donor.name;

            const amount = document.createElement("span");
            amount.textContent = money(donor.total);

            item.append(name, amount);
            invitationList.appendChild(item);
        });
    }
}

function renderDonations() {
    const search = (
        document.getElementById("searchDonations")?.value || ""
    ).trim().toLowerCase();

    const category =
        document.getElementById("filterDonationType")?.value || "ALL";

    const filtered = allDonations.filter(d => {
        const matchesName = String(d.donorName || "")
            .toLowerCase()
            .includes(search);

        const matchesCategory =
            category === "ALL" || d.donationType === category;

        return matchesName && matchesCategory;
    });

    donationList.replaceChildren();

    if (filtered.length === 0) {
        const row = document.createElement("tr");
        const cell = document.createElement("td");

        cell.colSpan = 8;
        cell.className = "empty-cell";
        cell.textContent = "No matching donation records.";

        row.appendChild(cell);
        donationList.appendChild(row);
    }

    filtered.forEach(d => {
        const row = document.createElement("tr");

        const values = [
            d.donationId,
            d.donorName,
            money(d.amount),
            d.donationType,
            d.paymentMode,
            d.festivalYear,
            d.donationDate || ""
        ];

        values.forEach((value, index) => {
            const cell = document.createElement("td");

            if (index === 3) {
                const badge = document.createElement("span");
                badge.className = "category-badge category-" +
                    String(value || "").toLowerCase();
                badge.textContent = value || "";
                cell.appendChild(badge);
            } else if (index === 4) {
                const badge = document.createElement("span");
                badge.className = "payment-badge";
                badge.textContent = value || "";
                cell.appendChild(badge);
            } else {
                cell.textContent = value ?? "";
            }

            row.appendChild(cell);
        });

        const actions = document.createElement("td");
        const deleteButton = document.createElement("button");

        deleteButton.type = "button";
        deleteButton.className = "action-button delete-button";
        deleteButton.textContent = "Delete";

        deleteButton.addEventListener("click", async () => {
            if (!confirm(`Delete the donation from ${d.donorName}?`)) {
                return;
            }

            try {
                const response = await fetch(
                    `${API_URL}/${d.donationId}`,
                    { method: "DELETE" }
                );

                if (!response.ok) {
                    throw new Error(`Delete failed: ${response.status}`);
                }

                showMessage("Donation deleted successfully.");
                await loadDonations();
            } catch (error) {
                console.error(error);
                showMessage("Could not delete the donation.");
            }
        });

        actions.appendChild(deleteButton);
        row.appendChild(actions);
        donationList.appendChild(row);
    });

    const recordCount = document.getElementById("recordCount");
    if (recordCount) {
        recordCount.textContent = `${filtered.length} records`;
    }

    const tableSummary = document.getElementById("tableSummary");
    if (tableSummary) {
        tableSummary.textContent =
            `Showing ${filtered.length} of ${allDonations.length} donations`;
    }
}

async function loadDonations() {
    try {
        const response = await fetch(API_URL);

        if (!response.ok) {
            throw new Error(`Loading failed: ${response.status}`);
        }

        allDonations = await response.json();

        updateDashboard();
        renderDonations();

        console.log("Donations loaded:", allDonations);
    } catch (error) {
        console.error("Error loading donations:", error);
        showMessage("Could not load donations. Check the backend server.");
    }
}

donationForm.addEventListener("submit", async event => {
    event.preventDefault();

    const donorName =
        document.getElementById("donorName").value.trim();
    const amount =
        Number(document.getElementById("amount").value);
    const donationType =
        document.getElementById("donationType").value;
    const paymentMode =
        document.getElementById("paymentMode").value;
    const festivalYear =
        Number(document.getElementById("festivalYear").value);
    const remarks =
        document.getElementById("remarks").value.trim();

    if (!donorName || !Number.isFinite(amount) || amount <= 0) {
        showMessage("Enter a donor name and a positive donation amount.");
        return;
    }

    if (!Number.isInteger(festivalYear) ||
        festivalYear < 2000 || festivalYear > 2100) {
        showMessage("Enter a valid festival year.");
        return;
    }

    const donation = {
        donorName,
        amount,
        donationType,
        paymentMode,
        festivalYear,
        remarks
    };

    try {
        const response = await fetch(API_URL, {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(donation)
        });

        if (!response.ok) {
            const errorText = await response.text();
            throw new Error(`${response.status}: ${errorText}`);
        }

        showMessage("Donation saved successfully!");
        donationForm.reset();
        await loadDonations();
    } catch (error) {
        console.error("Error saving donation:", error);
        showMessage("Could not save donation. Check the browser console.");
    }
});

document.getElementById("searchDonations")
    ?.addEventListener("input", renderDonations);

document.getElementById("filterDonationType")
    ?.addEventListener("change", renderDonations);

loadDonations();
