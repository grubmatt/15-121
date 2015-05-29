package HW2;
/**
 * @author Matt Gruber <mgruber1>
 * @section A
 * 
 */

// You may not import any additional classes or packages.

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class FlightManager {

	public ArrayList<Flight> flights = new ArrayList<Flight>();
	
	// Do not change this method.
	public FlightManager() {
		loadFlights();
	}

	/**
	 * Loads the flight data using the given specification. You must use the
	 * specification provided in the write-up in order to receive full credit.
	 */
	private void loadFlights() {
		String filename = "flights.txt";

		try {
			Scanner sc = new Scanner(new File(filename));
			while (sc.hasNextLine()) {
				String line = sc.nextLine();

				String[] splitLine = line.split("[|]");

				Flight currentFlight = new Flight(splitLine[0], splitLine[1],
						splitLine[2], splitLine[3], splitLine[4],
						Integer.parseInt(splitLine[5]));

				flights.add(currentFlight);
			}
		} catch (FileNotFoundException fnfe) {
			System.out.println("I could not load the file. Please make sure "
					+ "the file is in the correct directory.");
			System.exit(0);
		} catch (Exception e) {
			System.out.println("Error while creating the flights:");
			e.printStackTrace();
			System.exit(0);
		}
	}

	/**
	 * Returns the flights ArrayList.
	 * 
	 * @return ArrayList<Flight>
	 */
	public ArrayList<Flight> getFlights() {
		return flights;
	}

	/**
	 * Finds and returns all Flights that depart from the given ICAO airport
	 * code. This method must run in O(n) time. For example (your data may be
	 * different): Flights f = new Flights(); f.getFlightsDepartingFrom("PIT");
	 * // could return [DL8273, WN2834, WN5243]
	 * 
	 * @param airport
	 *            - the ICAO airport code to search for
	 * @return - an ArrayList of Flight objects that depart from the given ICAO
	 *         code
	 */
	public ArrayList<Flight> getFlightsDepartingFrom(String airport) {
		ArrayList<Flight> flightsDepartingFrom = new ArrayList<Flight>();

		Flight currentFlight;

		for (int i = 0; i < flights.size(); i++) {
			currentFlight = flights.get(i);
			if (currentFlight.getDepartureAirport().equals(airport))
				flightsDepartingFrom.add(currentFlight);

		}
		return flightsDepartingFrom;
	}

	/**
	 * Finds and returns an ArrayList<Flight> representing all direct Flights
	 * starting in startAirport and ending at endAirport, grouped by their
	 * unique identifier. For example: Flights f = new Flights();
	 * f.getFlightsAlongPath("PIT", "LAS"); // could return [WN2834, WN5243]
	 * 
	 * @param departureAirport
	 *            - the ICAO code of the departure airport
	 * @param arrivalAirport
	 *            - the ICAO code of the arrival airport
	 * @return - an ArrayList<Flight> that are non-stop
	 */
	public ArrayList<Flight> getNonStopFlights(String departureAirport,
			String arrivalAirport) {

		ArrayList<Flight> nonStopFlights = new ArrayList<Flight>();
		Flight currentFlight;

		for (int i = 0; i < flights.size(); i++) {
			currentFlight = flights.get(i);
			if (currentFlight.getDepartureAirport().equals(departureAirport)
					&& currentFlight.getArrivalAirport().equals(arrivalAirport))
				nonStopFlights.add(currentFlight);

		}
		return nonStopFlights;
	}

	/**
	 * Cancels all flights that stop in the given airport. You must deepCopy the
	 * global flights ArrayList and then remove any flights that ever exist in
	 * at a given airport.
	 * 
	 * @param airport
	 *            - the airport that has canceled all flights
	 * @return - an ArrayList<Flight> that are still able to fly
	 */
	public ArrayList<Flight> cancelFlights(String airport) {
		ArrayList<Flight> newFlights = new ArrayList<Flight>();
		newFlights = deepCopy(flights);

		Flight currentFlight;

		for (int i = 0; i < flights.size(); i++) {
			currentFlight = flights.get(i);
			if (currentFlight.getDepartureAirport().equals(airport)
					|| currentFlight.getArrivalAirport().equals(airport))
				newFlights.remove(currentFlight);
		}

		return newFlights;

	}

	// Extra credit method. If you don't want to implement this method,
	// just leave it as is.
	public ArrayList<Flight> cancelFlightsCorrectly(String airport) {
		return null; // remove this line when you're done
	}

	/**
	 * Calculates the total distance traveled for all given flightIdentifiers
	 * 
	 * @param flightIdentifiers
	 *            - an ArrayList<String> representing the flightIdentifiers to
	 *            search for
	 * @return - the total distance (in miles) traveled
	 */
	public int getTotalDistance(ArrayList<String> flightIdentifiers) {
		int totalDistance = 0;
		Flight currentFlight;

		for (int i = 0; i < flights.size(); i++) {
			currentFlight = flights.get(i);
			for (int j = 0; j < flightIdentifiers.size(); j++) {
				if (currentFlight.getIdentifier().equals(
						flightIdentifiers.get(j)))
					totalDistance += currentFlight.getDistance();
			}
		}
		return totalDistance;
	}

	/**
	 * Arranges the flights in an ArrayList<ArrayList<Flight>>. Each
	 * ArrayList<ArrayList> represents a unique flight path. Each
	 * ArrayList<ArrayList<Flight>> represents a collection of the flight path,
	 * in the same order as the input file.
	 */
	public ArrayList<ArrayList<Flight>> arrangedFlights() {
		ArrayList<ArrayList<Flight>> arrangedFlights = 
				new ArrayList<ArrayList<Flight>>();
		ArrayList<Flight> currentFlight = new ArrayList<Flight>();

		String currentID;

		for (int i = 0; i < flights.size(); i++) {
			currentID = flights.get(i).getIdentifier();
			currentFlight.add(flights.get(i));

			//Adds to i to prevent flights from being added multiple times
			int iModifier =0;
			
			for (int j = i+1; j < flights.size(); j++) {
				if (currentID.equals(flights.get(j).getIdentifier())) {
					currentFlight.add(flights.get(j));
					iModifier++;
				}
			}
			arrangedFlights.add((ArrayList<Flight>) currentFlight.clone());
			currentFlight.clear();
			i+= iModifier;
		}

		return arrangedFlights;
	}

	/**
	 * Returns an ArrayList<ArrayList<Flight>> corresponding to all flights that
	 * start at departureAirport and end at arrivalArrival airport and have at
	 * least 1 stop in between.
	 * 
	 * HINT: You may find it helpful to use the arrangedFlights() method.
	 * 
	 * @param departureAirport
	 *            - the ICAO code of the departure airport
	 * @param arrivalAirport
	 *            - the ICAO code of the arrival airport
	 * @return - an organized list of all multi-hop flights
	 */
	public ArrayList<ArrayList<Flight>> getMultiHopFlights(String departureAirport,
	        String arrivalAirport) {
		ArrayList<ArrayList<Flight>> multiFlights = new ArrayList<ArrayList<Flight>>();
		ArrayList<ArrayList<Flight>> arrangedFlights = new ArrayList<ArrayList<Flight>>();
		arrangedFlights = arrangedFlights();
		
		ArrayList<Flight> currentFlight = new ArrayList<Flight>();
		
		for(int i=0;i<arrangedFlights.size();i++)
		{
			currentFlight = arrangedFlights.get(i);
			if(currentFlight.size()>1)
			{
				ArrayList<Flight> optimalFlight = new ArrayList<Flight>();
				//Loops though the currentFlight to find which path has
				// the correct departure location
				for (int j = 0; j < currentFlight.size(); j++) {
					String currentDeparture = currentFlight.get(j)
							.getDepartureAirport();

					// Loops though the currentFlight to find which path has
					// the correct arrival location
					for (int k = j+1; k < currentFlight.size(); k++) {
						optimalFlight.add(currentFlight.get(k));
						String currentArrival = currentFlight.get(k)
								.getArrivalAirport();

						if (currentDeparture.equals(departureAirport) &&
								currentArrival.equals(arrivalAirport))
						{
							optimalFlight.add(0, currentFlight.get(j));
							multiFlights.add((ArrayList<Flight>) optimalFlight
									.clone());
							optimalFlight.remove(0);
						}
					}
					optimalFlight.clear();
				}
			}
		}
		return multiFlights;
	}

	/**
	 * Returns an ArrayList<Flight> of all the flights that depart from the
	 * given airport in the AM (i.e. between 0000 and 1159, midnight and noon).
	 * This method must run in O(n) time.
	 * 
	 * @param departureAirport
	 *            - the ICAO code of the departure airport
	 * @return - a list of all the departing AM flights from the airport
	 */
	public ArrayList<Flight> morningDepartingFlights(String departureAirport) {
		ArrayList<Flight> morningFlights = new ArrayList<Flight>();
		
		Flight currentFlight;
		
		for(int i=0;i<flights.size();i++)
		{
			currentFlight = flights.get(i);
			if(currentFlight.getDepartureAirport().equals(departureAirport) &&
					Integer.parseInt(currentFlight.getDepartureTime())<1200)
				morningFlights.add(currentFlight);
		}
		
		return morningFlights;
	}

	/**
	 * Returns an ArrayList<Flight> of Flight(s) that go from the specified
	 * departure airport to the specified arrival airport in the fewest number
	 * of miles. The shortest flight path can be nonstop or multi-hop. If there
	 * is no flight path that goes between the two specified airports, return an
	 * empty List.
	 * 
	 * @param departureAirport
	 *            - the ICAO code of the departure airport
	 * @param arrivalAirport
	 *            - the ICAO code of the arrival airport
	 * @return - a list of the Flights in the shortest flight path, in order
	 */
	public ArrayList<Flight> shortestFlight(String departureAirport,
			String arrivalAirport) {
		ArrayList<Flight> shortestFlight = new ArrayList<Flight>();
		
		shortestFlight = getNonStopFlights(departureAirport, arrivalAirport);
		
		if(shortestFlight.size()>1)
		{
			int shortestFlightDistance = 0;
			int shortestIndex = -1;
			for(int i=0;i<shortestFlight.size();i++)
			{
				int flightDistance = shortestFlight.get(i).getDistance();
				if(shortestIndex<0)
				{
					shortestIndex = i;
					shortestFlightDistance = flightDistance;
				}
				else if(shortestFlightDistance > flightDistance)
				{
					shortestIndex = i;
					shortestFlightDistance = flightDistance;
				}
			}
			Flight actuallyShortestFlight = shortestFlight.get(shortestIndex);
			shortestFlight.clear();
			shortestFlight.add(actuallyShortestFlight);

		}
		else if(shortestFlight.size() == 0)
		{
			// created seperate method to help clarify code
			shortestFlight = shortestMultiHopFlight(departureAirport, arrivalAirport);				
		}
		
		
		return shortestFlight;
	}
	
	public ArrayList<Flight> shortestMultiHopFlight(String departureAirport,
			String arrivalAirport) {
		ArrayList<ArrayList<Flight>> availableFlights = new ArrayList<ArrayList<Flight>>();
		
		availableFlights = getMultiHopFlights(departureAirport, arrivalAirport);
		ArrayList<Flight> currentFlight = new ArrayList<Flight>();
		
		int shortestFlightDistance = 0;
		int shortestIndex = -1;
		
		for(int i=0;i<availableFlights.size();i++)
		{
			currentFlight = availableFlights.get(i);
			int totalDistance = 0;
			for(int j=0;j<currentFlight.size();j++)
			{
				totalDistance += currentFlight.get(j).getDistance();
			}
			// first flight will start as shortest
			if(shortestIndex<0)
			{
				shortestIndex = i;
				shortestFlightDistance = totalDistance;
			}
			else if(totalDistance < shortestFlightDistance)
			{
				shortestIndex = i;
				shortestFlightDistance = totalDistance;
			}
		}
		
		if(shortestIndex<0)
			// Returns a blank list in case no availableFlight is found
			return currentFlight;
		else
			return availableFlights.get(shortestIndex);
		
	}
	

	/**
	 * deepCopies an ArrayList<Flight>
	 * 
	 * @param theFlights
	 * @return - a new ArrayList<Flight> containing the same Flights in the same
	 *         order as the given ArrayList<Flight>
	 */
	private ArrayList<Flight> deepCopy(ArrayList<Flight> theFlights) {
		ArrayList<Flight> newFlights = new ArrayList<Flight>();
		for (int i = 0; i < theFlights.size(); i++)
			newFlights.add(theFlights.get(i));
		return newFlights;
	}
}
