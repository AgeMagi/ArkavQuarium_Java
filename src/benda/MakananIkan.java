package benda;

import controller.BendaAkuarium;

public class MakananIkan extends BendaAkuarium {
  public MakananIkan(double x, double y) {
    super(x, y, 0, 500);
    this.setImage("image/makananikan");
  }

  public void gerak() {
    this.setY(this.getY() + 2);
  }
}
