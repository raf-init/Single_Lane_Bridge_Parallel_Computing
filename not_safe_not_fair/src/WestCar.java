import java.util.Date;

public class WestCar implements Runnable{
	
    private String id;          
    private Bridge bridge;      
    private boolean finished = false; //has the thread finished it's task?

    
    public WestCar(String id, Bridge bridge) {
        this.id=id; 
        this.bridge = bridge;
    }

    public String getID()
    {
      return id;
    }
    
    @Override  
    public void run() {
      try {
        while (!finished && Main.interrupted==false)
        {
        	//thread has started but car is not on bridge yet 
        	System.out.println(this.getID()+": Waiting for bridge. Going towards East " + new Date());  //print initial waiting for bridge
        	doProcessing();
        }
      } catch (InterruptedException e) {}
    }

    private void doProcessing () throws InterruptedException {
    	//car enters bridge
    	bridge.westCross(this);
    	//car exits bridge
    	bridge.upWestExited(this);
    	finished=true;
    }
}
