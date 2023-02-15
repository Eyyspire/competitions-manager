package competitions.observer;

public class QuotDependingAlgorithm implements Algorithm{
	
	private double coeff;
	
	public QuotDependingAlgorithm(double coeff){
		this.coeff = coeff;
	}
	
	public double compute(double quot, double other) {
		return 1 + (quot - 1) * (Math.min(0.9, 0.5 + Math.abs(quot - other) * this.coeff));
	}

}
