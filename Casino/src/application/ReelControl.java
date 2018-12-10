/*
 * RollDisplay ist eine Klasse um die Rollen des Spielautomaten besser zu managen: Ein RollDisplay Objekt speichert jeweils die zugehörige Anzeige (ImageView) sowie das derzeit angezeigte Bild als ReelSymbol-Objekt
 * @author Tim Storm
 */

package application;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

import com.sun.org.apache.xerces.internal.util.SynchronizedSymbolTable;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class ReelControl {
	
	boolean r1,r2,r3;
	List<ImageView> rolls;
	List<ReelSymbol> symbols;
	Map<ImageView,ReelSymbol> boardstate;
	Timeline t;
	public ReelControl(List<ImageView> iv, List<ReelSymbol> rs){
		rolls = iv;
		symbols = rs;
		boardstate = new HashMap<ImageView, ReelSymbol>();
		initialize();
	}
	
	private void initialize() {
		for(ImageView i : rolls) {
			int randomNum = ThreadLocalRandom.current().nextInt(0, symbols.size());
			ReelSymbol newSymbol = symbols.get(randomNum);
			show(i,newSymbol);
		}
	}
	public String getBoardstate() {
		String o = "";
		for (ReelSymbol value : boardstate.values()) {
		    o+=value.getDescription()+" ";
		}
		return o;
	}
	
	public void start() {
		r1=r2=r3=true;
		t = new Timeline(new KeyFrame(Duration.millis(60),ae -> {
			if(r1) nextRandomSymbol(rolls.get(0));
			if(r2) nextRandomSymbol(rolls.get(1));
			if(r3) nextRandomSymbol(rolls.get(2));
		}));
		t.setCycleCount(Animation.INDEFINITE);
		t.play();
	}
	
	
	public void stop() {
		
		int t = ThreadLocalRandom.current().nextInt(500, 1000);
		KeyFrame kf1 = new KeyFrame(Duration.millis(t),e->r1=false);
		t+=ThreadLocalRandom.current().nextInt(500, 1000);
		KeyFrame kf2 = new KeyFrame(Duration.millis(t),e->r2=false);
		t+=ThreadLocalRandom.current().nextInt(500, 1000);
		KeyFrame kf3 = new KeyFrame(Duration.millis(t),e->r3=false);

	    Timeline timeline = new Timeline(kf1, kf2, kf3);
	    timeline.play();
	    timeline.setOnFinished(e->System.out.println(getBoardstate()));
	}
	
	private void nextSymbol(ImageView i) {
		int p = symbols.indexOf(boardstate.get(i));
		ReelSymbol next = symbols.get((p+1)%(symbols.size()));
		show(i,next);
	}
	private void nextRandomSymbol(ImageView i) {
		int randomNum = ThreadLocalRandom.current().nextInt(0, symbols.size());
		ReelSymbol next = symbols.get(randomNum);
		if(next.equals(boardstate.get(i))) nextRandomSymbol(i);
		else show(i,next);
	}
	
	private void show(ImageView i, ReelSymbol r) {
		i.setImage(r.getImage());
		boardstate.put(i, r);
	}
}
