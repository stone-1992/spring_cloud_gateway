package com.stone.auth.constant;

/**
 * @classname SecurityConstants
 * @description Security常量
 */
public interface SecurityConstants {

    /**
     * PASSWORD模式登录处理地址
     */
    String PASSWORD_LOGIN_PRO_URL = "/oauth/token";

    /**
     * token请求头名称
     */
    String TOKEN_HEADER = "Authorization";

    /**
     * 默认保存code的前缀
     */
    String DEFAULT_CODE_KEY = "DEFAULT_CODE_KEY";

    /**
     * 默认生成图形验证码过期时间
     */
    int DEFAULT_IMAGE_EXPIRE = 60;
    /**
     * 允许登录 1
     */
    Integer ALLOW_LOGIN = 1;

    /**+
     * 公钥
     */
    String RSA_PUBLIC_KEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCsOD0oHb007bxLJwjkMZA5N44eZ3xUxm89jnHgpmP8Cy1+5b8gKWOiVMkHB4WAn2eblciwevpGNJH80b9G0ic52Shr5FlzOzek55NN24CSaTFNMjVFHkt6KtdUnGYanvyFVlRzwX119Oii7YQHhNYPSl6Zqk2I+N5gTkmtvpCJRQIDAQAB";

    /**
     * 私钥
     */
    String RSA_PRIVATE_KEY = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAKw4PSgdvTTtvEsnCOQxkDk3jh5nfFTGbz2OceCmY/wLLX7lvyApY6JUyQcHhYCfZ5uVyLB6+kY0kfzRv0bSJznZKGvkWXM7N6Tnk03bgJJpMU0yNUUeS3oq11ScZhqe/IVWVHPBfXX06KLthAeE1g9KXpmqTYj43mBOSa2+kIlFAgMBAAECgYEAihz3/soKyP23OdXuLYMKFLjXAs8fSKVy5Lzt2qnWGXWlYNVDN7m5q5+vMDH6ALLV1lKDBfU57T+PrF9RtKwow8w5i+dik1c4KDhcuB6jxAi6FYcjvgEULZ9xQlUc0v25RVRILgrU7sGSGJGy6RpYPHZOw4PcouPY27DA0JjNh+kCQQDgcxIMPlUJT/Lws4T18/7aqgpNOwCcr8nU0yJe49LRdGiXb67XHYX7Z+mODPAJPXf4BkMmZ/SK+xly/ijBHxbHAkEAxG2m6lIHJbOFlDnX+tXKhrwHyTyuLerCE8xePcko9U4xd+EuxdRGGQrDHZNPLePwR4ueYsZJOzkN0kMWH1zjkwJAQByeayDi6WYM3vRoZljr00n+51CTPC56WHB2wOQStAGsXpVoO8oTG1zzOcA4UGteeVpQlAfdslVHT7GJsqBOwQJAN4qVzt3g3X00gSQV+Pwopw5V2UYKLjuaIwT/hNLzfOFeRXprwk5yf7Q2VY+IrP4tu90zrNRKcq+AQ/rqL3YRdwJAOvCvbIDrfb2TSObpicbldqaSUK5Y2eqr2Trep29Y6ebwDbdLQwg/0GaOoH9Kxi8ekF9HmF/bUQX9Ij/IH5WZXw==";
}
