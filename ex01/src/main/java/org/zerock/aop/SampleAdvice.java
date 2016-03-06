package org.zerock.aop;


import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.zerock.controller.BoardController;



@Component
@Aspect
public class SampleAdvice {

	private static final Logger logger = LoggerFactory.getLogger(SampleAdvice.class);
	
	@Before("execution(* org.zerock.service.MessageService*.*(..))")
	public void startLog(JoinPoint jp){
		logger.info("-------------------------------");
		logger.info("-------------------------------");
		//전달되는 모든 파라미터를 가져 올수 있다.
		logger.info(Arrays.toString(jp.getArgs()));
	}
	
	@Around("execution(* org.zerock.service.MessageService*.*(..))")
	public Object timeLog(ProceedingJoinPoint pjp) throws Throwable{
		long startTime = System.currentTimeMillis();
		logger.info(Arrays.toString(pjp.getArgs()));
		
		Object result = pjp.proceed();
		
		long endTime = System.currentTimeMillis();
		logger.info(pjp.getSignature().getName()+":"+(endTime-startTime));
		logger.info("=======================================================");
		return result;
	}
}
