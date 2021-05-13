// Controller & client-server implementation

$(document).ready(function()
{
 $("#alertSuccess").hide();
 $("#alertError").hide();
});
// SAVE ============================================
$(document).on("click", "#btnSave", function(event)
{
// Clear alerts---------------------
 $("#alertSuccess").text("");
 $("#alertSuccess").hide();
 $("#alertError").text("");
 $("#alertError").hide();
// Form validation-------------------
var status = validateFundingBodyForm();
if (status != true)
 {
 $("#alertError").text(status);
 $("#alertError").show();
 return;
 }
// If valid------------------------
var type = ($("#hiddenFundingBodyIDsave").val() == "") ? "POST": "PUT";
$.ajax(
		{
		 url : "FundingBodyAPI",
		 type : type,
		 data : $("#formFundingBodyinfo").serialize(),
		 dataType : "text",
		 complete : function(response, status)
		 {
		 onFundingBodySaveComplete(response.responseText, status);
		 }
		});
});

function onFundingBodySaveComplete(response, status)
{
if (status == "success")
 {
 var resultSet = JSON.parse(response);
 if (resultSet.status.trim() == "success")
 {
 $("#alertSuccess").text("Saved Successfully.");
 $("#alertSuccess").show();
 $("#divFundingBodyGrid").html(resultSet.data);
 } else if (resultSet.status.trim() == "error")
 {
 $("#alertError").text(resultSet.data);
 $("#alertError").show();
 }
 } else if (status == "error")
 {
 $("#alertError").text("Error occurred while saving.");
 $("#alertError").show();
 } else
 {
 $("#alertError").text("Unknown error occured while saving..!");
 $("#alertError").show();
 }
 $("#hiddenFundingBodyIDSave").val("");
 $("#formFundingBodyinfo")[0].reset();
}



// UPDATE==========================================
$(document).on("click", ".btnUpdate", function(event)
{
 $("#hiddenFundingBodyIDSave").val($(this).closest("tr").find('#hiddenFundingBodyIDUpdate').val());
 $("#FundingBodyID").val($(this).closest("tr").find('td:eq(0)').text());
 $("#Name").val($(this).closest("tr").find('td:eq(1)').text());
 $("#Telno").val($(this).closest("tr").find('td:eq(2)').text());
 $("#CompanyName").val($(this).closest("tr").find('td:eq(3)').text());
 $("#CompanyAdress").val($(this).closest("tr").find('td:eq(4)').text());
 $("#Email").val($(this).closest("tr").find('td:eq(5)').text());
});

// REMOVE 
$(document).on("click", "#btnRemove", function(event)
		{$.ajax(
				{
					 url : "FundingBodyAPI",
					 type : "DELETE",
					 data : "FundingBodyID=" + $(this).data("FundingBodyID"),
					 dataType : "text",
					 complete : function(response, status)
					 {
					 onFundingBodyDeleteComplete(response.responseText, status);
					 }
					});
			});

function onFundingBodyDeleteComplete(response, status)
{
if (status == "success")
 {
 var resultSet = JSON.parse(response);
 if (resultSet.status.trim() == "success")
 {
 $("#alertSuccess").text("Successfully Removed.");
 $("#alertSuccess").show();
 $("#divPaymentGrid").html(resultSet.data);
 } else if (resultSet.status.trim() == "error")
 {
 $("#alertError").text(resultSet.data);
 $("#alertError").show();
 }
 } else if (status == "error")
 {
 $("#alertError").text("Error occured while Removing.");
 $("#alertError").show();
 } else
 {
 $("#alertError").text("Unknown error occured while saving..!");
 $("#alertError").show();
 }
}

// CLIENTMODEL=========================================================================
function validateFundingBodyForm()
{
// ID
if ($("#FundingBodyID").val().trim() == "")
 {
 return "Insert FundingBody ID!";
 }
// validate name field
if ($("#Name").val().trim() == "")
 {
 return "Insert Name!";
 } 
// validate telephone field
if ($("#Telno").val().trim() == "")
{
return "Insert Telephone Number!";
}
// validate Telno field 
var tmpTel = $("#Telno").val().trim();
if (!$.isNumeric(tmpTel))
 {
 return "Insert a valid Telephone Number";
 }


// validate Company Name field
if ($("#CompanyName").val().trim() == "")
 {
 return "Insert Company Name";
 }
//validate Company Address field
if ($("#CompanyAddress").val().trim() == "")
 {
 return "Insert Company Address";
 }
// validate email field
if ($("#Email").val().trim() == "")
{
return "Insert Email";
}
return true;
}