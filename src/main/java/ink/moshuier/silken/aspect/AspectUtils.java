package ink.moshuier.silken.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import ink.moshuier.silken.common.Log;
import ink.moshuier.silken.entity.Note;

//@Aspect
public class AspectUtils {
	@Before("ServicePointcut.testServices()")
	public void authority() {
	Log.print("@Before", "Ȩ�޼��");
	}
	
	@After("ServicePointcut.testServices()")
	public void backUp() {
		Log.print("@After", "�ͷ���Դ");
	}
	
	@Around("ServicePointcut.testServices()")
	public void processTx(ProceedingJoinPoint jp) {
		Object[] args = jp.getArgs();
				Log.print("@Around", "��ʼ");
		try {
			Object rvt = jp.proceed(args);
			Log.print("@Around", "����");
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
	
	@AfterReturning(returning = "rvt",pointcut = "ServicePointcut.testServices()")
	public void log(Object rvt) {
		if(rvt instanceof Note){
			Log.print("@AfterReturning",((Note)rvt).getCreate_date());
			
		}
	}
	
	@AfterThrowing(throwing = "ex",pointcut = "ServicePointcut.testServices()")
	public void fix(Throwable ex) {
		Log.print("@AfterThrowing",ex.getMessage());
	}
}
