package com.zxtech.platform.spring.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 用于获得方法执行时间的切片
 * 
 * @author wangyc
 */

@Aspect
@Component
public class ExcutionTimeAspect {
	private static Logger log = LoggerFactory.getLogger(ExcutionTimeAspect.class);

	@Around(value = "execution(* com.zxtech.ess.module.**.mapper.*Mapper.*(..))")
	public Object recordExcutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
		Signature signature = joinPoint.getSignature();
		String clazzName = signature.getDeclaringTypeName();
		String methodName = signature.getName();

		long startTime = System.currentTimeMillis();

		Object result = joinPoint.proceed(); // continue on the intercepted

		log.debug("@@Excution Time@@[" + clazzName + "##" + methodName + "]@@"
				+ (System.currentTimeMillis() - startTime));
		return result;
	}
}
