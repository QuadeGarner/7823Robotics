package Robot;

/**
 *Quade Garner - qmgarner
 *CIS171 20432 
 *Apr 17, 2025
 */
public class Wheel {
	private double radius;
	private double circumfice ;
	public Wheel () {}
	
	public Wheel(double r) {
		this.radius = r;
	}
	// getter and setters
	public double getRadius() {
		return radius;
	}

	public void setRadius(double radius) {
		this.radius = radius;
	} 
	public void setCircumfice() {
		this.circumfice = Math.PI * 2* radius;
	}
	public double getCircumfice() {
		return circumfice;
	}
}
