package com.zhcx.business.common.dubbo.filter;

import com.alibaba.dubbo.common.Constants;
import com.alibaba.dubbo.common.extension.Activate;
import com.alibaba.dubbo.rpc.*;
import core.util.JsonUtils;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import com.zhcx.business.common.dubbo.constant.DubboConstant;
import model.dto.AuthUserInfoDTO;

/**
 * dubbo 消费者拦截器
 * @author zfq
 * @version 1.0
 * @Description
 * @date 2020年04月10日　18:15
 */
@Slf4j
@Activate(order = 26, group = Constants.CONSUMER)
public class DubboConsumerFilter implements Filter {

	@Override
	@SneakyThrows
	public Result invoke(Invoker<?> invoker, Invocation invocation){
		// 获取用户信息
		// AuthUserInfoDTO userInfoDTO = UserAuthContextHolder.get();
		AuthUserInfoDTO userInfoDTO = new AuthUserInfoDTO();
		RpcContext.getContext().setAttachment(DubboConstant.DUBBO_USER_INFO, JsonUtils.objectMapper.writeValueAsString(userInfoDTO));
		try {
			return invoker.invoke(invocation);
		}finally {
			// 移除dubbo绑定变量
			RpcContext.getContext().clearAttachments();
		}
	}

}
