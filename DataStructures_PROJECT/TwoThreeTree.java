//class TwoThreeTree<T extends Comparable<T>> {
//    public class Node {
//        T val1;
//        T val2;
//        Node left;
//        Node middle;
//        Node right;
//
//
//
//
//
//
//
//
//    }
//
//    private Node root;
//
//    public void insert(T val) {
//        root = insert(root, val);
//    }
//
//    public Node insert(Node node, T val) {
//        if (node == null) {
//            // If the node is null, create a new node with the given value
//            node = new Node();
//            node.val1 = val;
//            return node;
//        }
//
//        if (node.val2 == null) {
//            // If the node has only one value, add the new value to the node
//            if (val.compareTo(node.val1) < 0) {
//                node.val2 = node.val1;
//                node.val1 = val;
//            } else {
//                node.val2 = val;
//            }
//        } else {
//            // If the node has two values, split the node and insert the new value
//            T val3 = val;
//            if (val.compareTo(node.val1) < 0) {
//                val3 = node.val2;
//                node.val2 = node.val1;
//                node.val1 = val;
//            } else if (val.compareTo(node.val2) < 0) {
//                val3 = node.val2;
//                node.val2 = val;
//            }
//
//            Node left = new Node();
//            left.val1 = node.val1;
//            left.left = node.left;
//            left.middle = node.middle;
//
//            Node right = new Node();
//            right.val1 = val3;
//            right.middle = node.right;
//
//            node.val1 = node.val2;
//            node.left = left;
//            node.middle = right;
//            node.right = null;
//            node.val2 = null;
//        }
//
//        return node;
//    }
//
//
//
//
//    public boolean search(int val) {
//        return search(root, val);
//    }
//
//    public boolean search(Node node, int val) {
//        if (node == null) {
//            // If the node is null, the value is not present in the tree
//            return false;
//        }
//
//
//        if (val == node.val1.hashCode() || (node.val2 != null && val == node.val2.hashCode())) {
//            // If the value is equal to one of the values at the node, it is present in the tree
//            return true;
//        }
//
//        if (val < node.val1.hashCode()) {
//            // If the value is less than the first value at the node, search the left subtree
//            return search(node.left, val);
//        } else if ( val < node.val2.hashCode()) {
//            // If the value is between the two values at the node, or if the node has only one value and the value is greater than that value, search the middle subtree
//            return search(node.middle, val);
//        } else {
//            // If the value is greater than the second value at the node, search the right subtree
//            return search(node.right, val);
//        }
//
//    }
//
//
//
//
//    public void remove(int val) {
//        root = remove(root, val);
//    }
//
//    public Node remove(Node node, int val) {
//        if (node == null) {
//            // If the node is null, the value is not present in the tree
//            return null;
//        }
//
//        if (val ==(node.val1.hashCode())) {
//            // If the value is equal to the first value at the node, remove it
//            if (node.val2 == null) {
//                // If the node has only one value, remove the node
//                return merge(node.left, node.middle);
//            } else {
//                // If the node has two values, remove the first value and move the second value to the front
//                node.val1 = node.val2;
//                node.val2 = null;
//                return node;
//            }
//        } else if (node.val2 != null && val == (node.val2.hashCode())) {
//            // If the value is equal to the second value at the node, remove it
//            node.val2 = null;
//            return node;
//        } else if (val-(node.val1.hashCode()) < 0) {
//            // If the value is less than the first value at the node, remove it from the left subtree
//            node.left = remove(node.left, val);
//        } else if (node.val2 == null || val-(node.val2.hashCode()) < 0) {
//            // If the value is between the two values at the node, or if the node has only one value and the value is greater than that value, remove it from the middle subtree
//            node.middle = remove(node.middle, val);
//        } else {
//            // If the value is greater than the second value at the node, remove it from the right subtree
//            node.right = remove(node.right, val);
//        }
//
//        return node;
//    }
//
//    public Node merge(Node left, Node right) {
//        if (left == null) {
//            return right;
//        }
//        if (right == null) {
//            return left;
//        }
//
//        // Merge the two nodes by combining their values and children
//        left.val2 = right.val1;
//        left.right = right.middle;
//        return left;
//    }
//
//
//
//
//    // Returns the successor of a given value in the 2-3 tree
//    public Node successor(T val) {
//        Node current = root;
//        Node successor = null;
//
//        // Search for the node containing the value
//        while (current != null) {
//            if (val.compareTo((T)current)==-1) {
//                successor = current;
//                current = current.left;
//            } else if (val.compareTo((T)current)==1) {
//                current = current.right;
//            } else {
//                // We found the node containing the value, so its successor is the
//                // minimum value in its right subtree
//                if (current.right != null) {
//                    successor = minValueNode(current.right);
//                }
//                break;
//            }
//        }
//
//        return successor;
//    }
//
//
//
//    private Node minValueNode(Node node) {
//        Node current = node;
//
//        // Find the leftmost leaf in the subtree
//        while (current.left != null) {
//            current = current.left;
//        }
//
//        return current;
//    }
//
//
//
//
//
//    // Returns the predecessor of a given value in the 2-3 tree
//    public Node predecessor(T val) {
//        Node current = root;
//        Node predecessor = null;
//
//        // Search for the node containing the value
//        while (current != null) {
//            if (val.compareTo((T)current)==-1) {
//                current = current.left;
//            } else if (val.compareTo((T)current)==1) {
//                predecessor = current;
//                current = current.right;
//            } else {
//                // We found the node containing the value, so its predecessor is the
//                // maximum value in its left subtree
//                if (current.left != null) {
//                    predecessor = maxValueNode(current.left);
//                }
//                break;
//            }
//        }
//
//        return predecessor;
//    }
//
//
//    // Returns the node with the maximum value in a given subtree
//    private Node maxValueNode(Node node) {
//        Node current = node;
//
//        // Find the rightmost leaf in the subtree
//        while (current.right != null) {
//            current = current.right;
//        }
//
//        return current;
//    }
//
//}
//
//



class Node<T extends Comparable<T>> {

    T value;
    Node<T> left, middle, right,parent;
    T key;

    public Node(T value) {
        this.value = value;
        this.key=value;
    }

    public Node(T val1,T val2) {
        this.value = val1;
        this.key=val2;
    }
}

class TwoThreeTree<T extends Comparable<T>> {
    Node<T> root;
    T MaxVal;
    T MinVal;


    public TwoThreeTree(T min,T max) {
        Node<T> x =new Node<T>(null);
        Node<T> l=new Node<T>(null);
        Node<T> m =new Node<T>(null);
        MaxVal=max;
        MinVal=min;
        l.key = min;
        l.value=min;
        m.key = max;
        m.value=max;
        l.parent =  x;
        m.parent=x;
       x.key = max;
        x.left = l;
      x.middle = m;
        root = x;

    }


    // Inserts a new value into the 2-3 tree
    public void insert(T value) {
        Node<T> y = root;
        Node<T> x=root;
        Node<T> z = new Node<T>(value);
        //System.out.println(z.key.compareTo(y.middle.key) < 0);
        if (y.value==null)
        while (y.value==null && y.key!=null) {
          //  System.out.println(y.key);
          //  System.out.println(z.key);
            if ((z.key.compareTo(y.left.key) < 0))
                y = y.left;

            else if (z.key.compareTo(y.middle.key) < 0) {
                y = y.middle;
             //   System.out.println(y.key);
            }

            else y = y.right;
        }

             x = y.parent;
            z = Insert_And_Split(x, z);
//            System.out.println(z.key);
          //  System.out.println(y.key);

         while (x !=root) {
             x = x.parent;
             if (z != null)
                 z = Insert_And_Split(x, z);
             else Update_Key(x);
         }
        if (z != null ){
        Node<T> w=new Node<T>(null, null);
         Set_Children(w, x, z, null);
        root = w;
    }
    }

    public void Update_Key(Node<T> x) {
        x.key = x.left.key;
        if (x.middle != null)
            x.key = x.middle.key;
        if (x.right != null)
        x.key = x.right.key;
    }


    public void Set_Children(Node<T> x,Node<T> l,Node<T> m,Node<T> r) {
        x.left = l;
        x.middle = m;
        x.right = r;
        l.parent = x;
        if (m != null)
            m.parent = x;
        if (r != null)
            r.parent = x;
        Update_Key(x);
    }
    public Node<T> Insert_And_Split(Node<T> x, Node<T> z) {
        Node<T> l = x.left;
        Node<T> m = x.middle;
        Node<T> r = x.right;
        if (r == null) {
            if (z.key.compareTo(l.key) < 0)
                Set_Children(x, z, l, m);

            else if (z.key.compareTo(m.key) < 0)
                Set_Children(x, l, z, m);
            else Set_Children(x, l, m, z);
            return null;
        }
        Node<T> y = new Node<T>(null, null);
        if (z.key.compareTo(l.key) < 0) {
            Set_Children(x, z, l, null);
            Set_Children(y, m, r, null);
        } else if (z.key.compareTo(m.key) < 0) {
            Set_Children(x, l, z, null);
            Set_Children(y, m, r, null);
        } else if (z.key.compareTo(r.key) < 0) {
            Set_Children(x, l, m, null);
            Set_Children(y, z, r, null);
        } else {
            Set_Children(x, l, m, null);
            Set_Children(y, r, z, null);
        }
        return y;
    }










        // public void remove(T value) {
    // Find the node containing the value
    //   Node<T> current = root;
    // while (current != null) {
    //   if (value.compareTo(current.value) < 0) {
    //     current = current.left;
    // } else if (value.compareTo(current.value) > 0) {
    //   current = current.right;
    //} else {
    // We found the node containing the value, so remove it
    //  remove(current);
    // break;
    //}
    // }
    //}

    // Removes a node from the 2-3 tree
    public Node<T> Borrow_Or_Merge(Node <T> y) {
    //    System.out.println(root.middle+ "  remove ");
        Node<T> z = y.parent;
        Node<T> x;
        if (y == z.left) {
            x = z.middle;
            if (x.right != null) {
                Set_Children(y, y.left, x.left, null);
                Set_Children(x, x.middle, x.right, null);
            } else {
                Set_Children(x, y.left, x.left, x.middle);
                // 8:delete y
                Set_Children(z, x, z.right, null);
            }
            return z;
        }
        if (y == z.middle) {
            x = z.left;
            if (x.right != null) {
                Set_Children(y, x.right, y.left, null);
                Set_Children(x, x.left, x.middle, null);
            } else {
                Set_Children(x, x.left, x.middle, y.left);
                //delete y
                Set_Children(z, x, z.right, null);
            }
            return z;
        }
        x = z.middle;
            if (x.right !=null) {
                Set_Children(y, x.right, y.left, null);
                Set_Children(x, x.left, x.middle, null);
            }
             else {Set_Children (x, x.left, x.middle, y.left);
           // 26:delete y
            Set_Children (z, z.left, x, null);
            }
        return z;
    }

    public void  remove(Node<T> x) {
        Node<T> y = x.parent;
        if (x == y.left)
            Set_Children(y, y.middle, y.right, null);
        else if (x == y.middle)
            Set_Children(y, y.left, y.right, null);
        else Set_Children(y, y.left, y.middle, null);
        //  7: delete x
        // 8: . . .
        while (y != null) {
     //       System.out.println(y + "   y ");
            if (y.middle == null) {
          //      System.out.println(" lo machakti ");

                if (y != root)
                    y = Borrow_Or_Merge(y);
                else {
                    root = y.left;


                    y.left.parent = null;
             //       System.out.println(root + " root ");
                    //14:delete y
                    return;
                }
            }

                    else {
                Update_Key(y);
                y = y.parent;
            }

        }
    }







    public Node<T> search( T k) {
       return search  (root, k);

    }
    public Node<T> search( int k) {
        return search  (root, k);

    }


    public Node<T> search(Node<T> x, int k) {


        if (x.key!=null && x.value!=null) {

            if (x.value.hashCode()==k) {
           //     System.out.println(k+" fdfgk ");

                return x;
            }
            else return null;
        }
        if (!((x.left.key.hashCode()<k)))
            return search(x.left, k);
        if (!(x.middle.key.hashCode()<k))
            return  search(x.middle, k);
        else return search(x.right, k);
    }

    public Node<T> search(Node<T> x, T k) {
   //     if (k.hashCode()==1595953398)
          //  System.out.println(x.left.key.compareTo(k)+ " ggg "+ x.left.key);


        if (x.key!=null && x.value!=null) {
            if (x.value.compareTo(k)==0) {
            //    System.out.println(k+"gg");
           //     System.out.println(x.value+"gg");
                return x;
            }
            else {
                System.out.println(x.key);
                //System.out.println(k.hashCode());
                return null;
            }
        }
        if ((k.compareTo(x.left.key)<=0)) {
          //  if (k.hashCode()==1595953398)
          //      System.out.println(x.left.key+ "   yes   " + k);
            return search(x.left, k);

        }
        else if ((k.compareTo(x.middle.key)<=0) && (k.compareTo(x.left.key)>0))
        {
      //      if (k.hashCode()==1595953398)
      //          System.out.println(x.middle.key+ " middle" + x.middle.value);
        return  search(x.middle, k);
        }
        else return search(x.right, k);
    }


    // Returns the successor of a given value in the 2-3 tree
    public Node<T> successor(T val) {
        Node x=search(root,val);
      //  System.out.println(x);
        Node<T> y;
        Node<T> z = x.parent;
         while (x == z.right || (z.right == null && x == z.middle)) {
            x = z;
            z = z.parent;
        }
         if (x == z.left)
         y = z.middle;

         else y = z.right;
         while (y.value==null && y.key!=null )
            y = y.left;

         return y;

         // return null;
    }






    // Returns the predecessor of a given value in the 2-3 tree
    public Node<T> predecessor(T val) {
        Node x=search(root,val);
        Node<T> y;
        Node<T> z = x.parent;
        while (x == z.left) {
            x = z;
            z = z.parent;
        }
        if (x == z.right)
            y = z.middle;

        else y = z.left;
        while (y.value==null && y.key!=null ) {
            if (y.right!=null)
            y = y.right;
            else
                y=y.middle;
        }
    //    if (y.key.compareTo(MinVal) >0)
            return y;
      //  else return null;
    }



        // We didn't find the value







        //   Node<T> current = root;
        // while (current != null) {

        //     if ((value-current.value.hashCode()) < 0) {

        //       current = current.left;
        // } else if (value-(current.value.hashCode()) > 0) {
        //    current = current.middle;
        //} else {
        // We found the value
        //   return current;
        //}
        //  }

        // We didn't find the value

    }

























































