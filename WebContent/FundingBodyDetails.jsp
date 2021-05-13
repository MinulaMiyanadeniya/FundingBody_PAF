
<%@page import="com.FundingBody"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>FundingBody Details</title>

<!--Link Bootstrap, jQuery, and FundingBody.js to the FundingBodyDetails page-->

<link rel="stylesheet" href="Views/bootstrap.css">
<script src="Components/jquery-3.2.1.min.js"></script>
<script src="Components/FundingBody.js"></script>

<style>
h1 {
	text-align: left;
	text-transform: uppercase;
	color:black;
	background:#9999ff;
	
}

body{
background:linear-gradient(to right, rgb(153, 153, 255), rgb(204, 204, 255));
font-weight: bold;

}
</style>

</head>

<!--Structure of the page-->

<body>

	<h1>GadgetBadget System - FundingBody</h1>
	

	<h2>Add FundingBody Details</h2>
	<br>

<!--Develop the form-->

	<div class="addform">
		<form name="formFundingBodyinfo" id="formFundingBodyinfo" class="form-horizontal font-weight-bold" action="FundingBodyDetails.jsp" method="post" >
			
			<div class="form-group">
				<label class="control-label col-sm-2" for="pwd">FundingBody ID :</label>
				<div class="col-sm-10">
					<input type="text" id="FundingBodyID" name="FundingBodyID" class="form-control" >
				</div>
			</div>

			<div class="form-group">
				<label class="control-label col-sm-2" for="pwd">Full Name :</label>
				<div class="col-sm-10">
					<input type="text" id="Name" name="Name" class="form-control" >
				</div>
			</div>

			<div class="form-group">
				<label class="control-label col-sm-2" for="pwd">Telephone No :</label>
				<div class="col-sm-10">
					<input type="text" id="Telno" name="Telno" class="form-control" >
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-2" for="pwd">Company Name :</label>
				<div class="col-sm-10">
					<input type="text" id="CompanyName" name="CompanyName" class="form-control" >
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-2" for="pwd">Company Address :</label>
				<div class="col-sm-10">
					<input type="text" id="CompanyAddress" name="CompanyAddress" class="form-control" >
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-2" for="pwd">Email :</label>
				<div class="col-sm-10">
					<input type="text" id="Email" name="Email" class="form-control" >
				</div>
			</div>
			
<br>			
			<div class="form-group">
				<input type="button" id="btnSave" name="btnSave" class="btn btn-primary btn-lg" value ="save">
				<input type="hidden" id="hiddenFundingBodyIDsave" name="hiddenFundingBodyIDsave" value="">
			</div>
	
		</form>		
<div id="alertSuccess" class="alert alert-success"></div>
<div id="alertError" class="alert alert-danger"></div>
<br>
 <div id="divFundingBodyGrid">
 	<%{
 		FundingBody FundingBodyObj = new FundingBody();
 		out.print(FundingBodyObj.readFundingBody());
 	  }
 	%>
</div>	
</div>
	

</body>
</html>