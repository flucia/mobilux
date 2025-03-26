document.addEventListener("DOMContentLoaded", () => {
	let errorMessagesU = document.getElementById("errorMessagesU");
	let errorMessagesP = document.getElementById("errorMessagesP");

	errorMessagesU.style.display = 'none';
	errorMessagesP.style.display = 'none';

	function validateUsername() {
		let username = document.getElementById("username").value;

		errorMessagesU.style.display = 'block';

		errorMessagesU.innerHTML = "";

		if (username === "") {
			errorMessagesU.innerHTML = "Username non può essere vuoto.";
		}
	}

	function validatePassword() {
		let password = document.getElementById("password").value;

		errorMessagesP.style.display = 'block';

		errorMessagesP.innerHTML = "";

		if (password === "") {
			errorMessagesP.innerHTML = "La password non può essere vuota.";
		} else if (password.length < 4) {
			errorMessagesP.innerHTML = "La password deve essere lunga almeno 4 caratteri.";
		}
	}

	function validateForm(event) {
		let username = document.getElementById("username").value;
		let password = document.getElementById("password").value;


		errorMessages.innerHTML = "";

		let valid = true;


		if (username === "") {
			errorMessagesU.innerHTML += "Username non può essere vuoto.<br>";
			valid = false;
		}


		if (password === "") {
			errorMessagesP.innerHTML += "La password non può essere vuota.<br>";
			valid = false;
		} else if (password.length < 4) {
			errorMessagesP.innerHTML += "La password deve essere lunga almeno 4 caratteri.<br>";
			valid = false;
		}


		if (!valid) {
			errorMessagesU.style.display = 'block';
			errorMessagesP.style.display = 'block';
			event.preventDefault();
			return false;
		}

		return true;
	}


	document.getElementById("username").addEventListener("input", validateUsername);
	document.getElementById("password").addEventListener("input", validatePassword);


	document.getElementById("loginForm").addEventListener("submit", validateForm);
});
