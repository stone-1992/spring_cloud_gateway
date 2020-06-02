package model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 授权用户信息
 * 
 * @title
 * @author 龚进
 * @date 2020年4月2日
 * @version 1.0
 */
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@Builder
@Data
public class AuthUserInfoDTO {
	/**
	 * 用户平台唯一id
	 */
	private Long userId;
	/**
	 * 登录账号
	 */
	private String identifier;
	/**
	 * 用户姓名
	 */
	private String username;

	private Long corpId;

	private String corpName;

	/**
	 * 是否企业管理员 true 是 false 否
	 */
	private Boolean corpAdminFlag;

	private Long appId;

	private String appName;
}
