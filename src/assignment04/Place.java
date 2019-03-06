package assignment04;

import java.util.ArrayList;

/**
 * File: Place.java
 * 
 * @author: Hannah Borreson
 * 
 * @version 5/5/2017
 * 
 *          Description: The main purpose of the Place program is to create an
 *          object called Place that holds an ArrayList of Strings containing
 *          zip codes of towns and also a String containing the actual name of
 *          the town and state that has those zip codes. Has appropriate setters
 *          and getters along with a few other helpful methods.
 * 
 */

public class Place implements Comparable<Place> {

	private String townAndState;
	private ArrayList<String> zipCodes;
	/*
	 * Below is a group of tests I used to make sure that I created the object
	 * Place correctly. Does not directly contribute to homework.
	 */

	/*public static void main(String[] args) {
		ArrayList<String> testArray = new ArrayList<String>();
		testArray.add("54701");
		testArray.add("54702");
		testArray.add("54703");

		Place printTest = new Place("Eau Claire, WI", testArray);
		System.out.println(printTest.toString());

		ArrayList<String> compareArray = new ArrayList<String>();
		compareArray.add("00210");
		compareArray.add("00211");
		compareArray.add("00212");

		Place compareTest = new Place("Portsmouth, NH", compareArray);
		System.out.println(printTest.compareTo(compareTest));
		System.out.println(compareTest.toString());
		System.out.println(compareTest.getZipCodesInt().toString());
		ArrayList<Integer> interesting = compareTest.getZipCodesInt();
		int weird = interesting.get(0) + interesting.get(0);
		System.out.println(weird);

	}*/

	// constructors
	public Place() {
		townAndState = "";
		zipCodes = new ArrayList<String>();
	}

	public Place(String townState, ArrayList<String> zips) {
		townAndState = townState;
		zipCodes = zips;

	}

	/**
	 * Gets the String containing the town and state of the current Place.
	 */
	public String getTownAndState() {
		return townAndState;
	}

	/**
	 * Gets the ArrayList of Strings containing zip codes of the current Place.
	 */
	public ArrayList<String> getZipCodes() {
		return zipCodes;
	}

	/**
	 * Converts the ArrayList of Strings containing zip codes of the current
	 * Place into an ArrayList of Integers. This method isn't used but is
	 * included regardless due to its possible practicality.
	 */
	public ArrayList<Integer> getZipCodesInt() {
		ArrayList<Integer> intArray = new ArrayList<Integer>();
		for (int i = 0; i < zipCodes.size(); i++) {
			intArray.add(Integer.parseInt(zipCodes.get(i)));
		}
		return intArray;
	}

	/**
	 * Sets the String containing the town and state of the current Place.
	 * 
	 * @param townState String town and state to be set 
	 */
	public void setTownAndState(String townState) {
		townAndState = townState;
	}

	/**
	 * Sets the ArrayList of Strings containing zip codes of the current Place.
	 * 
	 * @param zips arraylist of zip codes to be set
	 */
	public void setZipCodes(ArrayList<String> zips) {
		zipCodes = zips;
	}

	/**
	 * Returns a String containing the town and state with all of the zip codes
	 * separated by commas.
	 */
	public String toString() {
		String concat = "";
		for (int i = 0; i < zipCodes.size(); i++) {

			concat += zipCodes.get(i) + ", ";
		}

		concat += " " + townAndState;
		return concat;

	}

	/**
	 * Returns a String containing all of the zip codes separated by commas.
	 */
	public String zipsToString() {
		String concat = "";
		for (int i = 0; i < zipCodes.size() - 1; i++) {

			concat += zipCodes.get(i) + ", ";
		}
		concat += zipCodes.get(zipCodes.size() - 1);
		return concat;

	}

	/**
	 * Compares current place with another one and returns the int compare
	 * value. The comparison is based off of the String town and state.
	 * 
	 * @param o Place that is compared to another place 
	 */
	@Override
	public int compareTo(Place o) {
		String paramPlace = o.getTownAndState().trim().replaceAll(",", "").toLowerCase().replaceAll("\\s", ""); // b
		String originalPlace = townAndState.trim().replaceAll(",", "").toLowerCase().replaceAll("\\s", ""); // a
		int compare = originalPlace.compareTo(paramPlace);

		return compare;
	}
	

}
