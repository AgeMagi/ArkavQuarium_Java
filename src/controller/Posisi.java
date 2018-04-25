package controller;

/**
 * Kelas Posisi ialah kelas yang menyimpan posisi suatu benda di akuarium.
 */
public interface Posisi {
  /**
   * Untuk mengganti attribut x.
   * @param x .
   */
  void setX(double x);

  /**
   * Untuk mengganti attribut y.
   * @param y .
   */
  void setY(double y);

  /**
   * Untuk mengembalikan nilai attribut x.
   * @return Double.
   */
  double getX();

  /**
   * Untuk mengembalikan nilai attribut y.
   * @return Double.
   */
  double getY();

  /**
   * Untuk menghitung jarak dari 2 posisi.
   * @param x .
   * @return Double.
   */
  double hitungjarak(Posisi x);
}

