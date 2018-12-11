/*
 * RollDisplay ist eine Klasse um die Rollen des Spielautomaten besser zu managen: Ein RollDisplay Objekt speichert jeweils die zugehörige Anzeige (ImageView) sowie das derzeit angezeigte Bild als ReelSymbol-Objekt
 * @author Tim Storm
 */

package application;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.effect.MotionBlur;
import javafx.util.Duration;

public class ReelControl {
	
	List<Reel> reels;
	List<ReelSymbol> symbols;
	MotionBlur spinning;
	Timeline leftSpin, midSpin, rightSpin;
	
	public ReelControl(Reel l, Reel m, Reel r, List<ReelSymbol> sym){
		
		symbols = sym;

		reels = new ArrayList<Reel>();
		reels.add(l);
		reels.add(m);
		reels.add(r);
		initReels();
	}
	
	public void startSpinning() {
		resetBlur();
		for(Reel r:reels) startSpin(r);
	}
	public void stopSpinning() {
		int t = 500;
		KeyFrame kf1 = new KeyFrame(Duration.millis(t/2),e->stopSpin(reels.get(0)));
		KeyFrame kf2 = new KeyFrame(Duration.millis(2*t),e->stopSpin(reels.get(1)));
		KeyFrame kf3 = new KeyFrame(Duration.millis(3*t),e->stopSpin(reels.get(2)));
	    Timeline s = new Timeline(kf1, kf2, kf3);
	    s.play();
	}
	
	
	private void initReels() {
		for(Reel r:reels) {
			r.showIn(r.top,randomSymbol());
			r.showIn(r.middle,randomSymbol());
			r.showIn(r.bottom,randomSymbol());

		}
	}
	
	private void startSpin(Reel r) {
		r.toggleBlur();
		Timeline t = new Timeline(new KeyFrame(Duration.millis(20),ae -> r.shift(randomSymbol())));

		if(r.equals(reels.get(0)))  leftSpin = t;
		else if(r.equals(reels.get(1))) midSpin = t;
		else if(r.equals(reels.get(2))) rightSpin = t;
			
		t.setCycleCount(-1);
		t.play();
	}
	
	private void stopSpin(Reel r) {
		stopSpinAnimation(r);
		Timeline s = new Timeline(new KeyFrame(Duration.millis(30),ae -> { 
			r.decreaseBlur(1.5f);
			r.shift(randomSymbol());
		}
		));
		s.setCycleCount(20);
		s.play();
		s.setOnFinished(x->{stopSpinAnimation(r);r.toggleBlur();});
	}
	
	private void stopSpinAnimation(Reel r) {
		if(r.equals(reels.get(0))) {
			leftSpin.stop();
		}
		else if(r.equals(reels.get(1))) {
			midSpin.stop();
		}
		else if(r.equals(reels.get(2))) {
			rightSpin.stop();
		}
	}
	
	private void resetBlur() {
		for(Reel r : reels) r.resetBlur();
	}
	
	@Deprecated
	public void newRow() {
		for(Reel r : reels) r.shift(randomSymbol());
	}
	
	private ReelSymbol randomSymbol() {
		int randomNum = ThreadLocalRandom.current().nextInt(0, symbols.size());
		return symbols.get(randomNum);
	}
	
	private List<ReelSymbol> getBoardState(){
		List<ReelSymbol> r = new ArrayList<ReelSymbol>();
		for(Reel n:reels) r.add(n.getMiddle());
		return r;
	}
	
	@SuppressWarnings("unused")
	public void handleResult() {
		List<ReelSymbol> result = getBoardState();
	}

}
