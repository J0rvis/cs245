package assignment03;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * File: Bioinfo.java
 * 
 * @author: Hannah Borreson
 * 
 * @version 3/27/2017
 * 
 *          Description: The main purpose of the Bioinfo program is to create a
 *          series of methods to read and manipulate DNA/RNA strings from a text
 *          file. The vital methods (excluding methods that were helper methods
 *          or variations of the following) included are read, create sequences,
 *          insert, copy, clip, swap, overlap, transcribe, and translate.
 * 
 */

public class Bioinfo {

	private static Sequence[] sequences;

	public static void main(String[] args) throws FileNotFoundException {

		if (args.length != 2) {
			System.err.print("Not enough information has been entered to satisfy the parameters of the problem.");
			System.exit(0);
		}
		createSequence(Integer.parseInt(args[0]));
		read(new File(args[1]));

	}

	/**
	 * Inserts sequence to position pos in the sequence array. It creates the
	 * sequence using the parameters, int position, String DNA/RNA/AA type, and
	 * takes the bases from a Sequence with a SLList of char bases
	 * 
	 * @param pos
	 * @param type
	 * @param bases
	 **/
	public static void insert(int pos, String type, Sequence bases) {
		if (pos < 0 || pos >= sequences.length) {
			System.out.println("Invalid position to insert at position:" + pos);
			return;
		}
		if ((type.equals("dna") && contains(bases.getBases(), 'u'))
				|| (type.equals("dna") && !isDNA(bases.getBases()))) {

			System.out.println("Given bases were not correct for given type to insert at position: " + pos);
			return;
		}
		if ((type.equals("rna") && contains(bases.getBases(), 't'))
				|| (type.equals("rna") && !isRNA(bases.getBases()))) {
			System.out.println("Given bases were not correct for given type to insert at position: " + pos);
			return;
		} else {
			if (contains(bases.getBases(), 'E')) {

				bases.setBases(toSLList(""));
				sequences[pos] = new Sequence(type, bases.getBases());

				return;
			}
			sequences[pos] = new Sequence(type, bases.getBases());

		}

	}

	/** Removes the sequence at position pos in the sequence array. 
	 * 
	 * @param pos
	 * **/
	public static void remove(int pos) {
		if (pos < 0 || pos >= sequences.length) {
			System.out.println("Invalid position to insert at position:" + pos);
			return;
		}

		if (sequences[pos].getType().equals("")) {
			System.out.println("There is no sequence to remove at position: " + pos);
			return;
		}
		sequences[pos].setBases(new SLList());
		sequences[pos].setType("");

	}

	/**
	 * Prints out all sequences in the sequence array including the position.
	 * 
	 **/
	public static void print() {
		for (int i = 0; i < sequences.length; i++) {
			System.out.println("" + i + " " + sequences[i].getType().toString().toUpperCase() + " "
					+ sequences[i].getBases().toString().toUpperCase());
		}
	}

	/**
	 * Print the index and the type at position pos in the sequence array and
	 * the bases.
	 * 
	 * 
	 * @param pos
	 **/
	public static void printPos(int pos) {
		if (pos < 0 || pos >= sequences.length) {
			System.out.println("Invalid position to print at position:" + pos);
			return;
		}
		if (sequences[pos].getType().equals("")) {
			System.out.println("There is no sequence at position: " + pos);
			return;
		}

		System.out.println("" + pos + " " + sequences[pos].getType().toString().toUpperCase() + " "
				+ sequences[pos].getBases().toString().toUpperCase());

	}

	/**
	 * Replace the sequence at position pos with a clipped version of that
	 * sequence. The clipped version is that part of the sequence beginning at
	 * character start and ending with character end.
	 * 
	 * @param pos
	 * @param start
	 * @param end
	 **/
	public static void clipPosStartEnd(int pos, int start, int end) {
		// sequence[pos] starts at start ends at end
		// start < end

		if (pos < 0 || pos >= sequences.length) {
			System.out.println("Invalid position to clip at position:" + pos);
			return;
		}
		if (start >= sequences[pos].getBases().length() || start < 0 || end < 0) {
			System.out.println("Invalid clip indexes passed.");
			return;
		}
		if (end >= sequences[pos].getBases().length()) {
			System.out.println("Stop cannot be greater than list length.");
			return;
		}
		if (sequences[pos].getType().equals("")) {
			System.out.println("There is no sequence to clip at position: " + pos);
			return;
		}
		if (end < start) {
			sequences[pos].setBases(new SLList());
		}
		SLList<Character> temp = new <Character>SLList();
		for (int i = start; i <= end; i++) {
			char c = (char) sequences[pos].getBases().getValue(i);
			temp.add(c);
		}

		sequences[pos].setBases(temp);

	}

	/**
	 * Replace the sequence at position pos with a clipped version of the
	 * sequence. The clipped version is that part of the sequence beginning at
	 * character start and continuing to the end of the original sequence.
	 * 
	 * @param pos
	 * @param start
	 ***/
	public static void clipPosStart(int pos, int start) {

		if (start < 0 || start >= sequences[pos].getBases().length()) {
			System.out.println("Invalid clip indexes passed.");
			return;
		}
		if (sequences[pos].getType().equals("")) {
			System.out.println("There is no sequence to clip at position: " + pos);
			return;
		}

		SLList<Character> temp = new <Character>SLList();
		for (int i = start; i < sequences[pos].getBases().length(); i++) {
			char c = (char) sequences[pos].getBases().getValue(i);
			temp.add(c);
		}

		sequences[pos].setBases(temp);

	}

	/**
	 * Insert the given bases to the sequence at position pos in the sequence
	 * array starting at the base of the start position.
	 * 
	 * @param index
	 * @param type
	 * @param bases
	 * @param start
	 **/
	public static void splice(int index, String type, SLList bases, int start) {

		if (index < 0 || index >= sequences.length) {
			System.out.println("Invalid position to insert at position:" + index);
			return;
		}
		if (start < 0 || start >= sequences[index].getBases().length()) {
			System.out.println("Invalid position to insert at position:" + index);
			return;
		}

		if (!sequences[index].getType().equals(type)) {
			System.out.println("The sequence you want to splice in is not made of valid bases.");
			return;
		}

		if ((type.equals("dna") && contains(bases, 'u')) || (type.equals("dna") && !isDNA(bases))) {
			System.out.println("The sequence you want to splice in is not made of valid bases.");
			return;
		}
		if ((type.equals("rna") && contains(bases, 't')) || (type.equals("rna") && !isRNA(bases))) {
			System.out.println("The sequence you want to splice in is not made of valid bases.");
			return;
		}
		sequences[index].getBases().insertList(bases, start - 1);

	}

	/**
	 * Copies the sequence in position pos1 to pos2. Copies each node to a
	 * temporary list first to avoid simply making a reference to the original.
	 * 
	 * @param pos1
	 * @param pos2
	 */
	public static void copy(int pos1, int pos2) {
		if (pos1 < 0 || pos1 >= sequences.length) {
			System.out.println("Invalid position to copy at position: " + pos1);
			return;
		}
		if (pos2 < 0 || pos2 >= sequences.length) {
			System.out.println("Invalid position to copy at position: " + pos2);
			return;
		}

		if (sequences[pos1].getType().equals("")) {
			System.out.println("There is no sequence to copy at position: " + pos1);
			return;
		}

		SLList<Character> temp = new <Character>SLList();
		for (int i = 0; i < sequences[pos1].getBases().length(); i++) {
			char c = (char) sequences[pos1].getBases().getValue(i);
			temp.add(c);
		}

		sequences[pos2].setBases(temp);
		sequences[pos2].setType(sequences[pos1].getType());

	}

	/**
	 * Swap the tails of the sequences at positions pos1 and pos2. The tail for
	 * pos1 begins at character located at start1 in the linked list of bases
	 * and the tail for pos2 begins at character located at start2 in the linked
	 * list of bases.
	 * 
	 * @param pos1
	 * @param start1
	 * @param pos2
	 * @param start2
	 **/

	public static void swap(int pos1, int start1, int pos2, int start2) {
		if (pos1 < 0 || pos1 >= sequences.length) {
			System.out.println("Invalid position to swap at position: " + pos1);
			return;
		}

		if (pos2 < 0 || pos2 >= sequences.length) {
			System.out.println("Invalid position to swap at position: " + pos2);
			return;
		}

		if (sequences[pos1].getType().equals("") || sequences[pos1].getBases().equals("")) {
			System.out.println("There are no sequences to swap at positions:" + pos1);
			return;
		}

		if (sequences[pos2].getType().equals("") || sequences[pos2].getBases().equals("")) {
			System.out.println("There are no sequences to swap at positions:" + pos2);
			return;
		}

		if (!sequences[pos1].getType().equals(sequences[pos2].getType())) {
			System.out.println("Have to be same type to swap");
			return;
		}

		if (start1 > sequences[pos1].getBases().length() || start2 > sequences[pos2].getBases().length() || start1 < 0
				|| start2 < 0) {
			System.out.println("Start position of swap is not valid.");
			return;
		}

		// capture from start to and place in pos1
		if (start1 == sequences[pos1].getBases().length() && start2 != sequences[pos2].getBases().length()) {
			SLList<Character> endPos2 = new <Character>SLList();
			for (int i = start2; i < sequences[pos2].getBases().length(); i++) {
				char c = (char) sequences[pos2].getBases().getValue(i);
				endPos2.add(c);

			}

			clipPosStartEnd(pos2, 0, start2 - 1);
			sequences[pos1].getBases().insertList(endPos2, start1);
			return;
		}

		// capture from start to end and place in pos2
		if (start2 == sequences[pos2].getBases().length() && start1 != sequences[pos1].getBases().length()) {
			SLList<Character> endPos1 = new <Character>SLList();
			for (int i = start1; i < sequences[pos1].getBases().length(); i++) {
				char c = (char) sequences[pos1].getBases().getValue(i);
				endPos1.add(c);
			}
			clipPosStartEnd(pos1, 0, start1 - 1);
			sequences[pos2].getBases().insertList(endPos1, start2);
			return;
		}

		SLList<Character> temp1 = new <Character>SLList();
		for (int i = start1; i < sequences[pos1].getBases().length(); i++) {
			char c = (char) sequences[pos1].getBases().getValue(i);
			sequences[pos1].getBases().remove(i);
			temp1.add(c);
		}

		SLList<Character> temp2 = new <Character>SLList();
		for (int i = start2; i < sequences[pos2].getBases().length(); i++) {
			char c = (char) sequences[pos2].getBases().getValue(i);
			sequences[pos2].getBases().remove(i);
			temp2.add(c);
		}
		sequences[pos2].getBases().insertList(temp1, start2);
		sequences[pos1].getBases().insertList(temp2, start1);

	}

	/**
	 * Determine the position of maximum overlap between the sequences at
	 * positions pos1 and pos2. The maximum overlap is the position such that
	 * the maximum number of characters match in the two sequences, where the
	 * overlapping characters must be a suffix of the sequence at pos1 and a
	 * prefix of the sequence at pos2. Print out the position of the start of
	 * the overlap of the sequence at pos1 and the overlapping subsequence. No
	 * sequences are altered.
	 * 
	 * 
	 * @param pos1
	 * @param pos2
	 **/
	public static void overlap(int pos1, int pos2) {
		if (pos1 < 0 || pos1 >= sequences.length) {
			System.out.println("Invalid position to find overlap at position: " + pos1);
			return;
		}
		if (pos2 < 0 || pos2 >= sequences.length) {
			System.out.println("Invalid position to find overlap at position: " + pos2);
			return;
		}

		if (!sequences[pos1].getType().equals(sequences[pos2].getType())) {
			System.out.println("Sequences must be of the same type for overlap method.");
			return;
		}

		if (sequences[pos1].getType().equals("") || sequences[pos1].getBases().equals("")) {
			System.out.println("There are no sequences to find overlap in at positions:" + pos1);
			return;
		}

		if (sequences[pos2].getType().equals("") || sequences[pos2].getBases().equals("")) {
			System.out.println("There are no sequences to to find overlap in at positions:" + pos2);
			return;
		}
		if ((sequences[pos1].getBases().length() == 1 || sequences[pos2].getBases().length() == 1)
				&& !sequences[pos2].getBases().getValue(0)
						.equals(sequences[pos1].getBases().getValue(sequences[pos1].getBases().length() - 1))) {
			System.out.println("There is no overlap in these sequences");
			return;
		}

		int position = makeEqual(sequences[pos1].getBases(), sequences[pos2].getBases());
		System.out.println("Overlap starts at pos " + position + "; bases that overlap "
				+ SLList.toSimpleString(sequences[pos1].getBases(), position));

	}

	/**
	 * Transcription converts a DNA sequence to an RNA sequence at pos1. The
	 * conversion occurs by changing its type field to RNA, converting any
	 * occurrences of T to U, complementing all the letters in the sequence
	 * (Letters A and U are complements of each other, and letters C and G are
	 * complements of each other), and by reversing the sequence.
	 * 
	 * 
	 * @param pos
	 **/
	public static void transcribe(int pos) {
		if (pos < 0 || pos >= sequences.length) {
			System.out.println("Invalid position to transcribe at position: " + pos);
			return;
		}

		if (!sequences[pos].getType().equals("dna")) {
			System.out.println("Sequence type must be DNA.");
			return;
		}
		if (sequences[pos].getType().equals("")) {
			System.out.println("There are no sequences at position:" + pos);
			return;
		}

		for (int i = 0; i < sequences[pos].getBases().length(); i++) {
			if (sequences[pos].getBases().getValue(i).equals('t')) {
				sequences[pos].getBases().insert(i, 'u');
				sequences[pos].getBases().remove(i + 1);
			}

			if (sequences[pos].getBases().getValue(i).equals('a')) {
				sequences[pos].getBases().insert(i, 'u');
				sequences[pos].getBases().remove(i + 1);
			} else if (sequences[pos].getBases().getValue(i).equals('u')) {
				sequences[pos].getBases().insert(i, 'a');
				sequences[pos].getBases().remove(i + 1);
			} else if (sequences[pos].getBases().getValue(i).equals('g')) {
				sequences[pos].getBases().insert(i, 'c');
				sequences[pos].getBases().remove(i + 1);
			} else if (sequences[pos].getBases().getValue(i).equals('c')) {
				sequences[pos].getBases().insert(i, 'g');
				sequences[pos].getBases().remove(i + 1);
			}

		}
		sequences[pos].getBases().reverse();
		sequences[pos].setType("rna");
	}

	/**
	 * Translation converts an RNA sequence into an amino acid sequence at pos1.
	 * The type is changed from RNA to AA. Translation involves converting
	 * groups of three RNA bases into their equivalent amino acid after finding
	 * a grouping of AUG.
	 * 
	 * @param pos
	 **/
	public static void translate(int pos) {
		if (pos < 0 || pos >= sequences.length) {
			System.out.println("Invalid position to translate at position: " + pos);
			return;
		}
		if (sequences[pos].getType().equals("dna")) {
			System.out.println("Sequence type must be RNA.");
			return;
		}
		if (sequences[pos].getType().equals("")) {
			System.out.println("There are no sequences at position:" + pos);
			return;
		}
		if (containsThree(sequences[pos].getBases(), 'a', 'u', 'g') == -1) {
			System.out.println("There is no open reading frame in this sequence. ");
			return;
		}

		SLList temp = sequences[pos].getBases().copy(sequences[pos].getBases());
		clipPosStart(pos, containsThree(sequences[pos].getBases(), 'a', 'u', 'g'));
		if (endByThrees(0, sequences[pos].getBases(), 'u', 'a', 'a') == -1
				&& endByThrees(0, sequences[pos].getBases(), 'u', 'a', 'g') == -1
				&& endByThrees(0, sequences[pos].getBases(), 'u', 'g', 'a') == -1) {
			System.out.println("There is no open reading frame in this sequence. ");
			sequences[pos].setBases(temp);
			return;
		}

		if (sequences[pos].getBases().length() % 3 != 0) {
			while (sequences[pos].getBases().length() % 3 != 0) {
				sequences[pos].getBases().add('0');
			}
		}

		for (int i = 0; i < sequences[pos].getBases().length(); i++) {
			if ((containByThrees(i, sequences[pos].getBases(), 'u', 'a', 'a'))) {

				clipPosStartEnd(pos, 0, i - 1);
				sequences[pos].setType("aa");

				return;
			} else if ((containByThrees(i, sequences[pos].getBases(), 'u', 'a', 'g'))) {
				clipPosStartEnd(pos, 0, i - 1);
				sequences[pos].setType("aa");

				return;
			} else if ((containByThrees(i, sequences[pos].getBases(), 'u', 'g', 'a'))) {

				clipPosStartEnd(pos, 0, i - 1);
				sequences[pos].setType("aa");

				return;

				// F

			} else

			if (containByThrees(i, sequences[pos].getBases(), 'u', 'u', 'u')) {
				removeThree(pos, i);
				sequences[pos].getBases().insert(i, 'f');

				sequences[pos].setType("aa");

			} else if (containByThrees(i, sequences[pos].getBases(), 'u', 'u', 'c')) {
				removeThree(pos, i);
				sequences[pos].getBases().insert(i, 'f');

				sequences[pos].setType("aa");

			} else

			// L
			if (containByThrees(i, sequences[pos].getBases(), 'u', 'u', 'a')) {
				removeThree(pos, i);
				sequences[pos].getBases().insert(i, 'l');
				sequences[pos].setType("aa");
				sequences[pos].setType("aa");

			} else if (containByThrees(i, sequences[pos].getBases(), 'u', 'u', 'g')) {
				removeThree(pos, i);
				sequences[pos].getBases().insert(i, 'l');
				sequences[pos].setType("aa");

			} else if (containByThrees(i, sequences[pos].getBases(), 'c', 'u', 'u')) {
				removeThree(pos, i);
				sequences[pos].getBases().insert(i, 'l');
				sequences[pos].setType("aa");

			} else if (containByThrees(i, sequences[pos].getBases(), 'c', 'u', 'c')) {
				removeThree(pos, i);
				sequences[pos].getBases().insert(i, 'l');
				sequences[pos].setType("aa");

			} else if (containByThrees(i, sequences[pos].getBases(), 'c', 'u', 'a')) {
				removeThree(pos, i);
				sequences[pos].getBases().insert(i, 'l');
				sequences[pos].setType("aa");

			} else if (containByThrees(i, sequences[pos].getBases(), 'c', 'u', 'g')) {
				removeThree(pos, i);
				sequences[pos].getBases().insert(i, 'l');
				sequences[pos].setType("aa");

			} else

			// I
			if (containByThrees(i, sequences[pos].getBases(), 'a', 'u', 'u')) {
				removeThree(pos, i);
				sequences[pos].getBases().insert(i, 'i');
				sequences[pos].setType("aa");

			} else if (containByThrees(i, sequences[pos].getBases(), 'a', 'u', 'c')) {
				removeThree(pos, i);
				sequences[pos].getBases().insert(i, 'i');
				sequences[pos].setType("aa");

			} else if (containByThrees(i, sequences[pos].getBases(), 'a', 'u', 'a')) {
				removeThree(pos, i);
				sequences[pos].getBases().insert(i, 'i');
				sequences[pos].setType("aa");

			} else

			// M
			if ((containByThrees(i, sequences[pos].getBases(), 'a', 'u', 'g'))) {
				removeThree(pos, i);
				sequences[pos].getBases().insert(i, 'm');
				sequences[pos].setType("aa");

			} else

			// V
			if (containByThrees(i, sequences[pos].getBases(), 'g', 'u', 'u')) {
				removeThree(pos, i);
				sequences[pos].getBases().insert(i, 'v');
				sequences[pos].setType("aa");

			} else if (containByThrees(i, sequences[pos].getBases(), 'g', 'u', 'c')) {
				removeThree(pos, i);
				sequences[pos].getBases().insert(i, 'v');
				sequences[pos].setType("aa");

			} else if (containByThrees(i, sequences[pos].getBases(), 'g', 'u', 'a')) {
				removeThree(pos, i);
				sequences[pos].getBases().insert(i, 'v');
				sequences[pos].setType("aa");

			} else if (containByThrees(i, sequences[pos].getBases(), 'g', 'u', 'g')) {
				removeThree(pos, i);
				sequences[pos].getBases().insert(i, 'v');
				sequences[pos].setType("aa");

			} else

			// S
			if (containByThrees(i, sequences[pos].getBases(), 'u', 'c', 'u')) {
				removeThree(pos, i);
				sequences[pos].getBases().insert(i, 's');
				sequences[pos].setType("aa");

			} else if (containByThrees(i, sequences[pos].getBases(), 'u', 'c', 'c')) {
				removeThree(pos, i);
				sequences[pos].getBases().insert(i, 's');
				sequences[pos].setType("aa");

			} else if (containByThrees(i, sequences[pos].getBases(), 'u', 'c', 'a')) {
				removeThree(pos, i);
				sequences[pos].getBases().insert(i, 's');
				sequences[pos].setType("aa");

			} else if (containByThrees(i, sequences[pos].getBases(), 'u', 'c', 'g')) {
				removeThree(pos, i);
				sequences[pos].getBases().insert(i, 's');
				sequences[pos].setType("aa");

			} else

			// P
			if (containByThrees(i, sequences[pos].getBases(), 'c', 'c', 'u')) {
				removeThree(pos, i);
				sequences[pos].getBases().insert(i, 'p');
				sequences[pos].setType("aa");

			} else if (containByThrees(i, sequences[pos].getBases(), 'c', 'c', 'c')) {
				removeThree(pos, i);
				sequences[pos].getBases().insert(i, 'p');
				sequences[pos].setType("aa");

			} else if (containByThrees(i, sequences[pos].getBases(), 'c', 'c', 'a')) {
				removeThree(pos, i);
				sequences[pos].getBases().insert(i, 'p');
				sequences[pos].setType("aa");

			} else if (containByThrees(i, sequences[pos].getBases(), 'c', 'c', 'g')) {
				removeThree(pos, i);
				sequences[pos].getBases().insert(i, 'p');
				sequences[pos].setType("aa");

			} else

			// T
			if (containByThrees(i, sequences[pos].getBases(), 'a', 'c', 'u')) {
				removeThree(pos, i);
				sequences[pos].getBases().insert(i, 't');
				sequences[pos].setType("aa");

			} else if (containByThrees(i, sequences[pos].getBases(), 'a', 'c', 'c')) {
				removeThree(pos, i);
				sequences[pos].getBases().insert(i, 't');
				sequences[pos].setType("aa");

			} else if (containByThrees(i, sequences[pos].getBases(), 'a', 'c', 'a')) {
				removeThree(pos, i);
				sequences[pos].getBases().insert(i, 't');
				sequences[pos].setType("aa");

			} else if (containByThrees(i, sequences[pos].getBases(), 'a', 'c', 'g')) {
				removeThree(pos, i);
				sequences[pos].getBases().insert(i, 't');
				sequences[pos].setType("aa");

			} else

			// A
			if (containByThrees(i, sequences[pos].getBases(), 'g', 'c', 'u')) {
				removeThree(pos, i);
				sequences[pos].getBases().insert(i, 'a');
				sequences[pos].setType("aa");

			} else if (containByThrees(i, sequences[pos].getBases(), 'g', 'c', 'c')) {
				removeThree(pos, i);
				sequences[pos].getBases().insert(i, 'a');
				sequences[pos].setType("aa");

			} else if (containByThrees(i, sequences[pos].getBases(), 'g', 'c', 'a')) {
				removeThree(pos, i);
				sequences[pos].getBases().insert(i, 'a');
				sequences[pos].setType("aa");

			} else if (containByThrees(i, sequences[pos].getBases(), 'g', 'c', 'g')) {
				removeThree(pos, i);
				sequences[pos].getBases().insert(i, 'a');
				sequences[pos].setType("aa");

			} else

			// Y
			if (containByThrees(i, sequences[pos].getBases(), 'u', 'a', 'u')) {
				removeThree(pos, i);
				sequences[pos].getBases().insert(i, 'y');
				sequences[pos].setType("aa");

			} else if (containByThrees(i, sequences[pos].getBases(), 'u', 'a', 'c')) {
				removeThree(pos, i);
				sequences[pos].getBases().insert(i, 'y');
				sequences[pos].setType("aa");

			} else

			// H
			if (containByThrees(i, sequences[pos].getBases(), 'c', 'a', 'u')) {
				removeThree(pos, i);
				sequences[pos].getBases().insert(i, 'h');
				sequences[pos].setType("aa");

			} else if (containByThrees(i, sequences[pos].getBases(), 'c', 'a', 'c')) {
				removeThree(pos, i);
				sequences[pos].getBases().insert(i, 'h');
				sequences[pos].setType("aa");

			} else

			// Q
			if (containByThrees(i, sequences[pos].getBases(), 'c', 'a', 'a')) {
				removeThree(pos, i);
				sequences[pos].getBases().insert(i, 'q');
				sequences[pos].setType("aa");

			} else if (containByThrees(i, sequences[pos].getBases(), 'c', 'a', 'g')) {
				removeThree(pos, i);
				sequences[pos].getBases().insert(i, 'q');
				sequences[pos].setType("aa");

			} else

			// N
			if (containByThrees(i, sequences[pos].getBases(), 'a', 'a', 'u')) {
				removeThree(pos, i);
				sequences[pos].getBases().insert(i, 'n');
				sequences[pos].setType("aa");

			} else if (containByThrees(i, sequences[pos].getBases(), 'a', 'a', 'c')) {
				removeThree(pos, i);
				sequences[pos].getBases().insert(i, 'n');
				sequences[pos].setType("aa");

			} else

			// K
			if (containByThrees(i, sequences[pos].getBases(), 'a', 'a', 'a')) {
				removeThree(pos, i);
				sequences[pos].getBases().insert(i, 'k');
				sequences[pos].setType("aa");

			} else if (containByThrees(i, sequences[pos].getBases(), 'a', 'a', 'g')) {
				removeThree(pos, i);
				sequences[pos].getBases().insert(i, 'k');
				sequences[pos].setType("aa");

			} else

			// D
			if (containByThrees(i, sequences[pos].getBases(), 'g', 'a', 'u')) {
				removeThree(pos, i);
				sequences[pos].getBases().insert(i, 'd');
				sequences[pos].setType("aa");

			} else if (containByThrees(i, sequences[pos].getBases(), 'g', 'a', 'c')) {
				removeThree(pos, i);
				sequences[pos].getBases().insert(i, 'd');
				sequences[pos].setType("aa");

			} else

			// E
			if (containByThrees(i, sequences[pos].getBases(), 'g', 'a', 'a')) {
				removeThree(pos, i);
				sequences[pos].getBases().insert(i, 'e');
				sequences[pos].setType("aa");

			} else if (containByThrees(i, sequences[pos].getBases(), 'g', 'a', 'g')) {
				removeThree(pos, i);
				sequences[pos].getBases().insert(i, 'e');
				sequences[pos].setType("aa");

			} else

			// C
			if (containByThrees(i, sequences[pos].getBases(), 'u', 'g', 'u')) {
				removeThree(pos, i);
				sequences[pos].getBases().insert(i, 'c');
				sequences[pos].setType("aa");

			} else if (containByThrees(i, sequences[pos].getBases(), 'u', 'g', 'c')) {
				removeThree(pos, i);
				sequences[pos].getBases().insert(i, 'c');
				sequences[pos].setType("aa");

			} else

			// W
			if (containByThrees(i, sequences[pos].getBases(), 'u', 'g', 'g')) {
				removeThree(pos, i);
				sequences[pos].getBases().insert(i, 'w');
				sequences[pos].setType("aa");

			} else

			// R
			if ((containByThrees(i, sequences[pos].getBases(), 'c', 'g', 'u'))) {
				removeThree(pos, i);
				sequences[pos].getBases().insert(i, 'r');
				sequences[pos].setType("aa");

			} else if (containByThrees(i, sequences[pos].getBases(), 'c', 'g', 'c')) {
				removeThree(pos, i);
				sequences[pos].getBases().insert(i, 'r');
				sequences[pos].setType("aa");

			} else if (containByThrees(i, sequences[pos].getBases(), 'c', 'g', 'a')) {
				removeThree(pos, i);
				sequences[pos].getBases().insert(i, 'r');
				sequences[pos].setType("aa");

			} else if (containByThrees(i, sequences[pos].getBases(), 'c', 'g', 'g')) {
				removeThree(pos, i);
				sequences[pos].getBases().insert(i, 'r');
				sequences[pos].setType("aa");

			} else

			// S
			if (containByThrees(i, sequences[pos].getBases(), 'a', 'g', 'u')) {
				removeThree(pos, i);
				sequences[pos].getBases().insert(i, 's');
				sequences[pos].setType("aa");

			} else if (containByThrees(i, sequences[pos].getBases(), 'a', 'g', 'c')) {
				removeThree(pos, i);
				sequences[pos].getBases().insert(i, 's');
				sequences[pos].setType("aa");

			} else

			// R
			if (containByThrees(i, sequences[pos].getBases(), 'a', 'g', 'a')) {
				removeThree(pos, i);
				sequences[pos].getBases().insert(i, 'r');
				sequences[pos].setType("aa");

			} else if (containByThrees(i, sequences[pos].getBases(), 'a', 'g', 'g')) {
				removeThree(pos, i);
				sequences[pos].getBases().insert(i, 'r');
				sequences[pos].setType("aa");

			} else

			// G
			if (containByThrees(i, sequences[pos].getBases(), 'g', 'g', 'u')) {
				removeThree(pos, i);
				sequences[pos].getBases().insert(i, 'g');
				sequences[pos].setType("aa");

			} else if (containByThrees(i, sequences[pos].getBases(), 'g', 'g', 'c')) {
				removeThree(pos, i);
				sequences[pos].getBases().insert(i, 'g');
				sequences[pos].setType("aa");

			} else if (containByThrees(i, sequences[pos].getBases(), 'g', 'g', 'a')) {
				removeThree(pos, i);
				sequences[pos].getBases().insert(i, 'g');
				sequences[pos].setType("aa");

			} else if (containByThrees(i, sequences[pos].getBases(), 'g', 'g', 'g')) {
				removeThree(pos, i);
				sequences[pos].getBases().insert(i, 'g');
				sequences[pos].setType("aa");

			}

		}

	}

	/**
	 * The public method read is used to scan in and organize the file pointed
	 * to in command prompt. It calls the necessary methods as they are found
	 * and organizes the data to be used in the parameters of the method calls.
	 * 
	 * @param file
	 **/
	public static void read(File file) throws FileNotFoundException {
		Scanner in = new Scanner(file);

		String rawInput = "";

		String param1 = "";
		String param2 = "";
		String param3 = "";
		String param4 = "";

		String[] parts = null;
		// reads file into a string of the same format
		while (in.hasNextLine()) {
			rawInput += in.nextLine().toLowerCase().trim().replaceAll("\\s", " ") + System.lineSeparator();
		}

		// turns the string into array of strings
		parts = rawInput.split("[\\r\\n]+");

		// debugs by printing out each line
		for (int i = 0; i < parts.length; i++) {
			if (parts[i].contains("insert")) {
				parts[i] = parts[i].replaceAll("insert", "");
				Scanner next = new Scanner(parts[i]).useDelimiter("\\s+");

				param1 = next.next().trim().replaceAll("\\s+", "");
				param2 = next.next().trim().replaceAll("\\s+", "");
				if (next.hasNext()) {
					param3 = next.next().trim().replaceAll("\\s+", "");
					insert(Integer.parseInt(param1), param2, new Sequence(param2, toSLList(param3)));
				} else {
					insert(Integer.parseInt(param1), param2, new Sequence(param2, toSLList(param3)));
				}

			}
			if (parts[i].contains("print")) {

				parts[i] = parts[i].replaceAll("print", "");
				Scanner next = new Scanner(parts[i]).useDelimiter("\\s+");
				if (next.hasNext()) {
					param1 = next.next().trim().replaceAll("\\s+", "");
					printPos(Integer.parseInt(param1));

				} else {
					print();
				}
			}
			if (parts[i].contains("remove")) {
				parts[i] = parts[i].replaceAll("remove", "");
				Scanner next = new Scanner(parts[i]).useDelimiter("\\s+");

				param1 = next.next().trim().replaceAll("\\s+", "");
				remove(Integer.parseInt(param1));

			}
			if (parts[i].contains("splice")) {
				parts[i] = parts[i].replaceAll("splice", "");
				Scanner next = new Scanner(parts[i]).useDelimiter("\\s+");

				param1 = next.next().trim().replaceAll("\\s+", "");
				param2 = next.next().trim().replaceAll("\\s+", "");
				param3 = next.next().trim().replaceAll("\\s+", "");
				param4 = next.next().trim().replaceAll("\\s+", "");
				splice(Integer.parseInt(param1), param2, toSLList(param3), Integer.parseInt(param4));

			}
			if (parts[i].contains("clip")) {
				parts[i] = parts[i].replaceAll("clip", "");
				Scanner next = new Scanner(parts[i]).useDelimiter("\\s+");

				param1 = next.next().trim().replaceAll("\\s+", "");
				param2 = next.next().trim().replaceAll("\\s+", "");

				if (next.hasNext()) {
					param3 = next.next().trim().replaceAll("\\s+", "");
					clipPosStartEnd(Integer.parseInt(param1), Integer.parseInt(param2), Integer.parseInt(param3));
				} else {
					clipPosStart(Integer.parseInt(param1), Integer.parseInt(param2));

				}

			}

			if (parts[i].contains("copy")) {
				parts[i] = parts[i].replaceAll("copy", "");
				Scanner next = new Scanner(parts[i]).useDelimiter("\\s+");

				param1 = next.next().trim().replaceAll("\\s+", "");
				param2 = next.next().trim().replaceAll("\\s+", "");
				copy(Integer.parseInt(param1), Integer.parseInt(param2));

			}
			if (parts[i].contains("swap")) {
				parts[i] = parts[i].replaceAll("swap", "");
				Scanner next = new Scanner(parts[i]).useDelimiter("\\s+");

				param1 = next.next().trim().replaceAll("\\s+", "");
				param2 = next.next().trim().replaceAll("\\s+", "");
				param3 = next.next().trim().replaceAll("\\s+", "");
				param4 = next.next().trim().replaceAll("\\s+", "");
				swap(Integer.parseInt(param1), Integer.parseInt(param2), Integer.parseInt(param3),
						Integer.parseInt(param4));

			}
			if (parts[i].contains("overlap")) {
				parts[i] = parts[i].replaceAll("overlap", "");
				Scanner next = new Scanner(parts[i]).useDelimiter("\\s+");

				param1 = next.next().trim().replaceAll("\\s+", "");
				param2 = next.next().trim().replaceAll("\\s+", "");
				overlap(Integer.parseInt(param1), Integer.parseInt(param2));

			}
			if (parts[i].contains("transcribe")) {
				parts[i] = parts[i].replaceAll("transcribe", "");
				Scanner next = new Scanner(parts[i]).useDelimiter("\\s+");

				param1 = next.next().trim().replaceAll("\\s+", "");
				transcribe(Integer.parseInt(param1));

			}
			if (parts[i].contains("translate")) {
				parts[i] = parts[i].replaceAll("translate", "");
				Scanner next = new Scanner(parts[i]).useDelimiter("\\s+");

				param1 = next.next().trim().replaceAll("\\s+", "");
				translate(Integer.parseInt(param1));

			}

		}
	}

	/**
	 * This method takes a string and turns it into a character based SLList of
	 * bases.
	 * 
	 * @param string
	 **/
	public static SLList toSLList(String string) {
		char[] converted = string.toCharArray();
		SLList<Character> converter = new <Character>SLList();
		for (int i = 0; i < converted.length; i++) {
			converter.add(converted[i]);
		}

		return converter;

	}

	/**
	 * This method checks to see if the SLList list contains char c by iterating
	 * through the SLList and comparing each node to the char.
	 * 
	 * @param list
	 * @param c
	 **/
	public static boolean contains(SLList list, char c) {

		for (int i = 0; i < list.length(); i++) {
			if (list.getValue(i).equals(c)) {
				return true;
			}

		}
		return false;

	}

	/**
	 * This method makes sure that the SLList bases contains only the characters
	 * a, t, c, and g. Thus proving that the SLList bases contains a valid DNA
	 * group of bases.
	 * 
	 * @param bases
	 **/
	public static boolean isDNA(SLList bases) {
		boolean bool = false;

		for (int i = 0; i < bases.length(); i++) {
			if (bases.getValue(i).equals('a') || bases.getValue(i).equals('t') || bases.getValue(i).equals('c')
					|| bases.getValue(i).equals('g')) {
				bool = true;
			}
			if (!bool) {
				return false;
			}
		}
		return bool;
	}

	/**
	 * This method makes sure that the SLList bases contains only the characters
	 * a, u, c, and g. Thus proving that the SLList bases contains a valid RNA
	 * group of bases.
	 * 
	 * @param bases
	 **/
	public static boolean isRNA(SLList bases) {
		boolean bool = false;

		for (int i = 0; i < bases.length(); i++) {
			if (bases.getValue(i).equals('a') || bases.getValue(i).equals('u') || bases.getValue(i).equals('c')
					|| bases.getValue(i).equals('g')) {
				bool = true;
			}
			if (!bool) {
				return false;
			}
		}
		return bool;
	}

	/**
	 * This method creates and initializes sequences for the sequence array to
	 * the size specified in command prompt.
	 * 
	 * @param size
	 **/
	public static void createSequence(int size) {
		sequences = new Sequence[size];
		for (int i = 0; i < sequences.length; i++) {
			sequences[i] = new Sequence();
		}
	}

	/**
	 * This method makes copies of two bases and trims them down to see at what
	 * point their suffixes/ prefixes overlap and is vital to the method
	 * overlap. By making copies of the SLLists the method avoids manipulating
	 * data contained in the actual array.
	 * 
	 * @param bases1
	 * @param bases2
	 **/
	public static int makeEqual(SLList bases1, SLList bases2) {
		SLList<Character> pos1 = bases1.copy(bases1);
		SLList<Character> pos2 = bases2.copy(bases2);
		int remove = 0;
		if (pos1.length() != pos2.length()) {
			if (pos1.length() < pos2.length()) {
				while (pos1.length() != pos2.length()) {
					pos2.remove(pos2.length() - 1);

				}

			} else if (pos1.length() > pos2.length()) {
				while (pos1.length() != pos2.length()) {
					pos1.removeFirst();
					remove++;
				}

			}
		}
		if (pos1.length() == pos2.length()) {

			while (!equal(pos1, pos2) && pos1.length() >= 0) {
				pos1.removeFirst();
				pos2.remove(pos2.length() - 1);
				remove++;

			}

		}
		return remove;
	}

	/**
	 * This method compares every element in two SLLists to see if they contain
	 * the same bases.
	 * 
	 * @param pos1
	 * @param pos2
	 **/
	public static boolean equal(SLList pos1, SLList pos2) {
		boolean bool = false;
		for (int i = 0; i < pos1.length(); i++) {
			if (pos1.getValue(i).equals(pos2.getValue(i))) {
				bool = true;
			} else {
				return false;

			}
		}
		return bool;

	}

	/**
	 * This method checks to see if the SLList in a sequence contain the
	 * specified chars. It iterates through every group of three consecutive
	 * chars and is used in the method translate to check for the starting
	 * pattern AUG.
	 * 
	 * @param list
	 * @param one
	 * @param two
	 * @param three
	 */
	public static int containsThree(SLList list, char one, char two, char three) {
		for (int i = 0; i < list.length(); i++) {
			if (list.getValue(i).equals(one) && list.getValue(i + 1).equals(two)
					&& list.getValue(i + 2).equals(three)) {
				return i;
			}
		}

		return -1;

	}

	/**
	 * This method checks to see if the next group of three chars in a sequence
	 * contain the specified chars and is vital in translate to find and replace
	 * certain patterns with singular abbreviations of amino acids.
	 * 
	 * @param start
	 * @param list
	 * @param one
	 * @param two
	 * @param three
	 */
	public static boolean containByThrees(int start, SLList list, char one, char two, char three) {
		SLList<Character> pos1 = list.copy(list);
		if (pos1.getValue(start).equals(one) && pos1.getValue(start + 1).equals(two)
				&& pos1.getValue(start + 2).equals(three)) {
			return true;

		}
		return false;
	}

	/**
	 * Is a method that checks through an entire SLList by every three chars to
	 * see if they match the given chars. Used in translate to make sure that
	 * there is an end pattern when translating. Int start is where the first
	 * occurrence of AUG happens.
	 * 
	 * @param start
	 * @param list
	 * @param one
	 * @param two
	 * @param three
	 */
	public static int endByThrees(int start, SLList list, char one, char two, char three) {
		SLList<Character> pos1 = list.copy(list);
		for (int i = start; i < pos1.length(); i += 3) {
			if (pos1.getValue(i).equals(one) && pos1.getValue(i + 1).equals(two)
					&& pos1.getValue(i + 2).equals(three)) {
				return i;

			}

		}
		return -1;
	}

	/**
	 * This method removes three consecutive chars in the sequence pos at the
	 * char base's index. Used in translate to delete the pattern of three chars
	 * to be replaced with a single char abbreviation of its amino acid. Made to
	 * save lines and avoid possible human error.
	 * 
	 * @param pos
	 * @param index
	 */
	public static void removeThree(int pos, int index) {
		sequences[pos].getBases().remove(index);
		sequences[pos].getBases().remove(index);
		sequences[pos].getBases().remove(index);

	}

}
