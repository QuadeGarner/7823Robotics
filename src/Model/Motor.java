package Model;

/**
 *Quade Garner - qmgarner
 *CIS171 20432 
 *Apr 17, 2025
 */
public class Motor extends Wheel {
	private double power;
	private Direction dir;
	public Motor() {}
	
	public Motor(Direction dir,double power, double radius ) {
		super(radius);
		this.dir = dir;
		setPower(power);
	}

	public double getPower() {
		return power;
	}

	public void setPower(double power) {
		if (power < 0) {
			this.power = 0;
		} else if (power > 1) {
			this.power = 1;
		} else {
			this.power = power;
		}
		this.power = power;
	}

	public Direction getDir() {
		return dir;
	}

	public void setDir(Direction dir) {
		this.dir = dir;
	}
	
	public double getRadius() {
		return super.getRadius();
	}
	
	public void setRadius(double r) {
		super.setRadius(r);
	}
	
}