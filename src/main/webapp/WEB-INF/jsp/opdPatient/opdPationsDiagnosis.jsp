<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>OPD Patient History</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"></script>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <style>
        #tableContainer {
            max-height: 270px;
            overflow-y: auto;
        }
        label {
            margin-top: 10px;
            padding: 10px;
            font-size: 15px;
            margin-bottom: 0px;
        }
        .mt-4 {
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
        table tr:nth-child(even) {
            background: linear-gradient(90deg, #a9ffc6 0%, #8ecfff 100%);
        }
        .row-cols-1 .card {
            padding-left: 20px;
            padding-right: 20px;
            box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2);
            transition: 0.3s;
            background: linear-gradient(90deg, #a9ffc6 0%, #8ecfff 100%);
        }
        .row-cols-1 .card:hover {
            box-shadow: 0 8px 16px 0 rgba(0, 0, 0, 0.3);
        }
        #exampleModalLabel {
            text-align: center;
            font-weight: bold;
            font-size: 25px;
        }
    </style>
</head>
<body>
    <jsp:include page="../common/header.jsp"></jsp:include>
    <div class="container mt-4 height-200">
        <div class="main-content">
            <form id="opdForm" action="/opds/todayOpdPatientHistory" method="get">
                <div class="row">
                    <div class="col-md-4 mb-3">
                        <label for="patientType">Day Type</label>
                        <select class="form-select" id="patientType" name="todayrecord" aria-label="Select Patient Type">
                            <option value="" disabled selected>Please Select An Option</option>
                            <option value="todaypatient">Today Patient</option>
                            <option value="weekpatient">Weekly Patient</option>
                            <option value="monthlypatient">Monthly Patient</option>
                            <option value="AllPatient">All Patient</option>
                        </select>
                    </div>
                    <div class="col-md-3 mb-3">
                        <label for="diseaseType">Disease Type</label>
                        <select id="diseaseType" name="disease" class="form-select" aria-label="Select Disease Type">
                            <option value="" disabled selected>Please Select An Option</option>
                            <option value="Corona">Corona</option>
                            <option value="Fever">Fever</option>
                            <option value="HIV/AIDS">HIV/AIDS</option>
                        </select>
                    </div>
                    <div class="col-md-4 d-flex align-items-end mb-3">
                        <button type="submit" class="btn btn-warning btn-sm add-btn" id="submitButton">Submit</button>
                    </div>
                </div>
            </form>
            <div id="tableContainer"></div>
            <div id="OPD Patient"></div>
        </div>
    </div>

    <script>
    $(document).ready(function () {
        $('#opdForm').on('submit', function (e) {
            e.preventDefault(); // Prevent the default form submission

            var todayrecord = $('#patientType').val();
            var disease = $('#diseaseType').val();

            if (todayrecord && disease) {
                getPatientData(disease, todayrecord);
            } else {
                alert("Please select both patient type and disease type.");
            }
        });

        function getPatientData(disease, todayrecord) {
            $.ajax({
                type: 'GET',
                url: "/opds/todayOpdPatientHistory/" + disease + "/" + todayrecord,
                success: function (data) {
                    console.log(data); // Debugging: log the response data

                    var tableHtml = "";
                    if (data.length === 0) {
                        tableHtml = '<tr><td colspan="4">No data available</td></tr>';
                        $('#OPD Patient').text(""); // Clear the header if no data
                    } else {
                        tableHtml = '<table border="1" style="width: 100%; font-size: 18px;"><thead><tr><th>Name</th><th>Phone Number</th><th>TreatmentDate</th><th>SeenByDoctor</th><th>Diagnosis</th></tr></thead><tbody>';
                        data.forEach(function (item) {
                            tableHtml += '<tr>';
                            tableHtml += '<td>' + item.patient.firstName + ' ' + item.patient.lastName + '</td>';
                            tableHtml += '<td>' + item.patient.phoneNumber + '</td>';
                            tableHtml += '<td>' + item.treatmentDate + '</td>';
                            tableHtml += '<td>' + item.seenByDoctor + '</td>';
                            tableHtml += '<td>' + item.diagnosis + '</td>';
                            tableHtml += '</tr>';
                        });
                        tableHtml += '</tbody></table>';
                        $('#OPD Patient').text("OPD Patient"); // Set the header if data is present
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
