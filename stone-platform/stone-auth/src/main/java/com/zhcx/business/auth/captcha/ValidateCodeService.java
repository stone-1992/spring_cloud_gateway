//package com.zhcx.business.auth.captcha;
//
//import javax.servlet.http.HttpServletRequest;
//
///**
// * @classname ValidateCodeService
// * @description 验证码接口
// * @date 2020/3/3 16:36
// * @author xhe
// */
//public interface ValidateCodeService {
//    /**
//     * 验证验证码
//     * @param request request对象
//     */
//    void validate(HttpServletRequest request);
//    /**
//     * 获取验证码
//     * @param deviceId 前端唯一标识
//     * @return 验证码
//     */
//    String getCode(String deviceId);
//
//    /**
//     * 删除验证码
//     * @param deviceId 设备唯一标识/手机号
//     */
//    void remove(String deviceId);
//
//    /**
//     * 保存图片验证码
//     * @param deviceId 设备唯一标识
//     * @param validateCode 验证码
//     */
//    void saveImageCode(String deviceId, String validateCode);
//}
