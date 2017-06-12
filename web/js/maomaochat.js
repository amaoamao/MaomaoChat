/**
 * Created by Jaho on 2017/5/14.
 */

var webSocket = null;

$(document).ready(function () {
    if (!window.WebSocket) {
        alert("您的浏览器不支持websocket！");
        return false;
    }

    //监听窗口关闭事件，当窗口关闭时，主动去关闭websocket连接，防止连接还没断开就关闭窗口，server端会抛异常。
    window.onbeforeunload = function(){
        webSocket.close();
    };

    webSocket = new WebSocket("ws://localhost:8080/MaomaoChat/chat/q4geid7nnjrowc8y");
    //接收到消息的回调方法
    webSocket.onmessage = function(event){
        receive(event)
    };

    //连接成功建立的回调方法
    webSocket.onopen = function(event) {
        open(event);
    };

    //连接发生错误的回调方法
    webSocket.onerror = function(event){
        error(event);
    };

    //连接关闭的回调方法
    webSocket.onclose = function(){
        close();
    };

});




//将消息显示在网页上
function setMessageInnerHTML(innerHTML){
    document.getElementById('message').innerHTML += innerHTML + '<br/>';
}

function open(event) {
    setMessageInnerHTML("已连接到服务器");
}

//关闭连接
function close(){
    webSocket.close();
    setMessageInnerHTML("已关闭连接");
}

//发送消息
function send(){
    var message = document.getElementById('text').value;
    webSocket.send(message)
}

function receive(event) {
    console.log('Client received a message',event);
    setMessageInnerHTML(event.data);
}

function error(event){
    setMessageInnerHTML("发生了错误");
}

