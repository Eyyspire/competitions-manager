package exceptions;

public class WrongAmountOfPlayersException extends RuntimeException {

	private static final long serialVersionUID = 1234;

	public WrongAmountOfPlayersException(String message) {
		super(message);
	}

}
