package com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;


public class FundingBody {
	
	public Connection connect() {
		Connection con = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/gadgetbadgetdb", "root", "root");
			// For testing
			System.out.print("Successfully connected");
		} catch (Exception e) {
			e.printStackTrace();
		}

		return con;
	}
	
	
	public String insertFundingBody(String FundingBodyID,String Name, String Telno, String CompanyName, String CompanyAddress, String Email) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for inserting.";
			}
			// create a prepared statement
			String query = " insert into fundingbody  ('Id', 'Name', 'Telno', 'Companyname', 'Companyaddress','Email')"
					+ " values (?, ?, ?, ?, ?,?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1,Integer.parseInt(FundingBodyID));
			preparedStmt.setString(2, Name);
			preparedStmt.setInt(3,Integer.parseInt(Telno));
			preparedStmt.setString(4, CompanyName);
			preparedStmt.setString(5, CompanyAddress);
			preparedStmt.setString(6, Email);
			//execute the statement
			preparedStmt.execute();
			con.close();
			String newFundingBody = readFundingBody();
			output = "{'status:'success', 'data': '" + newFundingBody + "'}";
		} catch (Exception e) {
			output =  "{'status':'error', 'data': 'Error while inserting the FundingBody.'}";
			System.err.println(e.getMessage());
		}
		return output;
	}
	
	
	public String readFundingBody() {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for reading.";
			}
// Prepare the html table to be displayed
			output = "<table border=\"1\" class=\"table table-dark\"><tr><th>FundingBody ID</th><th>FundingBody Name</th><th>Telno</th><th>Company Name</th><th>Company Address</th><th>Email</th><th>Update</th><th>Remove</th></tr>";
			String query = "select * from fundingbody";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
// iterate through the rows in the result set
			while (rs.next()) {
				String FundingBodyID = Integer.toString(rs.getInt("Id"));
				String Name = rs.getString("Name");
				String Telno = Integer.toString(rs.getInt("Telno"));
				String CompanyName = rs.getString("Companyname");
				String CompanyAddress = rs.getString("Companyaddress");
				String Email = rs.getString("Email");
// Add into the html table
				output += "<tr><td><input id='hidFundingBodyIDupdate' name='hidFundingBodyIDupdate' type='hidden' value='"+ FundingBodyID 
						+"'>" +FundingBodyID  + "</td>";
				output += "<td>" + Name + "</td>";
				output += "<td>" + Telno + "</td>";
				output += "<td>" + CompanyName + "</td>";
				output += "<td>" + CompanyAddress + "</td>";
				output += "<td>" + Email + "</td>";

// buttons
				output += "<td><input name='btnUpdate' type='button' value='Update' class='btn btn-success'></td>"
						+ "<td><input name='btnRemove' type='button' value='Remove' class='btnRemove btn btn-danger' data-FundingBodyID='"
						+  FundingBodyID + "'>" + "</td></tr>"; 
				
				
			}
			con.close();
// Complete the html table
			output += "</table>";
		} catch (Exception e) {
			output = "Error while reading the FundingBody details.";
			System.err.println(e.getMessage());
		}
		return output;
	}
	
	
	public String updateFundingBody(String FundingBodyID, String Name, String Telno, String CompanyName,
			String CompanyAddress, String Email) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for updating.";
			}
// create a prepared statement
			String query = "UPDATE fundingbody SET Name=?, Telno=?, Companyname=?, Companyaddress=?, email=?  WHERE id=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
// binding values
			preparedStmt.setString(1, Name);
			preparedStmt.setInt(2, Integer.parseInt(Telno));
			preparedStmt.setString(3, CompanyName);
			preparedStmt.setString(4, CompanyAddress);
			preparedStmt.setString(5, Email);
			preparedStmt.setInt(6, Integer.parseInt(FundingBodyID));

// execute the statement
			preparedStmt.execute();
			con.close();
			String newFundingBody = readFundingBody();
			output = "{'status':'success', 'data': '" +newFundingBody + "'}"; 
		} catch (Exception e) {
			output = "{'status':'error', 'data': 'Error while updating the FundingBody details.'}"; 
			System.err.println(e.getMessage());
		}
		return output;
	}
	
	
	public String deleteFundingBody(String FundingBodyID) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for deleting.";
			}
// create a prepared statement
			String query = "delete from fundingbody where id=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
// binding values
			preparedStmt.setInt(1, Integer.parseInt(FundingBodyID));
// execute the statement
			preparedStmt.execute();
			con.close();
			String newFundingBody = readFundingBody();
			 output = "{'status':'success', 'data: '" +newFundingBody + "'}"; 
		} catch (Exception e) {
			output =  "{'status':'error', 'data': 'Error while deleting the fundingbody.'}";
			System.err.println(e.getMessage());
		}
		return output;
	}
}
	


