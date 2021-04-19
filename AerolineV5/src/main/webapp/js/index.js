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
}

function printLocationOption(e) {
    let completelocation = e.location_country + e.location_airport_name;
    let option = '<option value="' + e.location_id + " " + completelocation + '" >' + completelocation + '<option/> ';

    document.getElementById("datalistSourceLocations").innerHTML += option;
    document.getElementById("datalistDestLocations").innerHTML += option;
}

loadOptions()
