package ansj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.HashSet;
import java.util.Set;

public class Combine {

	private static FileReader fr=null;
	private static BufferedReader br=null;
	private static Set<String> set=read("/home/ethan/crawler/paser/mysql/dic.txt");
	
	public static Set<String> read(String path)
	{
		Set<String> set=new HashSet<>();
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
					set.add(array[i]);
				}
				tem=br.readLine();
			}
			br.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return set;
	}
	
	public Combine(String path) {
		// TODO Auto-generated constructor stub
		try {
			
			fr=new FileReader(path);
			br=new BufferedReader(fr);
			String tem=br.readLine();
			String[] array;
			while(tem!=null)
			{
				array=tem.split(",");
				System.out.println(array.length);
				for(int i=0;i<array.length;i++)
				{
					if(!set.contains(array[i]))
					{
						set.add(array[i]);
						write(array[i]);
					}
					
				}
				
				tem=br.readLine();
			}
			br.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}
	public  void write(String text)
	{
		FileWriter fw=null;
		BufferedWriter bw=null;
		try {
			fw=new FileWriter("/home/ethan/crawler/paser/dic.txt",true);
			bw=new BufferedWriter(fw);
			bw.write(text+",");
			bw.flush();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	public static void main(String[] args) {
		Combine combine=new Combine("/home/ethan/crawler/dic.txt");
		
}
}
