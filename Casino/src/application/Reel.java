package application;

import java.util.HashMap;
import java.util.Map;

import javafx.animation.Timeline;
import javafx.scene.effect.Effect;
import javafx.scene.effect.MotionBlur;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class Reel {
	
	VBox views;
	public ImageView top, middle, bottom;
	Map<ImageView, ReelSymbol> reelState;
	boolean blurOn;
	MotionBlur mb;
	public Timeline spinningAnimation;
	
	public Reel(VBox v) {
		views = v;

		top = 	 (ImageView) v.getChildren().get(0);
		middle = (ImageView) v.getChildren().get(1);
		bottom = (ImageView) v.getChildren().get(2);
		
		reelState = new HashMap<ImageView, ReelSymbol>();
		resetBlur();
		blurOn=false;
	}
	
	public void showIn(ImageView i, ReelSymbol r) {
		reelState.put(i, r);
		i.setImage(r.getImage());
	}
	
	private void refreshView(ImageView i) {
		ReelSymbol x = reelState.get(i);
		i.setImage(x.getImage());
	}
	
	public void shift(ReelSymbol r) {
		ReelSymbol newMid = reelState.put(top, r);
		ReelSymbol newBot = reelState.put(middle, newMid);
		reelState.put(bottom, newBot);
		
		refreshView(top);
		refreshView(middle);
		refreshView(bottom);
	}
	
	public void toggleBlur() {
		for (ImageView key : reelState.keySet()) {
			Object o = blurOn?null:mb;
			key.setEffect((Effect) o);
		}
		blurOn = !blurOn;
	}
	
	public void resetBlur() {
		mb = new MotionBlur(90,45.0f);
	}
	public void decreaseBlur(float n) {
		mb.setRadius(mb.getRadius()-n);
	}
	public ReelSymbol getMiddle() {
		return reelState.get(middle);
	}
	
}
