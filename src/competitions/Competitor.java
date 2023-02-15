package competitions;

import competitions.display.util.Colors;

/**
 * Defining a competitor, that can participate to any kind of competition
 */
public class Competitor {

	/** the name of the competitor */
	private final String name;
	/** the points of the competitor (reset to 0 at the start of each competition */
	private int points;
	/** the color used for this competitor */
	private Colors color;
	/** the quotation of the competitor*/
	private double quotation;

	/**
	 * Creates a new instance of Competitor
	 * 
	 * @param name the name of the competitor
	 */
	public Competitor(String name) {
		this.name = name;
		this.points = 0;
		this.color = Colors.randomColor();
		this.quotation = 2;
	}

	/**
	 * Creates a new instance of Competitor
	 * 
	 * @param name  the name of the competitor
	 * @param color the color to put when displayed
	 */
	public Competitor(String name, Colors color) {
		this(name);
		this.color = color;
	}

	/**
	 * Adds points to the total of the Competitor's points
	 * 
	 * @param points the number of points to add
	 */
	public void addPoints(int points) {
		this.points += points;
	}

	/**
	 * Gets points of the Competitor
	 * 
	 * @return points of the Competitor
	 */
	public int getPoints() {
		return this.points;
	}

	/**
	 * Sets Competitor's points
	 * 
	 * @param points the number of points to set
	 */
	public void setPoints(int points) {
		this.points = points;
	}

	/**
	 * Gets Competitor's name
	 * 
	 * @return Competitor's name
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Gets Competitor's name in color
	 * 
	 * @return Competitor's name in color
	 */
	public String getColoredName() {
		return Colors.colorize(this.name, this.color);
	}

	/**
	 * Gets Competitor's color
	 * 
	 * @return Competitor's color
	 */
	public Colors getColor() {
		return this.color;
	}
	
	/**
	 * Gets Competitor's quotation
	 * @return Competitor's quotation
	 */
	public double getQuotation() {
		return this.quotation;
	}
	
	/**
	 * Sets Competitor's quotation
	 * @param quotation the new quotation
	 */
	public void setQuotation(double quotation) {
		this.quotation = quotation;
	}
}