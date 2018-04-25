import java.util.Random;

public class Guppy extends Ikan {
  private final int waktukeluarkankoin = 5;
  private double waktukoin;
  private static int makannaiklevel = 5;
  private static String[][] daftargambar = {
    {"image/guppy1kiri.png",
      "image/guppy1kanan.png",
      "image/guppylapar1kiri.png",
      "image/guppylapar1kanan.png"},
    {"image/guppy2kiri.png",
      "image/guppy2kanan.png",
      "image/guppylapar2kiri.png",
      "image/guppylapar2kanan.png"},
    {"image/guppy3kiri.png",
      "image/guppy3kanan.png",
      "image/guppylapar3kiri.png",
      "image/guppylapar3kanan.png"}
  };
  private int level;
  private int jumlahmakanyangdimakan;

  /**
   * Constructor dengan parameter.
   * @param x .
   * @param y .
   * @param arah .
   * @param kecepatan .
   */
  public Guppy(double x, double y, double arah, double kecepatan) {
    super(x, y, arah, kecepatan, "Guppy");
    level = 1;
    jumlahmakanyangdimakan = 0;
    waktukoin = Akuarium.time_since_start();
    this.setImage(daftargambar[0][0]);
  }

  public int getWaktuKeluarkanKoin() {
    return waktukeluarkankoin;
  }

  public double getWaktuKoin() {
    return waktukoin;
  }

  @Override
  public int getLevel() {
    return level;
  }

  public int getJumlahMakanYangDimakan() {
    return jumlahmakanyangdimakan;
  }

  public void setWaktuKoin(double waktukoin) {
    this.waktukoin = waktukoin;
  }

  public void setLevel(int level) {
    this.level = level;
  }

  public void setJumlahMakanYangDimakan(int jumlahmakanyangdimakan) {
    this.jumlahmakanyangdimakan = jumlahmakanyangdimakan;
  }

  public void cekLevel() {
    level = Math.min(jumlahmakanyangdimakan / (makannaiklevel) + 1, 3);
  }

  /**
   * Untuk mengecek apakah guppy mengeluarkan koin atau tidak.
   * @return boolean.
   */
  public boolean keluarkanKoinGuppy() {
    double wk = this.getWaktuKoin();
    int wkk = this.getWaktuKeluarkanKoin();
    if (Akuarium.time_since_start() - wk >= wkk) {
      waktukoin = Akuarium.time_since_start();
      return true;
    }
    return false;
  }

  /**
   * Untuk melakukan gerakan pada Guppy.
   */
  public void gerak() {
    Random rand = new Random();
    if (Akuarium.time_since_start() - this.getWaktuRandom() >= 3) {
      Posisi tujuan = new Siput(rand.nextInt(Akuarium.SCREEN_WIDTH),
              rand.nextInt(Akuarium.SCREEN_HEIGHT) + 100, 0, 0);
      this.setPointTujuan(tujuan);
      this.setWaktuRandom(Akuarium.time_since_start());
    }
    this.setArah(Math.atan2(this.getPointTujuan().getY() - this.getY(),
            this.getPointTujuan().getX() - this.getX()));

    if (this.getArah() * 180 / Math.PI > -90 && this.getArah() * 180 / Math.PI < 90) {
      this.setImage(daftargambar[this.getLevel() - 1][1]);
    } else {
      this.setImage(daftargambar[this.getLevel() - 1][0]);
    }
    this.setX(this.getX() + this.getKecepatan() * Math.cos(this.getArah()) * 0.0001);
    this.setY(this.getY() + this.getKecepatan() * Math.sin(this.getArah()) * 0.0001);
    if (Math.abs(this.getX() - this.getPointTujuan().getX()) < 5
            && Math.abs(this.getY() - this.getPointTujuan().getY()) < 5) {
      Posisi tujuan = new Siput(rand.nextInt(Akuarium.SCREEN_WIDTH),
              rand.nextInt(Akuarium.SCREEN_HEIGHT) + 100, 0, 0);
      this.setPointTujuan(tujuan);
    }
    if ((int) Akuarium.time_since_start() - (int) this.getWaktuMakan() == this.getTahanKenyang()) {
      this.setLapar(true);
    }
  }

  /**
   * Untuk mencari makananikan paling dekat dengan guppy.
   * @param listmakananikan .
   * @return int.
   */
  public int cariMakananIkanTerdekat(List<MakananIkan> listmakananikan) {
    double min = 99999999;
    int indeksmin = -1;

    Posisi now = new Siput(this.getX(), this.getY(), 0, 0);

    for (int i = 0; i < listmakananikan.getSize(); i++) {
      Posisi a = new Siput(listmakananikan.getIdx(i).getX(),
              listmakananikan.getIdx(i).getY(), 0, 0);
      if (min > now.hitungjarak(a)) {
        indeksmin = i;
        min = now.hitungjarak(a);
      }
    }

    return indeksmin;
  }

  /**
   * Untuk melakukan gerakan guppy ketika lapar.
   * @param listmakananikan .
   * @return int.
   */
  public int cariMakanGuppy(List<MakananIkan> listmakananikan) {

    Random rand = new Random();

    Posisi now = new Siput(this.getX(), this.getY(), 0, 0);

    int terdekat = this.cariMakananIkanTerdekat(listmakananikan);

    if (terdekat != -1) {
      this.setArah(Math.atan2(listmakananikan.getIdx(terdekat).getY()
              - this.getY(), listmakananikan.getIdx(terdekat).getX() - this.getX()));
      if (this.getArah() * 180 / Math.PI > -90 && this.getArah() * 180 / Math.PI < 90) {
        this.setImage(daftargambar[this.getLevel() - 1][3]);
      } else {
        this.setImage(daftargambar[this.getLevel() - 1][2]);
      }
      this.setX(this.getX() + this.getKecepatan() * Math.cos(this.getArah()) * 0.0001);
      this.setY(this.getY() + this.getKecepatan() * Math.sin(this.getArah()) * 0.0001);
      if (Math.abs(this.getX() - listmakananikan.getIdx(terdekat).getX()) < 5
              && Math.abs(this.getY() - listmakananikan.getIdx(terdekat).getY()) < 5) {
        this.setLapar(false);
        this.setWaktuMakan(Akuarium.time_since_start());
        jumlahmakanyangdimakan++;
        this.cekLevel();
        return terdekat;
      }
    } else {
      if (Akuarium.time_since_start() - this.getWaktuRandom() >= 3) {
        Posisi tujuan = new Siput(rand.nextInt(Akuarium.SCREEN_WIDTH),
                rand.nextInt(Akuarium.SCREEN_HEIGHT) + 100, 0, 0);
        this.setPointTujuan(tujuan);
        this.setWaktuRandom(Akuarium.time_since_start());
      }
      this.setArah(Math.atan2(this.getPointTujuan().getY() - this.getY(),
              this.getPointTujuan().getX() - this.getX()));
      if (this.getArah() * 180 / Math.PI > -90 && this.getArah() * 180 / Math.PI < 90) {
        this.setImage(daftargambar[this.getLevel() - 1][3]);
      } else {
        this.setImage(daftargambar[this.getLevel() - 1][2]);
      }
      this.setX(this.getX() + this.getKecepatan() * Math.cos(this.getArah()) * 0.0001);
      this.setY(this.getY() + this.getKecepatan() * Math.sin(this.getArah()) * 0.0001);
      if (Math.abs(this.getX() - this.getPointTujuan().getX()) < 5
              && Math.abs(this.getY() - this.getPointTujuan().getY()) < 5) {
        Posisi tujuan = new Siput(rand.nextInt(Akuarium.SCREEN_WIDTH),
                rand.nextInt(Akuarium.SCREEN_HEIGHT) + 100, 0, 0);
        this.setPointTujuan(tujuan);
      }
    }

    return -1;
  }
}