package model.context;


import model.dto.AuthUserInfoDTO;

/**
 * @classname UserAuthContextHolder
 * @description 用户认证信息上下文
 * @date 2020/4/20 9:27
 * @author xhe
 */
public class UserAuthContextHolder {
    private final static ThreadLocal<AuthUserInfoDTO> THREAD_LOCAL_USER_AUTH = new InheritableThreadLocal<AuthUserInfoDTO>();

    /**
     * 设置当前线程用户
     * @param authUserInfoDTO
     */
    public static void set(AuthUserInfoDTO authUserInfoDTO){
        THREAD_LOCAL_USER_AUTH.set(authUserInfoDTO);
    }

    /**
     * 获取当前线程用户
     * @return
     */
    public static AuthUserInfoDTO get(){
        return THREAD_LOCAL_USER_AUTH.get();
    }

    /**
     * 删除当前线程用户信息
     */
    public static void remove(){
        THREAD_LOCAL_USER_AUTH.remove();
    }
}
