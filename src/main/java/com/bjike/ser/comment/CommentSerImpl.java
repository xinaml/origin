package com.bjike.ser.comment;

import com.bjike.common.exception.SerException;
import com.bjike.common.util.bean.BeanCopy;
import com.bjike.dto.Restrict;
import com.bjike.dto.comment.CommentDTO;
import com.bjike.dto.comment.LikesDTO;
import com.bjike.dto.comment.PictureDTO;
import com.bjike.dto.comment.ShopDTO;
import com.bjike.entity.comment.Comment;
import com.bjike.entity.comment.Likes;
import com.bjike.entity.comment.Picture;
import com.bjike.entity.comment.Shop;
import com.bjike.entity.user.User;
import com.bjike.ser.ServiceImpl;
import com.bjike.to.comment.CommentTO;
import com.bjike.vo.comment.CommentDetailsVO;
import com.bjike.vo.comment.CommentVO;
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
    @Autowired
    private ILikesSer likesSer;

    @Override
    public Comment add(CommentTO to, List<File> files) throws SerException {
        try {
            Comment comment = BeanCopy.copyProperties(to, Comment.class);
            User user = getUser(to.getUserId());
            comment.setUserId(user.getTu_id());
            ShopDTO shopDTO = new ShopDTO();
            shopDTO.getConditions().add(Restrict.eq("pointId", to.getPointId()));
            Shop shop = shopSer.findOne(shopDTO);
            if (null != shop) {
                comment.setShop(shop);

            } else {
                shop = new Shop();
                shop.setPointX(to.getPointX());
                shop.setPointY(to.getPointY());
                shop.setName(to.getShopName());
                shop.setAddress(to.getAddress());
                shop.setPointId(to.getPointId());
                shopSer.add(shop);
                comment.setShop(shop);
            }
             super.save(comment);
            uploadImg(comment.getId(),files);
            return comment;

        } catch (SerException e) {
            for (File file : files) {
                file.delete();
            }
            throw new SerException(e.getMessage());
        }


    }

    @Override
    public List<CommentVO> list(CommentDTO dto) throws SerException {
        List<Comment> comments = super.findByCis(dto);
        List<CommentVO> vos = BeanCopy.copyProperties(comments, CommentVO.class);
        if(null!=vos){
            for (CommentVO comment : vos) {
                comment.setAlreadyLikes(alreadyLike(dto.getUserId()));
                PictureDTO pictureDTO = new PictureDTO();
                dto.getConditions().add(Restrict.eq("comment.id", comment.getId()));
                List<Picture> pictures = pictureSer.findByCis(pictureDTO);
                String[] images = new String[pictures.size()];
                for (int i = 0; i < pictures.size(); i++) {
                    images[i] = pictures.get(i).getPath();
                }
                User user = getUser(dto.getUserId());
                comment.setNickname(user.getNickname());
                comment.setHeadPath(user.getHeadPath());
            }
        }

        return vos;
    }

    @Override
    public Long count(String pointId) throws SerException {
        if(StringUtils.isBlank(pointId)){
            throw  new SerException("坐标id不能为空");
        }
        CommentDTO dto = new CommentDTO();
        dto.getConditions().add(Restrict.eq("shop.pointId", pointId));
        return super.count(dto);
    }

    @Transactional
    @Override
    public void like(String commentId, String userId) throws SerException {
        Comment comment = super.findById(commentId);
        if (null != comment) {
            comment.setLikes(comment.getLikes() != null ? (comment.getLikes() + 1) : 1);
            super.update(comment);
            LikesDTO dto = new LikesDTO();
            dto.getConditions().add(Restrict.eq("userId",userId));
            if(likesSer.findByCis(dto).size()==0){
                Likes likes = new Likes();
                likes.setComment(comment);
                likes.setUserId(userId);
                likesSer.save(likes);
            }
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
    public CommentDetailsVO details(String commentId, String userId) throws SerException {
        User user = getUser(userId);
        Comment comment = super.findById(commentId);
        if (null != comment) {
            CommentDetailsVO vo = BeanCopy.copyProperties(comment, CommentDetailsVO.class);
            vo.setImages(getImages(commentId));
            vo.setAlreadyLikes(alreadyLike(userId));
            vo.setHeadPath(user.getHeadPath());
            vo.setNickname(user.getNickname());
            return vo;
        }
        throw new SerException("该商品不存在或已被删除");
    }

    private Boolean alreadyLike(String userId) throws SerException {
        LikesDTO likesDTO = new LikesDTO();
        likesDTO.getConditions().add(Restrict.eq("userId", userId));
        List<Likes> likes = likesSer.findByCis(likesDTO);
        return likes.size() > 0;
    }

    private String[] getImages(String commentId) throws SerException {
        PictureDTO dto = new PictureDTO();
        dto.getConditions().add(Restrict.eq("comment.id", commentId));
        List<Picture> pictures = pictureSer.findByCis(dto);
        String[] images = new String[pictures.size()];
        for (int i = 0; i < pictures.size(); i++) {
            images[i] = pictures.get(i).getPath();
        }
        return images;
    }

    public User getUser(String userId) throws SerException {
        String sql = "select a.tu_id ,b.avatar_image as headPath,a.nickname from ike_user a,ike_avatar b where" +
                " a.avatar_id=b.avatar_id and a.tu_id='" + userId + "'";
        List<User> users = pictureSer.findBySql(sql, User.class, new String[]{"tu_id","headPath","nickname"});
        if (null != users && users.size() > 0) {
            return users.get(0);
        }
        throw new SerException("没找到用户");
    }
}
