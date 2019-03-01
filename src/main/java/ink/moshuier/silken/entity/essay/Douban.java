package ink.moshuier.silken.entity.essay;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import ink.moshuier.silken.entity.Category;
import ink.moshuier.silken.entity.Music;
import ink.moshuier.silken.entity.gallery.item.Figure;
import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.filters.AndFilter;
import org.htmlparser.filters.HasAttributeFilter;
import org.htmlparser.filters.TagNameFilter;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;

import ink.moshuier.silken.entity.Opus;

public class Douban extends Essay {
	//ҳ���������
	private Parser parser;
	private NodeList nodes;
	private Opus opus = new Opus();
	static private final String portraitReg = "(?<=src=\")[^\"]*(?=\")";
	static private final String author_linkReg = "(?<=href=\")[^\"]*(?=\")";
	static private final String authorReg = "(?<=alt=\").*?(?=\")";
	static private final String author_movie_bookReg = "(?<=<span property=\"v:reviewer\">).*?(?=</span>)";
	static private final String author_descReg = "(?<=</a>).*?(?=<br/>)";
	static private final String book_authorReg = "(?<=����:</span>).*?(?=<br/>)";
	static private final String movie_directorReg = "(?<=����:</span>).*?(?=</li>)";
	static private final String movie_castReg = "(?<=����:</span>).*?(?=</li>)";
	static private final String original_nameReg = "(?<=ԭ����:</span>).*?(?=<br/>)";
	static private final String translatorReg = "(?<=����:</span>).*?(?=<br/>)";
	static private final String publishReg = "(?<=������:</span>).*?(?=<br/>)";
	static private final String releaseReg = "(?<=<li>).*?(?=��</li>)";
	public Douban(String url){
		//��ʼ��
		super();//������ڵ�һ��
		setOriginal_flag(false);
		setMusic(new Music(0));
		setLabel("");
		setAuthority(10);
		setDel_flag(false);
		setOriginal_link(url);
		try {
			HttpURLConnection connection = (HttpURLConnection)(new URL(url)).openConnection();
			connection.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
			parser = new Parser(connection);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(url.indexOf("douban.com/note")>=0){
			parseAuthorDetailFromNote();                     
			setCategory(new Category(5008));
		}else if(url.indexOf("movie.douban.com/review")>=0){
			parseAuthorDetailFromMovie();                     
			parseWorksDetailFromMovie();
			setCategory(new Category(5010));
		}else if(url.indexOf("book.douban.com/review")>=0){
			parseAuthorDetailFromBook();    
			parseWorksDetailFromBook();    
			setCategory(new Category(5009));
		}
		parseTitle();
		parseContent();                     
	};
	private String parseTitle(){
		NodeFilter tagFilter = new TagNameFilter("title");
		try {
			parser.reset();
			nodes = parser.extractAllNodesThatMatch(tagFilter);
			title = nodes.toNodeArray()[0].getFirstChild().toPlainTextString().trim();
			System.out.println("title :"+title);
		} catch (ParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return title;
	}
	
	private String parseContent(){
			NodeFilter tagFilter = new TagNameFilter("div");
			NodeFilter attrFilter = new HasAttributeFilter("id","link-report");
			NodeFilter filter = (NodeFilter) new AndFilter(tagFilter, attrFilter);
			try {
				parser.reset();//����һ�κ���Ҫreset��λ
				content = parser.extractAllNodesThatMatch(filter).toNodeArray()[0].toHtml().replaceAll("((\t\n)|(\n\t)|\n|\t)", "");
			} catch (ParserException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return content;
	}
	

private String parseAuthorDetailFromNote(){
	NodeFilter tagFilter = new TagNameFilter("div");
	NodeFilter attrFilter = new HasAttributeFilter("class","mod mod-usercard");
	NodeFilter filter = (NodeFilter) new AndFilter(tagFilter, attrFilter);
	try {
		parser.reset();
		nodes = parser.extractAllNodesThatMatch(filter);
		String author_detail = nodes.toNodeArray()[0].toHtml();
		Matcher matcher = Pattern.compile(portraitReg).matcher(author_detail);
		if(matcher.find()){
			portrait = matcher.group();
		}
		  matcher = Pattern.compile(author_linkReg).matcher(author_detail);
		  if(matcher.find()){
			  author_link = matcher.group();
		  }
		  
		  matcher = Pattern.compile(authorReg).matcher(author_detail);
		  if(matcher.find()){
			  author = matcher.group();
		  }
		  
		  matcher = Pattern.compile(author_descReg).matcher(author_detail);
		  if(matcher.find()){
			  author_desc = matcher.group();
		  }
		
		System.out.println("author_link :"+author_link);
		System.out.println("author :"+author);
		System.out.println("portrait :"+portrait);
		System.out.println("author_desc :"+author_desc);
	} catch (ParserException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return author;
}
private String parseAuthorDetailFromMovie(){
	NodeFilter tagFilter = new TagNameFilter("div");
	NodeFilter attrFilter = new HasAttributeFilter("class","main-hd");
	NodeFilter filter = (NodeFilter) new AndFilter(tagFilter, attrFilter);
	try {
		parser.reset();
		nodes = parser.extractAllNodesThatMatch(filter);
		String author_detail = nodes.toNodeArray()[0].toHtml();
		Matcher matcher = Pattern.compile(portraitReg).matcher(author_detail);
		if(matcher.find()){
			portrait = matcher.group();
		}
		matcher = Pattern.compile(author_linkReg).matcher(author_detail);
		if(matcher.find()){
			author_link = matcher.group();
		}
		
		matcher = Pattern.compile(author_movie_bookReg).matcher(author_detail);
		if(matcher.find()){
			author = matcher.group();
		}
		
		matcher = Pattern.compile(author_descReg).matcher(author_detail);
		if(matcher.find()){
			author_desc = matcher.group();
		}
		
		System.out.println("author_link :"+author_link);
		System.out.println("author :"+author);
		System.out.println("portrait :"+portrait);
		System.out.println("author_desc :"+author_desc);
	} catch (ParserException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return author;
}

private String parseAuthorDetailFromBook(){
	NodeFilter tagFilter = new TagNameFilter("div");
	NodeFilter attrFilter = new HasAttributeFilter("class","piil");
	NodeFilter filter = (NodeFilter) new AndFilter(tagFilter, attrFilter);
	try {
		parser.reset();
		nodes = parser.extractAllNodesThatMatch(filter);
		String author_detail = nodes.toNodeArray()[0].toHtml();
		Matcher matcher = Pattern.compile(portraitReg).matcher(author_detail);
		if(matcher.find()){
			portrait = matcher.group();
		}
		matcher = Pattern.compile(author_linkReg).matcher(author_detail);
		if(matcher.find()){
			author_link = matcher.group();
		}
		
		tagFilter = new TagNameFilter("span");
		attrFilter = new HasAttributeFilter("class","pl2");
		filter = (NodeFilter) new AndFilter(tagFilter, attrFilter);
		parser.reset();
		nodes = parser.extractAllNodesThatMatch(filter);
		author_detail = nodes.toNodeArray()[0].toHtml();
		matcher = Pattern.compile(author_movie_bookReg).matcher(author_detail);
		if(matcher.find()){
			author = matcher.group();
		}
		
		matcher = Pattern.compile(author_descReg,Pattern.DOTALL).matcher(author_detail);
		if(matcher.find()){
			author_desc = matcher.group().trim();
		}
		
		System.out.println("author_link :"+author_link);
		System.out.println("author :"+author);
		System.out.println("portrait :"+portrait);
		System.out.println("author_desc :"+author_desc);
	} catch (ParserException e) {		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return author;
}

private String parseWorksDetailFromBook(){
	NodeFilter tagFilter = new TagNameFilter("div");
	NodeFilter attrFilter = new HasAttributeFilter("class","indent subject-info");
	NodeFilter filter = (NodeFilter) new AndFilter(tagFilter, attrFilter);
	try {
		parser.reset();
		nodes = parser.extractAllNodesThatMatch(filter);
		String works_detail = nodes.toNodeArray()[0].toHtml();
		Matcher matcher = Pattern.compile(portraitReg).matcher(works_detail);
		if(matcher.find()){
			opus.cover = matcher.group();
		}
		matcher = Pattern.compile(authorReg).matcher(works_detail);
		if(matcher.find()){
			opus.name = matcher.group();
		}
		matcher = Pattern.compile(book_authorReg).matcher(works_detail);
		if(matcher.find()){
			opus.author_directior = new Figure();//matcher.group();
		}
		matcher = Pattern.compile(original_nameReg).matcher(works_detail);
		if(matcher.find()){
			opus.name = null;//matcher.group();
		}
		matcher = Pattern.compile(translatorReg).matcher(works_detail);
		if(matcher.find()){
			label = "���ߣ�" + matcher.group() + ";" + label;
		}
		matcher = Pattern.compile(publishReg).matcher(works_detail);
		if(matcher.find()){
			opus.publish_date = matcher.group();
		}
		
		System.out.println("cover :"+opus.cover);
		System.out.println("works_name :"+opus.name);
		System.out.println("author_directior :"+opus.author_directior);
		System.out.println("publish_date :"+opus.publish_date);
	} catch (ParserException e) {		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return author;
}
private String parseWorksDetailFromMovie(){
	NodeFilter tagFilter = new TagNameFilter("div");
	NodeFilter attrFilter = new HasAttributeFilter("id","movie-summary");
	NodeFilter filter = (NodeFilter) new AndFilter(tagFilter, attrFilter);
	try {
		parser.reset();
		nodes = parser.extractAllNodesThatMatch(filter);
		String works_detail = nodes.toNodeArray()[0].toHtml();
		Matcher matcher = Pattern.compile(portraitReg).matcher(works_detail);
		if(matcher.find()){
			opus.cover = matcher.group();
		}
		matcher = Pattern.compile(authorReg).matcher(works_detail);
		if(matcher.find()){
			opus.name = matcher.group();
		}
		matcher = Pattern.compile(movie_directorReg,Pattern.DOTALL).matcher(works_detail);
		if(matcher.find()){
			opus.author_directior = null;matcher.group().trim();
		}
		matcher = Pattern.compile(movie_castReg,Pattern.DOTALL).matcher(works_detail);
		if(matcher.find()){
			opus.protagonists = null;matcher.group().trim();
		}
		matcher = Pattern.compile(releaseReg).matcher(works_detail);
		if(matcher.find()){
			opus.publish_date = matcher.group() + "��";
		}
		
		System.out.println("cover :"+opus.cover);
		System.out.println("works_name :"+opus.name);
		System.out.println("author_directior :"+opus.author_directior);
		System.out.println("protagonists :"+opus.protagonists);
		System.out.println("publish_date :"+opus.publish_date);
	} catch (ParserException e) {		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return author;
}

	public Opus getOpus() {
	return opus;
	}
	public void setOpus(Opus opus) {
	this.opus = opus;
	}
	public String toString(){
		return "id: " + id + "\n" + "title: " + title + "\n" + "subtitle: " + subtitle + "\n" + "profile: " + portrait + "\n" + "author: " + author + "\n" + "portrait: " + portrait
				+ "\n" + "author_link: " + author_link + "\n" + "author_desc: " + author_desc + "\n" + "label: " + label + "\n" + "category: " + category + "\n" + "id: " + id + "\n" + "read_cnt: " + read_cnt
				+ "\n" + "favor_cnt: " + favor_cnt + "\n" + "original_flag: " + original_flag + "\n" + "original_link: " + original_link + "\n" + "create_date: " + create_date
				+ "\n" + "create_time: " + create_time + "\n" + "del_flag: " + del_flag;
	}
}
