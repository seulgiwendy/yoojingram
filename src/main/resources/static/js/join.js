/**
 * Proudly Developed By Hwi Jun Jeong,
 * Inspired By Bomee, the smartest puppy of the galaxy.
 */

var idCheck = true;
var pwdCheck = false;
var url = "/api/join/checkid";

function setJoinButtonActive() {
	if (idCheck && pwdCheck == true) {

		$('#join-submit').attr('disabled', false);
		console.log('submit button freed!');
	} else {
		$('#join-submit').attr('disabled', true);
		console.log('not yet!');

	}

}

function checkId() {

	var input = $('#id').val();
	console.log(input);

	if (input.length > 0 && input.length < 6) {
		idCheck = false;
		$('#id-short-warning').show();
		$('#id-warning').hide();
	} else if (input.length == 0) {
		$('#id-short-warning').hide();

	} else {
		$('#id-short-warning').hide();
		$.ajax({
			type : 'POST',
			url : url,
			data : input,
			error : function() {
				console.log('something unpleasant has occured.')

			},

			success : function(data) {
				console.log(data);
				if (data.unique) {
					idCheck = true;
					$('#id-warning').hide();
				} else {
					idCheck = false;
					$('#id-warning').show();
				}
			}

		});
	}

	setJoinButtonActive();

}

function checkPwd() {

	var input = $('#pwd').val();

	if (input.length > 5) {
		pwdCheck = true;
		$('#pwd-warning').hide();
	}

	else if (input.length == 0) {
		pwdCheck = false;
		$('#pwd-warning').hide();
	}

	else {
		pwdCheck = false;
		$('#pwd-warning').show();
	}

	setJoinButtonActive();

}