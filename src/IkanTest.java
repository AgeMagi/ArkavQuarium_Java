import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.*;

public class IkanTest {

  @Test
  public void getLapar() {
    Ikan test = new Piranha(30, 30, 3.14, 100);
    assertFalse(test.getLapar());
  }

  @Test
  public void setLapar() {
    Ikan test = new Piranha(30, 30, 3.14, 100);
    assertFalse(test.getLapar());
    test.setLapar(true);
    assertTrue(test.getLapar());
  }

  @Test
  public void getTahanKenyang() {
    Ikan test = new Piranha(30, 30, 3.14, 100);
    assertEquals(9, test.getTahanKenyang());
  }

  @Test
  public void getWaktuMakan() {
    Ikan test = new Piranha(30, 30, 3.14, 100);
    assertEquals(0f, test.getWaktuMakan(), 1e-10);
  }

  @Test
  public void setWaktuMakan() {
    Ikan test = new Piranha(30, 30, 3.14, 100);
    assertEquals(0f, test.getWaktuMakan(), 1e-10);
    test.setWaktuMakan(5.6f);
    assertEquals(5.6f, test.getWaktuMakan(), 1e-10);
  }

  @Test
  public void getHunger() {
    Ikan test = new Piranha(30, 30, 3.14, 100);
    assertEquals(15, test.getHunger());
  }

  @Test
  public void getType() {
    Ikan test = new Piranha(30, 30, 3.14, 100);
    assertEquals("Piranha", test.getType());
    Ikan test2 = new Guppy(30, 30, 3.14, 100);
    assertEquals("Guppy", test2.getType());
  }

  @Test
  public void setType() {
    Ikan test = new Piranha(30, 30, 3.14, 100);
    assertEquals("Piranha", test.getType());
    test.setType("dory");
    assertEquals("dory", test.getType());
  }

  @Test
  public void getPointTujuan() {
    Ikan test = new Piranha(30, 30, 3.14, 100);
    Ikan test2 = new Piranha(30, 30, 3.14, 100);
    assertNotEquals(test.getPointTujuan().getX(), test2.getPointTujuan().getX(), 1e-10);
    assertNotEquals(test.getPointTujuan().getY(), test2.getPointTujuan().getY(), 1e-10);
  }

  @Test
  public void setPointTujuan() {
    Ikan test = new Piranha(30, 30, 3.14, 100);
    Random rand = new Random();
    Posisi tespos = new Siput(rand.nextInt(Akuarium.SCREEN_WIDTH), rand.nextInt(Akuarium.SCREEN_HEIGHT), 0, 0);
    test.setPointTujuan(tespos);
    assertEquals(test.getPointTujuan().getX(), tespos.getX(), 1e-10);
    assertEquals(test.getPointTujuan().getY(), tespos.getY(), 1e-10);
  }

  @Test
  public void getWaktuRandom() {
    Ikan test = new Piranha(30, 30, 3.14, 100);
    assertEquals(0, test.getWaktuRandom(), 1e-10);
  }


  @Test
  public void setWaktuRandom() {
    Ikan test = new Piranha(30, 30, 3.14, 100);
    test.setWaktuRandom(102.56);
    assertEquals(102.56, test.getWaktuRandom(), 1e-10);
  }

  @Test
  public void gerak() {
    Ikan test = new Piranha(30, 30, 3.14, 100);
    double tesx = test.getX();
    double tesy = test.getY();
    test.gerak();
    assertNotEquals(tesx, test.getX(), 1e-10);
    assertNotEquals(tesy, test.getY(), 1e-10);
  }

  @Test
  public void mati() {
    Ikan test = new Piranha(30, 30, 3.14, 100);
    assertFalse(test.mati());
  }

  @Test
  public void cariIkanTerdekat() {
    Ikan test = new Piranha(30, 30, 3.14, 100);
    List<Ikan> teslist = new List<>();
    assertEquals(-1, test.cariIkanTerdekat(teslist));
    teslist.add(new Guppy(31, 31, 3, 20));
    teslist.add(new Guppy(50, 50, 3, 20));
    assertEquals(0, test.cariIkanTerdekat(teslist));
  }

  @Test
  public void cariMakanGuppy() {
    Ikan test = new Guppy(30, 30, 3.14, 100);
    List<MakananIkan> teslist = new List<>();
    assertEquals(-1, ((Guppy) test).cariMakananIkanTerdekat(teslist));
    teslist.add(new MakananIkan(31, 40));
    teslist.add(new MakananIkan(31, 30));
    assertEquals(1, ((Guppy) test).cariMakananIkanTerdekat(teslist));
  }

  @Test
  public void keluarkanKoinGuppy() {
    Ikan test = new Guppy(30, 30, 3.14, 100);
    assertFalse(test.keluarkanKoinGuppy());
  }

  @Test
  public void getWaktuKoin() {
    Ikan test = new Guppy(30, 30, 3.14, 100);
    assertEquals(Akuarium.time_since_start(), test.getWaktuKoin(), 1e-10);
  }

  @Test
  public void getLevel() {
    Ikan test = new Guppy(30, 30, 3.14, 100);
    assertEquals(1, test.getLevel());
  }

  @Test
  public void getJumlahMakanYangDimakan() {
    Ikan test = new Guppy(30, 30, 3.14, 100);
    assertEquals(0, test.getJumlahMakanYangDimakan());
    List<MakananIkan> teslist = new List<>();
    teslist.add(new MakananIkan(31, 30));
    test.cariMakanGuppy(teslist);
    assertEquals(1, test.getJumlahMakanYangDimakan());
  }

  @Test
  public void setLevel() {
    Ikan test = new Guppy(30, 30, 3.14, 100);
    assertEquals(1, test.getLevel());
    test.setLevel(3);
    assertEquals(3, test.getLevel());
  }

  @Test
  public void setJumlahMakanYangDimakan() {
    Ikan test = new Guppy(30, 30, 3.14, 100);
    assertEquals(0, test.getJumlahMakanYangDimakan());
    test.setJumlahMakanYangDimakan(100);
    assertEquals(100, test.getJumlahMakanYangDimakan());
  }

  @Test
  public void setWaktuKoin() {
    Ikan test = new Guppy(30, 30, 3.14, 100);
    assertEquals(0, test.getWaktuKoin(), 1e-10);
    test.setWaktuKoin(12.5);
    assertEquals(12.5, test.getWaktuKoin(), 1e-10);
  }
}