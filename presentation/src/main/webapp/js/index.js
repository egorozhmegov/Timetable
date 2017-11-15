
var url = 'ws://' + window.location.host + port + window.location.pathname + 'shout';
var ws = new WebSocket(url);
ws.onconnect = function(e) {
    console.log("connected");
}
ws.onerror = function (error) {
    console.log('WebSocket Error ' + error);
};
ws.onclose = function(event){
    console.log("Remote host closed or refused WebSocket connection");
    console.log(event);
};
ws.onmessage = function(message) {
    document.getElementById("result").innerHTML = message.data;
};
document.getElementById("shout").onclick =  function(event) {
    event.preventDefault();
    var text = document.getElementById("text").value;
    ws.send(text);
};
