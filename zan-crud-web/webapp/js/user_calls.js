var contextRoot = "service";
var restUserUrl = contextRoot + "/user";
var dataType = "json";
var contentType = "application/" + dataType;

$(function () {
	
	listUser();
	
	$("#userDialog").dialog({
	    autoOpen: false,
	    modal: true,
	    height: 250,
		width: 400,
	    title: "Dati utente",
	    buttons: {
	    	Conferma: function () {
	    		var userId = $("#idForm").val()
	    		var jsonUser = getJsonFromUserForm();
	    		if(userId > 0) {
	    			updateUser(userId, jsonUser);
	    		} else {
	    			insertUser(jsonUser);
	    		}
	    		
	            $(this).dialog("close");
	        },
	        Chiudi: function () {
	            $(this).dialog("close");
	        }
	    }
	});
	$("#userDialog").load("user_dialog.html");
});

function listUser() {
	$.ajax({
        url: restUserUrl,
        type: "GET",
        contentType: contentType,
        cache: false,
        dataType: dataType,
        success: function(data) {
        	buildUserTable("#userBody", data.userList);
        }
    });
}

function getUserAjax(userId) {
	return $.ajax({
        url: restUserUrl + "/" + userId,
        type: "GET",
        contentType: contentType,
        cache: false,
        dataType: dataType
    });
}

function insertUser(jsonUser) {
	$.ajax({
        url: restUserUrl,
        type: "POST",
        contentType: contentType,
        data: jsonUser,
        cache: false,
        success: function(data) {
        	window.location.reload();
        }
	});
}

function updateUser(userId, jsonUser) {
	$.ajax({
        url: restUserUrl + "/" + userId,
        type: "PUT",
        contentType: contentType,
        data: jsonUser,
        cache: false,
        success: function(data) {
        	window.location.reload();
        }
	});
}

function deleteUser(userId) {
	$.ajax({
        url: restUserUrl + "/" + userId,
        type: "DELETE",
        cache: false,
        success: function(data) {
        	window.location.reload();
        }
	});
}

// Apre il dialog popolando, se possibile, la form
function openUserDialog(userId) {
	clearUserForm();
	if(userId > 0) {
		populateUserForm(userId);
	}
	$("#userDialog").dialog("open");	
}

// Svuota la form
function clearUserForm() {
	$("#idForm").val('');
	$("#nomeForm").val('');
	$("#cognomeForm").val('');
	$("#dataNascitaForm").val('');
}

// Popola la form con i dati dell'utente con id specificato
function populateUserForm(userId) {
	var userAjax = getUserAjax(userId);
	userAjax.done(function(user) {
		$("#idForm").val(user.id);
    	$("#nomeForm").val(user.nome);
    	$("#cognomeForm").val(user.cognome);
    	var dataNascita = convertDateFormat(user.dataNascita);
    	$("#dataNascitaForm").val(dataNascita);
	});
}

//Restituisce la form utente in formato json
function getJsonFromUserForm() {
	var json = "{ ";
	json = json.concat("\"firstName\": \"" +  $("#nomeForm").val() + "\",");
	json = json.concat("\"lastName\": \"" +  $("#cognomeForm").val() + "\",");
	json = json.concat("\"dateOfBirth\": \"" +  $("#dataNascitaForm").val() + "\" ");
	json = json.concat("}");
	return json;
}

// Converte una data dal formato dd/MM/yyyy in formato yyyy-MM-dd
function convertDateFormat(dataStr) {
	var giorno = dataStr.substring(0, 2);
	var mese = dataStr.substring(3, 5);
	var anno = dataStr.substring(6, 10);
	return anno + "-" + mese + "-" + giorno;
}

// Costruisce il corpo della tabella HTML
function buildUserTable(tableId, userList) {
	var beginRow = "<tr>";
	var endRow = "</tr>";
	var beginColumn = "<td align='center'>";
	var endColumn = "</td>";
	
	for(var i = 0; i < userList.length; i++) {
		
		// Colonne dati utente
		var idColumn 		= beginColumn + userList[i].id 			+ endColumn;
		var firstNameColumn = beginColumn + userList[i].nome 		+ endColumn;
		var lastNameColumn 	= beginColumn + userList[i].cognome 	+ endColumn;
		var birthDateColumn = beginColumn + userList[i].dataNascita + endColumn;
		
		// Colonna modifica
		var onClickEdit = "onclick='openUserDialog(" + userList[i].id + ")'";
		var editElement = "<a href='#' " + onClickEdit + "><img src='img/edit.png' /></a>";
		var editColumn = beginColumn + editElement + endColumn;
		
		// Colonna eliminazione
		var onClickDelete = "onclick='deleteUser(" + userList[i].id + ")'";
		var deleteElement = "<a href='#' " + onClickDelete + "><img src='img/delete.png' /></a>";
		var deleteColumn = beginColumn + deleteElement + endColumn;
		
		// Riga HTML
		var row = beginRow + 
				  	idColumn +
				  	firstNameColumn +
				  	lastNameColumn +
				  	birthDateColumn +
				  	editColumn +
				  	deleteColumn +
				  endRow;
		
		$(row).appendTo(tableId);
	}
}
