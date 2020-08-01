import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayDeque;
import java.util.NoSuchElementException;
import java.util.Queue;
import java.util.Scanner;
import java.util.Timer;

public class Main {
	
    static int W=0,E=0;  //number of east and west cars is always the same
		         //we use different variables for ordering purposes
    //we set edelay>wdelay 
    static int wdelay = 1; //delay of west cars
    static int edelay = 2; //delay of east cars
    static boolean interrupted = false; //car crash handler
    
    public static void main(String[] args) throws InterruptedException {
        
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
        b = new Bridge();
        
        Queue<Thread> wq = new ArrayDeque<Thread>();
        Queue<Thread> eq = new ArrayDeque<Thread>();
        
        for (int i=0; i<W; i++) {
        	wq.add(new Thread(new WestCar("W_Car" + (i+1), b)));
        }
        
        for (int i=0; i<E; i++) {
        	eq.add(new Thread(new EastCar("E_Car" + (i+1), b)));

        } 
        
        //timer for the arrival of each car
        Timer timer1 = null;

        TimerObject o1 = new TimerObject(wq, wq.peek(), timer1, wdelay);
        timer1 = new Timer("Timer1");
        timer1.scheduleAtFixedRate(o1, wdelay*1000, wdelay*1000);
        
        
        Timer timer2 = null;

        TimerObject o2 = new TimerObject(eq, eq.peek(), timer2, edelay);
        timer2 = new Timer("Timer2");
        timer2.scheduleAtFixedRate(o2, edelay*1000, edelay*1000);
        
        
    }
}    

