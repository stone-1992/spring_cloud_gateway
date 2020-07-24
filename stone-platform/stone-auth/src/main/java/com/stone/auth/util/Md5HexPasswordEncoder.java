package com.stone.auth.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * @classname DoubleMD5PasswordEncoder
 * @description 双重MD5+盐 加密解密器
 * @date 2020/2/25 13:55
 */
@Slf4j
public class Md5HexPasswordEncoder {
    /**
     * 双重MD5 + salt 密码匹配器
     * @param rawPassword
     * @param encodedPassword
     * @param salt
     * @return
     */
    public static boolean matches(CharSequence rawPassword, String encodedPassword,String salt) {
        if (encodedPassword == null || encodedPassword.length() == 0) {
            log.warn("Empty encoded password");
            return false;
        }
        //密码加密 [MD5（MD5加密+盐值）]
        String rawPasswordEncoded = DigestUtils.md5Hex(DigestUtils.md5Hex(rawPassword.toString())+salt);
        boolean result = StringUtils.equals(DigestUtils.md5Hex(DigestUtils.md5Hex(rawPassword.toString())+salt), encodedPassword);
        log.debug("原始密码：{} 盐值：{}",rawPassword,salt);
        log.debug("原始密码加密后：{}", rawPasswordEncoded);
        log.debug("encodedPassword：{}  ", encodedPassword);
        log.debug("密码对比结果：{}", result);
        return result;
    }
}
