package com.bjike.ser.comment;

import com.bjike.common.exception.SerException;
import com.bjike.dto.comment.CommentDTO;
import com.bjike.entity.comment.Comment;
import com.bjike.ser.Ser;
import com.bjike.to.comment.CommentTO;
import com.bjike.type.comment.ScoreType;
import com.bjike.vo.comment.CommentDetailsVO;
import com.bjike.vo.comment.CommentVO;

import java.io.File;
import java.util.List;

/**
 * @Author: [liguiqin]
 * @Date: [2017-06-27 08:51]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public interface CommentSer extends Ser<Comment, CommentDTO> {
    /**
     * 添加评论
     *
     * @param to
     * @param files
     * @return
     * @throws SerException
     */
    default Comment add(CommentTO to, List<File> files) throws SerException {
        return null;
    }

    /**
     * 店铺评论列表
     *
     * @param dto
     * @return
     * @throws SerException
     */
    default List<CommentVO> list(CommentDTO dto) throws SerException {
        return null;
    }

    /**
     * 店铺评论列表数量
     *
     * @param pointId
     * @return
     * @throws SerException
     */
    default Long count(String pointId) throws SerException {
        return null;
    }

    /**
     * 点赞
     *
     * @param commentId
     * @throws SerException
     */
    default void like(String commentId ) throws SerException {
    }

    /**
     * 取消点赞
     *
     * @param commentId
     * @throws SerException
     */
    default void cancelLike(String commentId ) throws SerException {
    }

    /**
     * 店铺总评分
     *
     * @param pointId
     * @return
     * @throws SerException
     */
    default ScoreType score(String pointId) throws SerException {
        return null;
    }

    /**
     * 上传图片
     *
     * @param commentId
     * @param files
     * @throws SerException
     */
    default void uploadImg(String commentId, List<File> files) throws SerException {
    }

    /**
     * 评论详情
     *
     * @param commentId
     * @return
     * @throws SerException
     */
    default CommentDetailsVO details(String commentId ) throws SerException {
        return null;
    }


}
