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


public class Goal {

	private Bitmap bitmap;	// the actual bitmap
	private mathVector position = new mathVector();
	//type 1 up
	//type 2 right
	//type 3 down
	//type 4 left
	//type 5 maddie
	private boolean isHit;	// if goal is hit.
	private boolean isInBossMode = false;
	private mathVector velocity = new mathVector();
	private Context context;

	
	public Goal(Bitmap bitmap, Context context) {
		this.context = context;
		this.bitmap = bitmap;
		this.isHit = false;
		randomizePos();
	}
	
	public void transform() {
		isInBossMode = true;
		velocity.set(getX()/25, getY()/25);
		setBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.doughnut));
	}
	
	public boolean isInBossMode() {
		return isInBossMode;
	}
	
	public Bitmap getBitmap() {
		return bitmap;
	}
	public void setBitmap(Bitmap bitmap) {
		this.bitmap = bitmap;
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

	public void hasBeenHit(boolean hit) {
		isHit = hit;
	}
	
	public void randomizePos() {
		if(!isInBossMode()) {
			position.setX((float)(Math.random() * 400) + 40);
			position.setY((float)(Math.random() * 700) + 70);
		}
		else {
			position.set(1000, 1000);
			velocity.set(0,0);
		}
	}
	
	public boolean isHit() {
		return this.isHit;
	}

	public void draw(Canvas canvas) {
		if(!(isHit && isInBossMode)) {
			canvas.drawBitmap(bitmap, (float)position.getX() - (bitmap.getWidth() / 2), (float)position.getY() - (bitmap.getHeight() / 2), null);
		}
	}

	/**
	 * Method which updates the goal every tick
	 */
	public void update() {
		position.set(position.plus(velocity));
		if (isHit) {
			randomizePos();
			if(!isInBossMode()) {
				isHit = false;
			}
		}
		if(getX() < 0 || getX() > 470) {
			velocity.toggleX();
		}
		if(getY() < 0 || getY() > 780) {
			velocity.toggleY();
		}
	}
	

}
