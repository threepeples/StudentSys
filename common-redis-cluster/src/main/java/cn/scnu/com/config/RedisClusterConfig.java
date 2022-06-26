package cn.scnu.com.config;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import lombok.Data;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
/**
 * @ClassName RedisClusterConfig
 * @Description
 * @date 2022/6/26 18:22
 * @Version 1.0
 * @Author HJW
 */

@Configuration
@Data
@ConfigurationProperties(prefix="spring.redis.cluster")
public class RedisClusterConfig {
    private List<String> nodes;
    private Integer maxTotal;
    private Integer maxIdle;
    private Integer minIdle;
    //初始化JedisCluster的方法
    @Bean
    public JedisCluster initJedisCluster(){
        //收集节点信息
        Set<HostAndPort> set=new HashSet<HostAndPort>();
        for (String node : nodes) {
            //node="192.168.243.133:8000"
            String host=node.split(":")[0];
            Integer port=Integer.
                    parseInt(node.split(":")[1]);
            set.add(new HostAndPort(host,port));
        }
        GenericObjectPoolConfig config=new GenericObjectPoolConfig();
        config.setMaxTotal(maxTotal);
        config.setMaxIdle(maxIdle);
        config.setMinIdle(minIdle);
        return new JedisCluster(set,config);
    }


}