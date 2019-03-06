package assignment04;


import java.util.Stack;
/**
 * File: BinarySearchTree.java
 * 
 * @author: Hannah Borreson
 * 
 * @version 4/18/2017
 * 
 *          Description: The main purpose of the BinarySearchTree program is to
 *          create a fully functional Binary Search Tree.
 * 
 */
public class BinarySearchTree<E extends Comparable<E>> {

	private BSTNode<E> root;

	private int size;

	public BinarySearchTree() {

		root = null;

		size = 0;

	}

	public BinarySearchTree(BSTNode<E> node) {

		root = node;

		size = 1;

	}

	/**
	 * searches for a node that contains it.
	 * 
	 * if it finds it, it returns that node
	 * 
	 * else it returns null
	 * 
	 * @param it
	 *            - the element to look for
	 * 
	 * @return the node that contains it
	 * 
	 */

	public BSTNode<E> search(E it) {
		BSTNode<E> node = root;
		while (node != null) {
			int res = it.compareTo(node.getElement());
			if (res < 0) {
				node = node.getLeft();
			} else if (res > 0) {
				node = node.getRight();
			} else {
				return node;
			}
		}
		System.out.println(it + " is not here.");
		return null;

	}

	/**
	 * determines is the tree contains the element
	 * 
	 * @return true if it is in the tree
	 * @param it item to look for
	 * 
	 */

	public boolean contains(E it) {
		BSTNode<E> node = root;
		while (node != null) {
			int res = it.compareTo(node.getElement());
			if (res < 0) {
				node = node.getLeft();
			} else if (res > 0) {
				node = node.getRight();
			} else {
				return true;
			}
		}
		System.out.println(it + " is not in the tree.");
		return false;

	}

	/**
	 * Add the element to the correct location
	 * 
	 * all elements to the left are less than the parent
	 * 
	 * all elements to the rights are greater than the parent
	 * 
	 * Do not allow duplicates
	 * 
	 * @param it
	 *            the element to insert
	 * 
	 */

	public void insert(E it) {
		BSTNode<E> newNode = new BSTNode<E>(it);
		if (root == null) {
			root = newNode;
			size++;
			return;
		}
		BSTNode<E> parent = null;
		BSTNode<E> node = root;
		while (node != null) {
			parent = node;
			int compareResult = it.compareTo(node.getElement());
			if (compareResult < 0) {
				node = node.getLeft();
			} else if (compareResult > 0) {
				node = node.getRight();
			} else {
				// duplicate
				return;
			}
		}
		int res = it.compareTo(parent.getElement());
		if (res < 0) {
			parent.setLeft(newNode);
		} else {
			parent.setRight(newNode);
		}
		size++;
	}

	/**
	 * Removes the node that contains it.
	 * 
	 * If the tree does not contain it, it prints that to
	 * 
	 * the user and does nothing else.
	 * 
	 * Otherwise it removes the node and maintains the
	 * 
	 * BST properties
	 * 
	 * if removing a node with two children, replace it
	 * 
	 * with its inorder predecessor.
	 * 
	 * @param the
	 *            element of the node you want to remove.
	 * 
	 */

	public void remove(E it) {
		BSTNode<E> parent = null;
		BSTNode<E> child = null;
		BSTNode<E> node = root;
		// find the node that contains it

		while (node != null && node.getElement() != it) {
			parent = node;
			int compareResult = it.compareTo(node.getElement());
			if (compareResult < 0) {
				node = node.getLeft();
			} else {
				node = node.getRight();
			}
		} // end while

		if (node == null) {
			System.out.println("Failed to find : " + it + " for removal");
			return;
		}
		// determine what the node is
		if (node.isLeaf()) { // leaf
			if (parent == null) { // single node tree
				root = null;
			} else if (it.compareTo(parent.getElement()) < 0) {
				parent.setLeft(null);
			} else {
				parent.setRight(null);
			}
			// not a leaf has right child

		} // end if leaf
		else if (node.getLeft() == null) {
			child = node.getRight();
			swapElements(node, child);
			node.setLeft(child.getLeft()); // attach kids
			node.setRight(child.getRight());
			// not leaf has left child
		} else if (node.getRight() == null) {
			child = node.getLeft();
			swapElements(node, child);
			node.setLeft(child.getLeft()); // attach kids
			node.setRight(child.getRight());
		} else {
			child = node.getLeft();
			parent = null;
			while (child.getRight() != null) {
				parent = child;
				child = parent.getRight();
			}
			if (parent == null) { // couldn't go right
				swapElements(node, child);
				node.setLeft(child.getLeft());
			} else { // you went right
				swapElements(node, child);
				parent.setRight(child.getLeft());
			}
		}
		size--;
	}

	/**
	 * Returns the height of the tree
	 * 
	 * if tree is empty, height is -1
	 * 
	 * if tree only has one node, height is 0
	 * 
	 * @return the integer height of the tree
	 *
	 * 
	 * 
	 */

	public int getHeight() {
		int height = -1;
		QueueList<BSTNode> q = new QueueList<BSTNode>();

		if (root == null) {
			return height;
		}
		q.enqueue(root);
		while (!q.isEmpty()) {
			int nodeCount = q.size();
			height++;

			while (nodeCount > 0) {
				BSTNode<E> node = q.dequeue();
				if (node.hasLeft()) {
					q.enqueue(node.getLeft());
				}
				if (node.hasRight()) {
					q.enqueue(node.getRight());
				}
				nodeCount--;
			}
		}
		return height;
	}

	/**
	 * Helper method
	 * 
	 * For removal you need to swap elements of nodes
	 * 
	 * @param node1
	 *            , node2 the nodes whose contents you are swapping
	 * 
	 */

	private void swapElements(BSTNode<E> node1, BSTNode<E> node2) {
		E temp1 = node1.getElement();
		E temp2 = node2.getElement();
		node1.setElement(temp2);
		node2.setElement(temp1);
	}

	/**
	 * prints each level of the tree on its own line
	 * 
	 * use your Queue class
	 * 
	 */
//breadth first 
	public void printLevelOrder() {
		int height = -1;
		QueueList<BSTNode> q = new QueueList<BSTNode>();

		if (root == null) {
			System.out.println("This tree is empty. ");
			return;
		}
		q.enqueue(root);
		while (!q.isEmpty()) {
			int nodeCount = q.size();

			while (nodeCount > 0) {
				BSTNode<E> node = q.dequeue();
				System.out.print(node.getElement() + " ");
				if (node.hasLeft()) {
					q.enqueue(node.getLeft());
					
				}
				if (node.hasRight()) {
					q.enqueue(node.getRight());
			
				}
				

				nodeCount--;
			}
			System.out.println("");

		}
	}

	/**
	 * prints the tree in a depth-first fashion
	 * 
	 * use your Stack class
	 * 
	 */

	public void printByDepth() {
		StackList<BSTNode> s = new StackList<BSTNode>();
		s.push(root);
		while (!s.isEmpty()) {
			BSTNode n = s.pop();
			System.out.println(n + " ");
			if (n.hasRight()) {
				s.push(n.getRight());
			}
			if (n.hasLeft()) {
				s.push(n.getLeft());
			}
		}

	}

	/**
	 * prints the tree in an inorder fashion.
	 * 
	 * uses a stack to push left children onto the stack
	 * 
	 */

	public void printInOrder() {
		Stack<BSTNode> S = new Stack<BSTNode>();
		BSTNode node = root;
		pushLeftNodesToStack(S, node);
		while (!S.empty()) {
			BSTNode<E> n = S.pop();
			System.out.println(n + " ");
			n = n.getRight();
			pushLeftNodesToStack(S, n);
		}

	}
	/**
	 * pushes all of the left nodes and left children onto a stack
	 * 
	 * @param s stack of BST nodes 
	 * @param b BST node 
	 * 
	 */
	public void pushLeftNodesToStack(Stack<BSTNode> s, BSTNode<E> b) {
		while (b != null) {
			s.push(b);
			b = b.getLeft();
		}
	}
	
	
	/**
	 * returns the root
	 * 
	 */
	public BSTNode <E> getRoot() {
		// TODO Auto-generated method stub
		return root;
	}

	/**
	 returns the size 
	 * 
	 */
	public int getSize() {
		// TODO Auto-generated method stub
		return size;
	}
	
	/**
	 * prints the tree in an inorder fashion.
	 * 
	 * uses recursive methods to do so
	 * 
	 */
	public void printInOrderRecursively(BSTNode n){
		if(n != null){
			printInOrderRecursively(n.getLeft());
			System.out.println(n.getElement());
			printInOrderRecursively(n.getRight());
			 
		}
	}

	/**
	 * prints the tree in a by depth fashion and uses recursive methods to do so.
	 * 
	 * @param n BST node 
	 * 
	 */
	public void printRecursiveByDepth(BSTNode n) {
		if(n != null){
			System.out.println(n.getElement());
			printRecursiveByDepth(n.getLeft());
			printRecursiveByDepth(n.getRight());
			 
		}
		
	}

	/**
	 * prints the tree in an postorder fashion and uses recursive methods to do so.
	 * 
	 *@param n BST node
	 */
	public void printPostOrderRecursively(BSTNode n) {
		if(n != null){
			printPostOrderRecursively(n.getLeft());
			printPostOrderRecursively(n.getRight());
			System.out.println(n.getElement()); 
		}

	}
}