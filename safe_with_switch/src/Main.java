import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayDeque;
import java.util.NoSuchElementException;
import java.util.Queue;
import java.util.Scanner;
import java.util.Timer;


public class Main {
	
    static int W=0,E=0; //o arithmos ton ditikon kai anatolikon autokiniton ine panta o idios
	//i xrisi diaforetikon metavliton ginete gia logous organosis
    //i metavlites wdelay, edelay mporoun na paroun diaforetikes times xoris na iparhei kapio
    //provlima stin ektelesi tou kodika, gia paradigma wdelay=2 kai edelay=5 
    
    //theoroume pos panta edelay>wdelay
    static int wdelay = 1; //i kathisterisi afiksis ton ditikon autokiniton
    static int edelay = 2; //i kathisterisi afiksis ton anatolikon autokiniton
    
    public static void main(String[] args) throws InterruptedException {
    	//to parakato kommati kodika ehi antigrafi apo tous etimous kodikes pou mas dothikan
    	//kata tin anathesi tis ergasias
        //File reading
        boolean success = false;    //looping file input
        
        String[] input;
        Scanner in = new Scanner(System.in);
        System.out.println("Enter file name eg input.txt: ");
        while (!success) {  //loop until a valid file is given
            try {
                String f = in.nextLine();
                Scanner file = new Scanner(new File(f));    //Throws file not found exception
                try {
                    //split by space
                    input = file.nextLine().split("\\s+");
     
                    //set number of west and east cars
                    W = Integer.parseInt(input[0]);    
                    E = Integer.parseInt(input[1]);
                    success = true; //no exception thrown, all went well, break loop
                } catch (NoSuchElementException e) {System.out.println("File was empty or invalid! Please enter a valid file.");}
                file.close();
            } catch (FileNotFoundException e) { System.out.println("File not found! Please enter a valid file.");}
        }
        in.close();
        //end file reading
        
        Bridge b;   //create bridge
        b = new SafeBridge();
        
        //hrisi ouras gia tin organosi ton threads
        Queue<Thread> wq = new ArrayDeque<Thread>();
        Queue<Thread> eq = new ArrayDeque<Thread>();
        
        for (int i=0; i<W; i++) {
        	wq.add(new Thread(new WestCar("W_Car" + (i+1), b)));
        }
        
        for (int i=0; i<E; i++) {
        	eq.add(new Thread(new EastCar("E_Car" + (i+1), b)));

        } 
        
        //hrisi timers gia ton kathorismo tis afiksis kathe autokinitou
        Timer timer1 = null;

        TimerObject o1 = new TimerObject(wq, wq.peek(), timer1, wdelay);
        timer1 = new Timer("MyTimer1");//create a new timer
        timer1.scheduleAtFixedRate(o1, wdelay*1000, wdelay*1000);
        
        Timer timer2 = null;

        TimerObject o2 = new TimerObject(eq, eq.peek(), timer2, edelay);
        timer2 = new Timer("MyTimer2");//create a new timer
        timer2.scheduleAtFixedRate(o2, edelay*1000, edelay*1000);
        
    }
}    

