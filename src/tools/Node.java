package tools;

public class Node<T> {
  public Node(T val) {
    this.val = val;
    next = null;
  }

  public T getValue() {
    return val;
  }

  public Node<T> getNext() {
    return next;
  }

  public void setNext(Node<T> ne) {
    next = ne;
  }

  public void setValue(T val) {
    this.val = val;
  }

  private T val;
  private Node<T> next;
}
