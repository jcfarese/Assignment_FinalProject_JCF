/**
 * 
 * @author Jim Farese
 * @version 12/1/2022
 * 
 *  Airport class that is used with Airplane class and Application class to create, track, and land an airplane on 1 of 4 runways while also checking if the airplane has an emergency
 * 
 */

import java.io.IOException;
import java.util.PriorityQueue;
import java.util.Queue;

public class Airport {
	
	//defines items used
	private Queue<Airplane> onApproach;
	private PriorityQueue<Airplane> readyToLand;
	private Airplane[] runways;
	private Airplane airportPlane;
	
	//Constructor
	public Airport() {
		onApproach = new PriorityQueue<>();
		readyToLand = new PriorityQueue<>();
		airportPlane = new Airplane();
		runways = new Airplane[4];
	}//end Airport method
	
	
	/**
	 * creates a number and checks it to a predefined number and if the randomly generated number is larger than it creates a plane on and adds it to onApporach queue
	 */
	public void addOnApproach() {
		int odds = (int)(Math.random()*(100));
		if(odds >= 50) {
			Airplane airportPlane=new Airplane();
			onApproach.add(airportPlane);
		}
	}//end addOnApproach
	
	/**
	 * prints out the information for the plane on the runway depending on its time on the runway and if the plane is an emergency plane or not to ensure the formating for the printout is correct
	 */
	public void printOnRunway() {
		for(int i = 0; i < runways.length; i++) {
			if (runways[i] != null) {
				if(runways[i].getRunwayTime()<=50) {
					if(runways[i].emergency()==true) {
						System.out.println(" |   " + (i + 1) + "    |   " + runways[i].getFlight() + " E  |             " + runways[i].getRunwayTime()+ "           |");
					}
					else {
						System.out.println(" |   " + (i + 1) + "    |   " + runways[i].getFlight() + "    |             " + runways[i].getRunwayTime()+ "           |");
					}
				}
				else {
					if(runways[i].emergency()==true) {
						System.out.println(" |   " + (i + 1) + "    |   " + runways[i].getFlight() + " E  |            " + runways[i].getRunwayTime()+ "           |");
					}
					else {
						System.out.println(" |   " + (i + 1) + "    |   " + runways[i].getFlight() + "    |            " + runways[i].getRunwayTime()+ "           |");
					}
				}
					
			}
		}
	}//end printOnRunway
	
	/**
	 * updates the plane information and moves it to the readyToLand queue if distance is 0 and then moves it to a runway if a runway is available
	 */
	public void update() {
		addOnApproach();
		
		for (Airplane plane : onApproach) {
			plane.update();
		}
		//moves planes to readyToLand queue if there is a plane in the onApporach queue and the distance is 0
		while(onApproach.peek() != null && onApproach.peek().getDistance()<=0) {
			readyToLand.add(onApproach.poll());
		}
		
		for(Airplane plane : readyToLand) {
			plane.update();
		}
		
		//if the runways are empty and there are planes that are in the readyToLand queue it moves them to the runway
		for(int i=0;i<runways.length;i++) {
			if(runways[i]==null) {
				runways[i]=readyToLand.poll();
				if (runways[i] != null) {					
					runways[i].setOnRunway(true);
				}
			}
			//updates the information for the planes on the runway if the runway has a plane on it
			else {
				runways[i].update();
				if(runways[i].getRunwayTime()<=0) {
					runways[i]=null;
				}
			}
		}
	}//end update
	
	/**
	 * formats the print screen to display all the information and formats it to make it easy to read 
	 */
	public void printScreen() {
		System.out.println("  _________________________________________________");
		System.out.println(" |                                                 |");
		System.out.println(" |        AIRPORT: SDF (Louisville Airport)        |");
		System.out.println(" |_________________________________________________|");
		System.out.println(" |     Planes on approach     |  Distance  (feet)  |");
		System.out.println(" |----------------------------|--------------------|");
		for (Airplane plane: onApproach) {			
			System.out.println(" |" + plane + "|");
		}
		System.out.println(" |____________________________|____________________|");
		System.out.println(" |              Planes ready to land               |");
		System.out.println(" |-------------------------------------------------|");
		for (Airplane plane: readyToLand) {			
			System.out.println(" |             " + plane + "              |");
		}
		System.out.println(" |_________________________________________________|");
		System.out.println(" | Runway |  Plane (E)  | Time on the runway (sec) |");
		System.out.println(" |--------|-------------|--------------------------|");
		printOnRunway();
		System.out.println(" |________|_____________|__________________________|");
	}//end printScreen
	
	/*
	 * clears the screen
	 */
	public void clear() {
        try
        {
            if (System.getProperty("os.name").contains("Windows"))
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            else
                Runtime.getRuntime().exec("clear");
        } catch (IOException | InterruptedException ex) {}
    }//end clear

}//end Airport Class
