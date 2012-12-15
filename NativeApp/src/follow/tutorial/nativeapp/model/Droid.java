/**
 * 
 */
package follow.tutorial.nativeapp.model;


import follow.tutorial.nativeapp.R;
import follow.tutorial.nativeapp.model.components.mathVector;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.Log;


public class Droid {

	private Bitmap bitmap;	// the actual bitmap
	private mathVector position = new mathVector();
	private mathVector touchPosition = new mathVector(200, 200);
	private mathVector velocity = new mathVector(5, 5);
	private boolean touched = false;	// if droid is touched/picked up
	private MoverStrategy strategy;
	private Context context;


	
	public Droid(Bitmap bitmap, int x, int y, Context context, int strat) {
		this.context = context;
		this.bitmap = bitmap;
		position.setX(x);
		position.setY(x);
		if(strat == 1) {
			strategy = new Attract();
		}
		else if(strat == 2) {
			strategy = new Teleport();
		}
		else if(strat == 3) {
			strategy = new Repulse();
		}
		else if(strat == 4) {
			strategy = new Orbital();
		}
	}
	
	public void transform() {
		bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.gertrude);
	}
	
	public mathVector getPosition() {
		return this.position;
	}
	
	public mathVector getVelocity() {
		return this.velocity;
	}
	
	public mathVector getTouchPosition() {
		return this.touchPosition;
	}
	
	public void setStrategy(Orbital a) {
		this.strategy = a;
	}
	
	public void setStrategy(Attract a) {
		this.strategy = a;
	}
	
	public void setStrategy(Repulse a) {
		this.strategy = a;
	}
	
	public Bitmap getBitmap() {
		return bitmap;
	}
	
	public void setBitmap(Bitmap bitmap) {
		this.bitmap = bitmap;
	}
	
	public void setTouchPosition(float x, float y) {
		touchPosition.set(x, y);
	}
	
	public int getX() {
		return (int)position.getX();
	}
	
	public void setX(int x) {
		position.setX(x);
	}
	
	public int getY() {
		return (int)position.getY();
	}
	
	public void setY(int y) {
		position.setY(y);
	}

	public boolean isTouched() {
		return touched;
	}

	public void setTouched(boolean touched) {
		this.touched = touched;
	}

	public void setVelocity(mathVector speed) {
		velocity.set(speed);
	}
	
	public void setPosition(mathVector position) {
		this.position.set(position);
	}

	public void draw(Canvas canvas) {
		canvas.drawBitmap(bitmap, (float)position.getX() - (bitmap.getWidth() / 2), (float)position.getY() - (bitmap.getHeight() / 2), null);
	}

	/**
	 * Method which updates the droid's internal state every tick
	 */
	public void update() {
		if (!touched) {
			//limitSpeed();
			setPosition(position.plus(velocity));
		}
		else {
			motionWhileTouched();
		}
		Log.d("Coords: xV=" + getVelocity().getX() + ",y=" + getVelocity().getY(), null, null);
	}
	
	public void motionWhileTouched() {
		strategy.move(this);
		//limitSpeed();

	}
	
	public void limitSpeed() {
		if(velocity.normSq() > 60) {
			setVelocity(velocity.scalarMult(Math.sqrt(50) / velocity.norm()));
		}
	}
}
