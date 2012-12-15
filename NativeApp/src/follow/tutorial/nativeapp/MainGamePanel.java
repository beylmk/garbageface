/**
 * 
 */
package follow.tutorial.nativeapp;

import follow.tutorial.nativeapp.model.Droid;
import follow.tutorial.nativeapp.model.Goal;
import follow.tutorial.nativeapp.model.ScoreKeeper;
import android.app.Activity;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import follow.tutorial.nativeapp.R;


public class MainGamePanel extends SurfaceView implements
		SurfaceHolder.Callback {

	private static final String TAG = MainGamePanel.class.getSimpleName();
	
	private MainThread thread;
	private ScoreKeeper score;
	private Droid droid;
	private Goal goal1;
	private Goal goal2;
	private Goal goal3;
	private Goal goal4;

	public MainGamePanel(Context context, int strat) {
		super(context);
		// adding the callback (this) to the surface holder to intercept events
		getHolder().addCallback(this);
		
		score = new ScoreKeeper(context);
		
		// create droid and load bitmap
		droid = new Droid(BitmapFactory.decodeResource(getResources(), R.drawable.dot), 50, 50, context, strat);
		
		// create goal with bitmap
		goal1 = new Goal(BitmapFactory.decodeResource(getResources(), R.drawable.up), context);
		goal2 = new Goal(BitmapFactory.decodeResource(getResources(), R.drawable.right), context);
		goal3 = new Goal(BitmapFactory.decodeResource(getResources(), R.drawable.down), context);
		goal4 = new Goal(BitmapFactory.decodeResource(getResources(), R.drawable.left), context);
		
		
		// create the game loop thread
		thread = new MainThread(getHolder(), this);
		
		// make the GamePanel focusable so it can handle events
		setFocusable(true);
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		// at this point the surface is created and
		// we can safely start the game loop
		thread.setRunning(true);
		thread.start();
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		Log.d(TAG, "Surface is being destroyed");
		// tell the thread to shut down and wait for it to finish
		// this is a clean shutdown
		boolean retry = true;
		while (retry) {
			try {
				thread.join();
				retry = false;
			} catch (InterruptedException e) {
				// try again shutting down the thread
			}
		}
		Log.d(TAG, "Thread was shut down cleanly");
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if (event.getAction() == MotionEvent.ACTION_DOWN) {
			// delegating event handling to the droid
			droid.setTouched(true);
			droid.setTouchPosition(event.getX(), event.getY());
			
			// check if in the lower part of the screen we exit
			//if (event.getY() > getHeight() - 50) {
			//	thread.setRunning(false);
			//	((Activity)getContext()).finish();
			//} else {
			//	Log.d(TAG, "Coords: x=" + event.getX() + ",y=" + event.getY());
			//}
			//}
		
		} if (event.getAction() == MotionEvent.ACTION_UP) {
			// touch was released
			droid.setTouched(false);
		}
		return true;
	}

	public void render(Canvas canvas) {
		canvas.drawColor(Color.BLACK);
		droid.draw(canvas);
		shouldDraw(canvas, goal1);
		shouldDraw(canvas, goal2);
		shouldDraw(canvas, goal3);
		shouldDraw(canvas, goal4);
		score.draw(canvas);
	}

	/**
	 * This is the game update method. It iterates through all the objects
	 * and calls their update method if they have one or calls specific
	 * engine's update method.
	 */
	public void update() {
		//check for hitting the goal
		if(isGoalHit(goal1)) {
			if(inRightDirection(1) || goal1.isInBossMode()){
				goal1.hasBeenHit(true);
				score.increment();
			}
		}
		
		if(isGoalHit(goal2)) {
			if(inRightDirection(2) || goal2.isInBossMode()){
				goal2.hasBeenHit(true);
				score.increment();
			}
		}
		
		if(isGoalHit(goal3)) {
			if(inRightDirection(3) || goal3.isInBossMode()){
				goal3.hasBeenHit(true);
				score.increment();
			}
		}
		
		if(isGoalHit(goal4)) {
			if(inRightDirection(4) || goal4.isInBossMode()){
				goal4.hasBeenHit(true);
				score.increment();
			}
		}
		
		
		// check collision with right wall if heading right
		if (droid.getVelocity().signX() == 1
				&& droid.getX() + droid.getBitmap().getWidth() / 2 >= getWidth()) {
			droid.getVelocity().toggleX();
		}
		// check collision with left wall if heading left
		if (droid.getVelocity().signX() == -1
				&& droid.getX() - droid.getBitmap().getWidth() / 2 <= 0) {
			droid.getVelocity().toggleX();
		}
		// check collision with bottom wall if heading down
		if (droid.getVelocity().signY() == 1
				&& droid.getY() + droid.getBitmap().getHeight() / 2 >= getHeight()) {
			droid.getVelocity().toggleY();
		}
		// check collision with top wall if heading up
		if (droid.getVelocity().signY() == -1
				&& droid.getY() - droid.getBitmap().getHeight() / 2 <= 0) {
			droid.getVelocity().toggleY();
		}
		
		droid.update();
		goal1.update();
		goal2.update();
		goal3.update();
		goal4.update();
		if(goal1.isInBossMode() && score.getScore() > 3) {
			thread.setRunning(false);
			((Activity)getContext()).finish();
		}
		if(score.getScore() > 9 && !goal1.isInBossMode()) {
			droid.transform();
			goal1.transform();
			goal2.transform();
			goal3.transform();
			goal4.transform();
			score.reset();
		}

	}
	
	public boolean isGoalHit(Goal goal) {
		if(goal.getX() + goal.getBitmap().getWidth() > droid.getX() && goal.getX() - goal.getBitmap().getWidth() < droid.getX()) {
			if(goal.getY() + goal.getBitmap().getHeight() > droid.getY() && goal.getY() - goal.getBitmap().getHeight() < droid.getY()) {
				return true;
			}
		}
		return false;
	}
	
	public boolean inRightDirection(int direction) {
		switch(direction) {
		case 1: if(droid.getVelocity().signY() == -1) {
			return true;
		}
		break;
		case 2: if(droid.getVelocity().signX() == 1) {
			return true;
		}
		break;
		case 3: if(droid.getVelocity().signY() == 1) {
			return true;
		}
		break;
		case 4: if(droid.getVelocity().signX() == -1) {
			return true;
		}
		break;
		default: return false;
		}
		return false;
	}
	
	public void shouldDraw(Canvas canvas, Goal goal) {
		if(!(goal.isHit() && goal.isInBossMode())) {
			goal.draw(canvas);
		}
	}

}
