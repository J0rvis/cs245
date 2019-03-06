package assignment04;
import javax.swing.plaf.synth.SynthSeparatorUI;

/**
 * File: TestSplay.java
 * 
 * @author: Hannah Borreson
 * 
 * @version 5/5/2017
 * 
 *          Description: Prints out a splay tree and acts as a debugger for the
 *          programmer to make sure that the classes concerning Splay Trees are
 *          right.
 * 
 */
public class TestSplay {
	public static void main(String[] args) {
		stringTree();

	}

	/**
	 * Inserts, removes, and prints a test splay tree of strings along with the
	 * size and height after every change in the tree.
	 */
	public static void stringTree() {
		SplayTree<String> st = new SplayTree<String>();
		System.out.println("***Inserts ******************************************************");
		System.out.println();
		System.out.println();

		System.out.println("Peculiar");
		st.insert("Peculiar");
		printOutDetails(st);

		System.out.println("Crapo");
		st.insert("Crapo");
		printOutDetails(st);

		System.out.println("Accident");
		st.insert("Accident");
		printOutDetails(st);

		System.out.println("Eau Claire");
		st.insert("Eau Claire");
		printOutDetails(st);

		System.out.println("Boring");
		st.insert("Boring");
		printOutDetails(st);

		System.out.println("Hell");
		st.insert("Hell");
		printOutDetails(st);

		System.out.println("Walla Walla");
		st.insert("Walla Walla");
		printOutDetails(st);

		System.out.println("Surprise");
		st.insert("Surprise");
		printOutDetails(st);

		System.out.println("Joseph");
		st.insert("Joseph");
		printOutDetails(st);

		System.out.println("Romance");
		st.insert("Romance");
		printOutDetails(st);

		System.out.println("Mars");
		st.insert("Mars");
		printOutDetails(st);

		System.out.println("Nuttsville");
		st.insert("Nuttsville");
		printOutDetails(st);

		System.out.println("Rough and Ready");
		st.insert("Rough and Ready");
		printOutDetails(st);

		System.out.println("Dynamite");
		st.insert("Dynamite");
		printOutDetails(st);

		System.out.println("Good Grief");
		st.insert("Good Grief");
		printOutDetails(st);

		System.out.println("Zarephath");
		st.insert("Zarephath");
		printOutDetails(st);

		System.out.println();
		System.out.println();

		System.out.println("***Removals ******************************************************");
		System.out.println();
		System.out.println();

		System.out.println("REMOVING: Eau Claire");
		st.remove("Eau Claire");
		printOutDetails(st);

		System.out.println("REMOVING: Accident");

		st.remove("Accident");
		printOutDetails(st);

		System.out.println("REMOVING: Rough and Ready");
		st.remove("Rough and Ready");
		printOutDetails(st);
		System.out.println("PRINT Out DETAILS AGAIN");
		printOutDetails(st);
		System.out.println();
		System.out.println();

		System.out.println("***Clear Tree ******************************************************");
		System.out.println();
		System.out.println();

		st.clear();
		printOutDetails(st);

		System.out.println("***INSERTING ALPHABETICALLY (A - Z)******************************************************");
		st.insert("Accident");
		st.insert("Boring");
		st.insert("Crapo");
		st.insert("Dynamite");
		st.insert("Eau Claire");
		st.insert("Good Grief");
		st.insert("Hell");
		st.insert("Joseph");
		st.insert("Mars");
		st.insert("Nuttsville");
		st.insert("Peculiar");
		st.insert("Romance");
		st.insert("Rough and Ready");
		st.insert("Surprise");
		st.insert("Walla Walla");
		st.insert("Zarephath");
		printOutDetails(st);
	}

	/**
	 * Prints out the tree by depth and by level along with their size and
	 * height. Has the proper formatting to ensure readability.
	 * @param st splay tree to have information printed out
	 * **/
	public static void printOutDetails(SplayTree<String> st) {
		System.out.println("*****By Depth **************");
		System.out.println("Printing by depth first:");
		st.printByDepth();
		System.out.println();

		System.out.println("*********By Level ************");
		System.out.println("Printing by level:");
		st.printLevelOrder();
		System.out.println();

		System.out.println("Size: " + st.getSize());
		System.out.println("Height: " + st.getHeight());
		System.out.println();
		System.out.println();
		System.out.println();
	}

}
