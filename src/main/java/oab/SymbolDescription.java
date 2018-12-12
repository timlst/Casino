package main.java.oab;

enum SymbolDescription{

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
