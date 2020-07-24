//package com.zhcx.business.auth.controller;
//
//import com.wf.captcha.GifCaptcha;
//import com.wf.captcha.base.Captcha;
//import com.wf.captcha.utils.CaptchaUtil;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.util.Assert;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//import javax.servlet.http.HttpServletResponse;
//
///**
// * @classname ValidateCodeController
// * @description 验证码控制器
// * @date 2020/3/3 17:21
// */
//@RequestMapping(value = "validateCode")
//@RestController
//public class ValidateCodeController {
//
//    @Autowired
//    private ValidateCodeService validateCodeService;
//
//    /**
//     * 创建验证码
//     *
//     * @throws Exception
//     */
//    @GetMapping(value = "/{deviceId}")
//    public void createCode(@PathVariable String deviceId, HttpServletResponse response) throws Exception {
//        Assert.notNull(deviceId, "机器码不能为空");
//        // 设置请求头为输出图片类型
//        CaptchaUtil.setHeader(response);
//        // 三个参数分别为宽、高、位数
//        GifCaptcha gifCaptcha = new GifCaptcha(100, 35, 4);
//        // 设置类型：字母数字混合
//        gifCaptcha.setCharType(Captcha.TYPE_DEFAULT);
//        // 保存验证码
//        validateCodeService.saveImageCode(deviceId, gifCaptcha.text().toLowerCase());
//        // 输出图片流
//        gifCaptcha.out(response.getOutputStream());
//    }
//}
