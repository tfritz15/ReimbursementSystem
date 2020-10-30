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
		type.innerText = reimbursement.reimbursementType; //not showing up
		//tr.append(reId, amount, submitted, resolved, author, status, type);
		tr.appendChild(reId);
		tr.appendChild(amount);
		tr.appendChild(submitted);
		tr.appendChild(resolved);
		tr.appendChild(author);
		tr.appendChild(resolver);
		tr.appendChild(status);
		tr.appendChild(type);
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
/*async function addReimbursement() {
	const reimbursement = {
	  amount: document.getElementById("amount"),
	  type: document.getElementById("description"),
	  description: document.getElementById("type")
	};
	const fetched = await fetch("http://localhost:8080/Reimbursement_System/pending", {
	  method: "post",
	  body: JSON.stringify(reimbursement),
	});
	const json = await fetched.text();
	const rows = document.getElementById('pendingTableBody').innerHTML='';
	asyncFetch("http://localhost:8080/HallowsMonsters/pending", renderTable);
  }*/

  document.getElementById("refresh").addEventListener("click", load);