function iniciar() {
    console.log("login in...");

    let username = $("#username").val();
    let password = $("#password").val();

    let user = { user_email: username, user_password: password }


    let wsUri = "ws://localhost:8080/AerolineV5/" + "resourceLogin";
    let exampleSocket = new WebSocket(wsUri);
    //falta hacer esto una promesa para cerrar el socket una vez que se termine el envio de datos
    exampleSocket.onopen = function (event) {
        exampleSocket.send(JSON.stringify({ action: "login", object: user }));
    };
    exampleSocket.onmessage = function (event) {
        var msg = JSON.parse(event.data);

        if (msg.user_id != null) {
            document.cookie = "user_name=; expires=Thu, 01 Jan 1970 00:00:00 UTC; path=/;";
            document.cookie = "user_id=; expires=Thu, 01 Jan 1970 00:00:00 UTC; path=/;";
            document.cookie = "user_lastname=; expires=Thu, 01 Jan 1970 00:00:00 UTC; path=/;";
            document.cookie = "user_email=; expires=Thu, 01 Jan 1970 00:00:00 UTC; path=/;";
            document.cookie = "user_type=; expires=Thu, 01 Jan 1970 00:00:00 UTC; path=/;";

            setCookie("user_id", msg.user_id, 0.25)
            setCookie("user_name", msg.user_name, 0.25)
            setCookie("user_lastnames", msg.user_lastnames, 0.25)
            setCookie("user_email", msg.user_email, 0.25)
            setCookie("user_type", msg.user_type, 0.25)
            if (msg.user_type === "admin") {
                window.location.assign("http://localhost:8080/AerolineV5/static/administration.html")
            }
            if (msg.user_type === "client") {
                window.location.assign("http://localhost:8080/AerolineV5/static/findFlight.html")
            }
        }



    };
}


function setCookie(cname, cvalue, exdays) {
    var d = new Date();
    d.setTime(d.getTime() + (exdays * 24 * 60 * 60 * 1000));
    var expires = "expires=" + d.toUTCString();
    document.cookie = cname + "=" + cvalue + ";" + expires + ";path=/";
}

function newUser() {
    let user = checkUser();

    let wsUri = "ws://localhost:8080/AerolineV5/" + "resourceLogin";
    let exampleSocket = new WebSocket(wsUri);
    //falta hacer esto una promesa para cerrar el socket una vez que se termine el envio de datos
    exampleSocket.onopen = function (event) {
        exampleSocket.send(JSON.stringify({ action: "insertUser", object: user }));
    };
    exampleSocket.onmessage = function (event) {
        var msg = JSON.parse(event.data);
        console.log(msg);
    };

}

function checkUser() {
    let user = {
        user_id: document.getElementById("n_user_id").value,
        user_name: "'" + document.getElementById("n_user_name").value + "'",
        user_password: "'" + document.getElementById("n_user_password").value + "'",
        user_type: "'" + "client" + "'",
        user_lastnames: "'" + document.getElementById("n_user_lastnames").value + "'",
        user_email: "'" + document.getElementById("n_user_email").value + "'",
        user_birthday: "'" + document.getElementById("n_user_birthday").value + "'",
        user_address: "'" + document.getElementById("n_user_address").value + "'",
        user_workphone: document.getElementById("n_user_workphone").value,
        user_personalphone: document.getElementById("n_user_personalphone").value,
    };
    return user;
}
