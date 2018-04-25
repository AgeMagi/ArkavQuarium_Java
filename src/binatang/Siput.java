package binatang;

import benda.Koin;
import benda.MakananIkan;
import controller.Akuarium;
import controller.BendaAkuarium;
import controller.Posisi;
import tools.List;

/**
 * Kelas siput ialah turunan dari kelas benda Akuarium yang merupakan
 * benda yang mengumpulkan koin.
 */
public class Siput extends BendaAkuarium {

  /**
   * Kecepatan dari siput.
   */
  private static final int KECEPATAN_SIPUT = 20000;

  /**
   * Radius ketika siput tidak bergerak lagi.
   */
  private static final double RADIUS_GERAKAN = 0.1;

  /**
   * Ratio Kecepatan dari siput untuk menyesuaikan dengan fps.
   */
  private static final double RATIO_KECEPATAN = 0.0001;

  /**
   * Jarak dari dasar koin pada akuarium.
   */
  private static final int DASAR_KOIN = 20;
  /**
   * Point tujuan dari siput untuk gerakan selanjutya.
   */
  private Posisi pointtujuan;

  /**
   * Mengembalikan PointTujuan dari Siput.
   * @return Posisi
   */
  public Posisi getPointtujuan() {
    return pointtujuan;
  }

  /**
   * Untuk mengganti point tujuan dari Siput.
   * @param x .
   */
  public void setPointtujuan(final Posisi x) {
    this.pointtujuan = x;
  }

  /**
   * Constructor.
   */
  public Siput() {
    super(Akuarium.rand.nextInt(Akuarium.SCREEN_WIDTH),
            Akuarium.SCREEN_HEIGHT, 0, KECEPATAN_SIPUT);
    pointtujuan = new MakananIkan(Akuarium.rand.nextInt(Akuarium.SCREEN_WIDTH),
            Akuarium.rand.nextInt(Akuarium.SCREEN_HEIGHT));
    pointtujuan.setX(Akuarium.rand.nextInt(Akuarium.SCREEN_WIDTH));
    pointtujuan.setY(Akuarium.rand.nextInt(Akuarium.SCREEN_HEIGHT));
    this.setImage("image/siputkanan.png");
  }

  /**
   * Constructor dengan parameter.
   * @param x         .
   * @param y         .
   * @param a         .
   * @param kecepatan .
   */
  public Siput(final double x, final double y,
               final double a, final double kecepatan) {
    super(x, y, a, kecepatan);
    pointtujuan = new MakananIkan(Math.random() % Akuarium.SCREEN_WIDTH,
            Math.random() % Akuarium.SCREEN_HEIGHT);
    this.setImage("image/siputkanan.png");
  }

  /**
   * Untuk melakukan gerakan dari siput.
   */
  @Override
  public void gerak() {
    boolean kanansiput = true;

    if ((pointtujuan.getX() - this.getX()) > 0) {
      kanansiput = true;
      this.setImage("image/siputkanan.png");
    } else {
      kanansiput = false;
      this.setImage("image/siputkiri.png");
    }

    if ((kanansiput) && (this.getX() != Akuarium.SCREEN_WIDTH)
            && (Math.abs(this.getX() - pointtujuan.getX())
            > RADIUS_GERAKAN)) {
      this.setX(this.getX() + this.getKecepatan() * RATIO_KECEPATAN);
    } else if ((this.getX() != 0) && (Math.abs(this.getX()
            - pointtujuan.getX()) > RADIUS_GERAKAN)) {
      this.setX(this.getX() - this.getKecepatan() * RATIO_KECEPATAN);
    }
  }

  /**
   * Untuk mencari apakah ada koin yang dapat dituju oleh siput.
   * @param listkoin .
   * @return int.
   */
  public int cariKoin(final List<Koin> listkoin) {
    boolean kanansiput = true;
    boolean ketemudasar = false;
    int terdekat = -1;

    Posisi tujuan = new Siput(this.getX(), this.getY(), 0, 0);
    Posisi now = new Siput(this.getX(), this.getY(), 0, 0);

    for (int i = 0; i < listkoin.getSize() && !ketemudasar; i++) {
      if (Math.abs(listkoin.getIdx(i).getY() - Akuarium.SCREEN_HEIGHT
              - 20) < 1) {
        ketemudasar = true;
      }
    }

    if (ketemudasar) {
      tujuan.setX(99999);
      tujuan.setY(Akuarium.SCREEN_HEIGHT);
      for (int i = 0; i < listkoin.getSize(); i++) {
        if (Math.abs(listkoin.getIdx(i).getY() - Akuarium.SCREEN_HEIGHT
                - 20) < 1) {
          if (Math.abs(now.getX() - listkoin.getIdx(i).getX())
                  < Math.abs(now.getX() - tujuan.getX())) {
            terdekat = i;
            tujuan.setX(listkoin.getIdx(i).getX());
          }
        }
      }
    } else {
      tujuan.setX(this.getX());
      tujuan.setY(0);
      for (int i = 0; i < listkoin.getSize(); i++) {
        if (listkoin.getIdx(i).getY() > tujuan.getY()) {
          terdekat = i;
          tujuan.setX(listkoin.getIdx(i).getX());
          tujuan.setY(listkoin.getIdx(i).getY());
        }
      }
    }

    if (terdekat != -1) {
      this.setArah(Math.atan2(listkoin.getIdx(terdekat).getY()
              - this.getY(), listkoin.getIdx(terdekat).getX()
              - this.getX()));
      if (this.getArah() * 180 / Akuarium.PI
              > -90
              && this.getArah() * 180 / Akuarium.PI
              < 90) {
        this.setImage("image/siputkanan.png");
        kanansiput = true;
      } else {
        this.setImage("image/siputkiri.png");
        kanansiput = false;
      }

      if ((kanansiput) && (this.getX() != Akuarium.SCREEN_WIDTH)
              && (Math.abs(this.getX() - listkoin.getIdx(terdekat).getX())
              > 1)) {
        this.setX(this.getX() + this.getKecepatan() * 0.0001);
      } else if ((this.getX() != 0) && (Math.abs(this.getX()
              - listkoin.getIdx(terdekat).getX()) > 1)) {
        this.setX(this.getX() - this.getKecepatan() * 0.0001);
      }
      if (Math.abs(this.getX() - listkoin.getIdx(terdekat).getX())
              < 5
              && Math.abs(listkoin.getIdx(terdekat).getY() - Akuarium.SCREEN_HEIGHT - 20) < 5) {
        return terdekat;
      }
    }

    return -1;
  }
}

