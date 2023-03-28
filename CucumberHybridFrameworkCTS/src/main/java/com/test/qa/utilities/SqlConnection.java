package com.test.qa.utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import com.test.qa.libraries.FunctionalLibrary;

public class SqlConnection extends FunctionalLibrary{
	//Connection object
//		public static Connection con = null;
//		//Statement object
//		public static Statement stmt;
		public static String my_contract_DB_URL = "jdbc:sqlserver://ctsazsimisstfd1.inso13dfbbbfa2150.database.windows.net:1433;databaseName=OneC_MyContracts";
		public static String my_contract_DB_USER = "OneC_MyContracts";
		public static String my_contract_DB_PASSWORD = "757@nonprod";
		
		public static String ARC_DB_URL = "jdbc:sqlserver://ctsazsimisstfd1.inso13dfbbbfa2150.database.windows.net:1433;databaseName=ARCDB";
		public static String ARC_DB_USER = "OneC_1429";
		public static String ARC_DB_PASSWORD = "OneC_ARC#";
		 
		public Connection setUp(String DBName) throws Exception 
		{ 		   				
			System.out.println("Inside Setup");
			try
			{
				// Make the database connection
				String dbClass = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
				Class.forName(dbClass).newInstance();
				// Get connection to DB
				if(DBName.equalsIgnoreCase("MyContracts"))
				{
					con = DriverManager.getConnection(my_contract_DB_URL, my_contract_DB_USER, my_contract_DB_PASSWORD);	
				System.out.println("E-cont DB called");
				}
				else if(DBName.equalsIgnoreCase("ARC"))
				{
					con = DriverManager.getConnection(ARC_DB_URL, ARC_DB_USER, ARC_DB_PASSWORD);
					System.out.println("ARC DB called");

				}
			}
			catch (Exception e)
			{
				e.printStackTrace();
				System.out.println(e.getMessage());
			}
			return con;
		}
		
		public void tearDown() throws Exception 
		{
			// Close DB connection
			if (con != null) 
			{
				con.close();
			}
		}

}