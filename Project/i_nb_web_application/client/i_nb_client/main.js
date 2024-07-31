alert("Javscript is running");

function doLogin(event) {
    event.preventDefault(); // Prevent form submission

    // Create XML Document
    const xmlDoc = document.implementation.createDocument('', '', null);

    // Create root element
    const customerElement = xmlDoc.createElement('customer');
    xmlDoc.appendChild(customerElement);

    // Append form data as XML elements
    const customerId = document.getElementById('customerId').value;


    const customerIdElement = xmlDoc.createElement('customerId');
    customerIdElement.textContent = escapeXml(customerId);
    customerElement.appendChild(customerIdElement);

    const loginElement = xmlDoc.createElement('login');
    customerElement.appendChild(loginElement);

    const password = document.getElementById('password').value;

    const passwordElement = xmlDoc.createElement('password');
    passwordElement.textContent = escapeXml(password);
    loginElement.appendChild(passwordElement);

    // Serialize XML Document to string
    const serializer = new XMLSerializer();
    const xmlString = serializer.serializeToString(xmlDoc);

    // Print XML to the console
    console.log(xmlString);
    doCustomerLogin(xmlString);
}

function escapeXml(unsafe) {
    return unsafe
        .replace(/&/g, "&amp;")
        .replace(/</g, "&lt;")
        .replace(/>/g, "&gt;")
        .replace(/"/g, "&quot;")
        .replace(/'/g, "&#039;");
}
// return flags 
// loginStatus = NEW => -1
// loginStatus = LOCKED => -2
// loginAttempts > 3 = -3
// INVALID PASSWORD = CountOfLoginAttempts
// INVALID CUSTOMERID = -4
// ALL VALID DETAILS = 1
function doCustomerLogin(xmlString) {
    console.log("customerLogin");

    // POST request to create a new employee
    fetch('http://localhost:8080/i_nb_web_api/LoginController', {
        method: 'POST',
        headers: {
            'Content-Type': 'text/xml',
        },
        body: xmlString
    })
    .then(response => {
        return response.text();
    })
    .then(data => {
        const message = document.getElementById("message");
        console.log(data);
        if(data == 0){
            console.log("Login Successfull");
            message.innerHTML = "Login Successfull";
        }
        if(data == -1){
            console.log("Approval Pending From Adminstrator");
            message.innerHTML = "Approval Pending From Adminstrator";
        }
        if(data == -2) {
            console.log("Your customerId is locked");
            message.innerHTML = "Your customerId is locked";
        }
        if(data == -3) {
            console.log("Customerid is locked becouse loging attempts > 3");
            message.innerHTML = "Customerid is locked becouse loging attempts > 3";
        }
        if(data > 0 && data <= 3 ){
            console.log("Invalid Password remaining attempt = " + data);
            message.innerHTML ="Invalid Password attempt = " + data;
        }
        if(data == -4) {
            console.log("Invalid CustomerId");
            message.innerHTML ="Invalid CustomerId";
        }
    })
    .catch(error => console.error('Error creating employee:', error));

}
