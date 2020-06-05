数据源配置示例：
spring:
  #主数据源
  datasource:
    type: com.alibaba.druid.pool.DruidDataSource
    driver-class-name: com.mysql.cj.jdbc.Driver
    druid:
      url: jdbc:mysql://172.16.102.107:3388/a_commons_db?zeroDateTimeBehavior=convertToNull&useUnicode=true&useSSL=true&characterEncoding=utf-8&serverTimezone=Asia/Shanghai
      username: test
      password: test123
      filter:
        wall:
          config:
            multi-statement-allow: true
##多数据源的配置
#dynamic:
#  datasource:
#    slave1:
#      driver-class-name: com.mysql.cj.jdbc.Driver
#      url: jdbc:mysql://172.16.102.107:3388/qr_cxpay?zeroDateTimeBehavior=convertToNull&useUnicode=true&useSSL=true&characterEncoding=utf-8&serverTimezone=Asia/Shanghai
#      username: test
#      password: test123
#    slave2:
#      driver-class-name: com.mysql.cj.jdbc.Driver
#      url: jdbc:mysql://172.16.102.107:3388/wisdombus2.0_bus_time?zeroDateTimeBehavior=convertToNull&useUnicode=true&useSSL=true&characterEncoding=utf-8&serverTimezone=Asia/Shanghai
#      username: test
#      password: test123   


数据源切换：
    默认不加注解使用主数据源
    
    Service层方法加 @DataSource(value = "slave1") 即可指定使用的数据源     