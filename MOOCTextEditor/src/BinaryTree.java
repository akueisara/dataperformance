import java.util.LinkedList;
import java.util.Queue;

public class BinaryTree<E extends Comparable<E>> {
  TreeNode<E> root;
  
  public BinaryTree() {
    root = null;
  }
 
  private void preOrder(TreeNode<E> node) {
    if(node != null) {
      node.visit();
      preOrder(node.getLeft());
      preOrder(node.getRight());
    }
  }
  
  public void preOrder() {
    this.preOrder(root);
  }
  
  private void postOrder(TreeNode<E> node) {
    if(node != null) {
      postOrder(node.getLeft());
      postOrder(node.getRight());
      node.visit();
    }
  }
  
  public void postOrder() {
    this.postOrder(root);
  }
  
  private void inOrder(TreeNode<E> node) {
    if(node != null) {
      inOrder(node.getLeft());
      node.visit();
      inOrder(node.getRight());
    }
  }
  
  public void inOrder() {
    this.inOrder(root);
  }
  
  public void levelOrder() {
    Queue<TreeNode<E>> q = new LinkedList<TreeNode<E>>();
    q.add(root);
    while(!q.isEmpty()) {
      TreeNode<E> curr = q.remove();
      if(curr != null) {
        curr.visit();
        q.add(curr.getLeft());
        q.add(curr.getRight());
      }
    }
  }
  
  public boolean contains(E toFind) {
    TreeNode<E> curr = root;
    while(curr != null) {
      int comp = toFind.compareTo(curr.getData());
      if(comp < 0) {
        curr = curr.getLeft();
      } else if(comp > 0) {
        curr = curr.getRight();
      } else {
        return true;
      }
    }
    return false;
  }

  public boolean insert(E toInsert) {
    TreeNode<E> curr = root;
    int comp = toInsert.compareTo(curr.getData());
    while(comp < 0 && curr.getLeft() != null || comp > 0 && curr.getRight() != null) {
      if(comp < 0) {
        curr = curr.getLeft();
      } else {
        curr = curr.getRight();
      }
      comp = toInsert.compareTo(curr.getData());
    }
    // curr points to the last node
    if(comp < 0) {
      curr.setLeft(toInsert, curr);
    } else if(comp > 0) {
      curr.setRight(toInsert, curr);
    } else {
      return false; // we found the element
    }
    return true;
  }
  
}
