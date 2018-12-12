/*
 * Eine Hilfsklasse um jedes Element dass auf der Rolle angezeigt werden kann darzustellen:
 * Ein ReelSymbol besteht jeweils aus einem Bild und einem Bezeichner aus der Aufzaehlung SymbolDescription
 */

package main.java.oab;

import javafx.scene.image.Image;

public class ReelSymbol {

	Image img;
	SymbolDescription kopf;

	public ReelSymbol(Image i, SymbolDescription s){
		img = i;
		kopf = s;
	}

	public Image getImage() {
		return img;
	}
	public SymbolDescription getDescription() {
		return kopf;
	}

	public enum SymbolDescription{

		AARON(3),
		ANGELINA(1),
		CLAAS(1),
		HANNES(2),
		JOSCHUA(5),
		TIM(1),
		TOBIAS(4),
		TEST(69),
		XENIA(2);

		private final int points;

		SymbolDescription(int i){
			this.points = i;
		}

		int getPoints() {
			return points;
		}
	}
}
