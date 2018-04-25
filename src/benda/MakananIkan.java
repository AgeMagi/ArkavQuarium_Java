package benda;

import controller.BendaAkuarium;

/**
 * Kelas Makanan Ikan ialah turunan dari kelas benda akuarium
 * yang menyimpan nilai nilai yang dimiliki oleh sebuah makanan
 * ikan.
 */
public class MakananIkan extends BendaAkuarium {
  public MakananIkan(double x, double y) {
    super(x, y, 0, 500);
    this.setImage("image/makananikan");
  }

  public void gerak() {
    this.setY(this.getY() + 2);
  }
}
