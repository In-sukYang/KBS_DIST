package kr.co.kbs.distribute.common.exception;

import java.lang.reflect.Method;

import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;

import kr.co.kbs.distribute.common.service.CommonService;

public class CustomAsyncExceptionHandler implements AsyncUncaughtExceptionHandler, CommonService{

	@Override
	public void handleUncaughtException(Throwable arg0, Method arg1, Object... arg2) {
		log.error("Exception Message == {}" , arg0.getMessage() );
		log.error("Method Name == {}", arg1.getName());
		
		for(Object param : arg2) {
			log.error("parameter value == {}", param);
		}
		
	}

}
