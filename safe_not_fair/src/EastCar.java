import java.util.Date;

public class EastCar implements Runnable{
    
    private String id;          
    private Bridge bridge;      
    private boolean finished=false; //has thread finished it's task?
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
        while (!finished)
        {
        	//thread has started but car is not on bridge yet
    		System.out.println(this.getID()+": Waiting for bridge. Going towards West " + new Date());
    		doProcessing();
        }
      } catch (InterruptedException e) {}
    }

    private void doProcessing () throws InterruptedException {
    	//car enters bridge
    	bridge.eastCross(this);
    	//car exits bridge
    	bridge.upEastExited(this);
    	finished=true;
    }

}
