package ink.moshuier.silken.entity.essay;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import ink.moshuier.silken.common.Log;
import ink.moshuier.silken.common.Spider;
import ink.moshuier.silken.entity.Category;
import ink.moshuier.silken.entity.Music;

public class Zhihu extends Essay {
	//ҳ���������
	private String html;
	static private final String contentReg = "(?<=<div class=\" zm-editable-content clearfix\">).*?(?=<a class=\"zg-anchor-hidden)";
	static private final String titleReg = "<a\\s*href=\"/question/\\d{8}\"\\s*>[^<]+</a>";
	static private final String subtitleReg = "(?<=<textarea class=\"content hidden\">).*(?=</textarea>)";
	static private final String labelReg = "<a[^>]*class=\"zm-item-tag\"[^>]*>[^<]*</a>";
	static private final String synopsisReg = "(?<=<h3 class=\"zm-item-answer-author-wrap\">).*(?=</h3>)";
	static private final String author_linkReg = "(?<=href=\")[^\"]*(?=\")";
	static private final String portraitReg = "(?<=src=\")[^\"]*(?=\")";
	static private final String author_descReg = "(?<=title=\")[^\"]*(?=\")";
	static private final String authorReg = "(?<=>)[^<>]*(?=</a>��)";
	static private final String profileReg = "<img.+?src\\s*=\\s*\"[^\"]+\"[^>]*>";
	static private final String srcReg = "(?<=src=\")[^\"]*(?=\")";
	static private final String removeSrcReg = "src\\s*=\\s*\"//s1\\.zhimg\\.com/misc/whitedot\\.jpg\"";
	static private final String removeOriginalReg = "data-original\\s*=\\s*\"[^\"]+\"";
	
	public Zhihu(String url){
		//��ʼ��
		super();//������ڵ�һ��
		setCategory(new Category(5001));
		setOriginal_flag(false);
		setMusic(new Music(0));
		setAuthority(10);
		setDel_flag(false);
		html = Spider.SendGet(url);
		//��ʼ����Ա����
		setOriginal_link(url);
		parseContent();
		parseSubtitle();
		parseTitle();
		parseLabel();
		parseSynopsis();
	};
	
	public String parseContent(){
		Pattern pattern = Pattern.compile(contentReg);
		Matcher matcher = pattern.matcher(html);
		if(matcher.find())
		{
			content = matcher.group();
			html.replaceAll(content, "");
		}
		html.replaceAll(contentReg, "");
		content = content.substring(0, content.lastIndexOf("</div>"));
		content = content.substring(0, content.lastIndexOf("</div>"));
		content = content.replaceAll("(?<=<noscript>).*?(?=</noscript>)", "");
		content = content.replaceAll("<noscript></noscript>", "");
		content = content.replaceAll(removeSrcReg, "");
		content = content.replaceAll(removeOriginalReg, "");
		content = content.replaceAll("data-actualsrc", "src");
		matcher = Pattern.compile(profileReg).matcher(content);
		String imgTag = null;
		if(matcher.find())
		{
		imgTag = matcher.group();
		matcher = Pattern.compile(srcReg).matcher(imgTag);;
		if(matcher.find())
		{
			portrait = matcher.group();
		}
		}
		Log.print("content", content);
		Log.print("profile", portrait);
		return content;
	}
	
	public String parseTitle(){
		Pattern pattern = Pattern.compile(titleReg);
		Matcher matcher = pattern.matcher(html);
		matcher.find();
		title = getInnerHtml( matcher.group());
		Log.print("title", title);
		return title;
	}
	
	public String parseSubtitle(){
		Pattern pattern = Pattern.compile(subtitleReg);
		Matcher matcher = pattern.matcher(html);
		if(matcher.find())
		{
			subtitle = matcher.group();
			html.replaceAll(subtitle, "");
		}
		Log.print("subtitle", subtitle);
		return subtitle;
	}
	
	public String parseLabel(){
		Pattern pattern = Pattern.compile(labelReg);
		Matcher matcher = pattern.matcher(html);
		label = "";
		for(;matcher.find();)
		{
			label =  getInnerHtml(matcher.group()) + ";" + label;
		}
		Log.print("label", label);
		return label;
	}
	
	public String parseSynopsis(){
		Pattern pattern = Pattern.compile(synopsisReg);
		Matcher matcher = pattern.matcher(html);
		matcher.find();
		String synopsis = matcher.group();
		//�������
		matcher = Pattern.compile(authorReg).matcher(synopsis);
		matcher.find();
		author = (matcher.group()).trim();
		//���ͷ��
		matcher = Pattern.compile(portraitReg).matcher(synopsis);
		matcher.find();
		portrait = matcher.group();
		Log.print("portrait", portrait);
		//��ü��
		matcher = Pattern.compile(author_descReg).matcher(synopsis);
		if(matcher.find())
		{
		author_desc = matcher.group();
		}
		Log.print("author_desc", author_desc);
		//�������
		matcher = Pattern.compile(author_linkReg).matcher(synopsis);
		matcher.find();
		author_link = "http://www.zhihu.com" + matcher.group();
		Log.print("author_link", author_link);
		return synopsis;
	}
	
	public String getInnerHtml(String node){
		return node.substring(node.indexOf(">")+1, node.lastIndexOf("<"));
	}

	public String toString(){
		return "id: " + id + "\n" + "title: " + title + "\n" + "subtitle: " + subtitle + "\n" + "profile: " + portrait + "\n" + "author: " + author + "\n" + "portrait: " + portrait
				+ "\n" + "author_link: " + author_link + "\n" + "author_desc: " + author_desc + "\n" + "label: " + label + "\n" + "category: " + category + "\n" + "id: " + id + "\n" + "read_cnt: " + read_cnt
				+ "\n" + "favor_cnt: " + favor_cnt + "\n" + "original_flag: " + original_flag + "\n" + "original_link: " + original_link + "\n" + "create_date: " + create_date
				+ "\n" + "create_time: " + create_time + "\n" + "del_flag: " + del_flag;
	}
}
