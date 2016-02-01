/**
 * 
 */
package flipcache.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import flipcache.IO.Input;
import flipcache.IO.Output;
import flipcache.service.CacheService;

/**
 * @author bhavesh
 *
 */

@RestController
@RequestMapping(value="/cache", 
method=RequestMethod.POST,
consumes={MediaType.APPLICATION_JSON_VALUE},
produces={MediaType.APPLICATION_JSON_VALUE})
public class ApiController {
	private static final Logger logger = LoggerFactory.getLogger(CacheService.class);

	@Autowired ApplicationContext context;
	@RequestMapping(value = "/add")
	public @ResponseBody Output add(@RequestBody Input input) {
		CacheService cacheService = context.getBean("cacheService", CacheService.class);
		 logger.info("Made it to the add method");
		return cacheService.add(input);
	}
	@RequestMapping(value = "/get")
	public @ResponseBody Output get(@RequestBody Input input) {
		CacheService cacheService = context.getBean("cacheService", CacheService.class);
		return cacheService.get(input);
	}
}

//curl -H "Content-Type: application/json" -X POST -d '{"id":"1","prodName":"xyz","prodDesc":"wfgw"}' http://localhost:8087/cache/add

