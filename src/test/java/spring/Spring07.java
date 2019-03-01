package spring;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.common.TemplateParserContext;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import ink.moshuier.silken.common.IPParser;
import ink.moshuier.silken.common.Log;
import ink.moshuier.silken.entity.Note;
import ink.moshuier.silken.entity.Opus;
import ink.moshuier.silken.service.NoteService;
import ink.moshuier.silken.service.impl.NoteServiceImpl;

public class Spring07 {

	@SuppressWarnings({ "resource", "static-access", "rawtypes", "unused" })
	public static void main(String[] args) {
		AbstractApplicationContext  ctx = new FileSystemXmlApplicationContext("C:/Users/Administrator/git/iBlog/WebContent/WEB-INF/applicationContext.xml");
		NoteService ns = ctx.getBean("noteService" ,NoteServiceImpl.class);
		Note note = ns.get(0);
		Log.print("note name", note.getCreate_date());
}

}
