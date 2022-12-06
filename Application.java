/**
 * 
 * @author Jim Farese
 * @version 12/1/2022
 * 
 *  Application class that is used with Airplane class and Airport class to create, track, and land an airplane on 1 of 4 runways while also checking if the airplane has an emergency
 * 
 */

import java.util.Timer;
import java.util.TimerTask;

public class Application {
	
	public static void main(String[] args) {
	
		//creates objects
		Airport myAirport = new Airport();
		Timer myTimer = new Timer();
		
		//creates a timer task and prints the number of times the tasks are ran
		TimerTask task = new TimerTask() {
			int i=1;
			public void run() {
				
				myAirport.update();
				myAirport.clear();
				myAirport.printScreen();
				System.out.println("\nTimer ticks: " + i);
				i++;
			}
			
		};
		
		myTimer.scheduleAtFixedRate(task, 0, 2500);
	 
	}//end Main method

}//end Application class
