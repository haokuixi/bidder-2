/**
 * 
 */
package com.bharatmehta.bannerconsumer.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

/**
 * @author Bharat.Mehta
 *
 */
@Component
@Aspect
public class ExecutionTimeAspect {

	private static final Logger LOGGER = LoggerFactory.getLogger(ExecutionTimeAspect.class);
	
	@Around("@annotation(com.bharatmehta.contactmgr.aspect.TimeTrack) || execution(* com.bharatmehta.contactmgr.aspect.TimeTrack+.*(..))")
	public Object calulateExecutionTime(ProceedingJoinPoint pjp) throws Throwable {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        Object retVal = pjp.proceed();
        stopWatch.stop();
        LOGGER.info("Executed {}  in  {} milliseconds",pjp.toShortString(),stopWatch.getTotalTimeMillis() );
        return retVal;
    }
	
		
}
