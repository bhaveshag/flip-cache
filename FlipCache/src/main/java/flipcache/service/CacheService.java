/**
 * 
 */
package flipcache.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import flipcache.IO.Input;
import flipcache.IO.Output;

/**
 * @author bhavesh
 *
 */
@Service("cacheService")
@ConfigurationProperties(prefix = "Cache")
public class CacheService {
	private static final Logger logger = LoggerFactory.getLogger(CacheService.class);
	
	@Autowired ApplicationContext context;
	
	@Value("${Cache.mechanism}")
    private String mechanism;
	
	//Must override methods
	public Output addToCache(Input input){
		return new Output();
	}
	public Output getFromCache(Input input){
		return new Output();
	}
	
	public Output add(Input input){ 
		logger.info("Adding input to cache!!");
		CacheService cacheService = context.getBean(mechanism.toLowerCase()+"CacheService", CacheService.class);
		cacheService.addToCache(input);
		return null;
	}
	public Output get(Input input){
		CacheService cacheService = context.getBean(mechanism.toLowerCase()+"CacheService", CacheService.class);
		cacheService.getFromCache(input);
		logger.info("returned output to cache!!");
		return null;
	}
	
}
