/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

////////////////////////////////////////////
//var host = "ws://localhost:8080/JSFWebSockets/notification";
//document.location.pathname
var wsUri = "ws://" + document.location.host + "/websocket/command";
var websocket;

//inicializar
function init() {
    output = document.getElementById("output");
    writeToScreen("Conectando con ....:" + wsUri)
    testWebSocket();
}

function testWebSocket() {

    websocket = new WebSocket(wsUri);
    websocket.onopen = function (evt) {
        onOpen(evt)
    };
    websocket.onclose = function (evt) {
        onClose(evt)
    };
    websocket.onmessage = function (evt) {
        onMessage(evt)
    };
    websocket.onerror = function (evt) {
        onError(evt)
    };
}

function onOpen(evt) {
    writeToScreen("CONNECTED");
    doSend("Iniciando conección..............");
}

function onClose(evt) {
    writeToScreen("DISCONNECTED");
}

function onMessage(evt) {
    alert("");    
    writeToScreen('<span style="color: blue;">RESPONSE: ' + evt.data + '</span>');
//    websocket.close();
}

function onError(evt) {
    writeToScreen('<span style="color: red;">ERROR:</span> ' + evt.data);
}

function doSend(message) {

    var json = JSON.stringify({
        "mensaje": message,
        "usuario": "joro"
    });

    writeToScreen("SENT: " + json.mensaje);

    websocket.send(json);
    //writeToScreen('<span style="color: blue;">RESPONSE: ' + mensaje + '</span>');
}

function writeToScreen(json) {

    var pre = document.createElement("p");
    pre.style.wordWrap = "break-word";
    pre.innerHTML = json.mensaje;
    output.appendChild(pre);
//    output.val= json;
}

function enviarMensaje() {
    var mensaje = document.getElementById("form:mensajeTexto").value;    
    doSend(mensaje);
    
}

window.addEventListener("load", init, false);