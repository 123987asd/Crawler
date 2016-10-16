package parser;

import java.util.LinkedList;
import java.util.List;

import org.jsoup.nodes.Document;

public class PaserDirver {

	// 整体流程就是 下载一个网页 解析页面 还有新的url 加入url管理中
	private static List<String> list = new LinkedList<String>();
	private static String url;
	static ParserBaiduBaike parser = new ParserBaiduBaike();
	// 再写一个判断函数
	public static void main(String[] args) {
		while (!ParserBaiduBaike.getTotal().isEmpty()) {
			String first = ParserBaiduBaike.getTotal().poll();
			String startUrl = "http://baike.baidu.com/search/word?word=" + first;
			Downloader downLoader = new Downloader();
			UrlManager manager = new UrlManager();
	
			manager.setUrl(startUrl);

			while (!manager.isEmpty()) {

				Document doc = null;
				try {

					url = manager.getUrl();
					list.add(url);
					if (list.size() > 1000) {
						list = new LinkedList<String>();
						manager.getQueue().clear();
						break;
					}
					doc = downLoader.downLoader(url);
					if (doc == null)
						continue;
					parser.parserPage(doc);// 这里可以改改
					manager.setUrl(parser.getUrls());
				} catch (Exception e) {
					// TODO: handle exception
				}

			}
		}
	}
}
