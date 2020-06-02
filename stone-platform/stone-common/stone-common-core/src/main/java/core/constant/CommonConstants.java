package core.constant;

/**
 * @classname CommonConstants
 * @description 通用常量
 * @date 2019/11/5 13:25
 * @author xhe
 */
public interface CommonConstants {

	/**
	 * 删除
	 */
	String STATUS_DEL = "1";
	/**
	 * 正常
	 */
	String STATUS_NORMAL = "0";
	/**
	 * 锁定
	 */
	String STATUS_LOCK = "9";
	/**
	 * 菜单
	 */
	String MENU = "0";

	/**
	 * 菜单树根节点
	 */
	Integer MENU_TREE_ROOT_ID = -1;
	/**
	 * 编码
	 */
	String UTF8 = "UTF-8";
	/**
	 * 成功标记
	 */
	Integer SUCCESS = 1;
	/**
	 * 失败标记
	 */
	Integer FAIL = 0;

	/**
	 * 当前页码
	 */
	String PAGE = "page";
	/**
	 * 每页显示记录数
	 */
	String LIMIT = "limit";
	/**
	 * 排序字段
	 */
	String ORDER_FIELD = "sidx";
	/**
	 * 排序方式
	 */
	String ORDER = "order";
	/**
	 *  升序
	 */
	String ASC = "asc";
	/**
	 * 验证码redis前缀
	 */
	String CAPTCHA_PREFIX = "business:captcha:";
	/**
	 * 通用角色设置的企业
	 */
	long ROLE_GENERAL_CORP = -1;
	
	/**
	 * 菜单类型是功能组
	 */
	String FUNCTION_MENU_TYPE_GROUP = "group";
	
	/**
	 * 菜单类型为菜单
	 */
	String FUNCTION_MENU_TYPE_MENU = "menu";
	
	/**
	 * 内置应用，字典值
	 */
	String APP_TYPE = "public_app";
}
