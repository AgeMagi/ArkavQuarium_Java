package tools;

public class List<T> {
  private int size;
  private Node<T> node;

  public List() {
    node = null;
    size = 0;
  }

  public boolean isEmpty() {
    return size == 0;
  }

  /**
   * Untuk menambahkan isi dari list.
   * @param val .
   */
  public void add(T val) {
    size++;
    if (node == null) {
      node = new Node<T>(val);
      return;
    }
    Node<T> now = node;
    while (now.getNext() != null) {
      now = now.getNext();
    }
    now.setNext(new Node<T>(val));
  }

  /**
   * Untuk menambahkan isi dengan indeks 0 pada list.
   * @param val .
   */
  public void addFirst(T val) {
    Node<T> now = node;
    node = new Node<T>(val);
    node.setNext(now);
    size++;
  }

  /**
   * Untuk menghilangkan isi list dengan indeks tertentu pada list.
   * @param idx .
   */
  public void removeIdx(int idx) {
    Node<T> temp = node;
    if (idx == 0) {
      delFirst();
      return;
    }
    while (temp.getNext() != null && idx > 1) {
      temp = temp.getNext();
      idx--;
    }
    if (temp.getNext() != null) {
      --size;
      Node<T> del = temp.getNext();
      temp.setNext(del.getNext());
    }

  }

  /**
   * Untuk menghapus isi list dengan objek tertentu.
   * @param item .
   */
  public void remove(T item) {
    if (item.equals(node.getValue())) {
      delFirst();
      return;
    }
    Node<T> now = node;
    while (now.getNext() != null && !item.equals(now.getNext().getValue())) {
      now = now.getNext();
    }
    if (now.getNext() == null) {
      return;
    }
    --size;
    now.setNext(now.getNext().getNext());
  }

  /**
   * Untuk menghapus isi pertama dari list.
   * @return T.
   */
  public T delFirst() {
    Node<T> now = node;
    node = node.getNext();
    size--;
    return now.getValue();
  }

  /**
   * Untuk menghapus isi terakhir dari list.
   * @return T.
   */
  public T delLast() {
    Node<T> now = node;
    if (node.getNext() == null) {
      node = null;
      size--;
      return now.getValue();
    }
    while (now.getNext().getNext() != null) {
      now = now.getNext();
    }
    Node<T> temp = now.getNext();
    now.setNext(null);
    size--;
    return temp.getValue();
  }

  /**
   * Untuk mengembalikan nilai attribut size.
   * @return int.
   */
  public int getSize() {
    return size;
  }

  /**
   * Untuk mengembalikan indeks objek tertentu pada parameter.
   * @param val .
   * @return int .
   */
  public int find(T val) {
    Node<T> now = node;
    int idx = 0;
    while (now != null && !val.equals(now.getValue())) {
      now = now.getNext();
      idx++;
    }
    if (now == null) {
      return -1;
    }
    return idx;
  }

  /**
   * Mengembalikan isi pada list dengan indeks tertentu pada parameter.
   * @param idx .
   * @return T.
   */
  public T getIdx(int idx) {
    Node<T> now = node;
    while (idx > 0) {
      now = now.getNext();
      idx--;
    }
    return now.getValue();
  }
}
