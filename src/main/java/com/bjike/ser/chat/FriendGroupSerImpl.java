package com.bjike.ser.chat;

import com.bjike.common.exception.SerException;
import com.bjike.common.util.bean.BeanCopy;
import com.bjike.dto.Restrict;
import com.bjike.dto.chat.FriendGroupDTO;
import com.bjike.entity.chat.FriendGroup;
import com.bjike.ser.ServiceImpl;
import com.bjike.to.chat.FriendGroupTO;
import org.springframework.stereotype.Service;

/**
 * @Author: [liguiqin]
 * @Date: [2017-07-21 10:49]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@Service
public class FriendGroupSerImpl extends ServiceImpl<FriendGroup, FriendGroupDTO> implements FriendGroupSer {

    @Override
    public void add(FriendGroupTO to) throws SerException {
        FriendGroupDTO dto = new FriendGroupDTO();
        dto.getConditions().add(Restrict.eq("userId", to.getUserId()));
        dto.getConditions().add(Restrict.eq("name", to.getName()));
        if (null == super.findOne(dto)) {
            FriendGroup group = new FriendGroup();
            group.setName(to.getName());
            group.setUserId(to.getUserId());
            super.save(group);
        } else {
            throw new SerException("该分组名已存在");
        }
    }

    @Override
    public void edit(FriendGroupTO to) throws SerException {
        FriendGroup friendGroup = super.findById(to.getId());
        BeanCopy.copyProperties(to, friendGroup);
        super.update(friendGroup);
    }
}
