package com.stone.gateway.zuul.oauth2;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 授权用户信息
 * 
 * @title
 * @date 2020年4月2日
 * @version 1.0
 */
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@Builder
@Data
public class AuthUserInfo {
	
	/**
	 * 账号
	 */
	private String identifier;

	/**
	 * 账号类型
	 */
	private Integer identityType;

	private Long userId;

	private String username;

	private Long corpId;

	private String corpName;

	/**
	 * 是否企业管理员 true 是 false 否
	 */
	private boolean corpAdminFlag;

	private Long appId;

	private String appName;
}
