package controller;

/**
 * Kelas Movement ialah kelas yang mengatur pergerakan
 * dari sebuah benda, yang mempunyai nilai kecepatan dan arah.
 */
public interface Movement {
  void setArah(double a);

  double getArah();

  double getKecepatan();
}

