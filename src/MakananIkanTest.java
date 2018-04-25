import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class MakananIkanTest {

  @Test
  public void gerak() {
    MakananIkan sushi = new MakananIkan(300, 450);
    while (sushi.getY() < 640) {
      sushi.gerak();
    }
    assertEquals(640, sushi.getY(), 0.0001);
  }
}