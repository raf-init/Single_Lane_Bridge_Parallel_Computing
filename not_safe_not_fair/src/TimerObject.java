import java.util.Queue;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ScheduledThreadPoolExecutor;

public class TimerObject extends TimerTask {
	
    final ScheduledThreadPoolExecutor executor1 = new ScheduledThreadPoolExecutor(Main.E);
	Queue<Thread> q;
	Runnable r;
  	private final Timer timer;
  	private final int delay;
  	int i;
  
  public TimerObject (Queue<Thread> q, Runnable r, Timer timer, int delay){
	this.q=q;
	this.r=r;
	this.timer = timer;
	this.delay = delay;
  }
  
public void run() {
	//if a crash has happened timer and executor are stopped
	if(Main.interrupted==true)
		{
			this.cancel();
			executor1.shutdown();
		}
	//runs everytime next thread is on queue 
	else if(!q.isEmpty())
	{
		executor1.execute(q.element());
		q.remove();
	}
  }

}
