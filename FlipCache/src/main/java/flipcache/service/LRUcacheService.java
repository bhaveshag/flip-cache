/**
 * 
 */
package flipcache.service;

import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;

import flipcache.IO.Input;
import flipcache.IO.Output;

/**
 * @author bhavesh
 *
 */
@Service("lruCacheService")
@ConfigurationProperties(prefix = "LRU")
public class LRUcacheService extends CacheService{

	private static final Logger logger = LoggerFactory.getLogger(LRUcacheService.class);

	@Value("${LRU.capacity}")
	private int capacity;

	class Node {
		String key;
		Input value;
		Node pre;
		Node next;

		public Node(String key, Input value) {
			this.key = key;
			this.value = value;
		}
	}

	HashMap<String, Node> map = new HashMap<String, Node>();
	Node head = null;
	Node end = null;

	public Input get(String key) {
		if (map.containsKey(key)) {
			Node n = map.get(key);
			remove(n);
			setHead(n);
			return n.value;
		}

		return null;
	}

	public void remove(Node n) {
		if (n.pre != null) {
			n.pre.next = n.next;
		} else {
			head = n.next;
		}

		if (n.next != null) {
			n.next.pre = n.pre;
		} else {
			end = n.pre;
		}

	}

	public void setHead(Node n) {
		n.next = head;
		n.pre = null;

		if (head != null)
			head.pre = n;

		head = n;

		if (end == null)
			end = head;
	}

	public void set(String key, Input value) {
		if (map.containsKey(key)) {
			Node old = map.get(key);
			old.value = value;
			remove(old);
			setHead(old);
		} else {
			Node created = new Node(key, value);
			if (map.size() >= capacity) {
				map.remove(end.key);
				remove(end);
				setHead(created);

			} else {
				setHead(created);
			}

			map.put(key, created);
		}
	}
	@Override
	public Output addToCache(Input input) {
		try {
			logger.info("Adding new entry to the cache: " + input.getId());
			this.set(input.getId(), input);
			Output output = new Output();
			output.setSuccess(true);
			output.setProdName(input.getProdName());
			output.setProdDesc(input.getProdDesc());
			return output;
		} catch (Exception e) {
			logger.error("Failed: ", e);
			return new Output();
		} finally {
			logger.info("Finishing Adding cache!!");
		}
	}
	@Override
	public Output getFromCache(Input input) {
		try {
			logger.info("Adding new entry to the cache: " + input.getId());
			Input out = this.get(input.getId());
			Output output = new Output();
			output.setSuccess(true);
			output.setProdName(out.getProdName());
			output.setProdDesc(out.getProdDesc());
			return output;
		} catch (Exception e) {
			logger.error("Failed: ", e);
			return new Output();
		} finally {
			logger.info("Finishing Adding cache!!");
		}
	}
}
