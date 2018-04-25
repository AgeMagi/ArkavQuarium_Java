import static org.junit.Assert.assertEquals;

public class PiranhaTest {

  @org.junit.Test
  public void gerak() {
    Piranha piranha1 = new Piranha(0, 0, 0, 10);
    while (piranha1.getX() < 10) {
      piranha1.gerak();
    }
    double temp1 = piranha1.getX();
    double temp2 = 10;
    assertEquals(temp2, temp1, 0.001);
  }

  @org.junit.Test
  public void cariIkanTerdekat() {
    final Ikan p = new Piranha(5, 2, 0, 0);
    Ikan g1 = new Guppy(10, 10, 0, 0);
    Ikan g2 = new Guppy(0, 10, 0, 0);
    Ikan g3 = new Guppy(10, 0, 0, 0);
    Ikan g4 = new Guppy(0, 0, 0, 0);
    List<Ikan> ikan = new List<Ikan>();
    ikan.add(g1);
    ikan.add(g2);
    ikan.add(g3);
    ikan.add(g4);
    System.out.println(((Piranha) p).cariIkanTerdekat(ikan));
  }
}