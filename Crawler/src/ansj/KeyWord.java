package ansj;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.ansj.app.keyword.KeyWordComputer;
import org.ansj.app.keyword.Keyword;

public class KeyWord {
	
	private static List<String> keylist=null;
	public static List<String> keyWord(String title,String content)
	{
		keylist=new LinkedList<>();
	 KeyWordComputer kwc=new KeyWordComputer(2);
	 List<Keyword> list=kwc.computeArticleTfidf(title, content);
	 Iterator<Keyword> itor=list.iterator(); 
	
	 while(itor.hasNext())
	 {
		 
		String keyWord=itor.next().getName();
		System.out.println(keyWord);
		 keylist.add(keyWord);
	 }
	 return keylist;
	}
	public static List<String> getKeylist() {
		return keylist;
	}
	public static void setKeylist(List<String> keylist) {
		KeyWord.keylist = keylist;
	}
	
     //在源包里面里面创建 词典
	
	public static void main(String[] args) {
		
		
		String title="广电宽带";
		String content="广电宽带，通常是各地有线电视网络公司（台）负责运营的，通过HFC（光纤+同轴电缆混合网）网向用户提供宽带服务，通过CableModem连接到计算机，理论到户最高速率38M，实际速度要视网络具体情况而定。而电信网是使用ADSL模式使用电话线连接到用户 ";
		KeyWord.keyWord(title, content);
		System.out.println(KeyWord.keylist);
	}

	
}
