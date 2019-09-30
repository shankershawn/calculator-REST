/**
 * 
 */
package com.shankarsan.calculator.interceptor;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.validation.BindingResult;

import com.shankarsan.calculator.dto.BaseOutputDTO;
import com.shankarsan.constants.CommonConstants;
import com.shankarsan.exception.ServiceException;

/**
 * @author SHANKARSAN
 *
 */

@Configuration
@Aspect
public class CalculatorInterceptor {
	
	private static final Logger _logger = LoggerFactory.getLogger(CalculatorInterceptor.class);
	
	@Autowired
	private Environment environment;
	
	@Around(value = "execution(* com.shankarsan.calculator.service.*.*(..))")
	public Object process(ProceedingJoinPoint proceedingJoinPoint) {
		BaseOutputDTO baseOutputDto = null;
		try {
			baseOutputDto = (BaseOutputDTO)((MethodSignature)proceedingJoinPoint.getSignature()).getReturnType().newInstance();
			if(((BindingResult)proceedingJoinPoint.getArgs()[1]).hasErrors()) {
				throw new ServiceException(environment.getProperty(CommonConstants.GENERIC_VALIDATION_FAILURE_MESSAGE));
			}
			baseOutputDto = (BaseOutputDTO)proceedingJoinPoint.proceed();
			baseOutputDto.setMessage(environment.getProperty(CommonConstants.GENERIC_SUCCESS_MESSAGE));
		} catch (ServiceException e) {
			_logger.error(e.getMessage(), e);
			baseOutputDto.setMessage(e.getMessage());
		} catch (Throwable t) {
			_logger.error(t.getMessage(), t);
			baseOutputDto.setMessage(environment.getProperty(CommonConstants.GENERIC_ERROR_MESSAGE));
		}
		return baseOutputDto; 
	}
	
	@Before(value = "execution(* com.shankarsan.calculator.service.*.*(..))")
	public void logBefore(JoinPoint joinPoint) {
		_logger.info("Entering " + joinPoint.getSignature().getName() + " method.");
	}
	
	@After(value = "execution(* com.shankarsan.calculator.service.*.*(..))")
	public void logAfter(JoinPoint joinPoint) {
		_logger.info("Exiting " + joinPoint.getSignature().getName() + " method.");
	}

}
