package com.stone.common.dubbo.filter;

import com.alibaba.dubbo.common.Constants;
import com.alibaba.dubbo.common.extension.Activate;
import com.alibaba.dubbo.rpc.*;
import com.stone.core.util.JsonUtils;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import com.stone.common.dubbo.constant.DubboConstant;
import model.context.UserAuthContextHolder;
import model.dto.AuthUserInfoDTO;

/**
 * dubbo 提供者拦截器
 * @version 1.0
 * @Description
 * @date 2020年04月10日　18:15
 */
@Slf4j
@Activate(order = 25, group = Constants.PROVIDER)
public class DubboProviderFilter implements Filter {

	@Override
	@SneakyThrows
	public Result invoke(Invoker<?> invoker, Invocation invocation) {
		// 从dubbo上下文获取用户信息
		String dubboUserInfo = RpcContext.getContext().getAttachment(DubboConstant.DUBBO_USER_INFO);
		AuthUserInfoDTO userInfo = JsonUtils.objectMapper.readValue(dubboUserInfo,AuthUserInfoDTO.class);
		UserAuthContextHolder.set(userInfo);
		try{
			return invoker.invoke(invocation);
		}finally {
			// 整个dubbo调用链完成后，及时清理线程变量
			UserAuthContextHolder.remove();
		}
	}

}
