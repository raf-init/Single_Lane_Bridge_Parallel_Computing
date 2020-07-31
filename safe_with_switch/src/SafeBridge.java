import java.util.Date;

//gia ti sigkekrimeni class stirihtika ston kodika pou mas dothike kata tin anathesi tis ergasias
public class SafeBridge extends Bridge{
	
	//o arithmos ton ditikon kai anatolikon autokiniton poy vriskonte mesa sti gefira
    private int nwest=0;
    private int neast=0;
    private boolean westturn = true; //diloni an ine i sira ton ditikon autokiniton

    public synchronized void upWestExited(WestCar f) throws InterruptedException {
    	
    	//mionete o arithmos ton autokiniton pou vriskonte sti gefira ke otan den ehi alla
    	//ksipnai ta threads oste na sinehisoun apo eki pou ixane mini
    	--nwest;
    	westturn = false;
    	if (nwest==0)
    		notifyAll();
    	System.out.println(f.getID()+": Across the Bridge." + new Date());
    }

    public synchronized void upEastExited(EastCar f) throws InterruptedException {
    	
    	//mionete o arithmos ton autokiniton pou vriskonte sti gefira ke otan den ehi alla
    	//ksipnai ta threads oste na sinehisoun apo eki pou ixane mini
    	--neast;
    	westturn = true;
    	if (neast==0)
    		notifyAll();
    	System.out.println(f.getID()+": Across the Bridge." + new Date());
    }

    
    public synchronized void westCross(WestCar f) throws InterruptedException {

    	//ginete eleghos ean vriskonte pano sti gefira ite ditika ite anatolika autokinita
    	//oste an vriskete kapio na min mpi allo mesa ke na pernai ena kathe fora
    	//episis ginete eleghos an ine i sira ton ditikon autokiniton 
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

    	//ginete eleghos ean vriskonte pano sti gefira ite ditika ite anatolika autokinita
    	//oste an vriskete kapio na min mpi allo mesa ke na pernai ena kathe fora
    	//episis ginete eleghos an ine i sira ton ditikon autokiniton 
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