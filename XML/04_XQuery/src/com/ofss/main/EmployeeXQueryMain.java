package com.ofss.main;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import javax.xml.xquery.XQConnection;
import javax.xml.xquery.XQDataSource;
import javax.xml.xquery.XQPreparedExpression;
import javax.xml.xquery.XQResultSequence;

import com.saxonica.xqj.SaxonXQDataSource;

public class EmployeeXQueryMain {

	public static void main(String[] args) {
		try {
			InputStream inputStream = new FileInputStream(new File("employeequery.xqy"));
			XQDataSource dataSource = new SaxonXQDataSource();
			XQConnection connection = dataSource.getConnection();
			XQPreparedExpression expression = connection.prepareExpression(inputStream);
			XQResultSequence result = expression.executeQuery();
			
			while(result.next()) {
				System.out.println(result.getItemAsString(null));
			}
			
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}

}
