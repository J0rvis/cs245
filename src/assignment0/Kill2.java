package assignment0;
/**
 * 
 * @author Hannah Borreson
 * 
 * @version 1/29/2017
 * 
 * 
 *
 */
public class Kill2 {

	/**
	 * 
	 * The main purpose of the Kill2 program is to take in a string and print it
	 * back to command prompt without the second character. In cases where the
	 * string is not entered or the string contains less than 2 characters a
	 * message is printed explaining why the program was closed.
	 *
	 */
	public static void main(String[] args) {

		/*
		 * The first two 'if' statements sift out the situations that cause
		 * exceptions to be thrown and thusly making it 'die nicely'. These if
		 * statement print explanations to why the program didn't work and exits
		 * the program. The '0' in 'System.exit(0);' means that it was
		 * successful and not technically containing an error. This specific
		 * 'if' statement cancels out the situation where no string is added in
		 * command prompt
		 */
		if (args.length != 1) {
			System.err.println("Not enough information has been entered to satisfy the parameters of the problem.");
			System.exit(0);
		}

		/*
		 * This 'if' statement cancels out the situation where the word entered
		 * in command prompt is too short and can't have the second character
		 * omitted. Once again this 'if' statement ends with 'System.exit(0);'
		 * in order to nicely kill the program to avoid errors and exceptions.
		 */
		if (args[0].length() < 2) {
			System.err.println("This word is too short.");
			System.exit(0);

			/*
			 * This 'if' statement doesn't need to be closed because it can only
			 * be run in a safe situation and the program can run without any
			 * issues. This 'if' statement prints the first character of the
			 * given string and all the characters after the omitted character.
			 */
		} else {
			System.out.println(args[0].charAt(0) + args[0].substring(2));

		}

	}
}
