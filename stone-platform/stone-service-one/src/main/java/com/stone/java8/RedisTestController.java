package com.stone.java8;


import com.stone.core.util.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import model.exption.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.stone.redis.lock.DistributedLock;

@Api(tags = "Redis测试接口")
@RequestMapping("/redisTest")
@RestController
public class RedisTestController {

	@Autowired
	private RedisTemplate<String, Object> redisTemplateNew;

	@Autowired
	private DistributedLock lock;

	// Redis key
	String REDIS_KEY = "bp:test";

	@ApiOperation(value = "设置Redis测试接口")
	@GetMapping("set")
	public R<String> setRedisTest() {
		ValueOperations<String, Object> stringObjectValueOperations = redisTemplateNew.opsForValue();
		Object o = stringObjectValueOperations.get(REDIS_KEY);
		if(o == null){
			o = "test";
		}
		stringObjectValueOperations.set(REDIS_KEY, o);
		return R.ok();
	}


	@ApiOperation(value = "查询Redis测试接口")
	@GetMapping("get")
	public R<Object> getRedisTest() {
		ValueOperations<String, Object> stringObjectValueOperations = redisTemplateNew.opsForValue();
		Object o = stringObjectValueOperations.get(REDIS_KEY);
		return R.ok(o);
	}

	@ApiOperation(value = "redis 分布式锁")
	@GetMapping("lock")
	public R<String> testLock(){
		// 加锁
		boolean lock = this.lock.lock("bp:site", 3);
		if(!lock){
			throw new BusinessException("系统繁忙，请稍后再重试");
		}
		try {
			// 业务逻辑代码
			Thread.sleep(15000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			// 释放锁
			this.lock.releaseLock("bp:site");
		}
		return R.ok("操作成功");
	}
	
	
}
