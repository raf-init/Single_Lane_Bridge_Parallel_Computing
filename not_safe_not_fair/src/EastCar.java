import java.util.Date;

public class EastCar implements Runnable{
    
    private String id;          
    private Bridge bridge;      
    private boolean finished=false; //has the thread finished the task?


    public EastCar(String id, Bridge bridge) {
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
        	//thread has started but car is not yet on the bridge
        	System.out.println(this.getID()+": Waiting for bridge. Going towards West " + new Date());
    		doProcessing();
        }
      } catch (InterruptedException e) {}
    }

    private void doProcessing () throws InterruptedException {
    	//car enters bridge
    	bridge.eastCross(this);
    	//car leaves bridge
    	bridge.upEastExited(this);
    	finished=true;
    }
}
