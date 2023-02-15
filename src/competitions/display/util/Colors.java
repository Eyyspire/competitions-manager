package competitions.display.util;

import java.util.Random;

public enum Colors {
	RED("\u001B[31m"), CYAN("\u001B[36m"), YELLOW("\u001B[33m"), BRIGHTYELLOW("\u001B[93m"), GREEN("\u001B[32m"),
	BLUE("\u001B[34m"), PURPLE("\u001B[38:5:130m"), WHITE("\u001B[37m"), BLACK("\u001B[30m"),
	BRIGHTBLACK("\u001B[38:5:246m"), RESET("\u001B[0m");

	private String color;

	/**
	 * Creates an instance of Colors
	 * 
	 * @param color the color to create
	 */
	private Colors(String color) {
		this.color = color;
	}

	/**
	 * Gets the color
	 * 
	 * @return the color
	 */
	public String getColor() {
		return color;
	}

	/**
	 * Colorizes a given String in the given color
	 * 
	 * @param toColorize the String to colorize
	 * @param color      the color to use
	 * @return the new colorized String
	 */
	public static String colorize(String toColorize, Colors color) {
		return color.getColor() + toColorize + Colors.RESET.getColor();
	}

	/**
	 * Returns a random color
	 * 
	 * @return a random color
	 */
	public static Colors randomColor() {
		return Colors.values()[new Random().nextInt(Colors.values().length)];
	}

}
