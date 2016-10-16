package parser;


import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class UrlManager {
     //管理URL就是 管理URL的添加  和取出
	private static HashMap<String,Integer> total;
	private Queue<String> queue;
	private List<String> timeoutQueue;
	public UrlManager()
	{
		total=new HashMap<String,Integer>();
		queue=new LinkedList<String>();
		timeoutQueue=new LinkedList<String>();
		
	}
	public void setUrl(List<String> urls)
	{
		//System.out.println("aa");
		//System.out.println(urls);
		for(String url:urls)
		{
			setUrl(url);
			
		}
		
	}
	public boolean setUrl(String url)
	{
	
		if(total.get(url)==null)
		{
			total.put(url, 1);
			queue.offer(url);
			
			return true;
		}
		else
		{
			return false;
		}
	}
	public String getUrl()
	{
		String url=queue.poll();
		if(url!=null)
		{
			total.put(url, 0);
		}
		return url;
	}
	public boolean isEmpty(){
		return queue.isEmpty();
	}
	public List<String> getTimeoutQueue() {
		return timeoutQueue;
	}

	public void setTimeoutQueue(String url) {
		this.timeoutQueue.add(url);
	}
	public Queue<String> getQueue() {
		return queue;
	}
	public void setQueue(Queue<String> queue) {
		this.queue = queue;
	}
}
