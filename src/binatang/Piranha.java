package binatang;

import controller.Akuarium;
import controller.Posisi;
import tools.List;

/**
 * Kelas piranha merupakan derived class dari ikan yang
 * pada dasarnya dapat bergerak dan makan.
 * Kelas ini memiliki atribut statik jumlahpiranha
 * untuk mengembalikan jumlah objek  piranha.
 * Piranha dapat bergerak, mengeluarkan koin,
 * dan makan dengan method-methodnya.
 */
public class Piranha extends Ikan {

  /**
   * Waktu random dari piranha.
   */
  private static final int WAKTU_RANDOM_PIRANHA = 3;

  /**
   * Jarak pergeseran dari piranha.
   */
  private static final int JARAK_PIRANHA = 100;

  /**
   * Ratio Kecepatan dari Piranha untuk menyesuaikan dengan fps.
   */
  private static final double RATIO_KECEPATAN = 0.0001;

  /**
   * Radius pengambilan dari suatu objek.
   */
  private static final int RADIUS_PENGAMBILAN = 5;

  /**
   * Gambar ke 3.
   */
  private static final int GAMBAR_KE3 = 3;
  /**
   * Gambar - gambar dari Piranha.
   */
  private static String[] daftargambar = {
    "image/piranhakiri.png",
    "image/piranhakanan.png",
    "image/piranhalaparkiri.png",
    "image/piranhalaparkanan.png"
  };

  /**
   * Constructor dengan parameter.
   * @param x         .
   * @param y         .
   * @param arah      .
   * @param kecepatan .
   */
  public Piranha(final double x, final double y,
                 final double arah, final double kecepatan) {
    super(x, y, arah, kecepatan, "Piranha");
    this.setImage("image/piranhakiri.png");
    this.setLapar(false);
  }

  /**
   * Untuk melakukan gerakan dari Piranha.
   */
  public void gerak() {

    if (Akuarium.time_since_start() - this.getWaktuRandom()
            >= WAKTU_RANDOM_PIRANHA) {
      Posisi tujuan = new Siput(Akuarium.rand.nextInt(Akuarium.SCREEN_WIDTH),
              Akuarium.rand.nextInt(Akuarium.SCREEN_HEIGHT)
                      + JARAK_PIRANHA, 0, 0);
      this.setPointTujuan(tujuan);
      this.setWaktuRandom(Akuarium.time_since_start());
    }
    this.setArah(Math.atan2(this.getPointTujuan().getY()
            - this.getY(), this.getPointTujuan().getX()
            - this.getX()));
    if (this.getArah() * 180 / Akuarium.PI
            > -90 && this.getArah()
            * 180 / Akuarium.PI
            < 90) {
      this.setImage(daftargambar[1]);
    } else {
      this.setImage(daftargambar[0]);
    }
    this.setX(this.getX() + this.getKecepatan()
            * Math.cos(this.getArah()) * RATIO_KECEPATAN);
    this.setY(this.getY() + this.getKecepatan()
            * Math.sin(this.getArah()) * RATIO_KECEPATAN);

    if (Math.abs(this.getX() - this.getPointTujuan().getX())
            < RADIUS_PENGAMBILAN && Math.abs(this.getY()
            - this.getPointTujuan().getY()) < RADIUS_PENGAMBILAN) {
      Posisi tujuan = new Siput(Akuarium.rand.nextInt(Akuarium.SCREEN_WIDTH),
              Akuarium.rand.nextInt(Akuarium.SCREEN_HEIGHT)
                      + JARAK_PIRANHA, 0, 0);
      this.setPointTujuan(tujuan);
    }
    if ((int) (Akuarium.time_since_start()
            - this.getWaktuMakan()) == this.getTahanKenyang()) {
      this.setLapar(true);
    }
  }

  /**
   * Untuk mencari ikan yang paling dekat dengan piranha.
   * @param listikan .
   * @return Int.
   */
  public int cariIkanTerdekat(final List<Ikan> listikan) {
    double min = 999999;
    int terdekat = -1;

    if (Akuarium.time_since_start() - this.getWaktuRandom()
            >= WAKTU_RANDOM_PIRANHA) {
      Posisi tujuan = new Siput(Akuarium.rand.nextInt(Akuarium.SCREEN_WIDTH),
              Akuarium.rand.nextInt(Akuarium.SCREEN_HEIGHT) + JARAK_PIRANHA,
              0, 0);
      this.setPointTujuan(tujuan);
      this.setWaktuRandom(Akuarium.time_since_start());
    }

    Posisi now = new Siput(this.getX(), this.getY(), 0, 0);

    for (int i = 0; i < listikan.getSize(); i++) {
      if (listikan.getIdx(i).getType().equals("Guppy")) {
        Posisi a = new Siput(listikan.getIdx(i).getX(),
                listikan.getIdx(i).getY(), 0, 0);
        if (min > a.hitungjarak(now)) {
          terdekat = i;
          min = a.hitungjarak(now);
        }
      }
    }

    if (terdekat != -1) {
      this.setArah(Math.atan2(listikan.getIdx(terdekat).getY()
              - this.getY(), listikan.getIdx(terdekat).getX()
              - this.getX()));
      if (this.getArah() * 180 / Math.PI
              > -90 && this.getArah()
              * 180 / Math.PI
              < 90) {
        this.setImage(daftargambar[GAMBAR_KE3]);
      } else {
        this.setImage(daftargambar[2]);
      }
      this.setX(this.getX() + this.getKecepatan()
              * Math.cos(this.getArah()) * RATIO_KECEPATAN);
      this.setY(this.getY() + this.getKecepatan()
              * Math.sin(this.getArah()) * RATIO_KECEPATAN);
      if (Math.abs(this.getX() - listikan.getIdx(terdekat).getX())
              < RADIUS_PENGAMBILAN && Math.abs(this.getY()
              - listikan.getIdx(terdekat).getY()) < RADIUS_PENGAMBILAN) {
        this.setLapar(false);
        this.setWaktuMakan(Akuarium.time_since_start());
        return terdekat;
      }
    } else {
      this.setArah(Math.atan2(this.getPointTujuan().getY()
              - this.getY(), this.getPointTujuan().getX() - this.getX()));
      if (this.getArah() * 180 / Math.PI > -90 && this.getArah() * 180 / Math.PI < 90) {
        this.setImage(daftargambar[3]);
      } else {
        this.setImage(daftargambar[2]);
      }
      this.setX(this.getX() + this.getKecepatan() * Math.cos(this.getArah()) * 0.0001);
      this.setY(this.getY() + this.getKecepatan() * Math.sin(this.getArah()) * 0.0001);
      if (Math.abs(this.getX() - this.getPointTujuan().getX()) < 5
              && Math.abs(this.getY() - this.getPointTujuan().getY()) < 5) {
        Posisi tujuan = new Siput(Akuarium.rand.nextInt(Akuarium.SCREEN_WIDTH),
                Akuarium.rand.nextInt(Akuarium.SCREEN_HEIGHT) + 100, 0, 0);
        this.setPointTujuan(tujuan);
      }
    }

    return -1;
  }
}

