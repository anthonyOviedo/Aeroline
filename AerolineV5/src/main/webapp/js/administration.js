//*************************//
//Control for the Plane
//*************************//

function searchPlane() {
    console.log("searching Plane");
    let srchby = document.getElementById("searchPlaneBy").value;
    let wsUri = "ws://localhost:8080/AerolineV5/" + "resourceAdmin";
    let exampleSocket = new WebSocket(wsUri);
    //falta hacer esto una promesa para cerrar el socket una vez que se termine el envio de datos
    exampleSocket.onopen = function (event) {
        exampleSocket.send(JSON.stringify({ action: "searchPlanesBy" + srchby, object: document.getElementById("searchPlane").value }));
    };
    exampleSocket.onmessage = function (event) {
        var msg = JSON.parse(event.data);
        cleanTable();
        msg.forEach(e => printOnTable(e));
    };

}

function updatePlane() {
    let plane = checkPlane();
    let wsUri = "ws://localhost:8080/AerolineV5/" + "resourceAdmin";
    let exampleSocket = new WebSocket(wsUri);
    //falta hacer esto una promesa para cerrar el socket una vez que se termine el envio de datos
    exampleSocket.onopen = function (event) {
        exampleSocket.send(JSON.stringify({ action: "updatePlane", object: plane }));
    };
    //exampleSocket.close();
}

function deletePlane(plane_id) {
    // Construct a msg object containing the data the server needs to process the message from the chat client.
    let plane = {
        plane_id: plane_id,
        plane_name: "@",
        plane_seats: 0
    };
    let wsUri = "ws://localhost:8080/AerolineV5/" + "resourceAdmin";
    let exampleSocket = new WebSocket(wsUri);
    //falta hacer esto una promesa para cerrar el socket una vez que se termine el envio de datos
    exampleSocket.onopen = function (event) {
        exampleSocket.send(JSON.stringify({ action: "deletePlane", object: plane }));
    };
    //exampleSocket.close();
}

function addNewPlane() {
    let plane = checkPlane();
    let wsUri = "ws://localhost:8080/AerolineV5/" + "resourceAdmin";
    let exampleSocket = new WebSocket(wsUri);
    //falta hacer esto una promesa para cerrar el socket una vez que se termine el envio de datos
    exampleSocket.onopen = function (event) {
        exampleSocket.send(JSON.stringify({ action: "addNewPlane", object: plane }));
    };
    //exampleSocket.close();
    document.getElementById("plane_id").value = "";
    document.getElementById("plane_name").value = "";
    document.getElementById("plane_seats").value = "";

}

function checkPlane() {
    //check that values are correct here 
    let plane = {
        plane_id: document.getElementById("plane_id").value,
        plane_name: document.getElementById("plane_name").value,
        plane_seats: document.getElementById("plane_seats").value
    };
    return plane;
}

function listPlanes() {
    let wsUri = "ws://localhost:8080/AerolineV5/" + "resourceAdmin";
    let exampleSocket = new WebSocket(wsUri);
    exampleSocket.onopen = function (event) {
        exampleSocket.send(JSON.stringify({ action: "listPlanes", object: "null" }));
    };
    //falta hacer esto una promesa para cerrar el socket una vez que se termine el envio de datos
    exampleSocket.onmessage = function (event) {
        var msg = JSON.parse(event.data);
        cleanTable();
        msg.forEach(e => printOnTable(e));
    };
}

function printPlanetoEdit(plane_id) {
    let table = document.getElementById("tablePlane");
    let rowPlane = table.rows.namedItem(plane_id);

    document.getElementById("plane_id").value = rowPlane.cells[0].innerHTML;
    document.getElementById('plane_id').readOnly = true;

    document.getElementById("plane_name").value = rowPlane.cells[1].innerHTML;
    document.getElementById("plane_seats").value = rowPlane.cells[2].innerHTML;

    //esconder/mostrar botones

    //canmbia los botones
    document.getElementById("saveNewPlaneButton").style.display = "none";
    document.getElementById("savePlaneButton").style.display = "block";
}

function printOnTable(plane) {
    let table = document.getElementById("tablePlane");
    let editBtn = document.createElement("button");
    let deleteBtn = document.createElement("button");
    editBtn.setAttribute("class", "btn btn-warning");
    editBtn.setAttribute("onclick", "printPlanetoEdit(" + plane.plane_id + ")");
    deleteBtn.setAttribute("class", "btn btn-danger");
    deleteBtn.setAttribute("onclick", "deletePlane(" + plane.plane_id + ")");
    editBtn.innerHTML = "Editar";
    deleteBtn.innerHTML = "Eliminar";
    let row = table.insertRow(0);
    row.setAttribute("id", plane.plane_id);
    let cell1 = row.insertCell(0);
    let cell2 = row.insertCell(1);
    let cell3 = row.insertCell(2);
    let cell4 = row.insertCell(3);
    cell1.innerHTML = plane.plane_id;
    cell2.innerHTML = plane.plane_name;
    cell3.innerHTML = plane.plane_seats;
    cell4.appendChild(editBtn);
    cell4.appendChild(deleteBtn);
}

function cleanTable() {
    let table = document.getElementById("tablePlane");
    table.innerHTML = "";
}

function cancelFrom() {
    document.getElementById('plane_id').readOnly = false;
    document.getElementById("plane_id").value = "";
    document.getElementById("plane_name").value = "";
    document.getElementById("plane_seats").value = "";

    //canmbia los botones
    document.getElementById("saveNewPlaneButton").style.display = "block";
    document.getElementById("savePlaneButton").style.display = "none";

}



//*************************//
//Control for the Flight
//*************************//

function searchFlight() {
    console.log("searching flights");
}

function updateFlight() {
    let flight = checkFlight();
    let wsUri = "ws://localhost:8080/AerolineV5/" + "resourceAdmin";
    let exampleSocket = new WebSocket(wsUri);
    //falta hacer esto una promesa para cerrar el socket una vez que se termine el envio de datos
    exampleSocket.onopen = function (event) {
        exampleSocket.send(JSON.stringify({ action: "updateFlight", object: flight }));
    };
    //exampleSocket.close();
    cleanTableFlight();
    listFlights();
}

function printFlightToEdit(flight_id) {
    let table = document.getElementById("tableFlight");
    let rowPlane = table.rows.namedItem(flight_id);

    document.getElementById("flight_id").value = rowPlane.cells[0].innerHTML;
    document.getElementById('flight_id').readOnly = true;

    document.getElementById("flight_plane_id").value = rowPlane.cells[1].innerHTML;
    document.getElementById("flight_from").value = rowPlane.cells[2].innerHTML;
    document.getElementById("flight_to").value = rowPlane.cells[3].innerHTML;

    //split the string is required to assaign the 2 input fields
    let fulldate = (rowPlane.cells[4].innerHTML).split(" ");
    let date = fulldate[0];
    let time = fulldate[1];

    document.getElementById("flight_date").value = date;
    document.getElementById("flight_time").value = time;
    document.getElementById("flight_price").value = rowPlane.cells[5].innerHTML;

    //esconder el boton de guardar vuelo.
    document.getElementById("saveNewFlightButton").style.display = "none";
    document.getElementById("saveFlightButton").style.display = "block";


}

function deleteFlight(flight_id) {
    // Construct a msg object containing the data the server needs to process the message from the chat client.
    let flight = {
        flight_id: flight_id
    };


    let wsUri = "ws://localhost:8080/AerolineV5/" + "resourceAdmin";
    let exampleSocket = new WebSocket(wsUri);
    //falta hacer esto una promesa para cerrar el socket una vez que se termine el envio de datos
    exampleSocket.onopen = function (event) {
        exampleSocket.send(JSON.stringify({ action: "deleteFlight", object: flight }));
    };
    //exampleSocket.close();
}

function cleanTableFlight() {
    let table = document.getElementById("tableFlight");
    table.innerHTML = "";
}

function printOnTableFlight(flight) {
    let table = document.getElementById("tableFlight");
    let editBtn = document.createElement("button");
    let deleteBtn = document.createElement("button");
    editBtn.setAttribute("class", "btn btn-warning");
    editBtn.setAttribute("onclick", "printFlightToEdit(" + flight.flight_id + ")");
    deleteBtn.setAttribute("class", "btn btn-danger");
    deleteBtn.setAttribute("onclick", "deleteFlight(" + flight.flight_id + ")");
    editBtn.innerHTML = "Editar";
    deleteBtn.innerHTML = "Eliminar";
    let row = table.insertRow(0);
    row.setAttribute("id", flight.flight_id);
    let cell1 = row.insertCell(0);//flightId
    let cell2 = row.insertCell(1);//flightplane
    let cell3 = row.insertCell(2);//flightfrom
    let cell4 = row.insertCell(3);//flightto
    let cell5 = row.insertCell(4);//flighttime
    let cell6 = row.insertCell(5);//flightprice
    let cell7 = row.insertCell(6);//actions

    cell1.innerHTML = flight.flight_id;
    cell2.innerHTML = flight.flight_plane_id;
    cell3.innerHTML = flight.flight_from;
    cell4.innerHTML = flight.flight_to;
    cell5.innerHTML = flight.flight_time;
    cell6.innerHTML = flight.flight_price;
    cell7.appendChild(editBtn);
    cell7.appendChild(deleteBtn);

}

function listFlights() {
    let wsUri = "ws://localhost:8080/AerolineV5/" + "resourceAdmin";
    let exampleSocket = new WebSocket(wsUri);
    exampleSocket.onopen = function (event) {
        exampleSocket.send(JSON.stringify({ action: "listFlights", object: "null" }));
    };
    //falta hacer esto una promesa para cerrar el socket una vez que se termine el envio de datos
    exampleSocket.onmessage = function (event) {
        var msg = JSON.parse(event.data);
        cleanTableFlight();
        msg.forEach(e => printOnTableFlight(e));
    };
}

function addNewFlight() {
    let flight = checkFlight();
    let wsUri = "ws://localhost:8080/AerolineV5/" + "resourceAdmin";
    let exampleSocket = new WebSocket(wsUri);
    //falta hacer esto una promesa para cerrar el socket una vez que se termine el envio de datos
    exampleSocket.onopen = function (event) {
        exampleSocket.send(JSON.stringify({ action: "addNewFlight", object: flight }));
    };
    //exampleSocket.close();
    exampleSocket.onmessage = function (event) {
        var msg = JSON.parse(event.data);
        document.getElementById("flight_id").value = "";
        document.getElementById("flight_plane_id").value = "";
        document.getElementById("flight_from").value = "";
        document.getElementById("flight_to").value = "";
        document.getElementById("flight_time").value = "";
        document.getElementById("flight_date").value = "";
        document.getElementById("flight_price").value = "";
        //aqui debo validar si se agrego bien o no...
        msg.forEach(e => console.log(e));
        exampleSocket.close()
    };


}

function checkFlight() {
    //check that values are correct here 
    let date = document.getElementById("flight_date").value;
    let time = document.getElementById("flight_time").value;
    let Srclctn = (document.getElementById("flight_from").value);
    let Deslctn = (document.getElementById("flight_to").value);

    let flight = {
        flight_id: parseInt(document.getElementById("flight_id").value),
        flight_plane_id: parseInt(document.getElementById("flight_plane_id").value),
        flight_from: "'" + Srclctn + "'",
        flight_to: "'" + Deslctn + "'",
        flight_time: "'" + date + " " + time + "'",
        flight_price: parseInt(document.getElementById("flight_price").value),
    };
    return flight;
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
    exampleSocket.onmessage = function (event) {
        let msg = JSON.parse(event.data);
        msg.forEach(printLocationOption);
    };

    // exampleSocket.onopen = function (event) {
    //     exampleSocket.send(JSON.stringify({ action: "listPlanes", object: "null" }));
    // };
    // exampleSocket.onmessage = function (event) {
    //     let msg = JSON.parse(event.data);
    //     msg.forEach(printLocationOption);
    // };

}

function showAdminPlane() {
    //canmbia los botones
    document.getElementById("PlaneInterface").style.display = "block";
    document.getElementById("FlightInterface").style.display = "none";
}

function showAdminFlight() {
    //canmbia los botones
    document.getElementById("PlaneInterface").style.display = "none";
    document.getElementById("FlightInterface").style.display = "block";
}

loadOptions()
