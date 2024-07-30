
function saveEmployeeDetails(event) {
    event.preventDefault(); // Prevent form submission

    // Create XML Document
    const xmlDoc = document.implementation.createDocument('', '', null);

    // Create root element
    const personElement = xmlDoc.createElement('employee');
    xmlDoc.appendChild(personElement);

    // Append form data as XML elements
    const firstName = document.getElementById('firstName').value;
    const lastName = document.getElementById('lastName').value;
    const salary = document.getElementById('salary').value;

    const firstNameElement = xmlDoc.createElement('firstName');
    firstNameElement.textContent = escapeXml(firstName);
    personElement.appendChild(firstNameElement);

    const lastNameElement = xmlDoc.createElement('lastName');
    lastNameElement.textContent = escapeXml(lastName);
    personElement.appendChild(lastNameElement);

    const salaryElement = xmlDoc.createElement('salary');
    salaryElement.textContent = escapeXml(salary);
    personElement.appendChild(salaryElement);

    // Serialize XML Document to string
    const serializer = new XMLSerializer();
    const xmlString = serializer.serializeToString(xmlDoc);

    // Print XML to the console
    console.log(xmlString);

    saveEmployeeToDatabase(xmlString);
}

function saveEmployeeToDatabase(xmlString) {
    // POST request to create a new employee
    fetch('http://localhost:8080/EmployeeCRUD/EmployeeCRUDController', {
        method: 'POST',
        headers: {
            'Content-Type': 'text/xml',
        },
        body: xmlString
    })
        .then(response => {
            console.log(response);
            window.location.replace('allemployees.html');
        })
        .catch(error => console.error('Error creating employee:', error));
}
function escapeXml(unsafe) {
    return unsafe
        .replace(/&/g, "&amp;")
        .replace(/</g, "&lt;")
        .replace(/>/g, "&gt;")
        .replace(/"/g, "&quot;")
        .replace(/'/g, "&#039;");
}


function showAddNewEmployeePage() {
    window.location.href = "addnewemployee.html";

}
function loadEmployeeDetails() {
    fetch("http://localhost:8080/EmployeeCRUD/EmployeeCRUDController")
        .then(response => response.text())
        .then(data => {
            let parsar = new DOMParser();
            let xmlDocument = parsar.parseFromString(data, "text/xml");
            console.log(xmlDocument);
            printDataInTable(xmlDocument);
        })
        .catch(error => console.error("Invalid url", error));
}

function printDataInTable(xmlDocument) {
    const employee = xmlDocument.getElementsByTagName("employee");
    let table = "<table border=1> <tr> <th>EmployeeId</th> <th>First Name</th> <th>Last Name</th> <th>Salary</th> <th colspan=2>Action</th> </tr>";

    for (let i = 0; i < employee.length; i++) {
        let employeeId = employee[i].getElementsByTagName("employeeId")[0].textContent;
        let firstName = employee[i].getElementsByTagName("firstName")[0].textContent;
        let lastName = employee[i].getElementsByTagName("lastName")[0].textContent;
        let salary = employee[i].getElementsByTagName("salary")[0].textContent;

        table += `<tr> 
            <td>${employeeId}</td> 
            <td>${firstName}</td> 
            <td>${lastName}</td> 
            <td>${salary}</td> 
        <td> <button onclick=updateEmployee(${employeeId})> Update </button> </td> 
        <td> <button onclick="deleteEmployee(${employeeId})"> Delete </button> </td> </tr>`;
    }

    table += "</table>";

    console.log(table);

    document.getElementById("table-container").innerHTML = table;
}

function updateEmployee(employeeId) {
    console.log(employeeId);
    window.location.href = `updateemployee.html?employeeId=${employeeId}`;
}

function deleteEmployee(employeeId) {
    console.log(employeeId);
    deleteEmployeeFromDatabase(employeeId);
}

function deleteEmployeeFromDatabase(employeeId) {
    // DELETE request to create a delete employee
    fetch('http://localhost:8080/EmployeeCRUD/EmployeeCRUDController?employeeId=' + employeeId, {
        method: 'DELETE'
    })
        .then(response => {
            console.log(response);
            window.location.replace('allemployees.html');
        })
        .catch(error => console.error('Error creating employee:', error));
}

function readEmployeeId() {
    // Get the current URL
    const url = new URL(window.location.href);

    // Get the search params object
    const params = new URLSearchParams(url.search);

    let employeeId = params.get('employeeId');
    console.log("employeeId loaded =" + employeeId);
    getEmployeeByEmployeeId(employeeId);
}

function getEmployeeByEmployeeId(employeeId) {
    fetch("http://localhost:8080/EmployeeCRUD/EmployeeCRUDController?employeeId=" + employeeId)
        .then(response => response.text())
        .then(data => {
            let parsar = new DOMParser();
            let xmlDocument = parsar.parseFromString(data, "text/xml");
            console.log(xmlDocument);
            displayDataOnPage(xmlDocument);
        })
        .catch(error => console.error("Invalid url", error));
}

function displayDataOnPage(xmlDocument) {
    const employee = xmlDocument.getElementsByTagName('employee')[0];
    let employeeId = employee.getElementsByTagName('employeeId')[0].textContent;
    let firstName = employee.getElementsByTagName("firstName")[0].textContent;
    let lastName = employee.getElementsByTagName("lastName")[0].textContent;
    let salary = employee.getElementsByTagName("salary")[0].textContent;

    document.getElementById("firstName").value = firstName;
    document.getElementById("lastName").value = lastName;
    document.getElementById("salary").value = salary;

}

function updateEmployeeDetails(event) {
    event.preventDefault(); // Prevent form submission

    // Get the current URL
    const url = new URL(window.location.href);

    // Get the search params object
    const params = new URLSearchParams(url.search);

    let employeeId = params.get('employeeId');

    // Create XML Document
    const xmlDoc = document.implementation.createDocument('', '', null);

    // Create root element
    const personElement = xmlDoc.createElement('employee');
    xmlDoc.appendChild(personElement);

    // Append form data as XML elements
    const firstName = document.getElementById('firstName').value;
    const lastName = document.getElementById('lastName').value;
    const salary = document.getElementById('salary').value;

    const employeeIdElement = xmlDoc.createElement('employeeId');
    employeeIdElement.textContent = escapeXml(employeeId);
    personElement.appendChild(employeeIdElement);


    const firstNameElement = xmlDoc.createElement('firstName');
    firstNameElement.textContent = escapeXml(firstName);
    personElement.appendChild(firstNameElement);

    const lastNameElement = xmlDoc.createElement('lastName');
    lastNameElement.textContent = escapeXml(lastName);
    personElement.appendChild(lastNameElement);

    const salaryElement = xmlDoc.createElement('salary');
    salaryElement.textContent = escapeXml(salary);
    personElement.appendChild(salaryElement);

    // Serialize XML Document to string
    const serializer = new XMLSerializer();
    const xmlString = serializer.serializeToString(xmlDoc);

    // Print XML to the console
    console.log(xmlString);
    updateEmployeeToDatabase(xmlString);
}

function updateEmployeeToDatabase(xmlString) {
    // PUT request to UPDATE employee
    console.log("in UpdateEmployeeToDatabase");
    console.log(xmlString);
    fetch('http://localhost:8080/EmployeeCRUD/EmployeeCRUDController', {
        method: 'PUT',
        headers: {
            'Content-Type': 'text/xml',
        },
        body: xmlString
    })
        .then(response => {
            alert("Employee Updated Successfully")
            console.log(response);
            window.location.replace('allemployees.html');
        })
        .catch(error => {
            alert("Exception !!")
            console.error('Error creating employee:', error)
        });

}