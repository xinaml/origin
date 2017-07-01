package com.bjike.act.comment;

import com.bjike.common.aspect.ADD;
import com.bjike.common.exception.ActException;
import com.bjike.common.exception.SerException;
import com.bjike.common.interceptor.login.LoginAuth;
import com.bjike.common.restful.ActResult;
import com.bjike.common.restful.Result;
import com.bjike.common.util.bean.BeanCopy;
import com.bjike.common.util.date.DateUtil;
import com.bjike.common.util.file.FileUtil;
import com.bjike.entity.comment.Comment;
import com.bjike.ser.comment.ICommentSer;
import com.bjike.to.comment.CommentTO;
import com.bjike.vo.comment.CommentDetailsVO;
import com.bjike.vo.comment.CommentVO;
import org.apache.commons.lang3.StringUtils;
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
    private ICommentSer commentSer;

    /**
     * 添加点评
     *
     * @param to
     * @param result
     * @return
     * @throws Exception
     */
    @PostMapping("/add")
    public Result add(@Validated(ADD.class) CommentTO to, BindingResult result,HttpServletRequest request) throws ActException {
        try {
            if(StringUtils.isBlank(to.getUserId())){
                to.setUserId(request.getHeader("userId"));
            }
            List<File> files = FileUtil.save(request,"/comment/"+ DateUtil.dateToString(LocalDate.now()));
            Comment comment = commentSer.add(to,files);
            CommentVO  vo = BeanCopy.copyProperties(comment,CommentVO.class);
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
    @GetMapping("/list/{shopId}")
    public Result list(@PathVariable String shopId) throws ActException {
        try {
            List<CommentVO> vos = BeanCopy.copyProperties(commentSer.list(shopId),CommentVO.class);
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
    @GetMapping("/count/{shopId}")
    public Result count(@PathVariable String shopId) throws ActException {
        try {
            return ActResult.initialize(commentSer.count(shopId));

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
            return ActResult.initialize("success");
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
            return ActResult.initialize("success");
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
    public Result uploadImg(@PathVariable String commentId,  HttpServletRequest request) throws ActException {
        try {
            List<File> files = FileUtil.save(request,"/comment/"+ DateUtil.dateToString(LocalDate.now()));
            commentSer.uploadImg(commentId,files);
            return ActResult.initialize("success");
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
           CommentDetailsVO vo =  commentSer.details(commentId);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }

    }

}
