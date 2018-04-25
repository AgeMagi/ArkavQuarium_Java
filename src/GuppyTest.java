import static org.junit.Assert.assertEquals;

import org.junit.Test;


public class GuppyTest {

  @Test
  public void gerak() {
    Guppy s1 = new Guppy(99, 640, 90, 10000);
    MakananIkan pointtujuan = new MakananIkan(1, 640);
    while (pointtujuan.getX() < s1.getX()) {
      s1.gerak();
    }
    assertEquals(s1.getX(), pointtujuan.getX(), 0.1);
  }

  @Test
  public void cariMakananIkanTerdekat() {
    final Ikan p = new Guppy(5, 2, 0, 0);
    MakananIkan g1 = new MakananIkan(10, 10);
    MakananIkan g2 = new MakananIkan(0, 10);
    MakananIkan g3 = new MakananIkan(10, 0);
    MakananIkan g4 = new MakananIkan(0, 0);
    List<MakananIkan> makanan = new List<MakananIkan>();
    makanan.add(g1);
    makanan.add(g2);
    makanan.add(g3);
    makanan.add(g4);
    assertEquals(2, ((Guppy) p).cariMakananIkanTerdekat(makanan), 0.0001);
  }

  @Test
  public void cariMakanGuppy() {
    final Ikan p = new Guppy(5, 2, 0, 0);
    MakananIkan g1 = new MakananIkan(10, 10);
    MakananIkan g2 = new MakananIkan(0, 10);
    MakananIkan g3 = new MakananIkan(10, 0);
    MakananIkan g4 = new MakananIkan(0, 0);
    List<MakananIkan> makanan = new List<MakananIkan>();
    makanan.add(g1);
    makanan.add(g2);
    makanan.add(g3);
    makanan.add(g4);
    System.out.println(p.cariMakanGuppy(makanan));
  }
}