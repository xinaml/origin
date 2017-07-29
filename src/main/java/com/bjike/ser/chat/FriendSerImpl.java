package com.bjike.ser.chat;

import com.bjike.common.exception.SerException;
import com.bjike.common.util.bean.BeanCopy;
import com.bjike.dto.Restrict;
import com.bjike.dto.chat.FriendDTO;
import com.bjike.entity.chat.Client;
import com.bjike.entity.chat.Friend;
import com.bjike.ser.ServiceImpl;
import com.bjike.ser.user.IUserSer;
import com.bjike.session.ChatSession;
import com.bjike.to.chat.FriendTO;
import com.bjike.type.chat.ApplyType;
import com.bjike.vo.chat.FriendGroupVO;
import com.bjike.vo.chat.FriendVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author: [liguiqin]
 * @Date: [2017-07-21 10:49]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@Service
public class FriendSerImpl extends ServiceImpl<Friend, FriendDTO> implements FriendSer {

    @Autowired
    private IUserSer userSer;

    @Transactional
    @Override
    public void add(FriendTO to) throws SerException {

        if (null != userSer.findById(to.getFriendId())) {
            FriendDTO dto = new FriendDTO();
            dto.getConditions().add(Restrict.eq("userId", to.getUserId()));
            dto.getConditions().add(Restrict.eq("friendId", to.getFriendId()));
            Friend friend = super.findOne(dto);
            if (null == friend) {
                friend = BeanCopy.copyProperties(to, Friend.class);
                super.save(friend);
            } else {
                throw new SerException("对方已是您的好友");
            }
        } else {
            throw new SerException("找不到该好友信息");
        }

    }

    @Transactional
    @Override
    public void delete(String friendId, String userId) throws SerException {
        FriendDTO dto = new FriendDTO();
        dto.getConditions().add(Restrict.eq("friendId", friendId));
        Friend friend = super.findOne(dto);
        if (null != friend) {
            super.remove(friend);
        }
        /**
         * 对方好友列表把自己移除
         */
        dto = new FriendDTO();
        dto.getConditions().add(Restrict.eq("userId", friendId));
        dto.getConditions().add(Restrict.eq("friendId", userId));
        friend = super.findOne(dto);
        if (null != friend) {
            super.remove(friend);
        }
    }

    @Override
    public List<FriendGroupVO> groupInfo(String userId) throws SerException {
        String sql = " select a.id,a.name,b.counts from ike_chat_friend_group a,( " +
                " select friend_group_id,count(friend_group_id) as counts from ike_chat_friend where apply_type=1 and user_id='" + userId + "' group by friend_group_id  )b " +
                "where a.id=b.friend_group_id" +
                " union " +
                " select null as id,'我的好友' as name ,count(id) as counts from ike_chat_friend where apply_type=1 and user_id='" + userId + "' " +
                " group by friend_group_id";
        List<FriendGroupVO> vos = super.findBySql(sql, FriendGroupVO.class, new String[]{"id", "name", "counts"});
        return vos;
    }

    @Override
    public List<FriendVO> list(String userId) throws SerException {
        String sql = "select b.tu_id as id,b.nickname,a.remark,c.avatar_image as headPath,a.friend_group_id as friendGroupId" +
                " from  ike_chat_friend a " +
                " left join  ike_user b on a.user_id = b.tu_id and a.apply_type = 1  " +
                " left join ike_avatar c  on b.avatar_id =c.avatar_id " +
                " and a.user_id='" + userId + "' " +
                " order by a.friend_group_id desc";
        List<FriendVO> friendVOS = super.findBySql(sql, FriendVO.class, new String[]{"id", "nickname", "remark", "headPath", "friendGroupId"});
        for (FriendVO vo : friendVOS) {
            Client client = ChatSession.get(vo.getId());
            vo.setOnline((null != client && client.getSession().isOpen()));
        }
        return friendVOS;
    }

    @Override
    public void editRemark(String friendId, String remark, String userId) throws SerException {
        FriendDTO dto = new FriendDTO();
        dto.getConditions().add(Restrict.eq("userId", userId));
        dto.getConditions().add(Restrict.eq("friendId", friendId));
        Friend friend = super.findOne(dto);
        if (null != friend) {
            friend.setRemark(remark);
            super.update(friend);
        }
    }

    @Override
    public void agree(String friendId, String userId) throws SerException {
        FriendDTO dto = new FriendDTO();
        dto.getConditions().add(Restrict.eq("userId", userId));
        dto.getConditions().add(Restrict.eq("friendId", friendId));
        Friend friend = super.findOne(dto);
        if (null != friend) {
            friend.setApplyType(ApplyType.PASS);
            super.update(friend);
        }
    }

    @Override
    public void refuse(String friendId, String userId) throws SerException {
        FriendDTO dto = new FriendDTO();
        dto.getConditions().add(Restrict.eq("userId", userId));
        dto.getConditions().add(Restrict.eq("friendId", friendId));
        Friend friend = super.findOne(dto);
        if (null != friend) {
            friend.setApplyType(ApplyType.REFUSE);
            super.update(friend);
        }
    }

    @Override
    public List<FriendVO> findByApplyType(ApplyType type, String userId) throws SerException {
        String coin = "";
        if (null != type) {
            coin = " and a.apply_type =" + type.getValue();
        } else {
            coin = " and a.apply_type in(0,2)";
        }

        String sql = "select * from(select b.tu_id as id,b.nickname,a.remark,c.avatar_image as headPath,a.friend_group_id as friendGroupId,a.apply_type as applyType" +
                " from  ike_chat_friend a " +
                " left join  ike_user b on a.user_id = b.tu_id " + coin +
                " left join ike_avatar c  on b.avatar_id =c.avatar_id " +
                " and a.user_id='" + userId + "' " +
                " order by a.friend_group_id desc)a  where a.id is not null";
        List<FriendVO> friendVOS = super.findBySql(sql, FriendVO.class, new String[]{"id", "nickname", "remark", "headPath", "friendGroupId","applyType"});
        return friendVOS;
    }

    @Override
    public List<FriendVO> groupMember(String groupId) throws SerException {
        String sql ="select *  from(select c.tu_id as id,c.nickname,b.remark,d.avatar_image as headPath,a.id as groupId from "+
                "ike_chat_group a left join ike_chat_friend b on a.user_id=b.user_id and b.apply_type=1 and a.id='"+groupId+"'"+
                "left join ike_user c on c.tu_id=b.user_id left join ike_avatar d on c.avatar_id=d.avatar_id)a";
        List<FriendVO> friendVOS = super.findBySql(sql, FriendVO.class, new String[]{"id", "nickname", "remark", "headPath", "groupId"});
        return friendVOS;
    }

    @Override
    public List<FriendVO> friendGroup(String id) throws SerException {
        String sql="select *  from(select c.nickname,b.remark,d.avatar_image as headPath,a.id as friendGroupId from "+
                " ike_chat_friend_group a left join ike_chat_friend b on a.user_id=b.user_id and b.apply_type=1 "+
                " left join ike_user c on c.tu_id=b.user_id left join ike_avatar d on c.avatar_id=d.avatar_id)a where a.friendGroupId" +
                "='"+id+"'";
        return super.findBySql(sql, FriendVO.class,new String[]{"nickname","remark","headPath","friendGroupId"});

    }
}
