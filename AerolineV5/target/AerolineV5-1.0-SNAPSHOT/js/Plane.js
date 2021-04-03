
function addPlane() {
    // Construct a msg object containing the data the server needs to process the message from the chat client.
    let plane = {
        plane_id: document.getElementById("plane_id").value,
        plane_name: document.getElementById("plane_name").value,
        plane_seats: document.getElementById("plane_seats").value
    };
    let wsUri = "ws://localhost:8080/AerolineV5/" + "resourceAdmin";
    let exampleSocket = new WebSocket(wsUri);
    exampleSocket.onopen = function (event) {
        exampleSocket.send(JSON.stringify({action: "addNewPlane", object: plane}));
    };
    //exampleSocket.close();
}


function search() {
    let wsUri = "ws://localhost:8080/AerolineV5/" + "resourceAdmin";
    let exampleSocket = new WebSocket(wsUri);
    exampleSocket.onopen = function (event) {
        exampleSocket.send(JSON.stringify({action: "listPlanes", object: "null"}));
    };
    exampleSocket.onmessage = function (event) {
        var msg = JSON.parse(event.data);
        console.log(msg);

    }
    //exampleSocket.close();
}