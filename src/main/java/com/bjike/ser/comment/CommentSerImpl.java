package com.bjike.ser.comment;

import com.bjike.common.exception.SerException;
import com.bjike.common.util.bean.BeanCopy;
import com.bjike.dto.Restrict;
import com.bjike.dto.comment.CommentDTO;
import com.bjike.dto.comment.PictureDTO;
import com.bjike.dto.comment.ShopDTO;
import com.bjike.entity.comment.Comment;
import com.bjike.entity.comment.Picture;
import com.bjike.entity.comment.Shop;
import com.bjike.entity.user.User;
import com.bjike.ser.ServiceImpl;
import com.bjike.to.comment.CommentTO;
import com.bjike.vo.comment.CommentDetailsVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: [liguiqin]
 * @Date: [2017-06-27 08:51]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@Service
public class CommentSerImpl extends ServiceImpl<Comment, CommentDTO> implements ICommentSer {
    @Autowired
    private IShopSer shopSer;
    @Autowired
    private IPictureSer pictureSer;

    @Override
    public Comment add(CommentTO to, List<File> files) throws SerException {
        try {
            Comment comment = BeanCopy.copyProperties(to, Comment.class);
            String[] fields = new String[]{"userid", "token", "nickname", "online"};
            String sql = "select userid,token,nickname,online from ike_user where userid='" + to.getUserId() + "'";
            List<User> users = super.findBySql(sql, User.class, fields);
            if (users.size() > 0) {
                comment.setUserId(to.getUserId());
            } else {
                throw new SerException("用户id不存在");
            }
            ShopDTO shopDTO = new ShopDTO();
            shopDTO.getConditions().add(Restrict.eq("pointX", to.getPointX()));
            shopDTO.getConditions().add(Restrict.eq("pointY", to.getPointY()));
            shopDTO.getConditions().add(Restrict.eq("name", to.getShopName()));
            Shop shop = shopSer.findOne(shopDTO);
            if (null != shop) {
                comment.setShop(shop);

            } else {
                shop = new Shop();
                shop.setPointX(to.getPointX());
                shop.setPointY(to.getPointY());
                shop.setName(to.getShopName());
                shop.setAddress("address");
                shopSer.add(shop);
                comment.setShop(shop);
            }
            return super.save(comment);
        } catch (SerException e) {
            for (File file : files) {
                file.delete();
            }
            throw new SerException(e.getMessage());
        }


    }

    @Override
    public List<Comment> list(String shopId) throws SerException {
        CommentDTO dto = new CommentDTO();
        dto.getConditions().add(Restrict.eq("shop.id", shopId));
        dto.getSorts().add("shop.seq");
        return super.findByCis(dto);
    }

    @Override
    public Long count(String shopId) throws SerException {
        CommentDTO dto = new CommentDTO();
        dto.getConditions().add(Restrict.eq("shop.id", shopId));
        return super.count(dto);
    }

    @Transactional
    @Override
    public void like(String commentId) throws SerException {
        Comment comment = super.findById(commentId);
        if (null != comment) {
            comment.setLikes(comment.getLikes() != null ? (comment.getLikes() + 1) : 1);
            super.update(comment);
        } else {
            throw new SerException("该评论不存在或已被删除!");
        }
    }

    @Transactional
    @Override
    public void uploadImg(String commentId, List<File> files) throws SerException {
        Comment comment = super.findById(commentId);
        List<Picture> pictures = new ArrayList<>(files.size());
        for (File file : files) {
            String path = StringUtils.substringAfter(file.getPath(), "/root/storage");
            Picture picture = new Picture();
            picture.setComment(comment);
            picture.setPath(path);
            pictures.add(picture);
        }
        pictureSer.save(pictures);
    }

    @Override
    public CommentDetailsVO details(String commentId) throws SerException {
        Comment comment = super.findById(commentId);
        if (null != comment) {
            CommentDetailsVO vo = BeanCopy.copyProperties(comment, CommentDetailsVO.class);
            PictureDTO dto = new PictureDTO();
            dto.getConditions().add(Restrict.eq("comment.id", commentId));
            List<Picture> pictures = pictureSer.findByCis(dto);
            String[] images = new String[pictures.size()];
            for (int i = 0; i < pictures.size(); i++) {
                images[i] = pictures.get(i).getPath();
            }
            vo.setImages(images);
            return vo;
        }
        throw new SerException("该商品不存在或已被删除");
    }
}
