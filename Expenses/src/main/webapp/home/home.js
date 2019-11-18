let currentUser;
function newReimbursementSubmit(event) {
    event.preventDefault(); // stop page from refreshing
    console.log('submitted');
    
    const reimbursement = getReimbursementFromInputs();

    fetch('http://localhost:8080/Expenses/reimbursements', {
        method: 'POST',
        body: JSON.stringify(reimbursement),
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

function newPendingSort(event) {
    event.preventDefault();

    fetch(`http://localhost:8080/Expenses/reimbursements?author=${currentUser.user_role_id}&statusId=${1}`, {
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

    fetch(`http://localhost:8080/Expenses/reimbursements?author=${currentUser.user_role_id}&statusId=${2}`, {
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

    fetch(`http://localhost:8080/Expenses/reimbursements?author=${currentUser.user_role_id}&statusId=${3}`, {
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
    const amountData = document.createElement('td');
    amountData.innerText = "$" + reimbursement.reimbursement_amount;
    row.appendChild(amountData);

    const dateSubData = document.createElement('td');
    //formats to readable date info
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
    //maybe put them into a function to avoid re-typing

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
    // same as above
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
    //could have used a switch statement???

    // append the row into the table
    document.getElementById('reimbursement-table-body').appendChild(row);
}

function getReimbursementFromInputs() {
    const author = currentUser.user_id
    const amount = document.getElementById('reimbursement-amount-input').value;
    const reimDescr = document.getElementById('reimbursement-description-input').value;
    let ider = document.getElementById('reimbursement-type-select').value;
    if (ider === "Lodging") {
        ider = 1;
    } else if (ider === "Travel") {
        ider = 2;
    } else if (ider === "Food") {
        ider = 3;
    } else if (ider === "Other") {
        ider = 4;
    }
    const reimType = ider;
    const reimbursement = {
        reimbursement_amount: amount,
        reimbursement_description: reimDescr,
        reimbursement_type_id: reimType,
        reimbursement_author: author
    }
    return reimbursement;
}

function refreshTable() {

    fetch(`http://localhost:8080/Expenses/reimbursements?author=${currentUser.user_id}`, {
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