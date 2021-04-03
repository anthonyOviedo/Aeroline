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

function search() {
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
    }
}

function printPlanetoEdit(plane_id) {
    let table = document.getElementById("tablePlane");
    let rowPlane = table.rows.namedItem(plane_id);

    document.getElementById("plane_id").value = rowPlane.cells[0].innerHTML;
    document.getElementById('plane_id').readOnly = true;

    document.getElementById("plane_name").value = rowPlane.cells[1].innerHTML;
    document.getElementById("plane_seats").value = rowPlane.cells[2].innerHTML;

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

function cancelFrom()  {
    document.getElementById('plane_id').readOnly = false;
    document.getElementById("plane_id").value = "";
    document.getElementById("plane_name").value = "";
    document.getElementById("plane_seats").value = "";
}