package benda;

import controller.Akuarium;
import controller.BendaAkuarium;

/**
 * Kelas koin ialah kelas turunan dari benda akuarium
 * yang mempunyai atribut nilai dari koin tersebut.
 */
public class Koin extends BendaAkuarium {
  private int nilai;
  private final int level;
  private static String[] daftargambar = {
    "image/koin1.jpg",
    "image/koin2.jpg",
    "image/koin3.jpg",
    "image/koin4.jpg"
  };

  /**
   * Constructor dengan parameter.
   * @param x .
   * @param y .
   * @param kecepatan .
   * @param nilai .
   * @param i .
   */
  public Koin(double x, double y, double kecepatan, int nilai, int i) {
    super(x, y, 0, kecepatan);
    this.level = i;
    this.nilai = nilai;
    this.setImage(daftargambar[i - 1]);
  }

  public int getNilai() {
    return nilai;
  }

  public void setNilai(int nilai) {
    this.nilai = nilai;
  }

  public int getLevel() {
    return level;
  }

  public static String[] getDaftargambar() {
    return daftargambar;
  }

  public static void setDaftargambar(String[] daftargambar) {
    Koin.daftargambar = daftargambar;
  }

  @Override
  public void gerak() {
    if (Math.abs(this.getY() - Akuarium.SCREEN_HEIGHT - 20) > 1) {
      this.setY(this.getY() + this.getKecepatan() * 0.01);
    }
  }
}

