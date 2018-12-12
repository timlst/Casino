package main.java.oab;

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

	/*
	 * Aktualisiert Mapping
	 * Zeigt bild im zugehoerigen Slot
	 */
	public void showIn(ImageView i, ReelSymbol r) {
		reelState.put(i, r);
		i.setImage(r.getImage());
	}

	/*
	 * Fuer den Fall das aktualisiert wird aber nicht neu gesetzt
	 */
	private void refreshView(ImageView i) {
		ReelSymbol x = reelState.get(i);
		i.setImage(x.getImage());
	}

	/*
	 * Setzt ein neues Symbol oben ein und schiebt alles nach unten
	 */
	public void shift(ReelSymbol r) {
		ReelSymbol newMid = reelState.put(top, r);
		ReelSymbol newBot = reelState.put(middle, newMid);
		reelState.put(bottom, newBot);

		refreshView(top);
		refreshView(middle);
		refreshView(bottom);
	}

	/*
	 * Schaltet Blur fuer alle ImageViews an/aus
	 */
	public void toggleBlur() {
		for (ImageView key : reelState.keySet()) {
			Object o = blurOn?null:mb;
			key.setEffect((Effect) o);
		}
		blurOn = !blurOn;
	}

	/*
	 * Geht wieder zum "Standard"-Blur der die Bewegung simuliert
	 */
	public void resetBlur() {
		mb = new MotionBlur(90,45.0f);
	}
	/*
	 * Blur wird "langsamer" gemacht
	 */
	public void decreaseBlur(float n) {
		mb.setRadius(mb.getRadius()-n);
	}
	/*
	 * Mittleres Walzensymbol wird zurueckgegeben
	 */
	public ReelSymbol getMiddle() {
		return reelState.get(middle);
	}

}
