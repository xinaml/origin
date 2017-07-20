package com.bjike.ser.chat;

import com.alibaba.fastjson.JSON;
import com.bjike.common.exception.SerException;
import com.bjike.dto.Restrict;
import com.bjike.dto.chat.GroupMemberDTO;
import com.bjike.dto.chat.MsgDTO;
import com.bjike.entity.chat.Client;
import com.bjike.entity.chat.GroupMember;
import com.bjike.entity.chat.Msg;
import com.bjike.entity.user.User;
import com.bjike.ser.chat.mongo.IMsgSer;
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
public class ChatSerImpl implements IChatSer {

    @Autowired
    private IGroupMemberSer groupMember;
    @Autowired
    private IUserSer userSer;
    @Autowired
    private IMsgSer msgSer;

    /**
     * 初始化客户端
     *
     * @param userId
     * @return
     */
    public Client initChatClient(String userId, Session session) {
        try {
            if (!ChatSession.exists(userId)) {
                Client client = new Client();
                User sender = userSer.findById(userId);
                client.setUsername(sender.getNickname());
                client.setHeadPath(sender.getHeadPath());
                client.setSession(session);
                //读取离线时未接收到的消息
                readOffLineMsg(userId);
                return client;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void broadcast(Msg msg,String senderId) throws SerException {
        switch (msg.getMsgType()) {
            case POINT:
                sendMsg(Arrays.asList(msg),senderId);
                break;
            case GROUP:
                GroupMemberDTO dto = new GroupMemberDTO();
                dto.getConditions().add(Restrict.eq("id", msg.getGroup()));
                List<GroupMember> groupMembers = groupMember.findByCis(dto);
                for (GroupMember member : groupMembers) {
                    msg.setReceiver(member.getUserId());
                    sendMsg(Arrays.asList(msg),senderId);
                }
                break;
            case PUB:
                break; //all
        }
    }

    /**
     * 读取离线时未接收到的消息
     *
     * @param userId
     */
    public void readOffLineMsg(String userId) throws SerException {
        MsgDTO dto = new MsgDTO();
        dto.getConditions().add(Restrict.eq("receiver",userId));
         List<Msg> msgs = msgSer.findByCis(dto);
         sendMsg(msgs,userId);
         msgSer.remove(msgs);
    }

    /**
     * 消息发送
     *
     * @param msgs
     * @throws SerException
     */
    private void sendMsg(List<Msg> msgs,String userId) throws SerException {
        Session session = null;
        Client client = ChatSession.get(userId);
        if (null != client) {
            session = client.getSession();
            if (session.isOpen()) {
                try {
                    session.getBasicRemote().sendText(JSON.toJSONString(msgs));
                } catch (IOException e) {
                    throw new SerException(e.getMessage());
                }
            }else {
                msgSer.save(msgs);
            }
        } else { //消息未推送
//            off-line
            msgSer.save(msgs);
        }
    }

}
