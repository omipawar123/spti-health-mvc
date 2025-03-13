<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Generate Patient Reports</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
<link rel="icon" type="image/x-icon" href="Verity.png">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet">
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

#tableID {
    display: none;
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
        <div class="row">
            <div class="col-12">
                <h5 class="modal-title" id="exampleModalLabel">Generate Patient Reports</h5>
                <hr>
            </div>
        </div>

        <div class="row">
            <div class="col-12 col-md-3 d-flex align-items-center">
                <select id="reportType" class="form-select" aria-label="Default select example">
                    <option value="" disabled selected>Please Select An Option</option>
                    <option value="Today Patient">Today Patient</option>
                    <option value="Weekly Patient">Weekly Patient</option>
                    <option value="Monthly Patient">Monthly Patient</option>
                    <option value="All Patient">All Patient</option>
                    <option value="Custom">Custom Filter</option>
                </select>
            </div>
             
             <div class="col-12 col-md-3 d-flex align-items-center">
                <select id="reportTypeForAdmitDischarge" class="form-select" aria-label="Default select example" disabled>
                    <option value="" disabled selected>Please Select An Option</option>
                    <option value="Admit">Admit</option>
                    <option value="Discharge">Discharge</option>
                </select>
            </div>
             
             
            <div class="row">
            <div class="col-6 mt-6">
                <div class="card-body d-flex align-items-center" style="padding: 10px; margin: 0;">
                    <form id="admitPatientForm">
                        <div class="form-group">
                            <div class="row">
                                <div class="col-md-4">
                                    <div id="startDateSection">
                                        <label class="col-form-label spti-label">Enter Start Date:</label>
                                        <input type="date" class="col-md-12 spti-inpt" id="startDate" name="startDate">
                                    </div>
                                </div>
                                <div class="col-md-4">
                                    <div id="endDateSection">
                                        <label class="col-form-label spti-label">Enter End Date:</label>
                                        <input type="date" class="col-md-12 spti-inpt" id="endDate" name="endDate">
                                    </div>
                                </div>
                                <div class="col-md-3">
                                    <div id="submitButton">
                                        <button type="button" class="btn btn-warning btn-sm add-btn float-end Spti-btn"  onclick="generateReportByDateRange()">Generate Report</button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
            </div>
        </div>

        <div class="row mt-4">
    <div class="col-md-12">
        <div class="card">
            <div class="table-responsive" id="patientDataTable">
                <table class="table table-new m-0" id="tableID" style="max-width: 100% !important;">
                    <thead>
                        <tr>
                            <th style="width: 25%;">Patient Name</th>
                            <th style="width: 10%;">Gender</th>
                            <th style="width: 15%;">Address</th>
                            <th style="width: 10%;">Age</th>
                            <th style="width: 15%;">Phone Number</th>
                            <th style="width: 20%;">Email</th>
                            <th style="width: 15%;">Status</th>
                        </tr>
                    </thead>
                    <tbody id="patientListBody">
                        <!-- Table body content will be dynamically populated -->
                    </tbody>
                </table>
            </div>

            <div id="noDataMessage" class="text-center" style="display: none;">
                No data available.
            </div>
        </div>
    </div>
</div>

    </div>
</div>

<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

<script>
$(document).ready(function() {
    $('#startDateSection, #endDateSection, #submitButton').hide();
    $('#reportTypeForAdmitDischarge').attr('disabled', true);
    
    $('#reportType').on('change', function() {
        var selectedOption = $(this).val();
        if (selectedOption === 'Custom') {
            $('#startDateSection, #endDateSection, #submitButton').show();
        } else {               
            $('#startDateSection, #endDateSection, #submitButton').hide();                
            clearDateFields();                
            hidePatientTable();               
            
            $('#reportTypeForAdmitDischarge').removeAttr('disabled');
        }
    });

    $('#reportTypeForAdmitDischarge').on('change', function() {
        var selectedOption = $(this).val();
        var reportType = $('#reportType').val();

        if (selectedOption !== '' && reportType !== '') {          
            var previousOption = $('#reportTypeForAdmitDischarge').data('previousOption');
            if ((previousOption === 'Admit' || previousOption === 'Discharge') && (selectedOption === 'Admit' || selectedOption === 'Discharge')) {
                $(this).val('');
            }
           
            $('#reportTypeForAdmitDischarge').data('previousOption', selectedOption);
            fetchPatientData(reportType, selectedOption);
        }
    });
      
    $('#submitButton').on('click', function() {            
        showPatientTable();            
        generateReportByDateRange();
    });
    
    $('#startDate, #endDate').on('change', function() {            
        if ($('#startDate').val() !== '' && $('#endDate').val() !== '') {                
            $('#submitButton').prop('disabled', false);
        } else {                
            $('#submitButton').prop('disabled', true);                
            hidePatientTable();
        }
    });
});

function fetchPatientData(reportType, action) {
    reportType = reportType.toLowerCase();
    action = action.toLowerCase();
    status = action;
    var url = "";
    if (action === 'admit') {
        url = "/admits/todaysWeeksMonthPatient/" + reportType;
    } else if (action === 'discharge') {
        url = "/admits/getTodayWeeklyMonthlyDischargePatient/" + reportType;
    } else {
        console.error("Invalid action:", action);
        return;
    }

    $.get(url)
        .done(function(data) {
            displayPatientDataTable(data);
            showPatientTable();
        })
        .fail(function(jqXHR, textStatus, errorThrown) {
            console.error("AJAX error:", textStatus, errorThrown);
            showError("There are no patients on that day.!");
        });
}

function displayPatientDataTable(data) {
    var patientTableBody = $("#patientListBody");
    patientTableBody.empty();

    if (data && data.length > 0) {
        var patientRows = data.map(function(patient) {
            return createPatientRow(patient);
        });

        patientTableBody.append(patientRows.join(''));
        $("#noDataMessage").hide();
        $("#patientDataTable").show();
    } else {
        $("#noDataMessage").text("No data available.").show();
        $("#patientDataTable").hide();
    }
}

function createPatientRow(patient) {
    var avatarHTML;
    var html;
    if (status === 'admit') {
        avatarHTML = '<div class="avatar"><span class="avatar-title">' + (patient.firstName ? patient.firstName.charAt(0).toUpperCase() : '') + '</span></div>';
        html = "<tr>" +
                "<td><div class='d-flex justify-content-start align-items-center'>" + avatarHTML + " " + (patient.firstName ? patient.firstName : '') + " " + (patient.lastName ? patient.lastName : '') + "</div></td>" +
                "<td>" + (patient.gender ? patient.gender : '') + "</td>" +
                "<td>" + (patient.address ? patient.address : '') + "</td>" +
                "<td>" + (patient.age ? patient.age : '') + "</td>" +
                "<td>" + (patient.phoneNumber ? patient.phoneNumber : '') + "</td>" +
                "<td>" + (patient.email ? patient.email : '') + "</td>" +
                "<td>" + (patient.admitDischargeStatus ? patient.admitDischargeStatus : '') + "</td>" +
               "</tr>";
    } else {
        avatarHTML = '<div class="avatar"><span class="avatar-title">' + (patient.patient.firstName ? patient.patient.firstName.charAt(0).toUpperCase() : '') + '</span></div>';
        html = "<tr>" +
                "<td><div class='d-flex justify-content-start align-items-center'>" + avatarHTML + " " + (patient.patient.firstName ? patient.patient.firstName : '') + " " + (patient.patient.lastName ? patient.patient.lastName : '') + "</div></td>" +
                "<td>" + (patient.patient.gender ? patient.patient.gender : '') + "</td>" +
                "<td>" + (patient.patient.address ? patient.patient.address : '') + "</td>" +
                "<td>" + (patient.patient.age ? patient.patient.age : '') + "</td>" +
                "<td>" + (patient.patient.phoneNumber ? patient.patient.phoneNumber : '') + "</td>" +
                "<td>" + (patient.patient.email ? patient.patient.email : '') + "</td>" +
                "<td>" + (patient.admitDischargeStatus ? patient.admitDischargeStatus : '') + "</td>" +
               "</tr>";
    }
    return html;
}

function showError(message) {
    $("#noDataMessage").text(message).show();
    $("#patientDataTable").hide();
    $('#tableID').hide();
}

function showPatientTable() {
    $('#tableID').show();
}

function hidePatientTable() {
    $('#tableID').hide();
}

function generateReportByDateRange() {
    var startDate = $("#startDate").val();
    var endDate = $("#endDate").val();

    $.get("/admits/admissionStartAndEndDate/" + startDate + "/" + endDate)
        .done(function(data) {           
            createPatientTable(data);
        })
        .fail(function(jqXHR, textStatus, errorThrown) {            
            console.error("Error fetching data:", textStatus, errorThrown);
            showError("Failed to fetch patient data. Please try again later.");
        });
}

function createPatientTable(data) {
    var patientTableBody = $("#patientListBody");
    patientTableBody.empty(); 

    if (data && data.length > 0) {
        try {
            var patientRows = data.map(function(patient) {
               
                var avatarHTML = '<div class="avatar"><span class="avatar-title">' + (patient.firstName ? patient.firstName.charAt(0).toUpperCase() : '') + '</span></div>';
                var html = "<tr>" +
                    "<td><div class='d-flex justify-content-start align-items-center'>" + avatarHTML + " " + (patient.firstName ? patient.firstName : '') + " " + (patient.lastName ? patient.lastName : '') + "</div></td>" +
                    "<td>" + (patient.gender ? patient.gender : '') + "</td>" +
                    "<td>" + (patient.address ? patient.address : '') + "</td>" +
                    "<td>" + (patient.age ? patient.age : '') + "</td>" +
                    "<td>" + (patient.phoneNumber ? patient.phoneNumber : '') + "</td>" +
                    "<td>" + (patient.email ? patient.email : '') + "</td>" +
                    "<td>" + (patient.admitDischargeStatus ? patient.admitDischargeStatus : '') + "</td>" +
                    "</tr>";
                return html;
            });

            patientTableBody.append(patientRows.join(''));
            $("#noDataMessage").hide();
            $("#patientDataTable").show();
        } catch (error) {
            console.error("Error creating patient table:", error);
        }
    } else {
        $("#noDataMessage").text("No data available.").show();
        $("#patientDataTable").hide();
    }
}

function clearDateFields() {
    $("#startDate, #endDate").val('');
}

</script>
</body>
</html>
