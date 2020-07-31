import java.util.Date;
 
public class  FairBridge extends Bridge{
	//number of west and east cars on bridge 
    private int nwest=0;
    private int neast=0;
    private boolean westturn = true; //whose turn is it?
    //we set edelay>wdelay 
    private int wdelay_counter = 0; // increase everytime a west car enters the bridge
    								//maximum value = how many times edelay > wdelay 
    private int edelay_counter = 0; //maximum value 1
    private int cc=0;
   
    //we devide edelay with wdelay
    //if we get e.g. 4, for every east car that crosses, 4 west cars have already crossed.
    public int calc()
    {
    	return Main.edelay/Main.wdelay;
    }


    public synchronized void upWestExited(WestCar f) throws InterruptedException {
        ++cc;
    	--nwest;
    	if (wdelay_counter==calc() || cc>=Main.E)
    	{
    		notifyAll();
    		wdelay_counter=0;
    		westturn=false;
    	}
    	if(cc>=Main.E)
    		westturn=false;
    	
        System.out.println(f.getID()+": Across the Bridge." + new Date());
    }

    public synchronized void upEastExited(EastCar f) throws InterruptedException {
        --neast;
        if (edelay_counter==1 || cc>=Main.E)
        {
        	notifyAll();
    		edelay_counter=0;
    		westturn=true;
        }
        
        if(cc>=Main.E)
    		westturn=false;
        
        System.out.println(f.getID()+": Across the Bridge." + new Date());
    }


    public synchronized void westCross(WestCar f) throws InterruptedException {
    	//are there any east cars on the bridge? whose turn is it? counter has it's maximum value?
    	while (neast>0 || wdelay_counter==calc() || westturn==false) wait();
    	++wdelay_counter;
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
    	//are there any east cars on the bridge? whose turn is it? counter has it's maximum value?

    	while (nwest>0 ||  edelay_counter==1 || westturn==true)  wait();
    	++edelay_counter;
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
