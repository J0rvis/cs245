package assignment0;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *  Answer to question: When you execute the questioned command then the
 *          ReadBackwards program will run using the "inputFile.txt" file as the
 *          first argument and write out the text backwards and make the
 *          "LookAtThis.txt" file as though it were the second argument. This
 *          new text file will be in the same directory as ReadBackwards and
 *          "inputFile.txt".
 * 
 * @author Hannah Borreson
 * 
 * @version 1/29/2017
 * 
 *        
 */

public class ReadBackwards {

	/**
	 * 
	 * The ReadBackwards program is supposed to take in one or two arguments
	 * that are path names. The first path name leads to a premade text file
	 * that contains a string to be written into the second path. Each word from
	 * the original file is to be read backwards making the first word the last
	 * and the last word the first and so forth. When no arguments are presented
	 * in command prompt an error message will be printed explaining that more
	 * information is needed for the program to run. When one argument is
	 * presented -an input text file- then the program will run as intended but
	 * printed instead of written into another file. When two arguments are
	 * presented the program will run as is stated above and the second argument
	 * will be the file that the information is written to as opposed to being
	 * printed to command prompt.
	 *
	 */

	public static void main(String[] args) throws IOException {

		/*
		 * The ArrayList was placed at the beginning of the class because it is
		 * to be used in most of the 'if' statements of this method.
		 */
		ArrayList<String> lines = new ArrayList<String>();

		/*
		 * The first two 'if' statements sift out the situations that cause
		 * exceptions to be thrown and thusly making it 'die nicely'. This first
		 * 'if' statement prints the explanation as to why the program didn't
		 * work and exits the program. The '0' in 'System.exit(0);' means that
		 * it was successful and not technically containing an error. This 'if'
		 * statement prints to command prompt that more information is needed to
		 * make the program run.
		 */

		if (args.length == 0) {
			System.err.print("Not enough information has been entered to satisfy the parameters of the problem.");
			System.exit(0);
		}

		/*
		 * This 'if' statement deals with a situation in which only one file
		 * path is put into command prompt. The program prints the string of the
		 * given file to the command prompt instead of writing out to a file- as
		 * there is no file to write out to.
		 * 
		 * It scans through the first file and writes out to the second file.
		 * The 'while' loop adds the newly scanned line to the end of a list
		 * until all the lines are in order from last to first in an ArrayList.
		 * 
		 */

		if (args.length == 1) {
			File inFile = new File(args[0]);
			Scanner in = new Scanner(inFile);

			while (in.hasNextLine()) {
				lines.add(0, in.nextLine());
			}

			/*
			 * This 'for' loop iterates through the recently created list made
			 * from the lines of a string. It retrieves a line and using a new
			 * scanner and a 'while' loop it reverses the words in each line.
			 */
			for (int i = 0; i < lines.size(); i++) {
				String changeIt = lines.get(i);
				Scanner elements = new Scanner(changeIt);
				String changed = "";
				while (elements.hasNext()) {
					changed = elements.next() + " " + changed;
				}
				lines.set(i, changed);
				elements.close();
			}

			/*
			 * This 'for' loop prints the reversed ArrayList of strings to
			 * command prompt as is required when only one argument is
			 * presented.
			 */
			for (int i = 0; i < lines.size(); i++) {
				System.out.println(lines.get(i) + " ");
			}
			in.close();

			/*
			 * This last 'if' statement is the perfect case in which two
			 * arguments are presented in command prompt. The input and output
			 * files are both created in this loop because their conditions can
			 * be satisfied. A PrintWriter is called so that the reversed string
			 * can be written back into another text document. The second
			 * argument presented is the output file. The inner workings of this
			 * 'if' statement is the same as the previous 'if' statement. All
			 * scanners and PrintWriters are closed when they are no longer
			 * needed.
			 */
		} else if (args.length == 2) {
			File inFile = new File(args[0]);
			Scanner in = new Scanner(inFile);
			File outFile = new File(args[1]);
			PrintWriter writer = new PrintWriter(outFile);

			while (in.hasNextLine()) {
				lines.add(0, in.nextLine());
			}

			for (int i = 0; i < lines.size(); i++) {
				String changeIt = lines.get(i);
				Scanner elements = new Scanner(changeIt);

				String changed = "";
				while (elements.hasNext()) {
					changed = elements.next() + " " + changed;
				}
				lines.set(i, changed);
				elements.close();
			}

			/*
			 * The 'for' loop iterates through each line and prints them to
			 * where the command prompt second argument directs it to be
			 * printed.
			 */
			for (int i = 0; i < lines.size(); i++) {

				writer.println(lines.get(i) + " ");
			}
			writer.close();
			in.close();

		}
	}
}
