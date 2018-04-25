package test;

import static org.junit.Assert.assertEquals;

import benda.Koin;
import org.junit.Test;

public class KoinTest {

  @Test
  public void getNilai() {
    Koin test = new Koin(100f, 100f, 1.3f, 10000, 2);
    assertEquals(10000, test.getNilai());
  }

  @Test
  public void setNilai() {
    Koin test = new Koin(100f, 100f, 1.3f, 10000, 2);
    assertEquals(10000, test.getNilai());
    test.setNilai(5000);
    assertEquals(5000, test.getNilai());
  }

  @Test
  public void getLevel() {
    Koin test = new Koin(100f, 100f, 1.3f, 10000, 2);
    assertEquals(2, test.getLevel());
  }

  @Test
  public void getDaftargambar() {
    String[] test = Koin.getDaftargambar();
    assertEquals(4, test.length);
    assertEquals("image/koin1.jpg", test[0]);
    assertEquals("image/koin2.jpg", test[1]);
    assertEquals("image/koin3.jpg", test[2]);
    assertEquals("image/koin4.jpg", test[3]);
  }

  @Test
  public void setDaftargambar() {
    String[] test = new String[4];
    for (int i = 0; i < 4; i++) {
      test[i] = new String(Koin.getDaftargambar()[i]);
      test[i] = "wew/" + test[i];
    }
    assertEquals(4, test.length);
    Koin.setDaftargambar(test);
    String[] test2 = Koin.getDaftargambar();
    assertEquals(4, test2.length);
    assertEquals("wew/image/koin1.jpg", test2[0]);
    assertEquals("wew/image/koin2.jpg", test2[1]);
    assertEquals("wew/image/koin3.jpg", test2[2]);
    assertEquals("wew/image/koin4.jpg", test2[3]);
  }

  @Test
  public void gerak() {
    Koin test = new Koin(100f, 100f, 200f, 10000, 2);
    test.gerak();
    assertEquals(102f, test.getY(), 1e-12);
    test.gerak();
    assertEquals(104f, test.getY(), 1e-12);
  }
}
