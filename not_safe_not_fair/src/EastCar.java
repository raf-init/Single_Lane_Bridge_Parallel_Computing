import java.util.Date;

public class EastCar implements Runnable{
    
    private String id;          
    private Bridge bridge;      
    private boolean finished=false; //metavliti pou diloni ehi oloklirothi to treksimo tou ekastote thread


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
        	//to thread ehi ksekinisi alla den ehi mpi akomi sti gefira to autokinito
        	System.out.println(this.getID()+": Waiting for bridge. Going towards West " + new Date());
    		doProcessing();
        }
      } catch (InterruptedException e) {}
    }

    private void doProcessing () throws InterruptedException {
    	//to autokinito mpeni sti gefira
    	bridge.eastCross(this);
    	//to autokinito vgeni apo ti gefira
    	bridge.upEastExited(this);
    	finished=true;
    }
}
