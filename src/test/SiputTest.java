package test;

import static org.junit.Assert.assertEquals;

import benda.Koin;
import benda.MakananIkan;
import binatang.Siput;
import org.junit.Test;
import tools.List;

public class SiputTest {

  @Test
  public void getPointtujuan() {
    Siput s1 = new Siput(10, 640, 0, 0);
    MakananIkan pointtujuan = new MakananIkan(Math.random() % 853, Math.random() % 640);
    s1.setPointtujuan(pointtujuan);
    assertEquals(s1.getPointtujuan(), pointtujuan);
  }

  @Test
  public void setPointtujuan() {
    Siput s1 = new Siput(10, 640, 0, 0);
    MakananIkan pointtujuan = new MakananIkan(Math.random() % 853, Math.random() % 640);
    s1.setPointtujuan(pointtujuan);
    assertEquals(s1.getPointtujuan(), pointtujuan);
  }

  @Test
  public void gerak() {
    Siput s1 = new Siput(99, 640, 90, 10000);
    MakananIkan pointtujuan = new MakananIkan(1, 640);
    while (pointtujuan.getX() < s1.getX()) {
      s1.gerak();
    }
    assertEquals(s1.getX(), pointtujuan.getX(), 0.1);
  }

  @Test
  public void cariKoin() {
    final Siput p = new Siput(5, 2, 0, 0);
    Koin k1 = new Koin(10, 10, 1.3f, 10000, 2);
    Koin k2 = new Koin(0, 10, 1.3f, 10000, 2);
    Koin k3 = new Koin(10, 10, 1.3f, 10000, 2);
    Koin k4 = new Koin(0, 0, 1.3f, 10000, 2);
    List<Koin> koin = new List<Koin>();
    koin.add(k1);
    koin.add(k2);
    koin.add(k3);
    koin.add(k4);
    System.out.println(p.cariKoin(koin));
  }
}
