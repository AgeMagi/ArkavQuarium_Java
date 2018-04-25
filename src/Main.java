import java.io.IOException;

import controller.Akuarium;

public class Main {
  /**
   * Fungsi main dari ArkavKuarium.
   * @param args .
   */
  public static void main(String[] args) {
    try {
      Akuarium akuarium = new Akuarium("image/guppy1kiri.png");
      akuarium.buildFrame();

      akuarium.setContent();
      akuarium.startAkuarium();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
