import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class PlayerTest {

  @Test
  public void getJumlahkoin() {
    Player p1 = new Player();
    assertEquals(2000, p1.getJumlahkoin(), 0.001);
  }

  @Test
  public void setJumlahkoin() {
    Player p = new Player();
    p.setJumlahkoin(100);
    assertEquals(100, p.getJumlahkoin(), 0.001);
  }

  @Test
  public void getBanyaktelur() {
    Player p1 = new Player(500, 1);
    assertEquals(500, p1.getJumlahkoin(), 0.001);
  }

  @Test
  public void setBanyaktelur() {
    Player p = new Player();
    p.setBanyaktelur(1);
    assertEquals(1, p.getBanyaktelur(), 0.001);
  }

  @Test
  public void tambahKoin() {
    Player p = new Player(0, 0);
    p.tambahKoin(1);
    assertEquals(1, p.getJumlahkoin(), 0.001);
  }

  @Test
  public void kurangkanKoin() {
    Player p = new Player();
    p.kurangkanKoin(2000);
    assertEquals(0, p.getJumlahkoin(), 0.001);
  }

  @Test
  public void tambahTelur() {
    Player p = new Player(0, 0);
    p.tambahTelur();
    assertEquals(1, p.getBanyaktelur(), 0.001);
  }
}