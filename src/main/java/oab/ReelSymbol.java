/*
 *  Eine Reihe von Konstanten die jeweils ein Symbol darstellt welches auf den Walzen auftauchen kann
 * 	Ein neues Walzensymbol mit Pfad der Bilddatei und Punkten erstellen (siehe unten)
 *  @author Tim
 */

package main.java.oab;

import javafx.scene.image.Image;

public enum ReelSymbol {

	AARON(3,new Image("/main/resources/images/1.png")),
	ANGELINA(1,new Image("/main/resources/images/2.png")),
	CLAAS(1, new Image("/main/resources/images/3.png")),
	HANNES(2, new Image("/main/resources/images/4.png")),
	JOSCHUA(5,new Image("/main/resources/images/5.png")),
	TIM(1,new Image("/main/resources/images/6.png")),
	TOBIAS(4,new Image("/main/resources/images/7.png")),
	TEST(69,new Image("/main/resources/images/8.png")),
	XENIA(2,new Image("/main/resources/images/9.png"));

	private final Image img;
	private final int points;

	ReelSymbol(int p, Image i){
		img = i;
		points = p;
	}
	/*
	 *@return Gibt ein Bild in Form eines Image Objekts zurueck
	 */
	public Image getImage() {
		return img;
	}
	/*
	 *@return Gibt die Punkte die dem Walzensymbol zugeordnet sind zurueck
	 */
	public int getPoints() {
		return points;
	}

}
