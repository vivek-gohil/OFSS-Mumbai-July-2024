<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:template match="/">
		<html>
			<body>
				<h2>All Employees From File</h2>
				<table border="1">
					<tr>
						<td>Employee id</td>
						<td>Name</td>
						<td>Salary</td>
					</tr>
					<xsl:for-each select="employees/employee">
						<xsl:if test="salary &gt; 9999">
							<tr>
								<td>
									<xsl:value-of select="employeeId" />
								</td>
								<td>
									<xsl:value-of select="name" />
								</td>
								<td>
									<xsl:value-of select="salary" />
								</td>
							</tr>
						</xsl:if>
					</xsl:for-each>
				</table>
			</body>
		</html>
	</xsl:template>
</xsl:stylesheet>