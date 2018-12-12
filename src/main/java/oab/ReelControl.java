/*
 * Diese Klasse verwaltet alle Walzen, das Starten und Stoppen wird alles hier verwaltet
 */
package main.java.oab;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.effect.Bloom;
import javafx.util.Duration;

public class ReelControl {

	List<Reel> reels;
	public Reel l,m,r;
	List<ReelSymbol> symbols;
	Map<Reel,Boolean> spinning;
	Timeline winline;

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

	/*
	 * Resettet den Blur und startet alle Walzen (wird gleichzeitig in Spinning Map geloggt)
	 */
	public void startSpinning() {
		if(winline!=null) winline.stop();
		for(Reel r:reels) {
			r.resetBlur();
			startSpin(r);
			spinning.put(r, true);
		}
	}
	/*
	 * Fuellt alle Walzen mit Symbolen
	 */
	private void initReels() {
		for(Reel r:reels) {
			r.showIn(r.top,randomSymbol());
			r.showIn(r.middle,randomSymbol());
			r.showIn(r.bottom,randomSymbol());
		}
	}

	/*
	 * Macht das eigentliche Drehen mit einer unendlichen Timeline die im Reel-Objekt gespeichert wird
	 */
	private void startSpin(Reel r) {
		r.toggleBlur();
		Timeline t = new Timeline(new KeyFrame(Duration.millis(20),ae -> r.shift(randomSymbol())));
		t.setCycleCount(-1);
		t.play();
		r.spinningAnimation = t;
	}

	/*
	 * Timeline im Objekt wird gestoppt (sofern die Rolle ueberhaupt dreht), dann neue TimeLine gestartet die das Langsamerwerden simuliert
	 */
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
			if(!isRunning()) handleResult();
		});
	}

	/*
	 * Zufaelliges neues Symbol das auf der Walze auftaucht
	 */
	private ReelSymbol randomSymbol() {
		int randomNum = ThreadLocalRandom.current().nextInt(0, symbols.size());
		return symbols.get(randomNum);
	}

	/*
	 * Gibt Liste der derzeitigen Mittellinie zurueck - null wenn noch am Laufen
	 */
	private List<ReelSymbol> getBoardState() {
		if(isRunning()) return null;
		List<ReelSymbol> r = new ArrayList<ReelSymbol>();
		for(Reel n:reels) r.add(n.getMiddle());
		return r;
	}

	/*
	 * Unimplementiert WIP
	 */
	@SuppressWarnings("unused")
	public void handleResult() {
			List<ReelSymbol> result = getBoardState(); //Kann null sein wenn das Ding gerade noch dreht
			winBlink();
	}

	/*
	 * Prueft ob gerade eine der Walzen laeuft
	 */
	public boolean isRunning() {
		return spinning.containsValue(true);
	}
	
	private void winBlink() {
		Bloom b = new Bloom(0.9);
		KeyFrame kf1 = new KeyFrame(Duration.millis(100),x->{
			for(Reel r:reels) {
			r.middle.setEffect(b);
		}});
		KeyFrame kf2 = new KeyFrame(Duration.millis(200),x->{
			for(Reel r:reels) {
			r.middle.setEffect(null);
		}});
		winline = new Timeline(kf1,kf2);
		winline.setCycleCount(10);
		winline.play();
	}
}
