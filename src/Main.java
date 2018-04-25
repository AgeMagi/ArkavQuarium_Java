import java.io.IOException;

import controller.Akuarium;

public class Main {
  /**
   * Fungsi main dari ArkavKuarium.
   * @param args .
   */
  public static void main(String[] args) {
    try {
      /**
       * Untuk menginisiasi Akuarium yang akan digunakan untuk
       * permainan ArkavQuarium dengan defaultImage pada parameter.
       */
      Akuarium akuarium = new Akuarium("image/guppy1kiri.png");

      /**
       * Untuk mengatur ukuran dari akuarium dan juga posisi akuarium
       * pada layar.
       */
      akuarium.buildFrame();

      /**
       * Untuk mendeteksi input user seperti mouse click, keyboard clik.
       */
      akuarium.setContent();

      /**
       * Untuk membuat GUI dari ArkavQuarium. Pada method startAkuarium
       * terdapat method syncAll yang berguna untuk mengupdate posisi
       * dari setiap objek pada akuarium. Terdapat juga method paintComponent
       * yang berguna sebagai controller dari GUI tersebut.
       */
      akuarium.startAkuarium();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
