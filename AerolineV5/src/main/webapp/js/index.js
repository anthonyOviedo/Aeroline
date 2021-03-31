/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var wsUri = "ws://" + document.location.host + document.location.pathname + "resourceAdmin";
var websocket = new WebSocket(wsUri);

websocket.onerror = function(evt) { onError(evt) };
websocket.send("puto");
websocket.onmessage = function(evt){  
  alert("I got data: " + evt.data)
}
function onError(evt) {
    writeToScreen('<span style="color: red;">ERROR:</span> ' + evt.data);
}
 

 
 