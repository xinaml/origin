package com.bjike.act.chat;

import com.bjike.common.exception.ActException;
import com.bjike.common.exception.SerException;
import com.bjike.common.interceptor.login.LoginAuth;
import com.bjike.common.restful.ActResult;
import com.bjike.common.restful.Result;
import com.bjike.common.util.UserUtil;
import com.bjike.entity.user.User;
import com.bjike.ser.chat.FriendSer;
import com.bjike.to.chat.FriendTO;
import com.bjike.type.chat.ApplyType;
import com.bjike.vo.chat.FriendGroupVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 我的好友
 *
 * @Author: [liguiqin]
 * @Date: [2017-07-21 11:18]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@LoginAuth //登录验证注解,header必须携带token
@RestController
@RequestMapping("chat/friend")
public class FriendAct {
    @Autowired
    private FriendSer friendSer;

    /**
     * 所有好友成员
     *
     * @return
     * @throws ActException
     */
    @RequestMapping(value = "list", method = RequestMethod.GET)
    @ResponseBody
    public Result list() throws ActException {
        try {
            String userId = UserUtil.currentUserID();
            return ActResult.initialize(friendSer.list(userId));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }

    }

    /**
     *
     *好友类型查询列表
     * @param type
     * @return
     * @throws ActException
     */
    @RequestMapping(value = "/type/list", method = RequestMethod.GET)
    @ResponseBody
    public Result findByApplyType(ApplyType type) throws ActException {
        try {
            String userId = UserUtil.currentUserID();
            return ActResult.initialize(friendSer.findByApplyType(type, userId));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 好友分组成员
     *
     * @param id
     * @return
     * @throws ActException
     */
    @RequestMapping(value = "/friendGroup/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Result friendGroup(@PathVariable String id) throws ActException {
        try {
            return ActResult.initialize(friendSer.friendGroup(id));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 好友分组信息
     *
     * @return
     * @throws ActException
     */
    @RequestMapping(value = "friendGroup/info", method = RequestMethod.GET)
    @ResponseBody
    public Result groupInfo() throws ActException {
        try {
            String userId = UserUtil.currentUserID();
            List<FriendGroupVO> groupVOS = friendSer.groupInfo(userId);
            return ActResult.initialize(groupVOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }

    }

    /**
     * 好友申请
     *
     * @param to
     * @return
     * @throws ActException
     */
    @RequestMapping(value = "apply", method = RequestMethod.POST)
    @ResponseBody
    public Result apply(FriendTO to) throws ActException {
        try {
            friendSer.add(to);
            return new ActResult("apply success");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }

    }

    /**
     * 同意好友申请
     *
     * @param friendId
     * @return
     * @throws ActException
     */
    @RequestMapping(value = "agree", method = RequestMethod.PUT)
    @ResponseBody
    public Result agree(String friendId, HttpServletRequest request) throws ActException {
        try {
            friendSer.agree(friendId);
            return new ActResult("agree success");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }

    }

    /**
     * 拒绝好友申请
     *
     * @param friendId
     * @return
     * @throws ActException
     */
    @RequestMapping(value = "refuse", method = RequestMethod.PUT)
    @ResponseBody
    public Result refuse(String friendId) throws ActException {
        try {
            friendSer.refuse(friendId);
            return new ActResult("refuse success");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }

    }


    /**
     * 删除好友
     *
     * @param friendId
     * @return
     * @throws ActException
     */
    @RequestMapping(value = "delete/{friendId}", method = RequestMethod.DELETE)
    @ResponseBody
    public Result delete(@PathVariable String friendId, HttpServletRequest request) throws ActException {
        try {
            friendSer.delete(friendId);
            return new ActResult("delete success");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }

    }

    /**
     * 备注好友
     *
     * @param friendId
     * @return
     * @throws ActException
     */
    @RequestMapping(value = "remark", method = RequestMethod.PUT)
    @ResponseBody
    public Result editNickname(String nickname, String friendId, HttpServletRequest request) throws ActException {
        try {
            friendSer.editRemark(friendId, nickname);
            return new ActResult("remark success");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


}
