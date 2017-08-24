package com.bjike.act.recommend;

import com.bjike.common.aspect.ADD;
import com.bjike.common.exception.ActException;
import com.bjike.common.exception.SerException;
import com.bjike.common.interceptor.login.LoginAuth;
import com.bjike.common.restful.ActResult;
import com.bjike.common.util.QRCodeUtil;
import com.bjike.ser.user.RecommendSer;
import com.bjike.to.user.RecommendTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;

/**
 * 推荐
 *
 * @Author: [liguiqin]
 * @Date: [2017-08-23 17:24]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@RestController
@RequestMapping("recommend")
public class RecommendAct {
    @Autowired
    private RecommendSer recommendSer;

    /**
     * 添加推荐并生成推荐码(通过推荐码生存二维码)
     *
     * @return
     * @throws ActException
     */
    @LoginAuth
    @PostMapping("/add")
    public ActResult add(@Validated({ADD.class}) RecommendTO to, BindingResult result) throws ActException {
        try {
            String code = recommendSer.add(to);
            return new ActResult(code);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 通过推荐码获取二维码
     *
     * @param code
     * @return
     * @throws ActException
     */
    @LoginAuth
    @GetMapping("/qr/{code}")
    public ActResult QRCode(@PathVariable String code, HttpServletResponse response) throws ActException {
        try {
            response.setContentType("image/jpeg");
            response.setDateHeader("expries", -1);
            response.setHeader("Cache-Control", "no-cache");
            BufferedImage image = QRCodeUtil.encode(code, response.getOutputStream());
            ImageIO.write(image, "JPEG", response.getOutputStream());
            image.flush();
        } catch (Exception e) {
            e.printStackTrace();
            throw new ActException("生成二维码错误!");
        }
        return new ActResult("success");
    }

    /**
     * 通过推荐码获取二维码
     *
     * @param code
     * @return
     * @throws ActException
     */
    @LoginAuth
    @GetMapping("/validate/{code}")
    public ActResult validate(@PathVariable String code) throws ActException {
        try {
            return ActResult.initialize(recommendSer.validate(code));
        } catch (Exception e) {
            throw new ActException("推荐码已失效!");
        }
    }
}
