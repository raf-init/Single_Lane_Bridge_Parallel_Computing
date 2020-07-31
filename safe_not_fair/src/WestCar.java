import java.util.Date;

public class WestCar implements Runnable{
    private String id;          
    private Bridge bridge;     
    private boolean finished = false; //metavliti pou diloni ehi oloklirothi to treksimo tou ekastote thread

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
        	//to thread ehi ksekinisi alla den ehi mpi akomi sti gefira to autokinito
        	System.out.println(this.getID()+": Waiting for bridge. Going towards East " + new Date());  //print initial waiting for bridge
        	doProcessing();
        }
      } catch (InterruptedException e) {}
    }

    private void doProcessing () throws InterruptedException {
    	//to autokinito mpeni sti gefira
    	bridge.westCross(this);
    	//to autokinito vgeni apo ti gefira
    	bridge.upWestExited(this);
    	finished=true;
    }

}
