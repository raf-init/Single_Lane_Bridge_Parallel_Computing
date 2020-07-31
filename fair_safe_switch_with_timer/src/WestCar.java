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
        while (!finished)
        {
        	//thread has started but car is not yet on the bridge 
    		System.out.println(this.getID()+": Waiting for bridge. Going towards East " + new Date());
        	doProcessing();
        	
        }
      } catch (InterruptedException e) {}


    }

    private void doProcessing () throws InterruptedException {
    	//car enters the bridge
    	bridge.westCross(this);
    	//car exits the bridge
    	bridge.upWestExited(this);
    	finished=true;
    	
    	
    	
    }

}
