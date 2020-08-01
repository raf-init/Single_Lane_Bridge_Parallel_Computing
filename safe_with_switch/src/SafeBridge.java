import java.util.Date;

public class SafeBridge extends Bridge{
	
	//number of west and east cars on bridge
    private int nwest=0;
    private int neast=0;
    private boolean westturn = true; //whose turn is it?

    public synchronized void upWestExited(WestCar f) throws InterruptedException {
    	
    	--nwest;
    	westturn = false;
    	if (nwest==0)
    		notifyAll();
    	System.out.println(f.getID()+": Across the Bridge." + new Date());
    }

    public synchronized void upEastExited(EastCar f) throws InterruptedException {
   
    	--neast;
    	westturn = true;
    	if (neast==0)
    		notifyAll();
    	System.out.println(f.getID()+": Across the Bridge." + new Date());
    }

    
    public synchronized void westCross(WestCar f) throws InterruptedException {

    	//check if there are cars on bridge to prevent others from entering
	//check whose turn is it
    	while (neast>0 || nwest>0 || !westturn) wait();
    	++nwest;
        System.out.println(f.getID()+": Crossing bridge ..." + new Date());

        //"crossing bridge"

        try {
            Thread.sleep(1);
        } catch (InterruptedException e1) {
        	// TODO Auto-generated catch block
			e1.printStackTrace();
        }
    }
    
    public synchronized void eastCross(EastCar f) throws InterruptedException {

    	//check if there are cars on bridge to prevent others from entering
	//check whose turn is it
    	while (nwest>0 || neast>0 || westturn) wait();
    	++neast;
    	System.out.println(f.getID()+": Crossing bridge ..." + new Date());

    	//"crossing bridge"

        try {
        	Thread.sleep(1);
        } catch (InterruptedException e1) {
        	// TODO Auto-generated catch block
        	e1.printStackTrace();
        }
    }
    
}
