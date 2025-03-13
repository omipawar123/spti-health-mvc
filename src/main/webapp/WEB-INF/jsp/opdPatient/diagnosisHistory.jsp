<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>OPD Patient History</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"></script>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<style>
.error-message {
color: red;
font-style: italic;
}

.table th,
.table td {
    font-size: 14px;
}

.spti-label {
    font-size: 12px;
}

.report-card {
    height: 250px;
}

.col-md-3 {
    width: 50%;
}
#tableID thead { 
    background-color:#ddd !important;
    
}
</style>
</head>
<body>
<jsp:include page="../common/header.jsp"></jsp:include>
<div class="height-90">
    <div class="main-content">
    <h5 class="modal-title" id="exampleModalLabel">Diagnosis History</h5> 
    <hr>
        <form id="opdForm" action="#" method="get">
            <div class="row">
                <div class="col-md-4 mb-3">
                    <label for="patientType" hidden>Day Type</label>
                    <select class="form-select" id="patientType" name="todayrecord" aria-label="Select Patient Type">
                        <option value="" disabled selected>Please Select An Option of Day-Type</option>
                        <option value="todaypatient">Today Patient</option>
                        <option value="weekpatient">Weekly Patient</option>
                        <option value="monthlypatient">Monthly Patient</option>
                        <option value="AllPatient">All Patient</option>
                    </select>
                </div>

                <div class="col-md-4 mb-3">
                    <label for="diagnosisHistory" hidden>Diagnosis Type</label>
                    <select name="disease" id="diagnosisHistory" class="form-select">
                    <option value="" disabled selected>Please Select An Option of Diagnosis-Type </option>
                        <c:forEach items="${Diagnosis}" var="disease">
                            <option value="${disease.diagnosis}" <c:if test="${disease.id eq param.disease}">selected="selected"</c:if>>${disease.diagnosis}</option>
                        </c:forEach>
                    </select>
                </div>

					<div class="col-md-4 mb-3">
						<label for="ageType" hidden>Select Age</label> <select name="ageType"
							id="ageType" class="form-select">
							<option value="" disabled selected>Please Select An Option of Age-Type</option>
							<option value="10to18">10 to 18</option>
							<option value="above18">Above 18</option>
						</select>

					</div>

					<div class="col-md-4 d-flex align-items-end mb-3">
                    <button type="submit" class="btn btn-warning btn-sm add-btn float-end Spti-btn" id="submitDiagnosisButton" >Submit</button>
                </div>
            </div>
        </form>
        <div id="OPD_Patient"></div>
        <div id="tableContainer"></div>
    </div>
</div>

<script>
$(document).ready(function () {
	let submitButton = document.getElementById("submitDiagnosisButton");
    var patientType = $('#patientType');
    var diagnosisHistory = $('#diagnosisHistory');
    var ageType = $('#ageType');

    $('#opdForm').on('submit', function (e) {
        e.preventDefault(); 

        var todayrecord = patientType.val();
        var disease = diagnosisHistory.val();
        var ages = ageType.val();

        if (todayrecord && disease && ages) {
            getDiagnosisHistoryData(todayrecord, disease, ages);
        } else {
            alert("Please select All Patient-type, Disease-type, and Age-range.");
        }
    });

    function buttonEnableOrDisable() {
        if (patientType.val() && diagnosisHistory.val() && ageType.val()) {
            submitButton.disabled = false;
        } else {
            submitButton.disabled = true; 
        }
    }

    buttonEnableOrDisable();

    patientType.add(diagnosisHistory).add(ageType).on('change', function () {
        buttonEnableOrDisable();
    });

     function getDiagnosisHistoryData(todayrecord, disease, ages) {
            $.ajax({
                type: 'GET',
                url: "/opds/todayOpdPatientHistory/" + disease + "/" + todayrecord + "/" + ages,
                success: function (data) {
                    console.log(data); // Debugging: log the response data

                    var tableHtml = "";
                    if (data.length === 0) {
                        tableHtml = '<table class="table table-new m-0 " id="tableID" style="max-width: 100% !important; "><thead><tr><td colspan="5">No data are available on this Date..!!</td></tr></tr></thead><tbody>';
                        $('#OPD_Patient').text(""); // Clear the header if no data
                    } else {
                    	var avatarHTML;
                    	tableHtml = '<table class="table table-new m-0 " id="tableID" style="max-width: 100% !important; "><thead><tr><th>Name</th><th>Phone Number</th><th>Treatment Date</th><th>Seen By Doctor</th><th>Diagnosis</th><th>Age</th></tr></thead><tbody>';

                    	data.forEach(function (item) {
                    	    var avatarHTML = '<div class="avatar"><span class="avatar-title">' + (item.patient.firstName ? item.patient.firstName.charAt(0).toUpperCase() : '') + '</span></div>';
                    	    tableHtml += '<tr>';
                    	    tableHtml += '<td><div class="d-flex justify-content-start align-items-center">' +  avatarHTML + ' ' + (item.patient.firstName ? item.patient.firstName : '') + ' ' + (item.patient.lastName ? item.patient.lastName : '') + '</div></td>';
                    	    tableHtml += '<td>' + (item.patient.phoneNumber ? item.patient.phoneNumber : '') + '</td>';
                    	    tableHtml += '<td>' + (item.treatmentDate ? item.treatmentDate : '') + '</td>';
                    	    tableHtml += '<td>' + (item.seenByDoctor ? item.seenByDoctor : '') + '</td>';
                    	    tableHtml += '<td>' + (item.diagnosis.diagnosis ? item.diagnosis.diagnosis : '') + '</td>';
                    	    tableHtml += '<td>' + (item.patient.age ? item.patient.age : '') + '</td>';
                    	    tableHtml += '</tr>';
                    	});

                    	tableHtml += '</tbody></table>';
                        $('#OPD_Patient').text(" "); // Set the header if data is present
                    }
                    $('#tableContainer').html(tableHtml);
                },
                error: function (jqXHR, textStatus, errorThrown) {
                    console.error('AJAX Error: ' + textStatus + ': ' + errorThrown); // Debugging: log any errors
                    alert('Failed to retrieve data. Please try again.');
                }
            });
        }
    });
</script>
</body>
</html>
