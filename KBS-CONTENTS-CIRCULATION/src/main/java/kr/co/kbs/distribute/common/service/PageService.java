package kr.co.kbs.distribute.common.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public interface PageService {
	public static final Logger log = LoggerFactory.getLogger(Runnable.class.getName());
	public int getTotalRecords() throws Exception;
}
