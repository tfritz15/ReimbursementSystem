function renderTable(reimbursements) {
	for(const reimbursement of reimbursements) {
		const tr = document.createElement("tr");
		const reId = document.createElement("td");
		const amount = document.createElement("td");
		const submitted = document.createElement("td");
		const resolved = document.createElement("td");
		const author = document.createElement("td");
		const resolver = document.createElement("td");
		const status = document.createElement("td");
		const type = document.createElement("td");
		reId.innerText = reimbursement.reimbursementID;
		amount.innerText = reimbursement.amount;
		submitted.innerText = reimbursement.reimbursementSubmitted;
		resolved.innerText = reimbursement.reimbursementResolved;
		author.innerText = reimbursement.author;
		resolver.innerText = reimbursement.resolver;
		status.innerText = reimbursement.reimbursementStatus;
		type.innerText = reimbursement.reimbursementType;
		tr.append(reId, amount, submitted, resolved, author, resolver, status, type);
		document.getElementById("pendingTableBody").append(tr);
	}
}

async function asyncFetch(url, expression) {
	const response = await fetch(url);
	const json = await response.json();
	expression(json);
}

function load() {
	asyncFetch("http://localhost:8080/Reimbursement_System/pending", renderTable);
}

document.getElementById("refresh").addEventListener("click", load);