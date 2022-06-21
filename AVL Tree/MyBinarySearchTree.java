import java.util.Stack;
public class MyBinarySearchTree<Type extends Comparable<Type>> {
    class Node {
        public Type item;
        public Node left;
        public Node right;
        public int height = 0;

        public int balanceFactor() {
            return height(left) - height(right);
        }
        public Node(Type item) {
            this.item = item;
        }

        @Override
        public String toString() {
            return item.toString() + ":H" + height + ":B" + balanceFactor();
        }
    }

    private Node root;
    private int size;
    public long comparisons;
    private boolean balancing = true;
    public Integer rotations = 0;

    public MyBinarySearchTree() {
        root = null;
        size = 0;
        comparisons = 0;
    }

    public MyBinarySearchTree(boolean balancing) {
        this.balancing = balancing;
    }

    public void add(Type item) {
        root = add(item, root);
    }

    private Node add(Type item, Node subtree) {
        // comparisons++;
        if (subtree == null) {
//            Node node = new Node(item);
//            //updateHeight method
//            updateHeight(node);
            size++;
            // root=node;

            return new Node(item);
        }
            if (item.compareTo(subtree.item) > 0) {
                subtree.right = add(item, subtree.right);
            } else if (item.compareTo(subtree.item) < 0) {
                subtree.left = add(item, subtree.left);
            } else {
                //return subTree;


//            size++;
//        updateHeight(subTree);
                return subtree;
            }
            //
            if (balancing) {
                subtree.height = 1 + Math.max(height(subtree.left), height(subtree.right));
                int balance = subtree.balanceFactor();
                if (balance > 1 && item.compareTo(subtree.left.item) < 0) {
                    return rotateRight(subtree);

                } else if (balance < -1 && item.compareTo(subtree.right.item) > 0) {
                    return rotateLeft(subtree);

                } else if (balance > 1 && item.compareTo(subtree.left.item) > 0) {
                    subtree.left = rotateLeft(subtree.left);
                    return rotateRight(subtree);

                } else if (balance < -1 && item.compareTo(subtree.right.item) < 0) {
                    subtree.right = rotateRight(subtree.right);
                    return rotateLeft(subtree);
                }

            } else {
                subtree.height = 1 + Math.max(height(subtree.left), height(subtree.right));
            }

        return subtree;
        }
    public void remove(Type item) {
        root = remove(item, root);
    }
        private Node remove(Type item, Node subTree) {
        if (subTree == null) {
            return subTree;
        }
        if (item.compareTo(subTree.item) > 0) {
            subTree.right= remove(item, subTree.right);
        } else if (item.compareTo(subTree.item) < 0) {
            subTree.left = remove(item, subTree.left);
        } else {
             if (subTree.right == null) {
                return subTree.left;
            }
            if (subTree.left == null) {
                return subTree.right;
            }
            subTree.item = findMin(subTree.right).item;
            subTree.right = remove(subTree.item, subTree.right);
        }

        if (balancing) {
            subTree.height = 1 + Math.max(height(subTree.left), height(subTree.right));
            int balance = subTree.balanceFactor();
            if (balance > 1 && subTree.left.balanceFactor() >= 0) {
                return rotateRight(subTree);
            } else if (balance < -1 && subTree.right.balanceFactor() <= 0) {
                return rotateLeft(subTree);
            } else if (balance > 1 && subTree.left.balanceFactor() < 0) {
                subTree.left = rotateLeft(subTree.left);
                return rotateRight(subTree);
            } else if (balance < -1 && subTree.right.balanceFactor() > 0) {
                subTree.right = rotateRight(subTree.right);
                return rotateLeft(subTree);
            }
        } else {
            subTree.height = 1 + Math.max(height(subTree.left), height(subTree.right));
        }

        return subTree;
    }
    private Node findMin(Node subTree) {
    if (subTree.left == null) {
        return subTree;
    }
    return findMin(subTree.left);
}

//find
    public Type find(Type item) {
        return find(item, root);
    }
    private Type find(Type item, Node subtree) {
        comparisons++;
        if (subtree == null) {
            return null;
        } else if (item.compareTo(subtree.item) < 0) {
            return find(item, subtree.left);
        } else if (item.compareTo(subtree.item) > 0) {
            return find(item, subtree.right);
        } else {
            return subtree.item;
        }
    }

    public int height() {
        return height(root);
    }

    public int size() {
        return size;
    }
    public boolean isEmpty() {
        return root == null;
    }
    private void updateHeight(Node node) {
        if (node != null) {
            node.height = 1 + Math.max(height(node.left), height(node.right));
        } else {
            node.height = 0;
        }
    }
    private Node rotateRight(Node node) {
        rotations++;
        Node swap = node.left;
        node.left = swap.right;
        swap.right = node;
        updateHeight(node);
        updateHeight(swap);
        return swap;
    }
    private Node rotateLeft(Node node) {
        rotations++;
        Node swap = node.right;
        node.right = swap.left;
        swap.left = node;
        updateHeight(node);
        updateHeight(swap);
        return swap;
    }

    private Node rebalance(Node node) {
        if (node == null) {
            return null;
        }
        int lHeight = node.left == null ? 0 : node.left.height;
        int rHeight = node.right == null ? 0 : node.right.height;
        node.height = Math.max(lHeight, rHeight) + 1;

        if (balancing) {
            int balanceFactor = node.balanceFactor();
            if (balanceFactor > 1) {
                if (node.left.balanceFactor() < 0) {
                    node.left = rotateLeft(node.left);
                }
                rotations++;
                return rotateRight(node);
            } else if (balanceFactor < -1) {
                if (node.right.balanceFactor() > 0) {
                    node.right = rotateRight(node.right);
                }
                rotations++;
                return rotateLeft(node);
            }
        }
        return node;
    }
    @Override
    public String toString() {
        if (root == null)
            return "[]";

        StringBuilder stringg = new StringBuilder("[");
        Stack<Node> stack = new Stack<>();
        Node current = root;
        while (!stack.isEmpty() || current != null) {
          while (current != null) {
                stack.push(current);
                current = current.left;
            }
            current = stack.pop();
            stringg.append(current);
            current = current.right;
            if (!stack.isEmpty() || current != null)
                stringg.append(", ");
        }
        return stringg.append("]").toString();
    }
//height
    private int height(Node node) {
        if (node == null) {
            return -1;
        }
        return node.height;
    }
}
























