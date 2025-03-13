
$(document).ready(function() {
	$("#login-form").validate({

		rules: {
			firstName: {
				required: true,
				minlength: 2
			},
			lastName: {
				required: true,
				minlength: 2
			},
			phoneNumber: {
				required: true,
				minlength: 10,
				maxlength: 12,

			},
			age: {
				required: true,
				min: 0.1,
				number: true
			},
			gender: {
				required: true
			},
			address: {
				required: true,
				minlength: 2
			},
			email: {
				required: true,
				email: true
			},
			admissionDate: {
				required: true,

			},
			admitDischargeStatus: {
				required: true,

			},
			fullname: {
				required: true,
				minlength: 2
			},
			seenByDoctor: {
				required: true,
				minlength: 2
			},
			diagnosis: {
				required: true,

			},
			treatment: {
				required: true,
				minlength: 2
			},
			bill: {
				required: true,
				min: 0.1,
				number: true
			},
			pendingAmount: {
				required: true,
				min: 0.1,
				number: true
			},
			billStatus: {
				required: true,

			},
			paymentType: {
				required: true,

			},
			Responsible_Person: {
				required: true,
				minlength: 2
			},


			MLC_No: {
				required: true,
				digits: true
			},
			IPD_No: {
				required: true,
				minlength: 2
			},
			dischargeDate: {
				required: true
			},
			consultatnt: {
				required: true,
				minlength: 2
			},
			diagnosis: {
				required: true,
				minlength: 2
			},
			amountPaid: {
				required: true,
				min: 0.1,
				number: true
			},
			amounttobePaid: {
				required: true,
				min: 0,
				number: true
			},




		},
		messages: {
			firstName: {
				required: "Please enter correct first name",
				minlength: "minimum two character"
			},
			lastName: {
				required: "please enter correct last name",
				minlength: "minimum two character"
			},
			phoneNumber: {
				required: "please enter correct phonenumber",

				minlength: "minimum 10 digit",
				maxlength: "maximum 12 digit"
			},
			age: {
				required: " please enter correct age",
				min: " please enter correct age",
				number: " please enter correct age",
			},
			gender: {
				required: " please enter gender",
			},
			address: {
				required: " please enter address"
			},
			email: {
				required: " please enter email",
				email: "please enter correct email please"
			},
			admissionDate: {
				required: " please enter admissionDate",

			},
			email: {
				required: " please enter email",
				email: "please enter correct email please"
			},
			admitDischargeStatus: {
				required: " please select status",

			},
			fullname: {
				required: "Please enter correct first name",
				minlength: "minimum two character"
			},
			seenByDoctor: {
				required: "please enter correct doctor",
				minlength: "please enter correct doctor"
			},
			diagnosis: {
				required: "please enter diagnosis",

			},
			treatment: {
				required: " please enter correct treatment",
				minlength: "please enter correct treatment"

			},
			bill: {
				required: " please enter correct bill ",
				min: " please enter correct bill ",
				number: " please enter correct  bill ",
			},
			pendingAmount: {
				required: " please enter correct bill ",
				min: " please enter correct bill ",
				number: " please enter correct  bill ",
			},
			billStatus: {
				required: " please enter billStatus",

			},
			paymentType: {
				required: " please enter paymentType",

			},
			Responsible_Person: {
				required: "please enter responsible person",
				minlength: "minimum two character"
			},
			MLC_No: {
				required: "Please enter correct MLCNo",
				digits: "please enter only digit",
			},

			IPD_No: {
				required: "Please enter correct IPDNo",
				digits: "please enter only digit",
			},
			dischargeDate: {
				required: " please enter dischargeDate ",
			},
			consultatnt: {
				required: " please enter consultatnt ",
				minlength: "minimum two character"
			},
			diagnosis: {
				required: " please enter diagnosis ",
				minlength: "minimum two character"
			},
			amountPaid: {
				required: " please enter amount Paid ",
				min: " please enter correct amount Paid",
				number: " please enter correct ageamount Paid",
			},
			amounttobePaid: {
				required: " please enter amounttobe Paid ",
				min: " please enter correct amounttobe Paid",
				number: " please enter correct amounttobe Paid",
			},



		},

	});


})