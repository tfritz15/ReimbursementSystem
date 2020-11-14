function renderTable(reimbursements) {
	document.getElementById("reimbursementTableBody").innerHTML = "";
	for(const reimbursement of reimbursements) {
		const tr = document.createElement("tr");
		const reId = document.createElement("td");
		const amount = document.createElement("td");
		const description = document.createElement("td");
		const submitted = document.createElement("td");
		const resolved = document.createElement("td");
		const author = document.createElement("td");
		const resolver = document.createElement("td");
		const status = document.createElement("td");
		const type = document.createElement("td");
		if(reimbursement.reimbursementStatus === 0) {
			reId.innerText = reimbursement.reimbursementID;
			amount.innerText = reimbursement.amount;
			description.innerText = reimbursement.description;
			let dateSubmit = new Date(reimbursement.reimbursementSubmitted)
			submitted.innerText = dateSubmit.toString();
			resolved.innerText = "N/A";
			author.innerText = reimbursement.author.firstName + " " + reimbursement.author.lastName;
			resolver.innerText = "N/A";
			status.innerText = reimbursement.reimbursementStatus;
			type.innerText = reimbursement.reimbursementType;
			tr.append(reId, amount, description, submitted, resolved, author, resolver, status, type);
		} else {
			reId.innerText = reimbursement.reimbursementID;
			amount.innerText = reimbursement.amount;
			description.innerText = reimbursement.description;
			let dateSubmit = new Date(reimbursement.reimbursementSubmitted)
			submitted.innerText = dateSubmit.toString();
			let dateResolved = new Date(reimbursement.reimbursementResolved);
			resolved.innerText = dateResolved.toString();
			author.innerText = reimbursement.author.firstName + " " + reimbursement.author.lastName;
			author.innerText = reimbursement.resolver.firstName + " " + reimbursement.resolver.lastName;
			status.innerText = reimbursement.reimbursementStatus;
			type.innerText = reimbursement.reimbursementType;
			tr.append(reId, amount, description, submitted, resolved, author, resolver, status, type);
		}
		document.getElementById("reimbursementTableBody").append(tr);
	}
}

async function asyncFetch(url, expression) {
	const response = await fetch(url);
	const json = await response.json();
	expression(json);
}

function load() {
	asyncFetch("http://localhost:8080/Reimbursement_System/reimbursement", renderTable);
	console.log("here");
}

load();

document.getElementById("refresh").addEventListener("click", load);