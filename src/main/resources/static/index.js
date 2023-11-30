 function displayMessage(message, type = 'info') {
        var messageDiv = document.getElementById("message");
        messageDiv.innerHTML = message;
        messageDiv.className = "alert alert-" + type;
    }

    function getPerson() {
        var personId = document.getElementById("personId").value;
        fetch("/getP/" + personId)
            .then(response => response.json())
            .then(data => {
                if (data) {
                    console.log(data);
                    displayMessage(JSON.stringify(data));
                } else {
                    displayMessage("No person found with the ID: " + personId, 'danger');
                }
            })
            .catch(error => {
                console.error('Error fetching data:', error);
                displayMessage("Error fetching data", 'danger');
            });
    }
function addPerson() {
	var name = document.getElementById("name").value;
	var age = document.getElementById("age").value;

	var person = {
		name: name,
		age: age
	};

	fetch("/add", {
		method: 'POST',
		headers: {
			'Content-Type': 'application/json',
		},
		body: JSON.stringify(person)
	})
		.then(response => response.json())
		.then(data => {
			console.log(data);
			displayMessage("Person added successfully", 'success');
		});
}

function updatePerson() {
	var personId = document.getElementById("personId").value;
	var name = document.getElementById("name").value;
	var age = document.getElementById("age").value;

	var person = {
		name: name,
		age: age
	};

	fetch("/update/" + personId, {
		method: 'PUT',
		headers: {
			'Content-Type': 'application/json',
		},
		body: JSON.stringify(person)
	})
		.then(response => response.json())
		.then(data => {
			console.log(data);
			displayMessage("Person updated successfully", 'warning');
		});
}

function deletePerson() {
	var personId = document.getElementById("personId").value;

	fetch("/delete/" + personId, {
		method: 'DELETE',
	})
		.then(response => response.json())
		.then(data => {
			console.log(data);
			displayMessage("Person deleted successfully", 'danger');
		});
}
