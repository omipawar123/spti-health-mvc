<html>

<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css"
	rel="stylesheet">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"></script>

<style>
  #tableContainer {
      max-height: 270px; /* Set the maximum height for the container */
      overflow-y: auto; /* Enable vertical scrolling */
   
    }
    
    label {
     
    margin-top: 10px;
    padding: 10px;
    font-size: 15px;
    margin-bottom: 0px;
   }
  .mt-4{
  margin-top: 0px;
  }
 
    table {
      border-collapse: collapse;
      width: 100%;
      border-radius: 8px;
      margin-bottom: 0px;
    }

    th, td {
      border: 1px solid #dddddd;
      text-align: left;
      padding: 5px;
    }

    thead {
      background-color: #f2f2f2;
       font-weight: bold;
    box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
      
    }
     
   
    table tr:nth-child(even){
     background: linear-gradient(90deg, #a9ffc6 0%, #8ecfff 100%); 
} 
     
 .row-cols-1 .card  {
  padding-left: 20px;
  padding-right: 20px;
  box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2); 
  transition: 0.3s;
  background: linear-gradient(90deg, #a9ffc6 0%, #8ecfff 100%); 
  }
  
 .row-cols-1 .card:hover {
  box-shadow: 0 8px 16px 0 rgba(0, 0, 0,0.3);
  
}

#exampleModalLabel
{
text-align:center;
font-weight: bold;
font-size:25px;


}
}


</style>	
	
</head>

<body>
	<jsp:include page="../common/header.jsp"></jsp:include>
	<div class="height-200">
				<!-- ============== -->
		<div class="main-content  ">
			<div class="row">
				<div class="col-9"></div>
				<div class="col-3">

					<select class="form-select" aria-label="Default select example"
						onchange="getOPDpatientBill(this.value)">
						<option selected>Today OpdPatient And Bill</option>
						<option value="weeklyrecord">Weekly OpdPatient And Bill</option>
						<option value="monthlylyrecord">Monthly OpdPatient And Bill</option>
					</select>

				</div>				
			</div>
<div class="row row-cols-1 row-cols-md-3 g-2 mt-3">
    <div class="col">
        <div class="card h-100">
            <div class="card-body d-flex justify-content-center align-items-center">
                <h5 class="card-title" >Total OPD Bill: <span id="totalOPDBill"></span></h5>
               
             
            </div>
        </div>
    </div>
    <div class="col">
        <div class="card h-100">
            <div class=" card-body d-flex flex-column">
                <h5 class="card-title text-center" >Paid Bill: <span id="paidBill"></span></h5>

                <div class="row">
                    <div class="card-body col-6 ">
                        <h6 class="card-title" >Online Paid : <span class="card-text" id="totalOPDBillonline"></span></h6>
                      
                    </div>
                    <div class="card-body col-6 text-right">
                        <h6 class="card-title">Cash Paid : <span class="card-text" id="totalOPDBillcash"></span></h6>
                        <span class="card-text" id="totalOPDBillcash"></span>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="col">
        <div class="card h-100">
            <div class="card-body d-flex justify-content-center align-items-center">
                <h5 class="card-title" >Pending Bill: <span class="card-text" id="PendingBill"></span></h5>                
            </div>
        </div>
    </div>
</div>
<div class="row">
	    <div class="col-4"><label  id="OPD Patient" class ="label-small"></label><div id="tableContainer1" style="overflow: auto" class="mt-4"></div>	</div>
	    <div class="col-4"><label  id="Admit Patient" class ="label-small"></label> <div id="tableContainer2" style="overflow: auto" class="mt-4"></div>  </div>
	    <div class="col-4"><label id="discharge" class ="label-small"></label><div id="tableContainer3" style="overflow: auto" class="mt-4"></div>  </div>
</div>		
</div>
 </div>
</div>
		
	</div>
	
	
		<script type="text/javascript">
	$(document).ready( function() {
		var todayrecord = "Today OpdPatient And Bill";
		$.ajax({
			type : 'GET',
			url :"/opds/opdPatienBillDashbord/"+todayrecord ,
			success : function(data) {
				
				console.log(data);
				
				var totalPendingbill = data.totalPendingBill;
				 $('#PendingBill').text( totalPendingbill);
				 
				 var totalPaidBill = data.totalPaidBill;
				 $('#paidBill').text( totalPaidBill);
				 
				 var totalPendingbill = data.totalBillOpd;
				 $('#totalOPDBill').text( totalPendingbill);

			},
			error : function() {
				// Handle errors here
			}
		});
		/* =====Online cash onload OneDayGe====== */
		
		var todayrecord = "Today OpdPatient And Bill";
		$.ajax({
			type : 'GET',
			url :"/opds/opdPatienBillOnlineDashbord/"+todayrecord ,
			success : function(data) {
				
				console.log(data);
				 
				 var totalPaidBill = data.totalPaidBill;
				 $('#totalOPDBillonline').text( totalPaidBill);
				 

			},
			error : function() {
				// Handle errors here
			}
		});

		/* =========== */
		
		/* =====Only cash onload OneDayGet====== */
		
		var todayrecord = "Today OpdPatient And Bill";
		$.ajax({
			type : 'GET',
			url :"/opds/opdPatienBillCasheDashbord/"+todayrecord ,
			success : function(data) {
				
				console.log(data);
				 
				 var totalPaidBill = data.totalPaidBill;
				 $('#totalOPDBillcash').text( totalPaidBill);
				 

			},
			error : function() {
				// Handle errors here
			}
		});

		/* =========== */
		
		// today  Data OPDPatient Data
		
		var todayrecord = "Today OpdPatient And Bill";
		$.ajax({
			type : 'GET',
			url :"/opds/getCurrentdatePatient/"+todayrecord ,
			success : function(data) {
				
				console.log(data);
				
				 if (data.length === 0) {
					  tableHtml += '<tr><td colspan="2"></td></tr>';
					  document.getElementById("OPD Patient").innerText = "";
				  }else {
					  var tableHtml = '<table border="1" id="patientTable" style="width: 100%; font-size: 18px;"><thead><tr><th>Name</th><th>Phone Number</th></tr></thead><tbody>';

					  document.getElementById("OPD Patient").innerText = "OPD Patient";
					  // Add data rows
				        for (var i = 0; i < data.length; i++) {
				            tableHtml += '<tr>';
				            tableHtml += '<td>' + data[i].patient.firstName + ' ' + data[i].patient.lastName + '</td>';
				            tableHtml += '<td>' + data[i].patient.phoneNumber + '</td>';
				            // Add other fields as needed
				            tableHtml += '</tr>';
				        }
				  }
				// Close table body and table tag
				tableHtml += '</tbody></table>';

				// Display the table
				$('#tableContainer1').html(tableHtml);
				//$('#tableContainer3').html(tableHtml);

				// Apply the fixed position for the header
				$('#patientTable thead th').css({
				    position: 'sticky',
				    top: -1,
				    background: '#f2f2f2', // Add background color if needed
				    zIndex: 100
				});

				// Add the following CSS to ensure proper behavior
				$('#patientTable').css({
				    overflowY: 'auto',
				    maxHeight: '400px' 
				    // Set a max height if needed
				});


			},
			error : function() {
				// Handle errors here
			}
		});
		
		// today  Data Admit_Patient Data
		
		$.ajax({
			type : 'GET',
		url :"/admits/getCurrentdateAdmitPatient/"+todayrecord ,
			success : function(data) {
				
				console.log(data);
				
				 if (data.length === 0) {
					  tableHtml += '<tr><td colspan="2"></td></tr>';
					  document.getElementById("Admit Patient").innerText = "";
				  }else {
					  var tableHtml = '<table border="1" id="patientTable" style="width: 100%; font-size: 18px;"><thead><tr><th>Name</th><th>Phone Number</th></tr></thead><tbody>';

					  document.getElementById("Admit Patient").innerText = "Admit Patient";
					  // Add data rows
				        for (var i = 0; i < data.length; i++) {
				            tableHtml += '<tr>';
				            tableHtml += '<td>' + data[i].patient.firstName + ' ' + data[i].patient.lastName + '</td>';
				            tableHtml += '<td>' + data[i].patient.phoneNumber + '</td>';
				            // Add other fields as needed
				            tableHtml += '</tr>';
				        }
				  }

				// Close table body and table tag
				tableHtml += '</tbody></table>';

				// Display the table
				$('#tableContainer2').html(tableHtml);

				// Apply the fixed position for the header
				$('#patientTable thead th').css({
				    position: 'sticky',
				    top: -1,
				    background: '#f2f2f2', // Add background color if needed
				    zIndex: 100
				});

				// Add the following CSS to ensure proper behavior
				$('#patientTable').css({
				    overflowY: 'auto',
				    maxHeight: '400px' 
				    // Set a max height if needed
				});


			},
			error : function() {
				// Handle errors here
			}
		});
		
		
		// today  Discharge_Patient Data
		
		$.ajax({
			
		
			type : 'GET',
			url :"/admits/getCurrentdateDischargePatient/"+todayrecord ,
			success : function(data) {
				
				console.log(data);
				
				if (data.length === 0) {
					  tableHtml += '<tr><td colspan="2"></td></tr>';
					  document.getElementById("discharge").innerText = "";
				  }else {
					  var tableHtml = '<table border="1" id="patientTable" style="width: 100%; font-size: 18px;"><thead><tr><th>Name</th><th>Phone Number</th></tr></thead><tbody>';

					
					  document.getElementById("discharge").innerText = "Discharge";
					  // Add data rows
				        for (var i = 0; i < data.length; i++) {
				    tableHtml += '<tr>';
				    tableHtml += '<td>' + data[i].patient.firstName + ' ' + data[i].patient.lastName + '</td>';
				    tableHtml += '<td>' + data[i].patient.phoneNumber + '</td>';
				    // Add other fields as needed
				    tableHtml += '</tr>';
					}
				  }
				
				

				// Add data rows
				

				// Close table body and table tag
				tableHtml += '</tbody></table>';

				// Display the table
				$('#tableContainer3').html(tableHtml);

				// Apply the fixed position for the header
				$('#patientTable thead th').css({
				    position: 'sticky',
				    top: -1,
				    background: '#f2f2f2', // Add background color if needed
				    zIndex: 100
				});

				// Add the following CSS to ensure proper behavior
				$('#patientTable').css({
				    overflowY: 'auto',
				    maxHeight: '400px' 
				    // Set a max height if needed
				});


			},
			error : function() {
				// Handle errors here
			}
		});
		
		
		
	}); 
	
	
	// END Ready ====
	

	
	function getOPDpatientBill(todayrecord) {
		$.ajax({
			type : 'GET',
			url :"/opds/opdPatienBillDashbord/"+todayrecord ,
			success : function(data) {
				
				console.log(data);
				
				var totalPendingbill = data.totalPendingBill;
				 $('#PendingBill').text( totalPendingbill);
				 
				 var totalPaidBill = data.totalPaidBill;
				 $('#paidBill').text( totalPaidBill);
				 
				 var totalPendingbill = data.totalBillOpd;
				 $('#totalOPDBill').text( totalPendingbill);

			},
			error : function() {
				// Handle errors here
			}
		});
		
		// Weekly or Monthly  OPDPatient Data
		
		$.ajax({
			type : 'GET',
			url :"/opds/getCurrentdatePatient/"+todayrecord ,
			success : function(data) {
				
				console.log(data);
				
				if (data.length === 0) {
					  tableHtml += '<tr><td colspan="2"></td></tr>';
					  document.getElementById("OPD Patient").innerText = "";
				  }else {
					  var tableHtml = '<table border="1" id="patientTable" style="width: 100%; font-size: 18px;"><thead><tr><th>Name</th><th>Phone Number</th></tr></thead><tbody>';

					  document.getElementById("OPD Patient").innerText = "OPD Patient";
					  // Add data rows
				        for (var i = 0; i < data.length; i++) {
				            tableHtml += '<tr>';
				            tableHtml += '<td>' + data[i].patient.firstName + ' ' + data[i].patient.lastName + '</td>';
				            tableHtml += '<td>' + data[i].patient.phoneNumber + '</td>';
				            // Add other fields as needed
				            tableHtml += '</tr>';
				        }
				  }
					  
				// Close table body and table tag
				tableHtml += '</tbody></table>';

				// Display the table
				$('#tableContainer1').html(tableHtml);

				// Apply the fixed position for the header
				$('#patientTable thead th').css({
				    position: 'sticky',
				    top: -1,
				    background: '#f2f2f2', // Add background color if needed
				    zIndex: 100
				});

				// Add the following CSS to ensure proper behavior
				$('#patientTable').css({
				    overflowY: 'auto',
				    maxHeight: '400px' // Set a max height if needed
				});

			},
			error : function() {
				// Handle errors here
			}

		});
		
		// Weekly or Monthly  AdmitPatient Data
		
		$.ajax({
			type : 'GET',
			url :"/admits/getCurrentdateAdmitPatient/"+todayrecord ,
			success : function(data) {
				
				console.log(data);
				
				if (data.length === 0) {
					  tableHtml += '<tr><td colspan="2"></td></tr>';
					  document.getElementById("Admit Patient").innerText = "";
				  }else {
					  var tableHtml = '<table border="1" id="patientTable" style="width: 100%; font-size: 18px;"><thead><tr><th>Name</th><th>Phone Number</th></tr></thead><tbody>';

					  document.getElementById("Admit Patient").innerText = "Admit Patient";
					  // Add data rows
				        for (var i = 0; i < data.length; i++) {
				            tableHtml += '<tr>';
				            tableHtml += '<td>' + data[i].patient.firstName + ' ' + data[i].patient.lastName + '</td>';
				            tableHtml += '<td>' + data[i].patient.phoneNumber + '</td>';
				            // Add other fields as needed
				            tableHtml += '</tr>';
				        }
				  }
					  

				// Close table body and table tag
				tableHtml += '</tbody></table>';

				// Display the table
				$('#tableContainer2').html(tableHtml);

				// Apply the fixed position for the header
				$('#patientTable thead th').css({
				    position: 'sticky',
				    top: -1,
				    background: '#f2f2f2', // Add background color if needed
				    zIndex: 100
				});

				// Add the following CSS to ensure proper behavior
				$('#patientTable').css({
				    overflowY: 'auto',
				    maxHeight: '400px' // Set a max height if needed
				});

			},
			error : function() {
				// Handle errors here
			}
	
		});

		
		// Weekly or Monthly  DischargePatient Data
		
		$.ajax({
			type : 'GET',
			url :"/admits/getCurrentdateDischargePatient/"+todayrecord ,
			success : function(data) {
				
				console.log(data);
				
				  if (data.length === 0) {
					  tableHtml += '<tr><td colspan="2"></td></tr>';
					  document.getElementById("discharge").innerText = "";
				  }else {
					  var tableHtml = '<table border="1" id="patientTable" style="width: 100%; font-size: 18px;"><thead><tr><th>Name</th><th>Phone Number</th></tr></thead><tbody>';

					  document.getElementById("discharge").innerText = "Discharge";
					  // Add data rows
				        for (var i = 0; i < data.length; i++) {
				            tableHtml += '<tr>';
				            tableHtml += '<td>' + data[i].patient.firstName + ' ' + data[i].patient.lastName + '</td>';
				            tableHtml += '<td>' + data[i].patient.phoneNumber + '</td>';
				            // Add other fields as needed
				            tableHtml += '</tr>';
				        }
				  }
					  
				  

				// Close table body and table tag
				tableHtml += '</tbody></table>';

				// Display the table
				$('#tableContainer3').html(tableHtml);

				// Apply the fixed position for the header
				$('#patientTable thead th').css({
				    position: 'sticky',
				    top: -1,
				    background: '#f2f2f2', // Add background color if needed
				    zIndex: 100
				});

				// Add the following CSS to ensure proper behavior
				$('#patientTable').css({
				    overflowY: 'auto',
				    maxHeight: '400px' // Set a max height if needed
				});

			},
			error : function() {
				// Handle errors here
			}
			

		});
		
		
		

/* =====Online cash onChange OneDayGe====== */
		$.ajax({
			type : 'GET',
			url :"/opds/opdPatienBillOnlineDashbord/"+todayrecord ,
			success : function(data) {
				
				console.log(data);
				 
				 var totalPaidBill = data.totalPaidBill;
				 $('#totalOPDBillonline').text( totalPaidBill);
				 

			},
			error : function() {
				// Handle errors here
			}
		});
		/* =========== */
		
/* =====Only cash onload OneDayGet====== */
		$.ajax({
			type : 'GET',
			url :"/opds/opdPatienBillCasheDashbord/"+todayrecord ,
			success : function(data) {
				
				console.log(data);
				 
				 var totalPaidBill = data.totalPaidBill;
				 $('#totalOPDBillcash').text( totalPaidBill);
				 

			},
			error : function() {
				// Handle errors here
			}
		});
		/* =========== */
	
    }


	</script>
	
</body>

</html>