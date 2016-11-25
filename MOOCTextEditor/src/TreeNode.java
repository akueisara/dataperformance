
public class TreeNode<E extends Comparable<E>> {
  private E value;
  private TreeNode<E> parent;
  private TreeNode<E> left;
  private TreeNode<E> right;
  
  public TreeNode(E newValue) {
    this.value = newValue;
    this.parent = null;
    this.left = null;
    this.right = null;
  }
  
  public TreeNode(E val, TreeNode<E> par) {
    this.value = val;
    this.parent = par;
    this.left = null;
    this.right = null;
  }
  
  public E getData() {
    return value;
  }
  
  public TreeNode<E> addLeftChild(E val) {
    this.left = new TreeNode<E>(val, this);
    return this.left;
  }
  
  public TreeNode<E> getLeft() {
    return left;
  }
  
  public void setLeft(E value, TreeNode<E> node) {
    TreeNode<E> newLeft = new TreeNode<E>(value, node);
    this.left = newLeft;
  }
  
  public TreeNode<E> getRight() {
    return right;
  }
  
  public void setRight(E value, TreeNode<E> node) {
    TreeNode<E> newRight = new TreeNode<E>(value, node);
    this.right = newRight;
  }
  
  public void visit() {
    System.out.print(value);
  }
    
  public int compareTo(TreeNode<E> otherTreeNode) {
    return this.getData().compareTo(otherTreeNode.getData());
  }
  
}
