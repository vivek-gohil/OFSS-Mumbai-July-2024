// XML data for new employee
const xmlData = `<?xml version="1.0" encoding="UTF-8"?>
    <employee>
        <id>3</id>
        <name>Vivek Gohil</name>
    </employee>`;

// POST request to create a new employee
fetch('http://localhost:8080/EmployeeCRUDAPI/', {
    method: 'POST',
    headers: {
        'Content-Type': 'text/xml',
    },
    body: xmlData
})
.then(response => {
    if (response.ok) {
        console.log('Employee created successfully');
    } else {
        console.error('Failed to create employee:', response.status);
    }
})
.catch(error => console.error('Error creating employee:', error));
