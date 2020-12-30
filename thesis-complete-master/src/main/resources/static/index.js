// $('.button').on('click', function(e){
//     $('.display').text('Clicked');
//     setTimeout(function(){
//       $('.display').text('');
//     },300);
//   });
//
var stompClient = null;
var x = 1500;
var y=true;
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
    var socket = new SockJS('/complete/gs-guide-websocket');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        setConnected(true);
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/greetings', function (greeting) {
            showGreeting(JSON.parse(greeting.body).content);
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

function sendLeft() {
    if(y===true){
    stompClient.send("/app/hello", {}, JSON.stringify('8101060115150103FF'));
    setTimeout(function(){
        stompClient.send("/app/hello", {}, JSON.stringify('8101060115150303FF'));
    },x);}
else{ 
    stompClient.send("/app/hello", {}, JSON.stringify('Xtrai'));
    setTimeout(function(){
        stompClient.send("/app/hello", {}, JSON.stringify('Xtrai'));
    },x);}

}

function sendRight() {
    if(y===true){
    stompClient.send("/app/hello", {}, JSON.stringify('8101060115150203FF'));
    setTimeout(function(){
        stompClient.send("/app/hello", {}, JSON.stringify('8101060115150303FF'));
    },x);}
else{
    stompClient.send("/app/hello", {}, JSON.stringify('Xphai'));
    setTimeout(function(){
        stompClient.send("/app/hello", {}, JSON.stringify('Xphai'));
    },x);}
}

function sendCenter() {
    if(y===true){
    stompClient.send("/app/hello", {}, JSON.stringify('81010604FF'));}
else{
    
    stompClient.send("/app/hello", {}, JSON.stringify('XStop'));
    setTimeout(function(){
        stompClient.send("/app/hello", {}, JSON.stringify('XStop'));
    },x);
}
}


function sendUp() {
    // stompClient.send("/app/hello", {}, 'abc');
    if(y===true){
       
    stompClient.send("/app/hello", {}, JSON.stringify('8101060115150301FF'));
    setTimeout(function(){
        stompClient.send("/app/hello", {}, JSON.stringify('8101060115150303FF'));
    },x);}
else{
    
    stompClient.send("/app/hello", {}, JSON.stringify('Xtoi'));
    setTimeout(function(){
        stompClient.send("/app/hello", {}, JSON.stringify('Xtoi'));
    },x);
}
}

function sendDown() {
    if(y===true){
    // stompClient.send("/app/hello", {}, 'abc');
    stompClient.send("/app/hello", {}, JSON.stringify('8101060115150302FF'));;
    setTimeout(function(){
        stompClient.send("/app/hello", {}, JSON.stringify('8101060115150303FF'));
    },x);}
else{
    
    stompClient.send("/app/hello", {}, JSON.stringify('Xlui'));
    setTimeout(function(){
        stompClient.send("/app/hello", {}, JSON.stringify('Xlui'));
    },x);
}}

function sendZoomIn() {
    // stompClient.send("/app/hello", {}, 'abc');
    stompClient.send("/app/hello", {}, JSON.stringify('left'));
}

function sendZoomOut() {
    // stompClient.send("/app/hello", {}, 'abc');
    stompClient.send("/app/hello", {}, JSON.stringify('left'));
}

function sendStop() {
    // stompClient.send("/app/hello", {}, 'abc');
    stompClient.send("/app/hello", {}, JSON.stringify('8101060115150303FF'));
}

function sendMode1() {
    // stompClient.send("/app/hello", {}, 'abc');
    stompClient.send("/app/hello", {}, JSON.stringify('8101060115150303FF'));
}

function sendMode2() {
    // stompClient.send("/app/hello", {}, 'abc');
    stompClient.send("/app/hello", {}, JSON.stringify('8101060115150303FF'));
}

function sendMode3() {
    // stompClient.send("/app/hello", {}, 'abc');
    stompClient.send("/app/hello", {}, JSON.stringify('8101060115150303FF'));
}

function showGreeting(message) {
    $("#greetings").append("<tr><td>" + message + "abc" + "</td></tr>");
}
function sendSTOP() {
    // stompClient.send("/app/hello", {}, 'abc');
    stompClient.send("/app/hello", {}, JSON.stringify('XStop'));
    y=!y;
    return y;
}
$(function () {
    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    $( "#power" ).click(function() { connect(); $(this).toggleClass('clicked');});

    $( "#disconnect" ).click(function() { disconnect(); });
    $( "#right" ).click(function(){sendRight();});
    $( "#left" ).click(function(){sendLeft();});
    $( "#Up" ).click(function() { sendUp;});
    $( "#Down" ).click(function(){sendDown;});
    $( "#ZoomIn" ).click(function() { sendZoomIn();});
    $( "#ZoomOut" ).click(function() { sendZoomOut();});
    $( "#Center" ).click(function() { sendCenter();});
    $( "#Stop" ).click(function() { sendStop(); });
    $( "#Mode1" ).click(function() { x=300; });
    $( "#Mode2" ).click(function() { x=500; });
    $( "#Mode3" ).click(function() { x=1000; });
    $( "#STOP" ).click(function() {sendSTOP();});
});


