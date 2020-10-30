function renderTable(reimbursements) {
	for(const reimbursement of reimbursements) {
		const tr = document.createElement("tr");
		const reId = document.createElement("td");
		const amount = document.createElement("td");
		const submitted = document.createElement("td");
		const resolved = document.createElement("td");
		const author = document.createElement("td");
		const status = document.createElement("td");
		const type = document.createElement("td");
		reId.innerText = reimbursement.reimbursementID;
		amount.innerText = reimbursement.amount;
		submitted.innerText = reimbursement.reimbursementSubmitted;
		resolved.innerText = reimbursement.reimbursementResolved;
		author.innerText = reimbursement.author;
		status.innerText = reimbursement.reimbursementStatus; reimbursementStatus
		type.innerText = reimbursement.reimbursementType;
		tr.append(reId, amount, submitted, resolved, author, status, type);
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
}

/*async function addReimbursement() {
	const reimbursement = {
	  id: document.getElementById("amount"),
	  status: document.getElementById("description")
	};
	const fetched = await fetch("http://localhost:8080/Reimbursement_System/reimbursement", {
	  method: "post",
	  body: JSON.stringify(reimbursement),
	});
	const json = await fetched.text();
	const rows = document.getElementById('reimbursementTableBody').innerHTML='';
	asyncFetch("http://localhost:8080/HallowsMonsters/reimbursement", renderTable);
  }*/

  document.getElementById("refresh").addEventListener("click", load);