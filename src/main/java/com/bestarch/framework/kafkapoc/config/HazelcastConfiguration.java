package com.bestarch.framework.kafkapoc.config;

import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.hazelcast.config.Config;
import com.hazelcast.config.EvictionPolicy;
import com.hazelcast.config.MapConfig;
import com.hazelcast.config.MaxSizeConfig;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.spring.cache.HazelcastCacheManager;

/**
 * 
 * @author bestarch
 *
 */
@Configuration
public class HazelcastConfiguration {

	@Bean
	public Config hazelCastConfig() {
		return new Config()
				.setInstanceName("dev")
				.addMapConfig(new MapConfig().setName("request")
				.setMaxSizeConfig(new MaxSizeConfig(200, MaxSizeConfig.MaxSizePolicy.FREE_HEAP_SIZE))
				.setEvictionPolicy(EvictionPolicy.LRU).setTimeToLiveSeconds(60));
	}
	
	@Bean
    CacheManager cacheManager() {
        return new HazelcastCacheManager(hazelcastInstance());
    }


    @Bean
    HazelcastInstance hazelcastInstance() {
        return Hazelcast.newHazelcastInstance(hazelCastConfig());
    }

}
