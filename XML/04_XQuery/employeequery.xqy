for $name in doc('employees.xml')/employees/employee
where $name/salary>9999
order by $name/name
return $name 