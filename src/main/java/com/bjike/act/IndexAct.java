package com.bjike.act;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * @Author: [liguiqin]
 * @Date: [2017-06-27 16:29]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@RestController
public class IndexAct {
    @RequestMapping(value = {"/","/index"})
    public String index(Model model) {
        model.addAttribute("title", "this is index");
        model.addAttribute("content", "神奇的 hello world...");
        return "index";
    }

}
