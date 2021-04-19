

//*************************//
//Control for the Flight
//*************************//

let flights_array;

function searchFlight() {
    cleanTableFlight();
    //query
    let src = document.getElementById("flight_from").value.split(" ")[1];
    let dest = document.getElementById("flight_to").value.split(" ")[1];
    let departure = document.getElementById("flight_departure_date").value;
    let comeback = document.getElementById("flight_back_date").value;

    //mode
    if (document.getElementById('mode1').checked) {
        console.log("solo ida");
        getFlights(src, dest, departure);
    } else if (document.getElementById('mode2').checked) {
        console.log("ida y vuelta");
        getFlights(src, dest, comeback);
    }
}

function getFlights(source, destination, departure) {
    let wsUri = "ws://localhost:8080/AerolineV5/" + "resourceAdmin";
    let exampleSocket = new WebSocket(wsUri);
    exampleSocket.onopen = function (event) {
        exampleSocket.send(JSON.stringify({ action: "getFlights", object: [source, destination, departure] }));
    };
    //falta hacer esto una promesa para cerrar el socket una vez que se termine el envio de datos
    exampleSocket.onmessage = function (event) {
        let msg = JSON.parse(event.data);
        msg.forEach(printOnTableFlight);
        flights_array = msg;
    };
}

function cleanTableFlight() {
    let table = document.getElementById("tableFlight");
    table.innerHTML = "";
    flights_array = [];
}

function printOnTableFlight(flight) {
    let table = document.getElementById("tableFlight");
    let comprarbtn = document.createElement("button");
    comprarbtn.setAttribute("onclick", "loadTicketTable(" + flight.flight_id + ")");
    comprarbtn.innerHTML = "Comprar";

    comprarbtn.setAttribute("class", "btn btn-outline-success");
    comprarbtn.setAttribute("data-bs-toggle", "modal");
    comprarbtn.setAttribute("data-bs-target", "#exampleModal");


    let row = table.insertRow(0);
    row.setAttribute("id", flight.flight_id);
    let cell1 = row.insertCell(0);//flightfrom
    let cell2 = row.insertCell(1);//flightto
    let cell3 = row.insertCell(2);//flighttime
    let cell4 = row.insertCell(3);//flightprice
    let cell5 = row.insertCell(4);//actions

    cell1.innerHTML = flight.flight_from;
    cell2.innerHTML = flight.flight_to;
    cell3.innerHTML = flight.flight_time.replace(":00.0", "");
    cell4.innerHTML = flight.flight_price;
    cell5.appendChild(comprarbtn);

}

function cancelFromFlight() {
    document.getElementById('flight_id').readOnly = false;
    document.getElementById("flight_id").value = "";
    document.getElementById("flight_plane_id").value = "";
    document.getElementById("flight_from").value = "";
    document.getElementById("flight_to").value = "";
    document.getElementById("flight_time").value = "";
    document.getElementById("flight_date").value = "";
    document.getElementById("flight_price").value = "";

    //canmbia los botones
    document.getElementById("saveNewFlightButton").style.display = "block";
    document.getElementById("saveFlightButton").style.display = "none";

    //cambiar el fondo tambien...
}

function printLocationOption(e) {
    let completelocation = e.location_country + " " + e.location_airport_name;
    let option = '<option value="' + e.location_id + " " + completelocation + '" >' + completelocation + '<option/> ';

    document.getElementById("datalistSourceLocations").innerHTML += option;
    document.getElementById("datalistDestLocations").innerHTML += option;
}

function loadOptions() {
    let wsUri = "ws://localhost:8080/AerolineV5/" + "resourceAdmin";
    let exampleSocket = new WebSocket(wsUri);
    exampleSocket.onopen = function (event) {
        exampleSocket.send(JSON.stringify({ action: "listLocations", object: "null" }));
    };
    //falta hacer esto una promesa para cerrar el socket una vez que se termine el envio de datos
    exampleSocket.onmessage = function (event) {
        let msg = JSON.parse(event.data);
        msg.forEach(printLocationOption);
    };
}


function cleanTicketTable() {
    document.getElementById('t_vuelo_ida').value = "";
    document.getElementById('t_user_id').value = "";
    document.getElementById("t_fch_sal").value = "";
    document.getElementById('t_precio_total').value = "";
    document.getElementById('t_precio_original').value = "";
    document.getElementById('t_cant_asientos').value = "";
}

function loadTicketTable(flight_id, flight_idB) {
    //preguntar en la base la cantidad de asientos disponibles
    document.getElementById('t_user_id').readOnly = true;
    document.getElementById('t_vuelo_ida').readOnly = true;
    document.getElementById('t_vuelo_regr').readOnly = true;
    document.getElementById('t_fch_sal').readOnly = true;
    document.getElementById('t_fch_reg').readOnly = true;
    document.getElementById('t_precio_total').readOnly = true;
    //aqui necesito llenar los campos del modal


    //check the flight
    let flight = flights_array.find(x => x.flight_id === flight_id);
    document.getElementById('t_vuelo_ida').value = flight.flight_id + ' : ' + flight.flight_from + ' - ' + flight.flight_to;
    document.getElementById('t_user_id').value = getCookie("user_id");
    document.getElementById("t_fch_sal").value = flight.flight_time.replace(":00.0", "");
    document.getElementById('t_precio_total').value = flight.flight_price;
    document.getElementById('t_precio_original').value = flight.flight_price;
    document.getElementById('t_cant_asientos').value = "1";
    //document.getElementById("flight_time").value = time;
}

function callTotal() {
    let total = parseInt(document.getElementById('t_precio_original').value);
    let asientos = parseInt(document.getElementById('t_cant_asientos').value);

    document.getElementById('t_precio_total').value = (total * asientos);
}

function ConfirmTicket() {
    console.log("comprando...");
    let wsUri = "ws://localhost:8080/AerolineV5/" + "resourcePublic";
    let exampleSocket = new WebSocket(wsUri);
    let ticket = {
        ticket_id: 0,
        ticket_flight_back_code: 0,
        ticket_flight_code: document.getElementById('t_vuelo_ida').value.split(" ")[0],
        ticket_user_id: document.getElementById('t_user_id').value,
        ticket_price: document.getElementById('t_precio_total').value
    }
    exampleSocket.onopen = function (event) {
        exampleSocket.send(JSON.stringify({ action: "buyTicket", object: ticket }));
    };
    //falta hacer esto una promesa para cerrar el socket una vez que se termine el envio de datos
    exampleSocket.onmessage = function (event) {
        let msg = JSON.parse(event.data);
        msg.forEach(printOnTableFlight);
        flights_array = msg;
    };
}

function idaVueltaOpt() {
    console.log("cambiando");
    if (document.getElementById('mode1').checked) {
        document.getElementById('flight_back_date').readOnly = true;

    } else if (document.getElementById('mode2').checked) {
        document.getElementById('flight_back_date').readOnly = false;
    }

}

function getCookie(cname) {
    var name = cname + "=";
    var ca = document.cookie.split(';');
    for (var i = 0; i < ca.length; i++) {
        var c = ca[i];
        while (c.charAt(0) == ' ') {
            c = c.substring(1);
        }
        if (c.indexOf(name) == 0) {
            return c.substring(name.length, c.length);
        }
    }
    return "";
}

loadOptions()