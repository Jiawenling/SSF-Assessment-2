package com.example.Library.repo;

import com.example.Library.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {
//    private static final Logger logger = LoggerFactory.getLogger(RedisConfig.class);
//
//    @Value("${spring.redis.host}")
//    private String redisHost;
//
//    @Value("${spring.redis.port}")
//    private Integer redisPort;
//
//    private String redisPassword;
//
//
//    @Bean
//    @Scope("singleton")
//    public RedisTemplate<String, String> redisTemplate(){
//        final RedisStandaloneConfiguration config
//                = new RedisStandaloneConfiguration();
//        redisPassword = System.getenv(Constants.ENV_REDIS_KEY);
//        config.setHostName(redisHost);
//        config.setPort(redisPort);
//        config.setPassword(redisPassword);
//
//        final JedisClientConfiguration jedisClient = JedisClientConfiguration
//                .builder().build();
//        final JedisConnectionFactory jedisFac =
//                new JedisConnectionFactory(config, jedisClient); jedisFac.afterPropertiesSet();
//
//        RedisTemplate<String, String> template = new RedisTemplate<>();
//        template.setConnectionFactory(jedisFac);
////                template.setExposeConnection(true);
//        template.setKeySerializer(new StringRedisSerializer());
////                RedisSerializer<Object> serializer = new JdkSerializationRedisSerializer(getClass().getClassLoader());
//        template.setValueSerializer(new StringRedisSerializer());
//        template.setHashKeySerializer(new StringRedisSerializer());
//        template.setHashValueSerializer(new StringRedisSerializer());
//
//
//        return template;
//    }
}
