<script src="js/jquery.2.1.4.min.js" type="text/javascript"
        charset="utf-8"></script>
<!DOCTYPE html>
<html>
<head>
    <title>Test webSockets</title>
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
    var socketUrl = "ws" + '://' + window.location.host + '/chat/13160877272';
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
        $("#messages").append("<img src='/file/thumbnails?path=/comment/2017-07-06/IMG_20170613_172333.jpg' ondblclick='download(\"/comment/2017-07-06/IMG_20170613_172333.jpg\")'/>")

    }

    function onOpen(event) {
        $("#messages").html('系统：连接聊天室成功!')
    }

    function onError(event) {
        alert(event.data);
    }



    function sendMsg() {
        var content ="测试发送消息";
        var chatMsg = new Object();
        chatMsg.senderId = "13818209557";
        chatMsg.senderName = "xinaml";
        chatMsg.receiver = "13818209557";
        chatMsg.msgType = 'POINT'
        chatMsg.content = content;
        console.info("send msg")
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
    function download(path) {
        window.open("/file/download?path="+path)

    }
</script>

</body>
</html>