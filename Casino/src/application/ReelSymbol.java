/*
 *  Eine Reihe von Konstanten die jeweils ein Symbol darstellt welches auf den Walzen auftauchen kann
 *  @author Tim
 */

package application;

import javafx.scene.image.Image;

public enum ReelSymbol {
	
	AARON(3,new Image("/icons/1.png")), 
	ANGELINA(1,new Image("/icons/2.png")),
	CLAAS(1, new Image("/icons/3.png")),
	HANNES(2, new Image("/icons/4.png")),
	JOSCHUA(5,new Image("/icons/5.png")), 
	TIM(1,new Image("/icons/6.png")), 
	TOBIAS(4,new Image("/icons/7.png")), 
	TEST(69,new Image("/icons/8.png")),
	XENIA(2,new Image("/icons/9.png"));
	
	private final Image img;
	private final int points;
	
	ReelSymbol(int p, Image i){
		img = i;
		points = p;
	}
	/*
	 *@return Gibt ein Bild in Form eines Image Objekts zurück
	 */
	public Image getImage() {
		return img;
	}
	/*
	 *@return Gibt die Punkte die dem Walzensymbol zugeordnet sind zurück
	 */
	public int getPoints() {
		return points;
	}
	
}
