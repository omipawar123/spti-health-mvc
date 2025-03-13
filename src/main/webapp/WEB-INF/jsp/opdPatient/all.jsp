<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

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
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"></script>

<script type="text/javascript"
	src="https://code.jquery.com/jquery-3.5.1.js">
	
</script>

<script defer type="text/javascript"
	src="https://cdn.datatables.net/1.10.22/js/jquery.dataTables.min.js"></script>
</head>

<body>
	<jsp:include page="../common/header.jsp"></jsp:include>
	<div class="height-90">
		<div class="main-content  ">
			<h5 class="modal-title" id="exampleModalLabel">All OPDPatients</h5>
			<hr>
			<table class="table table-new m-0" id="tableID"
				style="max-width: 100% !important;">
				<thead>
					<tr>
						<th style="width: 10%;">Name</th>
						<th style="width: 10%;">Phone Number</th>
						<th style="width: 15%;">Preferd Doctor</th>
						<th style="width: 15%;">Date Of OPDTreatment</th>
						<th style="width: 15%;">Diagnosis</th>
						<th style="width: 15%;">BillStatus</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${list.content}" var="user">
						<tr>

							<td style="width: 15%;"><div
									class=" d-flex justify-content-start align-items-center">
									<div class=" ">
										<span class="avatar-title">${fn:toUpperCase(fn:substring(user.patient.firstName, 0, 1))}</span>
									</div>
									<div class="userName">
										<h5 class="m-0" style="cursor: pointer;">
											<a
												onclick="window.location.href='/patients/details-form/${user.patient.id}'">${user.patient.firstName}
												${user.patient.lastName}</a>
										</h5>
									</div>
								</div></td>
							<td style="width: 10%;">${user.patient.phoneNumber}</td>
							<td style="width: 15%;">${user.seenByDoctor}</td>
							<td style="width: 12%;">${user.dateOfTreatment}</td>
							<td style="width: 10%;">${user.diagnosis}</td>
							<td style="width: 10%;">${user.billStatus}</td>

							
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
	<script> 		
		$(document).ready( function() {
			loadTable(${list.number}, ${list.numberOfElements});
		}); 
	</script>
</body>
</html>