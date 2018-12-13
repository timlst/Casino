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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.Bloom;
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


	public ReelControl(Reel l, Reel m, Reel r, List<ReelSymbol> sym, Label p, TextField t, int points/*,int einsatz, int jackpot*/){
		
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

	/*
	 * Unimplementiert WIP
	 */
	public void handleResult(int bet) {
			List<ReelSymbol> result = getBoardState(); //Kann null sein wenn das Ding gerade noch dreht
			winBlink();
			ReelSymbol l = result.get(0);
			ReelSymbol m = result.get(1);
			ReelSymbol r = result.get(2);
			/*
			if(l == m && m == r)
			{
				int multipl = l.getPoints();
				if(multipl != 0 || multipl != 1000)
				{
					gameScore = gameScore + activeBet*multipl;
				}
				else if (multipl == 0) freeSpin += 4;
				else gameScore += jackpot;
			}*/
			
			//ANMERKUNG TIM: Ich verstehe das Konzept noch nicht so ganz: Man gewinnt nur wenn drei gleiche kommen? Das ist seeeehr unwahrscheinlich tbh
			//Temporäre Gewinnmethode
			//Deswegen ist es jetzt so dass jede Person einen Multiplikator gibt, alle 3 Walzen werden addiert, durch 3 geteilt und anschließend mal den Einsatz zurückgegeben
			double mult = (l.getPoints()+m.getPoints()+r.getPoints())/3;
			//System.out.println(mult);
			refreshGamescore((int)(mult*activeBet));
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
}
