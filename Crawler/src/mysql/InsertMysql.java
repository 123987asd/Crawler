package mysql;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Properties;

public class InsertMysql {

	private static HashSet<String> set=new HashSet<>();
	private static  String username;
	private static String password;
	private static String url;
	private static String driver;
	private static Connection conn;
	static{
		
			load();
		
	}

	public static void load() 
	{
		Properties prop=new Properties();
		InputStream input=InsertMysql.class.getResourceAsStream("/jdbc.properties");
		try {
			prop.load(input);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		username=prop.getProperty("jdbc.username");
	    password=prop.getProperty("jdbc.password");
	    url=prop.getProperty("jdbc.url");
	    driver=prop.getProperty("jdbc.driver");
	
	}
	
	public static Connection getConnection()
	{
	
		try {
			Class.forName(driver);
			conn=DriverManager.getConnection(url, username, password);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
		
	}
	
	public void insert(String title,String content)
	{
		conn=getConnection();
		try {
			String sql="insert into baike (title,content) values(?,?)";
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setString(1, title);
			ps.setString(2, content);
			int re=ps.executeUpdate();
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static boolean select(String title)
	{
		conn=getConnection();
		
		try {
			String sql="select * from baike where title=?";
			//System.out.println(title);
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setString(1, title);
			ResultSet re=ps.executeQuery();
		    if(re.next()==false)
		    {
		    	return false;
		    }
			/*while(re.next())
			{
				System.out.println(re.getString(1));
			}
			*/
		   
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 return true;
	}
	public  void readFile(String path)
	{
		FileReader fr=null;
		BufferedReader br=null;
		try {
			fr=new FileReader(path);
			br=new BufferedReader(fr);
			String title=br.readLine();
	
			while(title!=null)
			{
				//System.out.println(title);
				String content=br.readLine();
				if(select(title)==false)
				{ 
				 if(!set.contains(title))
				 {
					set.add(title);
				  insert(title, content);
				}
				}
				title=br.readLine();
			
			}
			br.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	@SuppressWarnings("resource")
	public void writeFile(String path) throws IOException
	{
		FileWriter fw=null;;
		BufferedWriter bw=null;
		conn=getConnection();
		try {
			fw=new FileWriter(path, true);
			bw=new BufferedWriter(fw);
			String sql="select * from baike";
			PreparedStatement ps=conn.prepareStatement(sql);
		
			ResultSet re=ps.executeQuery();
		   
			while(re.next())
			{
				
				String title=re.getString(2);
				String content=re.getString(3);
				bw.write(title+"\n"+content+"\n");
				bw.flush();
			}
		  bw.close();
		   
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	//非静态函数 可以调用静态的函数  但是静态的函数里面 不能调用非静态
	public static void main(String[] agrs) throws IOException
	{
		//System.out.println(InsertMysql.select("FTTH"));
		InsertMysql insertMysql=new InsertMysql();
		//insertMysql.readFile("/home/ethan/crawler/paser/mysql/tmp.txt");
		insertMysql.writeFile("/home/ethan/crawler/paser/mysql/百科.txt");
	}
}

