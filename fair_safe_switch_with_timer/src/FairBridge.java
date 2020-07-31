import java.util.Date;
 
public class  FairBridge extends Bridge{
	//o arithmos ton ditikon kai anatolikon autokiniton poy vriskonte mesa sti gefira
    private int nwest=0;
    private int neast=0;
    private boolean westturn = true; //diloni an ine i sira ton ditikon autokiniton
    //theoroume pos edelay>wdelay kathe fora
    private int wdelay_counter = 0; //auksanete kathe fora pou aneveni sti gefira ena ditiko amaksi
    								//kai i megisti timi tis isoute me to poses fores i metavliti
    								//edelay ine megaliteri tis metavlitis wdelay
    private int edelay_counter = 0; //i megisti timi tis metavlitis afti tha ine 1
    private int cc=0;
   
    //dieroume tin edelay me tin wdelay oste na doume poses fores i proti ine megaliteri tis deuteris
    //etsi argotera ean apo ti dieresi ehi prokipsi gia paradigma o arithmos 4, auto tha simeni pos
    //gia kathe anatoliko autokinito pou pernai, tha ehoun perasi idi 4 ditika
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
    	//ginete eleghos gia to an iparhoun sti gefira amaksia anatolika, gia to an
    	//ine i sira ton ditikon ke an o metritis ehi ftasi ti megisti timi tou
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
    	//ginete eleghos gia to an iparhoun sti gefira amaksia ditika, gia to an
    	//ine i sira ton ditikon ke an o metritis ehi ftasi ti megisti timi tou
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
