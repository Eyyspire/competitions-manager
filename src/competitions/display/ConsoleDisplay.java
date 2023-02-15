package competitions.display;

/**
 * implements the display interface and uses the console as displayer
 */
public class ConsoleDisplay implements Display {

	/**
	 * Redefines the System.out.print method
	 * @param message : the message to print   
	 */
	public void print(String message) {
		System.out.print(message);
	}

	/**
	 * Redefines the System.out.printf method
	 * @param message : the message to print 
	 * @param vargs : the elements that the string will replace
	 */
	public void printf(String message, Object... vargs) {
		System.out.printf(message, vargs);
	}

	/**
	 * Redefines the System.out.println method
	 * @param message : the message to print   
	 */
	public void println(String message) {
		System.out.println(message);
	}
}