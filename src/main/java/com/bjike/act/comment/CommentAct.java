package com.bjike.act.comment;

import com.bjike.common.aspect.ADD;
import com.bjike.common.constant.UserCommon;
import com.bjike.common.exception.ActException;
import com.bjike.common.exception.SerException;
import com.bjike.common.interceptor.login.LoginAuth;
import com.bjike.common.restful.ActResult;
import com.bjike.common.restful.Result;
import com.bjike.common.util.UserUtil;
import com.bjike.common.util.bean.BeanCopy;
import com.bjike.common.util.date.DateUtil;
import com.bjike.common.util.file.FileUtil;
import com.bjike.dto.comment.CommentDTO;
import com.bjike.entity.comment.Comment;
import com.bjike.entity.user.User;
import com.bjike.ser.comment.CommentSer;
import com.bjike.ser.user.UserSer;
import com.bjike.to.comment.CommentTO;
import com.bjike.vo.comment.CommentDetailsVO;
import com.bjike.vo.comment.CommentVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.time.LocalDate;
import java.util.List;

/**
 * @Author: [liguiqin]
 * @Date: [2017-06-28 14:25]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@LoginAuth //登录验证注解,header必须携带token
@RestController
@RequestMapping("comment")
public class CommentAct {
    @Autowired
    private CommentSer commentSer;

    /**
     * 添加点评
     *
     * @param to
     * @param result
     * @return
     * @throws Exception
     */
    @PostMapping("/add")
    public Result add(@Validated(ADD.class) CommentTO to, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            String userId =  UserUtil.currentUserID();
            List<File> files = FileUtil.save(request, getCommentPath(userId));
            Comment comment = commentSer.add(to, files);
            CommentVO vo = BeanCopy.copyProperties(comment, CommentVO.class);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }


    }

    /**
     * 店铺点评列表
     *
     * @return
     * @throws Exception
     */
    @GetMapping("/list")
    public Result list(CommentDTO dto) throws ActException {
        try {
            String userId = UserUtil.currentUserID();
            dto.setUserId(userId);
            List<CommentVO> vos = commentSer.list(dto);
            return ActResult.initialize(vos);

        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 店铺点评量
     *
     * @return
     * @throws Exception
     */
    @GetMapping("/count")
    public Result count(String pointId) throws ActException {
        try {
            return ActResult.initialize(commentSer.count(pointId));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 点评点赞
     *
     * @return
     * @throws Exception
     */
    @PutMapping("/like/{commentId}")
    public Result like(@PathVariable String commentId) throws ActException {
        try {
            commentSer.like(commentId);
            return new ActResult("success");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }

    }

    /**
     * 点评取消点赞
     *
     * @return
     * @throws Exception
     */
    @PutMapping("/cancel/like/{commentId}")
    public Result notLike(@PathVariable String commentId) throws ActException {
        try {
            commentSer.cancelLike(commentId);
            return new ActResult("success");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }

    }


    /**
     * 删除点评
     *
     * @param commentId
     * @return
     * @throws Exception
     */
    @DeleteMapping("/delete/{commentId}")
    public Result delete(@PathVariable String commentId) throws ActException {
        try {
            commentSer.remove(commentId);
            return new ActResult("success");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }

    }


    /**
     * 店铺总评分
     *
     * @return
     * @throws Exception
     */
    @GetMapping("/score/{pointId}")
    public Result score(String pointId) throws ActException {
        try {
            return ActResult.initialize(commentSer.score(pointId));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 上传
     *
     * @param commentId
     * @return
     * @throws Exception
     */
    @PostMapping("/upload/img/{commentId}")
    public Result uploadImg(@PathVariable String commentId, HttpServletRequest request) throws ActException {
        try {
            String userId = UserUtil.currentUserID();
            List<File> files = FileUtil.save(request, getCommentPath(userId));
            commentSer.uploadImg(commentId, files);
            return new ActResult("success");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }

    }

    /**
     * 点评详情
     *
     * @param commentId
     * @return
     * @throws Exception
     */
    @GetMapping("/details/{commentId}")
    public Result details(@PathVariable String commentId) throws ActException {
        try {
            CommentDetailsVO vo = commentSer.details(commentId);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }

    }

    /**
     * 评论图片保存路径
     *
     * @param userId
     * @return
     */
    private String getCommentPath(String userId) {
        return "/" + userId + "/comment/" + DateUtil.dateToString(LocalDate.now()).replaceAll("-", "");
    }



}
