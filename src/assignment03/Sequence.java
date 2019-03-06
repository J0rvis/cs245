package assignment03;

/**
 * File: Sequence.java
 * 
 * @author: Hannah Borreson
 * 
 * @version 3/27/2017
 * 
 *          Description: The main purpose of the Sequence program is to create a
 *          sequence object that holds a string type DNA/RNA/AA and a SLList of
 *          bases for those types. It contains methods to get/set type, get
 *          length, and get/set the SLList bases in a sequence.
 * 
 */
public class Sequence {

	private String type; // DNA or RNA
	private SLList bases;

	public Sequence() {
		type = "";
		bases = new SLList();
	}

	public Sequence(String kind, SLList base) {
		// TODO Auto-generated constructor stub
		type = kind;
		bases = base;

	}

	/** Returns the type of the sequence. */
	public String getType() {
		return type;
	}

	/** Returns the bases of the sequence. */
	public SLList getBases() {
		return bases;

	}

	/** Changes the bases to the given SLList. 
	 * 
	 * @param base*/
	public void setBases(SLList base) {
		bases = base;

	}

	/** Changes the type to the given type 
	 * 
	 * @param kind*/
	public void setType(String kind) {
		type = kind;
	}

	/** Returns the length of the list of a sequence */
	public int getLength() {
		int temp = 0;
		temp = getBases().length();
		System.out.println(temp);
		return temp;
	}
}
