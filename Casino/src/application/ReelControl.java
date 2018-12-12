/*
 * RollDisplay ist eine Klasse um die Rollen des Spielautomaten besser zu managen: Ein RollDisplay Objekt speichert jeweils die zugehörige Anzeige (ImageView) sowie das derzeit angezeigte Bild als ReelSymbol-Objekt
 * @author Tim Storm
 */

package application;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

public class ReelControl {
	
	List<Reel> reels;
	Reel l,m,r;
	List<ReelSymbol> symbols;
	Map<Reel,Boolean> spinning;
	
	public ReelControl(Reel l, Reel m, Reel r, List<ReelSymbol> sym){
		
		symbols = sym;

		reels = new ArrayList<Reel>();
		reels.add(l);
		reels.add(m);
		reels.add(r);
		
		this.l = l;
		this.m = m;
		this.r = r;
		
		spinning = new HashMap<Reel,Boolean>();
		spinning.put(l,false);
		spinning.put(m,false);
		spinning.put(r,false);
		
		initReels();
	}
	
	public void startSpinning() {
		for(Reel r:reels) {
			r.resetBlur(); 
			startSpin(r); 
			spinning.put(r, true);
		}
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
		t.setCycleCount(-1);
		t.play();
		r.spinningAnimation = t;
	}
	
	public void stopSpin(Reel r) {
		if(!spinning.get(r)) return;
		Timeline s = new Timeline(new KeyFrame(Duration.millis(30),ae -> { 
			r.decreaseBlur(3.0f);
			r.shift(randomSymbol());
		}
		));
		s.setCycleCount(10);
		s.play();
		r.spinningAnimation.stop();
		r.spinningAnimation = s;
		s.setOnFinished(x->{
			r.toggleBlur();
			spinning.put(r, false);
		});
	}
	
	private ReelSymbol randomSymbol() {
		int randomNum = ThreadLocalRandom.current().nextInt(0, symbols.size());
		return symbols.get(randomNum);
	}
	
	private List<ReelSymbol> getBoardState() {
		if(isRunning()) return null;
		List<ReelSymbol> r = new ArrayList<ReelSymbol>();
		for(Reel n:reels) r.add(n.getMiddle());
		return r;
	}
	
	@SuppressWarnings("unused")
	public void handleResult() {
			List<ReelSymbol> result = getBoardState(); //Kann null sein wenn das Ding gerade noch dreht
	}
	
	public boolean isRunning() {
		return spinning.containsValue(true);
	}
}
