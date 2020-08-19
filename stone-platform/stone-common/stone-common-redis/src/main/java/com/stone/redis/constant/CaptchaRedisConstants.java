package com.stone.redis.constant;

import static com.stone.redis.constant.BaseRedisConstants.BASE;

/**
 * @classname CaptchaConstant
 * @description 验证码服务器redis key常量
 * @date 2020/4/23 9:59
 * @author stone
 */
public interface CaptchaRedisConstants {
    /**
     * 图形验证码redis前缀
     */
    String IMAGE_CAPTCHA_PREFIX = BASE+"captcha:image:";
    /**
     * 短信验证码redis前缀
     */
    String SMS_CAPTCHA_PREFIX = BASE+"captcha:sms:";
    /**
     * 每日同一ip + 手机号短信限制数
     */
    String SMS_LIMIT_PREFIX = BASE+"captcha:count:";

}
