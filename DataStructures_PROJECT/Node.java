//public class Node<E>  {
//    private E data;
//    private Node<E> left;
//    private Node<E> middle;
//    private Node<E> right;
//
//    public Node(E data, Node<E> left,Node<E> middle, Node<E> right) {
//        this.data = data;
//        this.left = left;
//        this.middle = middle;
//        this.right = null;
//    }
//
//    public Node(E data) {
//        this(data,null, null, null);
//    }
//
//    public int getFacultyId() {
//        if (this.data instanceof FacultyData) {
//            FacultyData data1 = (FacultyData) data;
//            return data1.getFaculty().getId();
//        }
//        return 0;
//    }
//
//
//
//    public E getData() {
//        return data;
//    }
//
//    public Node<E> getLeft() {
//        return left;
//    }
//
//    public Node<E> getRight() {
//        return right;
//    }
//
//    public void setData(E data) {
//        this.data = data;
//    }
//
//    public void setLeft(Node<E> left) {
//        this.left = left;
//    }
//
//    public void setRight(Node<E> right) {
//        this.right = right;
//    }
//
//
//}