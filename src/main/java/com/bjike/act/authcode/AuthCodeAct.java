package com.bjike.act.authcode;

import com.bjike.common.exception.ActException;
import com.bjike.common.exception.SerException;
import com.bjike.common.util.AuthCodeGenerate;
import com.bjike.ser.user.RegisterSer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Map;

/**
 * 验证码
 *
 * @Author: [liguiqin]
 * @Date: [2017-01-14 17:28]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@RestController
public class AuthCodeAct {

    @Autowired
    private RegisterSer registerSer;

    /**
     * 进入注册页面,生成注册验证码
     *
     * @return {name:'testName',type:'string',defaultValue:'',description:'图片流.'}
     * @des 登录找回密码注册, 验证码图片流
     * @version v1
     */
    @GetMapping("generate/code")
    public void generateCode(HttpServletRequest request, HttpServletResponse response) throws ActException {
        response.setContentType("image/jpeg");
        response.setDateHeader("expries", -1);
        response.setHeader("Cache-Control", "no-cache");
        String sid = request.getSession().getId();
        try {
            Map<String, BufferedImage> imageMap = AuthCodeGenerate.build();
            BufferedImage bufferedImage = null;
            String code = null;
            for (Map.Entry<String, BufferedImage> entry : imageMap.entrySet()) {
                bufferedImage = entry.getValue();
                code = entry.getKey();
            }
            ImageIO.write(bufferedImage, "JPEG", response.getOutputStream());
            bufferedImage.flush();
            registerSer.handleAuthCode(sid, code);
        } catch (IOException e) {
            throw new ActException(e.getMessage());
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}
