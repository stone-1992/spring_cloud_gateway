package com.stone.common.async.config;

import com.stone.common.async.properties.AsyncProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.AsyncConfigurerSupport;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.lang.reflect.Method;
import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @classname AsyncAutoConfiguration
 * @description 异步执行器自动配置
 * @date 2020/3/20 9:07
 */
@Slf4j
@EnableAsync
@Configuration
@Import(AsyncProperties.class)
public class AsyncAutoConfiguration extends AsyncConfigurerSupport {

    @Autowired
    private AsyncProperties asyncProperties;

    @Bean
    public Executor asyncExecutor() {
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setMaxPoolSize(asyncProperties.getMaxPoolSize());
        taskExecutor.setCorePoolSize(asyncProperties.getCorePoolSize());
        taskExecutor.setQueueCapacity(asyncProperties.getQueueCapacity());
        taskExecutor.setThreadNamePrefix(asyncProperties.getThreadNamePrefix());
        taskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        taskExecutor.initialize();
        return taskExecutor;
    }

    @Bean
    public ThreadPoolTaskExecutor threadPoolExecutor() {
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setMaxPoolSize(asyncProperties.getMaxPoolSize());
        taskExecutor.setCorePoolSize(asyncProperties.getCorePoolSize());
        taskExecutor.setQueueCapacity(asyncProperties.getQueueCapacity());
        taskExecutor.setThreadNamePrefix(asyncProperties.getThreadNamePrefix());
        taskExecutor.setRejectedExecutionHandler(new CustomRejectedExecutionHandler());
        taskExecutor.initialize();
        return taskExecutor;
    }

    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return new SpringAsyncExceptionHandler();
    }

    /**
     * 异步任务异常处理器
     */
    public static class SpringAsyncExceptionHandler implements AsyncUncaughtExceptionHandler {
        @Override
        public void handleUncaughtException(Throwable ex, Method method, Object... params) {
            log.error("异步任务异常:{} {}  {}",ex,ex.getStackTrace(),ex.getMessage());
            log.error("method:{}  ",method);
            log.error("params:{}  ",params);
        }
    }

    /**
     * 异步任务异常处理器
     */
    public static class CustomRejectedExecutionHandler implements RejectedExecutionHandler {
        private static final long SLEEP_MILLIS = 100L;
        public CustomRejectedExecutionHandler() {
        }
        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
            log.error(Thread.currentThread().getName() + ":因线程池和缓存队列已满,任务被拒绝,休眠" + 100L + ",尝试重新加入任务队列");
            try {
                Thread.sleep(100L);
                executor.getQueue().put(r);
            } catch (InterruptedException var4) {
                log.error(var4.getMessage(), var4);
            }
        }
    }
}
