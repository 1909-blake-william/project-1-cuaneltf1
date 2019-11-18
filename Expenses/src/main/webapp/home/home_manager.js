let currentUser;
function approveOrDeny(event) {
    event.preventDefault(); // stop page from refreshing
    console.log('submitted');
    
    const ruling = getRulingFromInputs();

    fetch('http://localhost:8080/Expenses/reimbursements', {
        method: 'PUT',
        body: JSON.stringify(ruling),
        headers: {
            'content-type': 'application/json'
        }
    })
    .then(res => res.json())
    .then(data => {
        refreshTable();
        console.log(data);
    })
    .catch(err => console.log(err));

}
function newAllSort(event) {
    event.preventDefault();
    refreshTable();
}

// function logOut(event) {
    
// }

//maybe place them in an if or switcgh statement
function newPendingSort(event) {
    event.preventDefault();

    fetch(`http://localhost:8080/Expenses/reimbursements?statusId=${1}`, {
    	credentials: 'include'
    })
        .then(res => res.json())
        .then(data => {
            let numRows = document.getElementById('reimbursement-table-body').rows.length
            for (let i = numRows - 1; i > -1; i--) {
                document.getElementById('reimbursement-table-body').deleteRow(i)
            }
            data.forEach(addReimbursementToTableSafe)
        })
        .catch(console.log);
}

function newAcceptedSort(event) {
    event.preventDefault();

    fetch(`http://localhost:8080/Expenses/reimbursements?statusId=${2}`, {
    	credentials: 'include'
    })
        .then(res => res.json())
        .then(data => {
            let numRows = document.getElementById('reimbursement-table-body').rows.length
            for (let i = numRows - 1; i > -1; i--) {
                document.getElementById('reimbursement-table-body').deleteRow(i)
            }
            data.forEach(addReimbursementToTableSafe)
        })
        .catch(console.log);
}

function newDenyedSort(event) {
    event.preventDefault();

    fetch(`http://localhost:8080/Expenses/reimbursements?statusId=${3}`, {
    	credentials: 'include'
    })
        .then(res => res.json())
        .then(data => {
            let numRows = document.getElementById('reimbursement-table-body').rows.length
            for (let i = numRows - 1; i > -1; i--) {
                document.getElementById('reimbursement-table-body').deleteRow(i)
            }
            data.forEach(addReimbursementToTableSafe)
        })
        .catch(console.log);
}

function addReimbursementToTableSafe(reimbursement) {

    // create the row element
    const row = document.createElement('tr');

    // create all the td elements and append them to the row
    const idData = document.createElement('td');
    idData.innerText = reimbursement.reimbursement_id;
    row.appendChild(idData);

    const amountData = document.createElement('td');
    amountData.innerText = "$" + reimbursement.reimbursement_amount;
    row.appendChild(amountData);

    const dateSubData = document.createElement('td');
    let d = new Date(reimbursement.reimbursement_submitted);
    let formattedDate = d.getDate() + "-" + (d.getMonth() + 1) + "-" + d.getFullYear();
    let hours = (d.getHours() < 10) ? "0" + d.getHours() : d.getHours();
    let minutes = (d.getMinutes() < 10) ? "0" + d.getMinutes() : d.getMinutes();
    let formattedTime = hours + ":" + minutes;
    formattedDate = formattedDate + " " + formattedTime;
    dateSubData.innerText = formattedDate;
    row.appendChild(dateSubData);

    const dateResData = document.createElement('td');
    //prevents the reimbursements that are yet to be resolved from showing up
    if(reimbursement.reimbursement_resolved != null){
    let r = new Date(reimbursement.reimbursement_resolved);
    let formattedD = r.getDate() + "-" + (r.getMonth() + 1) + "-" + r.getFullYear();
    let h = (r.getHours() < 10) ? "0" + r.getHours() : r.getHours();
    let m = (r.getMinutes() < 10) ? "0" + r.getMinutes() : r.getMinutes();
    let formattedT = h + ":" + m;
    formattedD = formattedD + " " + formattedT;
    dateResData.innerText = formattedD;
    }else{
        dateResData.innerText = "Not resolved yet";
    }
    row.appendChild(dateResData);

    const descData = document.createElement('td');
    descData.innerText = reimbursement.reimbursement_description;
    row.appendChild(descData);

    const zavResData = document.createElement('td');
    zavResData.innerText = reimbursement.reimbursement_resolution;
    row.appendChild(zavResData);

    const statData = document.createElement('td');
    //specifies the request type in a way an employee could understand it
    if(reimbursement.reimbursement_status_id === 1){
        reimbursement.reimbursement_status_id = "Pending";
    }else if(reimbursement.reimbursement_status_id === 2){
        reimbursement.reimbursement_status_id = "Accepted";
    }else if(reimbursement.reimbursement_status_id === 3){
        reimbursement.reimbursement_status_id = "Denied"; 
    }
    statData.innerText = reimbursement.reimbursement_status_id;
    row.appendChild(statData);

    const typeData = document.createElement('td');
    if(reimbursement.reimbursement_type_id === 1){
        reimbursement.reimbursement_type_id = "Lodging";
    } else if(reimbursement.reimbursement_type_id === 2){
        reimbursement.reimbursement_type_id = "Travel";
    } else if(reimbursement.reimbursement_type_id === 3){
        reimbursement.reimbursement_type_id = "Food";
    } else if(reimbursement.reimbursement_type_id === 4){
        reimbursement.reimbursement_type_id = "Other";
    }
    typeData.innerText = reimbursement.reimbursement_type_id;
    row.appendChild(typeData);

    // append the row into the table
    document.getElementById('reimbursement-table-body').appendChild(row);
}

function getRulingFromInputs() {
    const author = currentUser.user_id
    const id = document.getElementById('reimbursement-dentifier').value;
    const reimVDescr = document.getElementById('vanguard-description-input').value;
    let ider = document.getElementById('approval-type-select').value;
    if (ider === "Accepted") {
        ider = 2;
    } else if (ider === "Denied") {
        ider = 3;
    } 
    const reimType = ider;
    const ruling = {
        reimbursement_resolution: reimVDescr,
        reimbursement_author: author,
        reimbursement_status_id: reimType,
        reimbursement_id: id
    }
    return ruling;
}

function refreshTable() {

    fetch(`http://localhost:8080/Expenses/reimbursements`, {
    	credentials: 'include'
    })
        .then(res => res.json())
        .then(data => {
            let numRows = document.getElementById('reimbursement-table-body').rows.length
            for (let i = numRows - 1; i > -1; i--) {
                document.getElementById('reimbursement-table-body').deleteRow(i)
            }
            data.forEach(addReimbursementToTableSafe)
        })
        .catch(console.log);
}


 function getCurrentUserInfo() {
     fetch('http://localhost:8080/Expenses/auth/session-user', {
         credentials: 'include'
     })
     .then(resp => resp.json())
     .then(data => {
         document.getElementById('users-name').innerText = "Welcome " + data.first_name;
         currentUser = data;
        refreshTable();
     })
     .catch(err => {
         window.location = '/Expenses/home/home.html';
     })
 }

 getCurrentUserInfo();