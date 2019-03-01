package ink.moshuier.silken.aspect;

import org.aspectj.lang.annotation.Pointcut;

public class ServicePointcut {
	@Pointcut("execution(* ink.moshuier.silken.service.impl.*.*(..))")
	public void allServices(){}
	@Pointcut("execution(* ink.moshuier.silken.service.impl.NoteServiceImpl.*(..))")
	public void testServices(){}
}
