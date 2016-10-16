package ansj;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.ansj.domain.Term;
import org.ansj.splitWord.analysis.ToAnalysis;

public class Participle implements Comparator<Word>{
	private static List<Term> list=new ArrayList<Term>();
	private static HashMap<String, String> map=new HashMap<>();
	private static HashMap<String, Word> wordMap=new HashMap<>();
	private static HashSet<Word> set=new HashSet<>();
  	private static List<Word>  wordList=new LinkedList<>();
  	private static List<String> numList=new LinkedList<>();
  	public void readFile(String path)
  	{
  		FileReader fr=null;
  		BufferedReader br=null;
  		try {
			fr=new FileReader(path);
			br=new BufferedReader(fr);
			String tem=br.readLine();
			while(tem!=null)
			{
				split(tem);
				tem=br.readLine();
			}
			br.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
  	}
  	public static void writeFile(List<String> list,String path)
  	{
  		FileWriter fw=null;
  		BufferedWriter bw=null;
  		try {
			fw=new FileWriter(path, true);
			bw=new BufferedWriter(fw);
			bw.write(list.toString());
			bw.flush();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
  		finally {
  			try {
				bw.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
  		
  	}
	public void split(String text)
	{
		 //text = "" ;
		 list=ToAnalysis.parse(text);//返回的是一个list类型
		 Iterator<Term> itor=list.iterator();
		 String[] array=null;
		 Word word=null;
		 while(itor.hasNext())
		 {
			 String Text=itor.next().toString();
			 System.out.println(Text);
			 if(Text.length()>2)
			 {
		      array=Text.split("/");
			  map.put(array[0], array[1]);
			word=new Word(array[0]);
			  if(map.get(array[0]).equals("n"))
			  {
				
				  if(wordMap.get(array[0])!=null)
				  {
					  word=wordMap.get(array[0]);
					  word.setNum(word.getNum()+1);
					  wordMap.put(array[0], word);
				  }
				  else
				  {
					  word=new Word(array[0],1);
					  wordMap.put(array[0], word);  
				  }
			  }
	
			 }
		 }
		 //前三 和 大于二
 	     Set<String> keyset=wordMap.keySet();
	     for(String first:keyset)
	     {
	    	 word=wordMap.get(first);
	    	 if(!set.contains(word))
	    	 {
	    	 set.add(word);
             wordList.add(wordMap.get(first));
	    	 }
	     }
    
          
	}

	@Override
	public int compare(Word o1, Word o2) {
		// TODO Auto-generated method stub
		if(o1.getNum()>o2.getNum())
		{
			return -1;
		}
		else if(o1.getNum()<o2.getNum())
		{ return 1;
		}
		else 
			return 0;
	}
	public static List<String> getList(String path)
	{
		Participle ap=new Participle();
		ap.readFile(path);
		//ap.split("数字移动电视");
	    Collections.sort(wordList, ap);
	    System.out.println(wordList.size());
	    for(int i=0;i<100;i++)
	    {
	    	//System.out.println(wordList.get(i).getNum());
	    	//System.out.println(wordList.get(i).getName());
	     numList.add(wordList.get(i).getName());
	    }
	     return numList;
	}
	public static void main(String[] args) {
		
		String readPath="/home/ethan/crawler/paser/mysql/百科.txt";
		String writePath="/home/ethan/crawler/paser/dic.txt";
		Participle.writeFile(Participle.getList(readPath), writePath);
		
	}
}
