package controller;

/**
 * Kelas ini Untuk mengatur attribut user yang memainkan game.
 */
public class Player {
  /**
   * Attribut untuk menyimpan nilai koin dari player.
   */
  private int jumlahkoin;

  /**
   * Attribut untuk menyimpan banyak telur yang dibeli dari player.
   */
  private int banyaktelur;

  /**
   * Koin awal yang diterima oleh player.
   */
  private static final int KOIN_AWAL = 1000;

  /**
   * Constructor.
   */
  public Player() {
    this.jumlahkoin = KOIN_AWAL;
    this.banyaktelur = 0;
  }

  /**
   * Constructor dengan parameter.
   *
   * @param x .
   * @param y .
   */
  public Player(final int x, final int y) {
    this.jumlahkoin = x;
    this.banyaktelur = y;
  }

  /**
   * Untuk mengembalikan nilai attribut jumlahkoin.
   *
   * @return int.
   */
  public int getJumlahkoin() {
    return jumlahkoin;
  }

  /**
   * Untuk menge-set nilai attribut jumlahkoin.
   *
   * @param x .
   */
  public void setJumlahkoin(final int x) {
    this.jumlahkoin = x;
  }

  /**
   * Untuk mengembalikan nilai attribut banyaktelur.
   *
   * @return int.
   */
  public int getBanyaktelur() {
    return banyaktelur;
  }

  /**
   * Untuk menge-set nilai attribut banyaktelur.
   *
   * @param x .
   */
  public void setBanyaktelur(final int x) {
    this.banyaktelur = x;
  }

  /**
   * Untuk menambahkan jumlahkoin dengan parameter.
   *
   * @param koin .
   */
  public void tambahKoin(final int koin) {
    jumlahkoin += koin;
  }

  /**
   * Untuk mengurangkan jumlahkoin dengan parameter.
   *
   * @param koin .
   */
  public void kurangkanKoin(final int koin) {
    jumlahkoin -= koin;
  }

  /**
   * Untuk menambahkan telur dari player.
   */
  public void tambahTelur() {
    banyaktelur++;
  }
}

