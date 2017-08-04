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
输入：<input id ='inp'  name="in"/>
</div>
<div>
    <button value="发送" onclick="sendMsg()">发送</button>

</div>
<br>


<script type="text/javascript">
    var socketUrl = "ws" + '://'+window.location.host+'/chat/13160877272';
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
        var msg =eval(event.data)[0];
        $("#messages").append(msg.content)

    }

    function onOpen(event) {
        $("#messages").html('系统：连接聊天室成功!')
    }

    function onError(event) {
        alert(event.data);
    }



    function sendMsg() {
        var content =$('#inp').val();
        var chatMsg = new Object();
        chatMsg.senderId = "13160877272";
        chatMsg.senderName = "11xinaml";
        chatMsg.receiver = "13818209557";
        chatMsg.msgType = 'POINT'
        chatMsg.content = content;
        webSocket.send(JSON.stringify(chatMsg));
        return false;
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