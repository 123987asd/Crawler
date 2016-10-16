package parser;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Test {

	public static void main(String[] args) throws IOException
	{
	
		Document doc = Jsoup.connect("https://www.amazon.cn/s/ref=nb_sb_noss_1?__mk_zh_CN=%E4%BA%9A%E9%A9%AC%E9%80%8A%E7%BD%91%E7%AB%99&url=search-alias%3Ddigital-text&field-keywords=macbook+pro")
				  .data("query", "Java")
				  .userAgent("Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.84 Safari/537.36")
				  .cookie("auth","token")
				  .timeout(3000)
				  .post();
		
	   
	   //System.out.println(doc.body().toString());
	  // Element content=doc.getElementById("cm_cr_dpwidget");
	  // Elements elements=content.getElementsByClass("a-section");
	   //for(Element element:elements)
		   //System.out.println(element.text());
	   //Element element=content.select("h3").first();
	  // System.out.println(elements.text());
		//System.out.println(content.text());
		//Elements element=content.select("a[href]");
		//System.out.println(element);
		//System.out.println(element.id());
		//System.out.println(element.select("").first().text());
		//到第三层
		//String title=doc.select("div.navbar-wrapper").select("div.wgt-navbar").select("div.navbar-bg").select("div.navbar-bg-top").first().text();
		//System.out.println(title);
		/*
		Elements elements=doc.getElementsByTag("div.");
		for(Element element:elements)
		{
	      Element content=element.getElementById("open-tag");
	      if(content!=null)
		   System.out.println(content.select("div.open-tag-title").first().text());
		}
		//System.out.println(content.select("div.nav-template nav-flyout-content nav-tpl-itemListDeepBrowse").text());					
		/*
		 * 
		 * session-token=7IpejvXAszBjZltZU/W2P0xvimcRTq00BBqcqEKxZpChBm30eDpdB1x9H83yfTHaB2jCZklJ9L7IrcP2jFINNlxS7EEZ47bk9i6N2Th+BBpH8zHgtcGhePv84rOaj3dIqcFwnzddAgF+Ro7RSBic5No4v2l4bBMZFjqCoNd2ebr2U+C6ChSjjR59ZgeNzhs+erE5elpTe94mL1vDsuREC9Q1waOobVWvh3RaQX/TeAOmw86ICPBL1g==; 
		// ubid-acbcn=455-8903377-0449547; session-id-time=2082729601l; session-id=455-8151996-1529837
		
		System.out.println(doc.title());
		
		//System.out.println(doc.select("h1").text());		  
		//Element input = doc.getElementById("query");
		//Element button=doc.getElementById("search");
		//System.out.println(button);
		//System.out.println(button.tagName());
		//input.select("input").val("北京");
		//System.out.println(input.val());
		//button=button.wrap(doc.html());
		//Document doc1=Jsoup.parse(doc.html());
		
		//System.out.println(doc.getClass());
		//System.out.println(doc.title());
		
		//System.out.println(doc.child(0).child(0).getClass());
		//doc.select("input").val("上海");
		//System.out.println(content.val());
		//System.out.println(doc.html());
		
		
		//Document doc1=Jsoup.parse(html);
		
		//String title=doc1.select("h1").first().text();
		//System.out.println(title);
		//System.out.println();
		//Element test;
		//Element links = doc.getElementsByTag("html").first();
		//test=links.tagName("title");
		//System.out.println(test.text()+"aa");
		//System.out.println(links.child(1).tagName());
	    //System.out.println(links.text());
	    //System.out.println(links.id());
		//Elements links = doc.select("a[href]");//a   和 a[href]有区别 
	   /* 	
		for(Element link:links)
		{
			//String url=link.attr("abs:href");
			System.out.println(link.text());

		}
*/
		//Document doc2=Jsoup.parseBodyFragment(html);
		//String title1=doc1.title();
		//String title2=doc2.select("div.para").first().text();
		//System.out.println(title1);
		//System.out.println(title2);
		String url="https://www.amazon.cn/%E6%89%8B%E6%9C%BA-%E9%80%9A%E8%AE%AF/b/ref=sa_menu_top_mobile_l1?ie=UTF8&amp;node=664978051";
		//System.out.println(url.replace("amp;", ""));
	    //https://www.amazon.cn/%E6%89%8B%E6%9C%BA-%E9%80%9A%E8%AE%AF/b/ref=sa_menu_top_mobile_l1?ie=UTF8&node=664978051
	 
	}
}
