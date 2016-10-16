package parser;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import ansj.KeyWord;

public class ParserBaiduBaike {
	
	 private static HashSet<String> set=new HashSet<>();
	private static Queue<String> total=readDic("/home/ethan/crawler/词库.txt");
   
	public static Queue<String> readDic(String path)
	{
		Queue<String> queue=new LinkedList<>();
		FileReader fr=null;
		BufferedReader br=null;
		try {
			fr=new FileReader(path);
			br=new BufferedReader(fr);
			String tem=br.readLine();
			String[] array;
			while(tem!=null)
			{
				array=tem.split(",");
				for(int i=0;i<array.length;i++)
				{
					if(!set.contains(array[i]))
				        queue.offer(array[i]);
				        set.add(array[i]);
				}
				tem=br.readLine();
			}
			br.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return queue;
	}
	public static void main(String[] args) {
		System.out.println(total.size());
		System.out.println(set.size());
	}
	private static List<String> alreadylist=new LinkedList<String>();
	private  static String content;
	private  List<String> urls;
	//爬取URL解析网页 然后接着爬取URL 解析网页
	public void parserPage(Document doc)
	{
		List<String> allUrls=getElementAhref(doc);
		getArticleUrls(allUrls);
		String html=doc.html();
		doc=Jsoup.parse(html);
		Element element=doc.select("h1").first();//直接使用不行  需要用节点
		if(element!=null)
		{
		content=doc.select("div.para").first().text();
		KeyWord.keyWord(element.text(), content);
		total.addAll(KeyWord.getKeylist());
		writeFile(KeyWord.getKeylist());
		String Text=element.text()+"\n"+content;
		if(!alreadylist.contains(element.text()))
		{
			alreadylist.add(element.text());
		for(String first:total)
		{
			if(first.equals(element.text())||element.text().equals(first))
			{
				System.out.println(first);
				writeFile(Text,"/home/ethan/crawler/paser/tmp.txt");
			}
		}
		}
	
		}
	  
	}
	
	/*
	 * 
	 * 过滤URL 只要百度百科的
	 */
	public static void writeFile(List<String> list)
	{
		FileWriter fw=null;
		BufferedWriter bw=null;
		try {
			
			fw=new FileWriter("/home/ethan/crawler/paser/dic.txt",true);
			bw=new BufferedWriter(fw);
			
			bw.write(list.toString()+"\n");
			bw.flush();
			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	public List<String> getArticleUrls(List<String> allUrls)
	{
		urls=new ArrayList<>();
		
		String rule="(http://baike.baidu.com/\\w+/\\d+.htm)*";//正则表达式 
		Pattern p = Pattern.compile(rule);
		Matcher m;
		
		for(String String:allUrls)
		{
			m=p.matcher(String);
			if(m.matches()&&!String.equals(""))
			{
				urls.add(String);
			}
			
		}
		return urls;
	}
	//https://www.baidu.com/s?ie=utf-8&fr=bks0000&wd=%E6%97%B6%E9%97%B4%E5%81%8F%E7%A7%BB%E8%A1%A8
	public List<String> getElementAhref(Document doc)
	{
		List<String> allUrls=new ArrayList<String>();
		Elements links=doc.select("a[href]");
		for(Element link:links)
		{
				allUrls.add(link.attr("abs:href"));
			
		} 
		return allUrls;
	}
	
	public void writeFile(String text,String path)
	{
		FileWriter fw=null;
		BufferedWriter bw=null;
		try {
			
			fw=new FileWriter(path,true);
			bw=new BufferedWriter(fw);
			
			bw.write(text+"\n");
			bw.flush();
			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	public String getTitle() {
		return content;
	}

	public void setTitle(String content) {
		this.content = content;
	}
	public List<String> getUrls() {
		return urls;
	}
	public  void setUrls(List<String> urls) {
		this.urls = urls;
	}
	public static Queue<String> getTotal() {
		return total;
	}
	public static void setTotal(Queue<String> total) {
		ParserBaiduBaike.total = total;
	}


	
	
}
