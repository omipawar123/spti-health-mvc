<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.time.LocalDateTime"%>
<%@ page import="java.time.format.DateTimeFormatter"%>

<!DOCTYPE html>
<html>
<head>
<title>SPTI</title>
<link rel="icon" type="image/x-icon" href="Verity.png">
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css"
	rel="stylesheet">
<style>
/* Add your custom CSS styles here */
.patient-details {
	background-color: #f7f7f7;
	padding: 20px;
	border-radius: 5px;
	box-shadow: 0 0 10px rgba(0, 0, 0, 0.2);
}

.patient-name {
	font-size: 20px;
	font-weight: bold;
	margin-bottom: 10px;
}

strong {
	font-size: 14px;
}

li {
	font-size: 12px;
}
/* Add more custom styles as needed */
</style>
</head>

<body>
	<jsp:include page="../common/header.jsp"></jsp:include>
	<div class="height-90">
		<div class="main-content">
			<div class="container-fluid">
				<h5 class="modal-title" id="exampleModalLabel">Patients
					Overview</h5>
				<hr>
				<div class="row">
					<div class="col-md-12 PatientsOverview">

						<div class="card">
							<div class="card-body">
								<a class="btn btn-sm Spti-btn"
									href="/opds/history-form/patients/${patientSingleData.id }">Add
									OPD</a>
								<div class="row">
									<div class="col-md-2">
										<img src="https://picsum.photos/200/300" class="img-thumbnail"
											alt="...">
									</div>
									<div class="col-md-10">
										<div class="border-bottom pb-2 my-2">
											<h2>${patientSingleData.firstName}
												${patientSingleData.lastName}</h2>
											<p class="m-0 p-0">${patientSingleData.address}</p>
										</div>

										<div class="row">
											<div class="col-md-4">
												<label class="spti-label">Phone Number:</label>
												<p>${patientSingleData.phoneNumber}</p>
											</div>

											<div class="col-md-4">
												<label class="spti-label">Phone Email:</label>
												<p>${patientSingleData.email}</p>
											</div>


										</div>

									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<!-- Add Bootstrap JS and jQuery scripts here -->
	<script src="https://code.jquery.com/jquery-3.5.1.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"></script>


</body>
</html>