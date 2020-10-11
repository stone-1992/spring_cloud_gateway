# spring boot 结合 redis 哨兵模式配置
spring:
  redis:
    sentinel:
      master: mymaster
      nodes: 192.168.180.132:26379,192.168.180.132:26380,192.168.180.132:26381
    port: 6379
    database: 4