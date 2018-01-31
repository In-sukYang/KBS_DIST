/**
 * 
 */
package kr.co.kbs.distribute.common.util;

import java.io.Closeable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *	
 * @Author : yhkim
 * @Date   : 2017. 4. 12.
 */
public class ResourceCloseHelper {
	
	public static final Logger log = LoggerFactory.getLogger(Runnable.class.getName()); 
	
	public static void close(Closeable  ... resources) {
		for (Closeable resource : resources) {
			if (resource != null) {
				try {
					resource.close();
				} catch (Exception ignore) {
					log.error("Occurred Exception to close resource is ingored!!");
				}
			}
		}
	}
}
