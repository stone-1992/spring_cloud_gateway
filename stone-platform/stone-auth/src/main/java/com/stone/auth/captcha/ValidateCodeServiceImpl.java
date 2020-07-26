//package com.zhcx.business.auth.captcha;
//
//import com.zhcx.business.auth.constant.SecurityConstants;
//import org.apache.commons.lang.StringUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.stereotype.Service;
//import org.springframework.web.bind.ServletRequestBindingException;
//import org.springframework.web.bind.ServletRequestUtils;
//import com.zhcx.business.common.model.exception.CaptchaException;
//import javax.servlet.http.HttpServletRequest;
//import java.util.concurrent.TimeUnit;
//
///**
// * @classname ValidateCodeServiceImpl
// * @description
// * @date 2020/3/3 16:37
// */
//@Service
//public class ValidateCodeServiceImpl implements ValidateCodeService {
//
//    @Autowired
//    private RedisTemplate<String,String> redisTemplate;
//
//    /**
//     * 验证验证码
//     */
//    @Override
//    public void validate(HttpServletRequest request) {
//        String deviceId = request.getParameter("deviceId");
//        if (StringUtils.isBlank(deviceId)) {
//            throw new CaptchaException("请在请求参数中携带deviceId参数");
//        }
//        String code = this.getCode(deviceId);
//        String codeInRequest;
//        try {
//            codeInRequest = ServletRequestUtils.getStringParameter(request, "validCode");
//        } catch (ServletRequestBindingException e) {
//            throw new CaptchaException("获取验证码的值失败");
//        }
//        if (StringUtils.isBlank(codeInRequest)) {
//            throw new CaptchaException("请填写验证码");
//        }
//
//        if (code == null) {
//            throw new CaptchaException("验证码不存在或已过期");
//        }
//
//        if (!StringUtils.equals(code, codeInRequest.toLowerCase())) {
//            throw new CaptchaException("验证码不正确");
//        }
//
//        this.remove(deviceId);
//    }
//
//    @Override
//    public String getCode(String deviceId) {
//        return (String) redisTemplate.opsForValue().get(buildKey(deviceId));
//    }
//
//    @Override
//    public void remove(String deviceId) {
//        redisTemplate.delete(buildKey(deviceId));
//    }
//
//    @Override
//    public void saveImageCode(String deviceId, String validateCode) {
//        redisTemplate.opsForValue()
//                .set(buildKey(deviceId),validateCode, SecurityConstants.DEFAULT_IMAGE_EXPIRE,TimeUnit.SECONDS);
//    }
//
//    /**
//     * 构造key前缀
//     * @param deviceId
//     * @return
//     */
//    private String buildKey(String deviceId) {
//        return SecurityConstants.DEFAULT_CODE_KEY + ":" + deviceId;
//    }
//}
