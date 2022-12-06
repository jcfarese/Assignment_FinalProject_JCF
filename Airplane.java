/**
 * 
 * @author Jim Farese
 * @version 12/1/2022
 * 
 *  Airplane class that is used with Airport class and Application class to create, track, and land an airplane on 1 of 4 runways while also checking if the airplane has an emergency
 * 
 */

public class Airplane implements Comparable<Airplane> {
	
	//defines items used
	private String flight;
	private int flightNumber;
	private int distance;
	private boolean isEmergency;
	private int runwayTime;
	private boolean onRunway;
	
	/*
	 * Constructor
	 *Creates an instance of an airplane defining its name, distance away from the runway, and sets the totatl time it would take to land when the plane is on the runway 
	 */
	public Airplane() {
		distance = 5000;
		runwayTime = 300;
		onRunway=false;
		flightNumber = 100 + (int)(Math.random()*((999 - 100)+1));
		flight = "UP" + flightNumber + "N";
		
	}//end Airplane method
	
	/**
	 * returns the distance
	 * 
	 * @return distance
	 */
	public int getDistance() {
		return distance;
	}//end getDistance
	
	/**
	 * returns the time on the runway
	 * 
	 * @return runwayTime
	 */
	public int getRunwayTime() {
		return runwayTime;
	}//end getRunwayTime
	
	/**
	 * returns if the plane is on the runway or not
	 * 
	 * @return onRunway
	 */
	public boolean getOnRunway() {
		return onRunway;
	}//end getOnRunway
	
	/**
	 * returns the flight information
	 * 
	 * @return flight
	 */
	public String getFlight() {
		return flight;
	}//end getFlight
	
	
	/*
	 *creates a number of type int called emergency and generates a random number for that and checks that against a defined number and if emergency is greater than the assigned number it creates an emergency for that flight 
	 * 
	 * @return isEmergency returns emergency as a boolean for true or false.  If true the plane is having an emrgency
	 */
	public boolean emergency() {
		int emergency = (int)(Math.random()*((1000)+1));
		//if the number created for emergency is greater than or equal to 975 than it causes isEmergency to be true.  This gives a 2.5 percent chance for an emrgency to occur
		if(distance > 0 && emergency >= 975) {
			isEmergency=true;
		}//end if
		
		return isEmergency;
	}//end emergency

	/*
	 * @param onRunway sets onRunway
	 */
	public void setOnRunway(boolean onRunway) {
		this.onRunway=onRunway;
	}//end setOnRunway
	
	//Updates the information for the plane.  It will update the distance of minus 500 for every tick and update the time on the runway if onRunway is true
	public void update() {
		distance = distance-500;
		//sets distance to 0 if distance goes less than 0 to prevent errors
		if (distance < 0) {
			distance = 0;
		}
		
		if(onRunway) {
			runwayTime = runwayTime-50;
		}
	}//end update
	
	/**
	 * Creates a string method to format the printout to be easily readable
	 * 
	 * @return print depending on if the distance is a certain distance and if the plane is having an emergency or not
	 * 
	 */
	public String toString() {
		if (distance == 0) {
			if(isEmergency) {
				return "***EMERGENCY***" + " " +flight;
			}
			else{
				return "        " + flight + "        ";
			}
		}
		else {
			if(distance<=500) {
				if(isEmergency) {
					return "   ***EMREGENCY*** " + flight + "   |         " +  distance+ "        ";
				}
				else {
					return"           " + flight + "           |         " + distance + "        ";
				}
			}
			else {
				if(isEmergency) {
					return "   ***EMREGENCY*** " + flight + "   |        " +  distance+ "        ";
				}
				else {
					return"           " + flight + "           |        " + distance + "        ";
				}
			}
		}
	}//end toString method
	
	/**
	 * sets priority for planes on whether the plane is having an emergency or not and then the distance away from the airport
	 * 
	 * @return priority based if the plane is having an emergency or not and the distance away from the runway
	 */
	public int priority() {
		return emergency() ? distance - 1 : distance;
	}//end priority method

	/**
	 * compares the planes based on priority
	 * 
	 * @return the comparison of the planes
	 */
	@Override
	public int compareTo(Airplane t) {
		return priority() - t.priority();
	}//end compareTo
}//end Airplane class
