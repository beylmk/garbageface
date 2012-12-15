package follow.tutorial.nativeapp.model.components;
import java.util.*;

public class mathVector {
	
	private double[] myVec = new double[2];
	
	public mathVector() {
		for(int i = 0; i < myVec.length; ++i) {
			this.myVec[i] = 0;
		}
	}
	
	public mathVector(double x, double y) {
		setX(x);
		setY(y);
	}
	
	public void set(mathVector src) {
		set(src.getX(), src.getY());
	}
	
	public mathVector plus(mathVector rhs) {
		return new mathVector(getX() + rhs.getX(), getY() + rhs.getY());
	}
	
	public mathVector minus(mathVector rhs) {
		return new mathVector(getX() - rhs.getX(), getY() - rhs.getY());
	}
	
	public void toggleX() {
		myVec[0] = -myVec[0];
	}	
	
	public void toggleY() {
		myVec[1] = -myVec[1];
	}
	
	public double getX() {
		return myVec[0];
	}
	
	public double getY() {
		return myVec[1];
	}
	
	public double get(int i) {
		if(i < myVec.length) {
			return myVec[i];
		}
		return 0;
	}
	
	public void setX(double x) {
		myVec[0] = x;
	}
	
	public void setY(double y) {
		myVec[1] = y;
	}
	
	public void set(int x, int y) {
		setX(x);
		setY(y);
	}	
	
	public void set(double x, double y) {
		setX(x);
		setY(y);
	}
	
	public mathVector scalarMult(double d) {
		return new mathVector(myVec[0] * d, myVec[1] * d);
	}
	
	public int signX() {
		if(myVec[0] > 0) {
			return 1;
		}
		return -1;
	}
	
	public int signY() {
		if(myVec[1] > 0) {
			return 1;
		}
		return -1;
	}
	
	public double normSq() {
		return Math.pow(myVec[0], 2) + Math.pow(myVec[1], 2);
	}
	
	public double norm() {
		return Math.sqrt(normSq());
	}
	
}
