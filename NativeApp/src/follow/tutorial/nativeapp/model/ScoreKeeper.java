/**
 * 
 */
package follow.tutorial.nativeapp.model;

import follow.tutorial.nativeapp.R;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

public class ScoreKeeper {
	
	private int score;
	
	Bitmap rBitmap;
	
	private Context context;

	
	public ScoreKeeper(Context cont) {
		
		this.context = cont;
		this.score = 0;
		rBitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.gertrude);
	}
	
	public int getScore() {
		return score;
	}
	public void setScore(int x) {
		this.score = x;
	}
	public void increment() {
		score++;
	}
	
	public void reset() {
		score = 0;
	}
	
	public void draw(Canvas canvas) {
		
		chooseBitMap();
		canvas.drawBitmap(rBitmap, 10, 10, null);
	}
	
	private void chooseBitMap() {
		
		switch (score) {
		
        case 1:  rBitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.score1);
                 break;
        case 2:  rBitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.score2);
                 break;
        case 3:  rBitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.score3);
                 break;
        case 4:  rBitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.score4);
                 break;
        case 5:  rBitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.score5);
                 break;
        case 6:  rBitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.score6);
                 break;
        case 7:  rBitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.score7);
                 break;
        case 8:  rBitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.score8);
                 break;
        case 9:  rBitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.score9);
                 break;
        case 0: rBitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.score0);
                 break;
        default: rBitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.score0);
        break;
		}
	}
}
