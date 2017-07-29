package com.bjike.ser.user;

import com.bjike.common.exception.SerException;
import com.bjike.entity.user.FriendChain;
import com.bjike.entity.user.Relationship;
import com.bjike.entity.user.User;
import com.bjike.ser.comment.LikesSer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @Author: [liguiqin]
 * @Date: [2017-07-10 14:31]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@Service
public class RelationshipSerImpl implements IRelationshipSer {
    @Autowired
    private LikesSer likesSer;
    @Autowired
    private IUserSer userSer;

    @Override
    public Relationship search(String name, String userId) throws SerException {
        Relationship relationship = new Relationship();
        User user = userSer.findById(userId);
        String sql = "select a.tu_id ,b.avatar_image as headPath,a.nickname from ike_user a,ike_avatar b where" +
                " a.avatar_id=b.avatar_id and ( a.nickname='%s' or tu_id='%s')";
        sql = String.format(sql, name, name);
        List<User> users = likesSer.findBySql(sql, User.class, new String[]{"tu_id", "headPath", "nickname"});
        User seek = null;
        if (null != users && users.size() > 0) {
            seek = users.get(0);
            initRelationship(seek, user, relationship);
        }
        return relationship;
    }

    private void initRelationship(User seek, User user, Relationship relationship) throws SerException {
        String sql = "select a.friend_id  from ike_friends_relationship a where a.tu_id='%s'";
        String[] himFriends = null; //他的朋友
        String[] myFriends = null;//我的朋友

        String exSql = String.format(sql, user.getTu_id());
        List<Object> objects = likesSer.findBySql(exSql);
        if (null != objects) {
            myFriends = objects.toArray(new String[objects.size()]);
            for(String fid: myFriends){
                if(fid.equals(seek.getTu_id())){
                    relationship.setYourFriend(true);
                    return;
                }
            }
        }
        exSql = String.format(sql, seek.getTu_id());
        objects = likesSer.findBySql(exSql);
        if (null != objects) {
            himFriends = objects.toArray(new String[objects.size()]);
            for (String mid : myFriends) {
                for (String hid : himFriends) {
                    if (hid.equals(mid)) { //第一条,第一层朋友链
                        if (relationship.getFriendChains().size() == 5) {
                            return;
                        }
                        User myFriend = userSer.findById(mid);
                        User u = new User();
                        u.setNickname(seek.getNickname());
                        u.setHeadPath(seek.getHeadPath());
                        u.setTu_id(seek.getTu_id());
                        myFriend.setChain(u);
                        FriendChain chain = new FriendChain();
                        chain.setFriendChain(myFriend);
                        List<FriendChain> chains = new ArrayList<>();
                        chains.add(chain);
                        relationship.getFriendChains().add(chains);
                    }
                }
            }
        }
        Map<String, String[]> friendsMap = new HashMap<>();
        if (relationship.getFriendChains().size() <= 5) {
            for (String my_fid : myFriends) {
                sql = "select a.friend_id  from ike_friends_relationship a where a.tu_id='%s'";
                //通过朋友的朋友查询
                exSql = String.format(sql, my_fid);
                objects = likesSer.findBySql(exSql);
                if (null != objects) {
                    String[] friend_friends = objects.toArray(new String[objects.size()]);
                    friendsMap.put(my_fid, friend_friends);
                    for (String friend_friend : friend_friends) {
                        for (String hid : himFriends) {
                            if (friend_friend.equals(hid)) { //第二层朋友链
                                if (relationship.getFriendChains().size() == 5) {
                                    return;
                                }
                                List<String> nameList = new ArrayList<>();
                                User friend_Friend = userSer.findById(friend_friend);
                                User second_chain = userSer.findById(my_fid);
                                User u = new User();
                                u.setNickname(seek.getNickname());
                                u.setHeadPath(seek.getHeadPath());
                                u.setTu_id(seek.getTu_id());
                                friend_Friend.setChain(second_chain);
                                friend_Friend.getChain().setChain(u);
                                nameList.add(friend_Friend.getNickname());
                                nameList.add(second_chain.getNickname());
                                nameList.add(seek.getNickname());
                                if(!isRepeat(nameList)){
                                    FriendChain chain = new FriendChain();
                                    chain.setFriendChain(friend_Friend);
                                    List<FriendChain> chains = new ArrayList<>();
                                    chains.add(chain);
                                    relationship.getFriendChains().add(chains);
                                }


                            }
                        }
                    }
                }
            }

        }

        if (relationship.getFriendChains().size() <=5) {
            for (Map.Entry<String, String[]> entry : friendsMap.entrySet()) {
                String friend = entry.getKey();
                String[] friend_friends =entry.getValue();
                for(String fid: friend_friends) {
                    sql = "select a.friend_id  from ike_friends_relationship a where a.tu_id='%s'";
                    //通过朋友的朋友查询
                    exSql = String.format(sql, fid);
                    objects = likesSer.findBySql(exSql);
                    if (null != objects) {
                        String[] friend_friend_friends = objects.toArray(new String[objects.size()]);
                        for (String friend_friend : friend_friend_friends) {
                            for (String hid : himFriends) {
                                if (friend_friend.equals(hid)) { //第三层朋友链
                                    if (relationship.getFriendChains().size() == 5) {
                                        return;
                                    }
                                    List<String> nameList = new ArrayList<>();
                                    User last_chain = userSer.findById(friend_friend);
                                    User u = new User();
                                    u.setNickname(seek.getNickname());
                                    u.setHeadPath(seek.getHeadPath());
                                    u.setTu_id(seek.getTu_id());
                                    last_chain.setChain(u);
                                    User second_chain = userSer.findById(fid);
                                    User first_chain = userSer.findById(friend);
                                    nameList.add(last_chain.getNickname());
                                    nameList.add(u.getNickname());
                                    nameList.add(second_chain.getNickname());
                                    nameList.add(first_chain.getNickname());
                                    if(!isRepeat(nameList)){
                                        second_chain.setChain(last_chain);
                                        first_chain.setChain(second_chain);
                                        FriendChain chain = new FriendChain();
                                        chain.setFriendChain(first_chain);
                                        List<FriendChain> chains = new ArrayList<>();
                                        chains.add(chain);
                                        relationship.getFriendChains().add(chains);
                                    }

                                }
                            }
                        }
                    }
                }

            }
        }

    }


    private boolean isRepeat(List<String> nameList){
        HashSet<String> nameSet = new HashSet<>();
        nameSet.addAll(nameList);
      return nameList.size()!=nameSet.size();
    }


}
