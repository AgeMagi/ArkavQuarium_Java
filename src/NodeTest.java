import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;

import org.junit.Test;

public class NodeTest {

  @Test
  public void getValue() {
    Node<Integer> test = new Node<Integer>(125);
    assertEquals((long) 125, (long) test.getValue());
  }

  @Test
  public void getNext() {
    Node<Integer> test1 = new Node<Integer>(1);
    assertNull(test1.getNext());
  }

  @Test
  public void setNext() {
    Node<Integer> test1 = new Node<Integer>(1);
    Node<Integer> test2 = new Node<Integer>(2);
    test2.setNext(test1);
    assertSame(test1, test2.getNext());
  }

  @Test
  public void setValue() {
    Node<Integer> test = new Node<Integer>(123456);
    test.setValue(654321);
    assertEquals((long) 654321, (long) test.getValue());
  }
}