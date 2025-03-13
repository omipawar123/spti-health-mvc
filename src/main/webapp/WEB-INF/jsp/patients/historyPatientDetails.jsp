<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>

<head>
<title>SPTI</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css"
	integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2"
	crossorigin="anonymous">
<!-- Import jquery cdn -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
	integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
	crossorigin="anonymous">
	
</script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-ho+j7jyWK8fNQe+A12Hb8AhRq26LrZ/JpcUGGOn+Y7RsweNrtN/tE3MoK7ZeZDyx"
	crossorigin="anonymous">
	
</script>
<style>
.row {
	border-left: 10px;
}

.col-md-5 {
	margin-left: 140px;
}

.spti-label {
	font-size: 200px; /* Adjust the font size as needed */
	font-weight: bold; /* Optionally, you can make the label bold */
}

.col-md-4 {
	border: solid;
	border-collapse: collapse;
	border-left: none;
}

.col-form-label-fs-6 {
	
}

.mb-3 {
	margin-top: 10px;
	margin-bottom: -1rem !important;
}

.modal-content {
	
}
</style>
</head>

<body>
	<jsp:include page="../common/header.jsp"></jsp:include>
	<div class="height-90">
		<div class="main-content  ">
			<!-- Modal -->
			<div class="modal fade" id="addUserModel" tabindex="-1"
				aria-labelledby="exampleModalLabel" aria-hidden="true">
				<div class="modal-dialog modal-lg">
					<div class="modal-content" style="border: solid;">
						<div class="modal-header">
							<h5 class="modal-title" id="exampleModalLabel"
								style="background-color: #6bd9e9; border: solid 2px; padding: 4px;">History</h5>
							<button type="button" class="close" data-dismiss="modal"
								aria-label="Close">
								<span aria-hidden="true" onclick="window.history.back()">x</span>
							</button>
						</div>
						<div class="modal-body">
							<form class="login-form"
								action="/opds/historyPatientDetails-form/${OPDHistory.id }"
								method="post" style="border-left: solid; border-color: gray;">

								<div class="row">

									<div class="mb-2"
										style="margin-left: 10px; background-color: #cb74d752; width: 98%;">
										<div style="display: inline; align-items: center;">
											<i class="fas fa-calendar-alt"
												style="color: #ff00b8; margin-right: 5px;"></i> <label
												class="col-form-label fs-6"
												style="padding: 2px; color: red; font-size: 2px;">
												Date </label>
										</div>
										<br> <span class="col-md-12 spti-inpt border-0">
											${ OPDHistory.treatmentDate } </span>
									</div>


									<div class="col-md-2" style="margin-left: 10px;width: auto">
										<label class="col-form-label fs-6" style="color: red;">Seen
											by doctor</label> <input type="text" name="seenByDoctor"
											class="col-md-12 spti-inpt border-0 "
											value="${OPDHistory.seenByDoctor}">
									</div>
									<div class="col-md-2" style="margin-left: 10px;">
										<label class="col-form-label fs-6" style="color: red;">Diagnosis</label>
										<input type="text" name="diagnosis"
											class="col-md-12 spti-inpt border-0 "
											placeholder="e.g Maleria"
											value="${OPDHistory.diagnosis.diagnosis}">
									</div>
									<div class="mb-3"
										style="border-top-style: none; margin-bottom: 0rem !important; margin-left: 23px;">
										<label class="form-label col-form-label fs-6"
											style="margin-left: -12px;color: red;">Treatment</label>
										<textarea class="form-control" id="w3review" rows="3"
											name="treatment" style="width: 650px;">${OPDHistory.treatment}</textarea>
									</div>


								</div>

								<!-- <div class="col-md-12">

									<button type="button"
										class="btn btn-secondary btn-sm cancel-btn float-end mx-1"
										onclick="window.history.back()">Close</button>
								</div> -->
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<script type="text/javascript">
		$(window).on('load', function() {
			$('#addUserModel').modal('show');
		});
	</script>
</body>

</html>