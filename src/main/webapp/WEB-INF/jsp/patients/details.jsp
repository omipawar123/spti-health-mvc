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
					<div class="col-md-12 ">

						<div class="card">
							<div class="card-body">

								<div class="row">
									<div class="col-md-2">
										<img src="https://picsum.photos/200/300" class="img-thumbnail"
											alt="...">
									</div>
									<div class="col-md-10">
										<div class="row PatientsOverview1">
											<div class="col-md-4">
												<div class="border-bottom pb-2 my-2">
													<h2>${patientList.firstName}${patientList.lastName}</h2>
													<p class="m-0 p-0">${patientList.address}</p>
												</div>
											</div>
											<div class="col-md-2 ml-2">
												<a class="btn btn-sm Spti-btn"
													href="/patients/admit-form/${patientList.id }">Admit</a>
											</div>
											<div class="col-md-2 ml-2">
												<a class="btn btn-sm Spti-btn"
													href="/opds/history-form/patients/${patientList.id }">Add
													OPD</a>
											</div>

											<div class="col-md-2">
												<a class="btn btn-sm Spti-btn"
													href="/opds/historyPatient-from/patients/${patientList.id }">History</a>
											</div>
											<div class="col-md-2">
												<a class="btn btn-sm Spti-btn"
													href="/admits/PatientDetails/${patientList.id }">admitHistory</a>
											</div>

										</div>

										<div class="row">
											<div class="col-md-4">
												<label class="spti-label">Phone Number:</label>
												<p>${patientList.phoneNumber}</p>
											</div>

											<div class="col-md-4">
												<label class="spti-label">Phone Email:</label>
												<p>${patientList.email}</p>
											</div>


										</div>

									</div>
								</div>
							</div>
						</div>
					</div>
				</div>

				<div class="row my-2">
					<div class="col-md-12">
						<div class="card">
							<div class="card-body PatientsPrescriptionWrapper">
								<div class="tab-content" id="nav-tabContent">
									<div class="tab-pane fade show active" id="nav-home"
										role="tabpanel" aria-labelledby="nav-home-tab">
										<table class="table table-striped">
											<thead>
												<tr>
													<th>Date</th>
													<th>Seen By</th>
													<th>Diagnosis</th>
													<th>Bill</th>
													<th>Pending Amount</th>
													<th>Status</th>
													<th>Pay</th>
												</tr>
											</thead>

											<c:choose>
												<c:when test="${opdHistory == null}">

													<tbody>
														<c:forEach items="${opd}" var="opd">
															<tr>
																<td><a
																	onclick="window.location.href='/opds/historyPatientDetails-form/${opd.id}'">
																		<span style="font-weight: 500; cursor: pointer;"
																		class="text-info">${opd.treatmentDate}</span>
																</a></td>

																<td>${opd.seenByDoctor }</td>
																<td>${opd.diagnosis }</td>
																<td>${opd.bill }</td>
																<td>${opd.pendingAmount }</td>
																<c:choose>
																	<c:when test="${opd.billStatus == 'Paid' }">
																		<td><span class="badge rounded-pill bg-success">Paid</span></td>
																	</c:when>
																	<c:otherwise>
																		<td><span class="badge rounded-pill bg-danger">Pending</span></td>
																	</c:otherwise>
																</c:choose>


																<c:choose>
																	<c:when test="${opd.billStatus == 'Paid' }">
																		<td><span class="badge rounded-pill bg-success"></span></td>
																	</c:when>
																	<c:otherwise>
																		<td><span class="badge rounded-pill bg-danger"><a
																				href="/opds/billingHistory-form/patients/${opd.id }">Pay</a></span></td>
																	</c:otherwise>
																</c:choose>

															</tr>
														</c:forEach>

													</tbody>



												</c:when>

												<c:otherwise>
													<tbody>
														<c:forEach items="${opdHistory}" var="opd">
															<tr>
																<td><a
																	onclick="window.location.href='/opds/historyPatientDetails-form/${opd.id}'">
																		<span style="font-weight: 500 ; cursor: pointer ;"class="text-info">${opd.treatmentDate}</span>
																</a></td>
																<td>${opd.seenByDoctor }</td>
																<td>${opd.diagnosis.diagnosis }</td>
																<td>${opd.bill }</td>
																<td>${opd.pendingAmount }</td>
																<c:choose>
																	<c:when test="${opd.billStatus == 'Paid' }">
																		<td><span class="badge rounded-pill bg-success">Paid</span></td>
																	</c:when>
																	<c:otherwise>
																		<td><span class="badge rounded-pill bg-danger">Pending</span></td>
																	</c:otherwise>
																</c:choose>


																<c:choose>
																	<c:when test="${opd.billStatus == 'Paid' }">
																		<td><span class="badge rounded-pill bg-success"></span></td>
																	</c:when>
																	<c:otherwise>
																		<td><span class="badge rounded-pill bg-danger"><a
																				href="/opds/billingHistory-form/patients/${opd.id }">Pay</a></span></td>
																	</c:otherwise>
																</c:choose>

															</tr>
														</c:forEach>

													</tbody>
												</c:otherwise>
											</c:choose>




										</table>
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

	<script type="text/javascript">
		function getPrescriptionHistory(id) {
			$
					.ajax({
						type : 'GET',
						url : '/prescriptions/allPrescriptions/' + id,
						success : function(data) {
							var listContainer = $(".prescription-history ul");
							listContainer.empty(); // Clear the current list items

							if (data && data.length > 0) {
								data
										.forEach(function(prescription) {
											var listItem = $("<li>")
													.append(
															$("<a>")
																	.attr(
																			"href",
																			"/prescriptions/medicine?id="
																					+ prescription.prescriptionId)
																	.append(
																			$(
																					"<span>")
																					.text(
																							prescription.prescriptionDate)));
											listContainer.append(listItem);
										});
							} else {
								listContainer
										.append("<li>No prescription history found.</li>");
							}
						},
						error : function() {
							// Handle errors here
						}
					});
		}
	</script>
</body>
</html>
