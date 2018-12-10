/*
 * Eine Hilfsklasse um jedes Element dass auf der Rolle angezeigt werden kann darzustellen:
 * Ein ReelSymbol besteht jeweils aus einem Bild und einem Bezeichner aus der Aufzählung SymbolDescription
 */

package application;

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
}
