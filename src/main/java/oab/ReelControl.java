/*
 * Diese Klasse verwaltet alle Walzen, das Starten und Stoppen wird alles hier verwaltet
 */
package main.java.oab;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.Bloom;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class ReelControl {

	//Attribute zur Verwaltung des Visuellen
	List<Reel> reels;
	public Reel l,m,r;
	List<ReelSymbol> symbols;
	Map<Reel,Boolean> spinning;
	Timeline winline;
	Label showPoints;
	TextField betInput;

	//Game Logic Attribute
	public Integer gameScore;
	int activeBet;
	int freeSpin = 0;
	int jackpot;
	
	ImageView gifViewer;


	public ReelControl(Reel l, Reel m, Reel r, List<ReelSymbol> sym, Label p, TextField t, int points, ImageView gifViewer){

		gameScore = points;
		showPoints = p;
		betInput = t;
		refreshPoints();
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
		this.gifViewer = gifViewer;
		
		initReels();
	}

	/*
	 * Resettet den Blur und startet alle Walzen (wird gleichzeitig in Spinning Map geloggt)
	 */
	public void startSpinning(int bet) {
		if(winline!=null) winline.stop();
		refreshGamescore(-bet);
		activeBet = bet;
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
			if(!isRunning()) handleResult(activeBet);
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


	public void handleResult(int bet) {
			List<ReelSymbol> result = getBoardState(); //Kann null sein wenn das Ding gerade noch dreht
			
			ReelSymbol win = null;
			double mult = 0;
			
			if(result==null) return;
			ReelSymbol l = result.get(0);
			ReelSymbol m = result.get(1);
			ReelSymbol r = result.get(2);
			if(l.equals(m) && m.equals(r)){
				win = l;
				mult = l.getThree();
			}
			else if(l.equals(m) || m.equals(r)){
				win = m;
				mult = m.getTwo();
			}
			
			if(win==null) return;
			
			//winBlink();
			playGif(win.getWin());
			refreshGamescore((int)(mult*bet));
	}

	/*
	 * Prueft ob gerade eine der Walzen laeuft
	 */
	public boolean isRunning() {
		return spinning.containsValue(true);
	}

	private void winBlink() {
		Bloom b = new Bloom(0.3);
		KeyFrame kf1 = new KeyFrame(Duration.millis(100),x->{
			for(Reel r:reels) {
			r.middle.setEffect(b);
		}});
		KeyFrame kf2 = new KeyFrame(Duration.millis(200),x->{
			for(Reel r:reels) {
			r.middle.setEffect(null);
		}});
		winline = new Timeline(kf1,kf2);
		winline.setCycleCount(5);
		winline.play();
	}

	private void refreshPoints() {
		showPoints.setText(gameScore.toString());
	}
	public boolean hasFreeSpin() {
		return freeSpin>0;
	}
	private void refreshGamescore(int n) {
		gameScore += n;
		refreshPoints();
	}
	
	private void playGif(Image g) {
		gifViewer.setVisible(true);
		gifViewer.setImage(g);
		KeyFrame kf1 = new KeyFrame(Duration.millis(2500), x -> {
			gifViewer.setVisible(false);
		});
		Timeline gifPlay = new Timeline(kf1);
		gifPlay.setCycleCount(1);
		gifPlay.play();
	}

}
