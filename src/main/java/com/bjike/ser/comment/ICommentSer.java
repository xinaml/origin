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
public interface ICommentSer extends Ser<Comment, CommentDTO> {
    default Comment add(CommentTO to, List<File> files) throws SerException {
        return null;
    }

    default List<CommentVO> list(CommentDTO dto) throws SerException {
        return null;
    }

    default Long count(String pointId) throws SerException {
        return null;
    }

    default void like(String commentId,String userId) throws SerException {
    }
    default ScoreType score(String pointId) throws SerException {
        return null;
    }

    default void uploadImg(String commentId, List<File> files) throws SerException {
    }

    default CommentDetailsVO details(String commentId,String userId) throws SerException {
        return null;
    }


}
