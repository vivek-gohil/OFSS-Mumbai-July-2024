fetch('http://localhost:8080/EmployeeCRUDAPI/')
    .then(response => response.text())
    .then(data => {
        // Parse XML response
        let parser = new DOMParser();
        let xmlDoc = parser.parseFromString(data, "text/xml");
        
        // Example: Output XML data to console
        console.log(xmlDoc);
    })
    .catch(error => console.error('Error fetching employees:', error));