
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

        if (y.value==null)
        while (y.value==null && y.key!=null) {
            if ((z.key.compareTo(y.left.key) < 0))
                y = y.left;

            else if (z.key.compareTo(y.middle.key) < 0) {
                y = y.middle;
            }

            else y = y.right;
        }

             x = y.parent;
            z = Insert_And_Split(x, z);

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

    // Removes a node from the 2-3 tree
    public Node<T> Borrow_Or_Merge(Node <T> y) {
        Node<T> z = y.parent;
        Node<T> x;
        if (y == z.left) {
            x = z.middle;
            if (x.right != null) {
                Set_Children(y, y.left, x.left, null);
                Set_Children(x, x.middle, x.right, null);
            } else {
                Set_Children(x, y.left, x.left, x.middle);
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
        while (y != null) {
            if (y.middle == null) {
                if (y != root)
                    y = Borrow_Or_Merge(y);
                else {
                    root = y.left;
                    y.left.parent = null;
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
        if (x.key!=null && x.value!=null) {
            if (x.value.compareTo(k)==0) {
                return x;
            }
            else {
                System.out.println(x.key);
                return null;
            }
        }
        if ((k.compareTo(x.left.key)<=0)) {
            return search(x.left, k);
        }
        else if ((k.compareTo(x.middle.key)<=0) && (k.compareTo(x.left.key)>0))
        {
        return  search(x.middle, k);
        }
        else return search(x.right, k);
    }


    // Returns the successor of a given value in the 2-3 tree
    public Node<T> successor(T val) {
        Node x=search(root,val);
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
        return y;
    }

}

























































