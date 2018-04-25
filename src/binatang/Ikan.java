package binatang;

import benda.MakananIkan;
import controller.Akuarium;
import controller.BendaAkuarium;
import controller.Posisi;
import tools.List;

public class Ikan extends BendaAkuarium {
  private boolean lapar;
  private final int tahankenyang = 9;
  private double waktumakan;
  private final int hunger = 15;
  private String type;

  private Posisi pointtujuan;
  double wakturandom;

  /**
   * Constructor dengan parameter.
   * @param x .
   * @param y .
   * @param arah .
   * @param kecepatan .
   * @param type .
   */
  public Ikan(double x, double y, double arah, double kecepatan, String type) {
    super(x, y, arah, kecepatan);

    lapar = false;
    waktumakan = 0;
    this.setImage("ikankiri.gif");
    this.type = type;
    waktumakan = Akuarium.time_since_start();

    pointtujuan = new Siput(Akuarium.rand.nextInt(Akuarium.SCREEN_WIDTH),
            Akuarium.rand.nextInt(Akuarium.SCREEN_HEIGHT), 0, 0);
  }

  public boolean getLapar() {
    return lapar;
  }

  public void setLapar(boolean lapar) {
    this.lapar = lapar;
  }

  public int getTahanKenyang() {
    return tahankenyang;
  }

  public double getWaktuMakan() {
    return waktumakan;
  }

  public void setWaktuMakan(double waktumakan) {
    this.waktumakan = waktumakan;
  }

  public int getHunger() {
    return hunger;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public Posisi getPointTujuan() {
    return pointtujuan;
  }

  public void setPointTujuan(Posisi pointtujuan) {
    this.pointtujuan = pointtujuan;
  }

  public double getWaktuRandom() {
    return wakturandom;
  }

  public void setWaktuRandom(double wakturandom) {
    this.wakturandom = wakturandom;
  }

  /**
   * Untuk melakukan gerakan dari Ikan.
   */
  public void gerak() {
    this.setArah(Math.atan2(pointtujuan.getY() - this.getY(), pointtujuan.getX() - this.getX()));
    if (this.getArah() * 180 / Math.PI > (-90) && this.getArah() * 180 / Math.PI < 90) {
      this.setImage("ikankanan.png");
    } else {
      this.setImage("ikankiri.gif");
    }
    this.setX(this.getX() + this.getKecepatan() * Math.cos(this.getArah()) * 0.0001);
    this.setY(this.getY() + this.getKecepatan() * Math.sin(this.getArah()) * 0.0001);
    if (Math.abs(this.getY() - pointtujuan.getX()) < 0.1
            && Math.abs(this.getY() - pointtujuan.getY()) < 0.1) {
      pointtujuan.setX(Akuarium.rand.nextInt(Akuarium.SCREEN_WIDTH));
      pointtujuan.setY(Akuarium.rand.nextInt(Akuarium.SCREEN_HEIGHT));
    }
    if (Akuarium.time_since_start() - (int) waktumakan == tahankenyang) {
      lapar = true;
    }
  }

  /**
   * Untuk melihat apakah ikan tersebut sudah mati atau tidak.
   * @return boolean.
   */
  public boolean mati() {
    if (Akuarium.time_since_start() - this.getWaktuMakan()
            - this.getTahanKenyang() >= this.getHunger()) {
      return true;
    }
    return false;
  }

  public int cariIkanTerdekat(List<Ikan> listikan) {
    return 0;
  }

  public int cariMakanGuppy(List<MakananIkan> listmakananikan) {
    return 0;
  }

  public boolean keluarkanKoinGuppy() {
    return false;
  }

  public double getWaktuKoin() {
    return 0.0f;
  }

  public int getLevel() {
    return 0;
  }

  public int getJumlahMakanYangDimakan() {
    return 0;
  }

  public void setLevel(int x) {
  }

  public void setJumlahMakanYangDimakan(int x) {
  }

  public void setWaktuKoin(double x) {
  }
}


