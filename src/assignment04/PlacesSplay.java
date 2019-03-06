package assignment04;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class PlacesSplay {

	/**
	 * File: PlacesSplay.java
	 * 
	 * @author: Hannah Borreson
	 * 
	 * @version 5/5/2017
	 * 
	 *          Description: The main purpose of the PlacesSplay program is to
	 *          create a command prompt friendly zip code finder. It reads the
	 *          file specified in commmand prompt and turns the .txt file into a
	 *          splay tree of Places.
	 * 
	 */
	public static void main(String[] args) throws FileNotFoundException {

		if (args.length != 1) {
			System.err.print("Not enough information has been entered to satisfy the parameters of the problem.");
			System.exit(0);
		}
		SplayTree<Place> splay = read(new File(args[0]));
		Scanner in = new Scanner(System.in);

		boolean yesNo = true;
		while (yesNo) {
			System.out.println("Height of tree: " + splay.getHeight());
			System.out.println("You want to search for the city: ");
			SplayNode<Place> node =  search(splay, in.nextLine());
			if (node != null) {
				System.out.println(node.getElement().zipsToString());
			}
			System.out.println("Do you want me to search again? ");
			if (!in.nextLine().startsWith("y")) {
				System.out.println("");
				System.out.println("Good Bye!");
				yesNo = false;
				break;
			}
		}

	}

	/**
	 * Reads in the specified .txt file and turns it into splay tree with the
	 * help of several helper methods.
	 * 
	 * @param file file to be read 
	 **/
	public static SplayTree<Place> read(File file) throws FileNotFoundException {
		Scanner in = new Scanner(file);

		String rawInput = "";

		String param1 = "";
		String param2 = "";
		String param3 = "";
		String param4 = "";

		String[] parts = null;
		// reads file into a string of the same format

		String first = in.nextLine();

		// read the file into a string to be pulled apart
		int count = 0;
		while (in.hasNextLine()) {
			rawInput += in.nextLine().toLowerCase().trim().replaceAll("\\s", " ").replaceAll("-", "")
					+ System.lineSeparator();
			// System.out.println("" + count);

			count++;

		}

		// get rid of coordinates
		rawInput = rawInput.replaceAll("\\b+?.[.].+?\\b", "");

		// turns the string into array of strings
		parts = rawInput.split("[\\r\\n]+");
		ArrayList<String[]> holdsLines = new ArrayList<String[]>();

		// splits each element into an array... places each array into arraylist
		for (int i = 0; i < parts.length; i++) {
			String[] words = parts[i].split("\\s+");
			String[] betterWords = new String[2];
			betterWords[0] = words[0];
			betterWords[1] = "" + words[1] + " " + words[2];
			if (words.length >= 4) {

				for (int k = 3; k < words.length; k++) {
					betterWords[1] += " " + words[k];
				}
			}
			holdsLines.add(betterWords);
		}

		// debugs by printing out each line Arrays.toString(parts)
		// System.out.println(toPlaceArray(holdsLines)[1].toString());
		Place[] B = findDuplicates(toPlaceArray(holdsLines));
		// System.out.println("Second Line To String: " + Arrays.toString(B));
		return toSplayTree(B);

	}

	/**
	 * Turns an arraylist of string arrays into a Place array so it can later be
	 * placed into a splay tree. It also removes duplicate towns and states and
	 * transferring their zip codes to the original town and state using a
	 * helper method.
	 * 
	 * @param holdsLines arraylist of string arrays to be turned into a Place array
	 **/
	public static Place[] toPlaceArray(ArrayList<String[]> holdsLines) {
		Place[] places = new Place[holdsLines.size()];

		for (int i = 0; i < holdsLines.size(); i++) {
			// add the zip as the first element in the arraylist string
			ArrayList<String> oneZip = new ArrayList<String>();
			oneZip.add(holdsLines.get(i)[0]);
			places[i] = new Place(holdsLines.get(i)[1], oneZip);
			// System.out.println(holdsLines.get(i)[1]);
		}

		List<Place> arrayList = Arrays.asList(places);
		Collections.sort(arrayList);
		for (int j = 0; j < arrayList.size(); j++) {
			places[j] = arrayList.get(j);
			// System.out.println(j + " " + places[j].toString());
		}

		return places;

	}

	/**
	 * Sets duplicate towns and states equal to null and transfers their zip
	 * codes to the original town and state.
	 * 
	 * @param places Place array to find duplicates in
	 **/

	public static Place[] findDuplicates(Place[] places) {

		int count = 0;
		Place[] tempPlaces = places;
		for (int i = 0; i < tempPlaces.length - 1; i++) {
			if (places[i].getTownAndState().equals(places[i + 1].getTownAndState())) {
				// two duplicates next to each other
				// add first arraylist onto second... delete first
				ArrayList<String> temp = places[i].getZipCodes();
				ArrayList<String> tempI = new ArrayList<String>();
				for (int k = 0; k < temp.size(); k++) {
					tempPlaces[i + 1].getZipCodes().add(temp.get(k));

				}

				tempPlaces[i] = null;
			}
		}
		return deleteNullElements(tempPlaces);
	}

	/**
	 * Makes sure that duplicate Places are deleted fully.
	 * 
	 * @param places Place array to delete null items in
	 **/

	public static Place[] deleteNullElements(Place[] places) {
		Place[] firstArray;

		ArrayList<Place> list = new ArrayList<Place>();

		// CHANGED
		for (int i = 0; i < places.length; i++) {
			if (places[i] != null) {
				list.add(places[i]);
			}
		}

		firstArray = list.toArray(new Place[list.size()]);
		return orderPlaces(firstArray);
	}

	/**
	 * Puts reorganizes the zip codes so they are in numerical order with-in
	 * their Place.
	 * 
	 * @param place Place array to put into alphabetical order 
	 **/
	public static Place[] orderPlaces(Place[] place) {

		for (int i = 0; i < place.length; i++) {
			Collections.sort(place[i].getZipCodes());
		}
		return place;
	}

	/**
	 * Places each Place into a splay tree and inserts them at a random order to
	 * make a more balanced tree (than if it were in alphabetical order).
	 * 
	 * @param placeArray Place array to be shuffled and put into a splay tree
	 **/
	public static SplayTree<Place> toSplayTree(Place[] placeArray) {
		SplayTree<Place> splayTree = new SplayTree();
		ArrayList<Place> placeList = new ArrayList<>();
		for (int i = 0; i < placeArray.length; i++) {
			placeList.add(placeArray[i]);
		}
		Collections.shuffle(placeList);

		for (int i = 0; i < placeList.size(); i++) {
			splayTree.insert(placeList.get(i));
		}
		System.out.println("Tree Size: " + splayTree.getSize());
		return splayTree;

	}

	/**
	 * Doesn't splay a tree while it searches for a certain Place. Used to find
	 * the comparisons without changing the tree.
	 * 
	 * @param splay splay tree to be searched in 
	 * @param townAndState string to be searched for
	 **/
	public static SplayNode<Place> search(SplayTree<Place> splay, String townAndState) {
		SplayNode<Place> node = splay.getRoot();
		int counter = 0;
		while (node != null) {
			int res = townAndState.toLowerCase().trim().replaceAll("\\s", "").replaceAll("-", "").replaceAll(",", "")
					.compareTo(node.getElement().getTownAndState().toLowerCase().trim().replaceAll("\\s", "")
							.replaceAll("-", "").replaceAll(",", ""));
			if (res < 0) {
				node = node.getLeft();
				counter++;
			} else if (res > 0) {
				node = node.getRight();
				counter++;
			} else {
				counter++;
				//System.out.println(/*"The number of comparisons needed to find the entry: "*/ + counter);
				splay.splay(node);
				return node;
			}
		}
		System.out.println("There is not a city by this name.");
		return null;

	}

}
