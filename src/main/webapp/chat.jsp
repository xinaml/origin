
<script src="js/jquery.2.1.4.min.js" type="text/javascript"
        charset="utf-8"></script>
<!DOCTYPE html>
<html>
<head>
    <title>Testing websockets</title>
</head>
<body>
<div id="messages" style="overflow-y:auto; overflow-x:auto;
 width:700px; height:350px;border: 2px solid darkslategrey;float: left">
</div>
<div id="friends" style="width:300px; height:550px;border: 2px solid darkslategrey;float: right">
</div>
<div>
    <button value="发送" onclick="sendMsg()">发送</button>

</div>
<br>
<script type="text/javascript">
    var socketUrl = "ws" + '://' + window.location.host + '/chat/13824692192';
    var webSocket = new WebSocket(socketUrl);

    webSocket.onerror = function (event) {
        onError(event)
    };

    webSocket.onopen = function (event) {
        onOpen(event)
    };

    webSocket.onmessage = function (event) {
        onMessage(event)
    };


    function onMessage(event) {
        var msg = event.data;
        console.info(msg)
        msg = jQuery.parseJSON(msg);
        $("#messages").append("</br>"+msg.senderName+":"+msg.content)

    }

    function onOpen(event) {
        $("#messages").html('系统：连接聊天室成功!')
    }

    function onError(event) {
        alert(event.data);
    }

    function online() {
        console.info("online ")
    }

    function sendMsg() {
        var content ="测试发送消息";
        var chatMsg = new Object();
        chatMsg.senderName = "xinaml";
        chatMsg.receiver = "13818209557";
        chatMsg.chatType = 'PUB'
        chatMsg.content = content;
        webSocket.send(JSON.stringify(chatMsg));
        return false;
    }


    function singleChat(receiver, sid) {
        var content = ue.getContent();
        var chatMsg = new Object();
        chatMsg.sender = $username;
        chatMsg.receiver = receiver;
        chatMsg.sid = sid
        chatMsg.content = content.substring(3, content.length - 4);
        chatMsg.msgType = 'POINT';
        webSocket.send(JSON.stringify(chatMsg));
    }
    //监听窗口关闭事件
    window.onbeforeunload = function () {
     webSocket.close();
    }
</script>

</body>
</html>