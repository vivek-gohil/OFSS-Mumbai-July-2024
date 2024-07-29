<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="2.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:template match="/">
		<html>
			<body>
				<h2>All Employees</h2>
				<table border="1">
					<tr>
						<td>Employee Id</td>
						<td>Name</td>
						<td>Salary</td>
					</tr>
					<xsl:for-each select="employees/employee">
						<tr>
							<td>
								<xsl:value-of select="employeeId"></xsl:value-of>
							</td>
							<td>
								<xsl:value-of select="name"></xsl:value-of>
							</td>
							<td>
								<xsl:value-of select="salary"></xsl:value-of>
							</td>
						</tr>
					</xsl:for-each>
				</table>
			</body>
		</html>
	</xsl:template>
</xsl:stylesheet>