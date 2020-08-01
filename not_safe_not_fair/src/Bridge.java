import java.util.Date;

class Bridge {
	
	//number of west and east cars
	private int nwest=0;
    private int neast=0;

     void eastCross(EastCar c){
     	++neast;
    	System.out.println(c.getID()+": Crossing bridge ..." + new Date());

        //"crossing bridge"
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e1) {
        	// TODO Auto-generated catch block
			e1.printStackTrace();
        }
        
        if(nwest>0)
        {
        	Main.interrupted=true;
        	System.out.println("Crash...");
        }
        
    }

     void upEastExited(EastCar c){
    	 --neast;
    	 
    	if(neast>0 && nwest>0)
    	{
        	Main.interrupted=true;
        	System.out.println("Crash...");
        }
    	if(Main.interrupted==false)
    		System.out.println(c.getID()+": Across the Bridge." + new Date());
    	
    }

     void westCross(WestCar c){
     	++nwest;
     	
    	System.out.println(c.getID()+": Crossing bridge ..." + new Date());

        //"crossing bridge"
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e1) {
        	// TODO Auto-generated catch block
			e1.printStackTrace();
        }
        
        if(neast>0)
        {
        	Main.interrupted=true;
        	System.out.println("Crash...");
        }
        
    }

     void upWestExited(WestCar c){
    	 --nwest;
    	 
    	 if(neast>0 && nwest>0)
    	 {
         	Main.interrupted=true;
         	System.out.println("Crash...");
         }
    	 if(Main.interrupted==false)
     		System.out.println(c.getID()+": Across the Bridge." + new Date());
    }
}
