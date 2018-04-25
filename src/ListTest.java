import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.junit.Test;

public class ListTest {

  @Test
  public void isEmpty() {
    List<Long> test = new List<Long>();
    assertTrue(test.isEmpty());
    test.add(123L);
    assertFalse(test.isEmpty());
    test.delLast();
    assertTrue(test.isEmpty());
  }

  @Test
  public void add() {
    List<Long> test = new List<Long>();
    test.add(123L);
    test.add(456L);
    assertFalse(test.isEmpty());
    assertEquals(2, test.getSize());
    assertEquals(123L, (long) test.delFirst());
    assertEquals(456L, (long) test.delFirst());
    assertTrue(test.isEmpty());
  }

  @Test
  public void addFirst() {
    List<Long> test = new List<Long>();
    test.addFirst(123L);
    test.addFirst(456L);
    assertFalse(test.isEmpty());
    assertEquals(2, test.getSize());
    assertEquals(456L, (long) test.delFirst());
    assertEquals(123L, (long) test.delFirst());
    assertTrue(test.isEmpty());
  }

  @Test
  public void removeIdx() {
    List<Long> test = new List<Long>();
    test.add(1L);
    test.add(2L);
    test.add(3L);
    test.add(4L);
    test.add(5L);
    test.add(6L);
    test.add(7L);
    test.add(8L);
    assertEquals(8, test.getSize());
    test.removeIdx(4);
    assertEquals(7, test.getSize());
    for (int i = 0; i < 4; i++) {
      assertEquals(i + 1, (long) test.getIdx(i));
    }
    for (int i = 4; i < 7; ++i) {
      assertEquals(i + 2, (long) test.getIdx(i));
    }
    test.removeIdx(0);
    assertEquals(6, test.getSize());
    for (int i = 0; i < 3; i++) {
      assertEquals(i + 2, (long) test.getIdx(i));
    }
    for (int i = 3; i < 6; ++i) {
      assertEquals(i + 3, (long) test.getIdx(i));
    }
    test.removeIdx(5);
    assertEquals(5, test.getSize());
    for (int i = 0; i < 3; i++) {
      assertEquals(i + 2, (long) test.getIdx(i));
    }
    for (int i = 3; i < 5; ++i) {
      assertEquals(i + 3, (long) test.getIdx(i));
    }
  }

  @Test
  public void remove() {
    List<Long> test = new List<Long>();
    test.add(1L);
    test.add(2L);
    test.add(3L);
    test.add(4L);
    test.add(5L);
    test.add(6L);
    test.add(7L);
    test.add(8L);
    assertEquals(8, test.getSize());
    test.remove(2L);
    assertEquals(3, (long) test.getIdx(1));
    assertEquals(1, (long) test.getIdx(0));
    assertEquals(7, test.getSize());
  }

  @Test
  public void delFirst() {
    List<Long> test = new List<Long>();
    test.add(1L);
    test.add(2L);
    test.add(3L);
    test.add(4L);
    test.add(5L);
    test.add(6L);
    test.add(7L);
    test.add(8L);
    for (int i = 0; i < 8; i++) {
      assertEquals(i + 1, (long) test.delFirst());
      assertEquals(7 - i, test.getSize());
    }
    assertTrue(test.isEmpty());
  }

  @Test
  public void delLast() {
    List<Long> test = new List<Long>();
    test.add(1L);
    test.add(2L);
    test.add(3L);
    test.add(4L);
    test.add(5L);
    test.add(6L);
    test.add(7L);
    test.add(8L);
    for (int i = 8; i > 0; i--) {
      assertEquals(i, (long) test.delLast());
      assertEquals(i - 1, test.getSize());
    }
    assertTrue(test.isEmpty());
  }

  @Test
  public void getSize() {
    List<Long> test = new List<Long>();
    for (int i = 0; i < 10; i++) {
      test.add((long) i);
      assertEquals(i + 1, test.getSize());
    }
  }

  @Test
  public void find() {
    List<Long> test = new List<Long>();
    test.add(123L);
    test.add(22L);
    test.add(78L);
    test.add(95L);
    test.add(15L);
    assertEquals(1, test.find(22L));
    assertEquals(4, test.find(15L));
    assertEquals(-1, test.find(2L));
  }

  @Test
  public void getIdx() {
    List<Long> test = new List<Long>();
    test.add(123L);
    test.add(22L);
    test.add(78L);
    test.add(95L);
    test.add(15L);
    assertEquals(22, (long) test.getIdx(1));
    assertEquals(78, (long) test.getIdx(2));
    assertEquals(15, (long) test.getIdx(4));
  }
}