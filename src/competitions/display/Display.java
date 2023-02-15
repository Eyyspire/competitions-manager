package competitions.display;

/**
 * Defines a display interface. The classes implementing it will define the medium
 *
 */
public interface Display {

	/**
	 * Print a message
	 * @param message : the message to print   
	 */
	public void print(String message);

	/**
	 * Print a formatted message
	 * @param message : the message to print  
	 * @param vargs : the elements that the string will replace
	 */
	public void printf(String message, Object... vargs);

	/**
	 * Print a message and goes to a new line
	 * @param message : the message to print  
	 */
	public void println(String message);
}



