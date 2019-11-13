var stompClient = null;

function setConnected(connected) {
  $("#connect").prop("disabled", connected);
  $("#disconnect").prop("disabled", !connected);
  if (connected) {
    $("#conversation").show();
  }
  else {
    $("#conversation").hide();
  }
  $("#greetings").html("");
}

function connect() {
  var socket = new SockJS('/super-shopping-cart');
  stompClient = Stomp.over(socket);
  stompClient.connect({}, function (frame) {
    setConnected(true);
    console.log('Connected: ' + frame);
    stompClient.subscribe('/topic/fromServer', function (greeting) {
      showGreeting(JSON.stringify(JSON.parse(greeting.body), null, 2));
    });
  });
}

function disconnect() {
  if (stompClient !== null) {
    stompClient.disconnect();
  }
  setConnected(false);
  console.log("Disconnected");
}

function sendToProduct() {
  stompClient.send("/app/product", {}, '{"messageType":"'+$("#messageType").val()+'","payload":'+$("#name").val()+"}");
}

function sendToCart() {
  stompClient.send("/app/cart", {},'{"messageType":"'+$("#messageType").val()+'","payload":'+$("#name").val()+"}");
}
function setTextAreaProduct(){
  $("#name").val('{"id":"","name":"","description":"","price":0}');
}

function showGreeting(message) {
  $("#greetings").append("<tr><td>" + message + "</td></tr>");
}

$(function () {
  $("form").on('submit', function (e) {
    e.preventDefault();
  });
  $( "#connect" ).click(function() { connect(); });
  $( "#disconnect" ).click(function() { disconnect(); });
  $( "#sendToProduct" ).click(function() { sendToProduct(); });
  $( "#sendToCart" ).click(function() { sendToCart(); });
  $( "#setProduct" ).click(function(){setTextAreaProduct();});
});
