package com.bjike.ser.chat;

import com.alibaba.fastjson.JSON;
import com.bjike.common.exception.SerException;
import com.bjike.dto.Restrict;
import com.bjike.dto.chat.FriendDTO;
import com.bjike.dto.chat.GroupMemberDTO;
import com.bjike.dto.chat.MsgDTO;
import com.bjike.entity.chat.Client;
import com.bjike.entity.chat.Friend;
import com.bjike.entity.chat.GroupMember;
import com.bjike.entity.chat.Msg;
import com.bjike.entity.user.User;
import com.bjike.ser.chat.mongo.MsgSer;
import com.bjike.ser.user.IUserSer;
import com.bjike.session.ChatSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.websocket.Session;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * @Author: [liguiqin]
 * @Date: [2017-07-20 14:09]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@Service
public class ChatSerImpl implements ChatSer {

    @Autowired
    private GroupMemberSer groupMember;
    @Autowired
    private IUserSer userSer;
    @Autowired
    private MsgSer msgSer;
    @Autowired
    private FriendSer friendSer;

    /**
     * 初始化客户端
     *
     * @param userId
     * @return
     */
    public Boolean initClient(String userId, Session session) throws SerException {
        Boolean exists = false; //已存在会话
        try {
            Client client = null;
            client = ChatSession.get(userId);
            if (null != client) {
                client.setSession(session); //更新session
                exists = true;

            } else {
                client = new Client();
                User sender = userSer.findById(userId);
                client.setUsername(sender.getNickname());
                client.setHeadPath(sender.getHeadPath());
                client.setSession(session);
                ChatSession.put(userId, client);
                exists = false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return exists;
    }

    public void broadcast(Msg msg) throws SerException {
        if (null != msg.getMsgType()) {
            switch (msg.getMsgType()) {
                case POINT:
                    sendMsg(Arrays.asList(msg), msg.getReceiver());
                    break;
                case GROUP:
                    GroupMemberDTO dto = new GroupMemberDTO();
                    dto.getConditions().add(Restrict.eq("id", msg.getGroup()));
                    List<GroupMember> groupMembers = groupMember.findByCis(dto);
                    for (GroupMember member : groupMembers) {
                        msg.setReceiver(member.getUserId());
                        sendMsg(Arrays.asList(msg), member.getUserId());
                    }
                    break;
                case ONLINE:
                    sendLineMsg(msg);
                    break;
                case OFFLINE:
                    sendLineMsg(msg);
                    break;
                case SYS:
                    break; //all
            }
        } else {
            throw new SerException("消息类型不能为空");
        }
    }

    /**
     * 读取离线时未接收到的消息
     *
     * @param userId
     */
    public void readOffLineMsg(String userId) throws SerException {
        MsgDTO dto = new MsgDTO();
        dto.getConditions().add(Restrict.eq("receiver", userId));
        List<Msg> msgs = msgSer.findByCis(dto);
        if ((null != msgs)) {
            sendMsg(msgs, userId);
            msgSer.remove(msgs);
        }
    }

    /**
     * 消息发送
     *
     * @param msgs
     * @throws SerException
     */
    private void sendMsg(List<Msg> msgs, String receiver) throws SerException {
        if(null!=msgs && msgs.size()>0){
            Session session = null;
            Client client = ChatSession.get(receiver);
            if (null != client) {
                session = client.getSession();
                if (session.isOpen()) {
                    try {
                        System.out.println("receiver:"+client.getUsername());
                        session.getBasicRemote().sendText(JSON.toJSONString(msgs));
                    } catch (IOException e) {
                        throw new SerException(e.getMessage());
                    }
                } else {
                    msgSer.save(msgs);
                }
            } else { //消息未推送
//            off-line
                msgSer.save(msgs);
            }
        }

    }

    private void sendLineMsg(Msg msg) throws SerException {
        FriendDTO dto = new FriendDTO();
        dto.getConditions().add(Restrict.eq("userId", msg.getUserId()));
        dto.getConditions().add(Restrict.eq("applyType", 1));
        List<Friend> friends = friendSer.findByCis(dto);
        for (Friend friend : friends) {
            Client client = ChatSession.get(friend.getUserId());
            if (null != client && client.getSession().isOpen()) {
                try {
                    client.getSession().getBasicRemote().sendText(JSON.toJSONString(msg));
                } catch (IOException e) {
                    throw new SerException(e.getMessage());
                }
            }
        }

    }


}
