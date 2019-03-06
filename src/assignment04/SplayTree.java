package assignment04;


/**
 * File: SplayTree.java
 * 
 * @author: Hannah Borreson
 * 
 * @version 5/5/2017
 * 
 *          Description: The main purpose of the SplayTree program is to create
 *          a fully functioning splay tree.
 * 
 */
import java.util.Stack;

public class SplayTree<E extends Comparable<E>> {

	private SplayNode<E> root;

	private int size;

	public SplayTree() {

		root = null;

		size = 0;

	}

	public SplayTree(SplayNode<E> node) {

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

	public SplayNode<E> search(E it) {
		SplayNode<E> node = root;
		while (node != null) {
			int res = it.compareTo(node.getElement());
			if (res < 0) {
				node = node.getLeft();
			} else if (res > 0) {
				node = node.getRight();
			} else {
				splay(node);
				return node;
			}
		}
		System.out.println(it + " is not here.");
		return null;

	}

	/**
	 * searches for a node that contains it.
	 * 
	 * if it finds it, it returns that node
	 * 
	 * else it returns null. Doesn't splay the tree ever.
	 * 
	 * @param it
	 *            - the element to look for
	 * 
	 * @return the node that contains it
	 * 
	 */
	public SplayNode<E> searchNoSplay(E it) {
		SplayNode<E> node = root;
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
	 * @param it item to be searched for 
	 * 
	 */

	public boolean contains(E it) {
		SplayNode<E> node = root;
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
	 * Do not allow duplicates. Splays inserted node.
	 * 
	 * @param it
	 *            the element to insert
	 * 
	 */

	public void insert(E it) {
		SplayNode<E> newNode = new SplayNode<E>(it);
		if (root == null) {
			root = newNode;

			size++;
			return;
		}
		SplayNode<E> parent = null;
		SplayNode<E> padre = null;
		SplayNode<E> node = root;
		while (node != null) {
			parent = node;
			// find node check for duplicates
			int compareResult = it.compareTo(node.getElement());
			if (compareResult < 0) {
				padre = node;
				node = node.getLeft();

			} else if (compareResult > 0) {
				padre = node;
				node = node.getRight();

			} else {
				// duplicate
				return;
			}
		}
		int res = it.compareTo(parent.getElement());
		if (res < 0) {

			parent.setLeft(newNode);
			newNode.setParent(padre);

		} else {

			parent.setRight(newNode);
			newNode.setParent(padre);
		}
		size++;
		splay(newNode);
	}

	/**
	 * Removes the node that contains it.
	 * 
	 * If the tree does not contain it, it prints that to
	 * 
	 * the user and does nothing else.
	 * 
	 * Finds the node to be removed and splays (through the search method) it so
	 * it becomes the root. Replaces root with left side successor and connects
	 * left and right subtrees accordingly.
	 * 
	 * @param the
	 *            element of the node you want to remove.
	 * 
	 */

	public void remove(E it) {
		SplayNode<E> node = search(it);//splays it to top
		SplayNode<E> L = root.getLeft(); //left subtree
		SplayNode<E> R = root.getRight(); //right subtree
		
		if (L != null) {
			while (L.hasRight()) {
				L = L.getRight(); //walk down tree
			}
			splay(L); //turn left to root
			L.setRight(R);
			root = L;
			size--;
		} else {
			root = root.getRight();
			size--;
		}
		if (node.hasLeft()) {
			node.getLeft().setParent(null);
		}
		if (node.hasRight()) {
			node.getRight().setParent(null);
		}

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
		QueueList<SplayNode> q = new QueueList<SplayNode>();

		if (root == null) {
			return height;
		}
		q.enqueue(root);
		while (!q.isEmpty()) {
			int nodeCount = q.size();
			height++;

			while (nodeCount > 0) {
				SplayNode<E> node = q.dequeue();
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

	private void swapElements(SplayNode<E> node1, SplayNode<E> node2) {
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

	public void printLevelOrder() {
		int height = -1;
		QueueList<SplayNode> q = new QueueList<SplayNode>();

		if (root == null) {
			size = 0;
			System.out.println(root);
			return;
		}
		q.enqueue(root);
		while (!q.isEmpty()) {
			int nodeCount = q.size();

			while (nodeCount > 0) {
				SplayNode<E> node = q.dequeue();
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
	 * Removes all nodes from tree.
	 **/

	public void clear() {
		if (root.hasLeft()) {
			root.setLeft(null);
		}
		if (root.hasRight()) {
			root.setRight(null);
		}
		root = null;
		size = 0;

	}

	/**
	 * prints the tree in a depth-first fashion
	 * 
	 * use your Stack class
	 * 
	 */

	public void printByDepth() {
		StackList<SplayNode> s = new StackList<SplayNode>();
		if (root == null) {
			System.out.println(root);
			return;
		}
		s.push(root);
		while (!s.isEmpty()) {
			SplayNode<E> n = s.pop();
			System.out.println(n + " ");
			// System.out.println("child : " + n + " parent : " +
			// search(n.getElement()).getParent());
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
		Stack<SplayNode> S = new Stack<SplayNode>();
		SplayNode node = root;
		pushLeftNodesToStack(S, node);
		while (!S.empty()) {
			SplayNode<E> n = S.pop();
			System.out.println(n + " ");
			n = n.getRight();
			pushLeftNodesToStack(S, n);
		}

	}

	/**
	 * Turns left nodes into a stack.
	 * 
	 * @param s stack of splay nodes 
	 * @param b splay of left children node to be pushed onto stack
	 * 
	 */
	public void pushLeftNodesToStack(Stack<SplayNode> s, SplayNode<E> b) {
		while (b != null) {
			s.push(b);
			b = b.getLeft();
		}
	}

	/**
	 * Returns the root of the Splay Tree.
	 * 
	 **/
	public SplayNode<E> getRoot() {
		// TODO Auto-generated method stub
		return root;
	}

	/**
	 * Returns the size of the Splay Tree.
	 * 
	 **/
	public int getSize() {
		// TODO Auto-generated method stub

		return size;
	}

	/**
	 * Prints tree in order using a recursive method.
	 * 
	 * @param n splay node 
	 **/
	public void printInOrderRecursively(SplayNode n) {
		if (n != null) {
			printInOrderRecursively(n.getLeft());
			System.out.println(n.getElement());
			printInOrderRecursively(n.getRight());

		}
	}

	/**
	 * Prints tree by depth using a recursive method.
	 * 
	 * @param n splay node
	 **/
	public void printRecursiveByDepth(SplayNode n) {
		if (n != null) {
			System.out.println(n.getElement());
			printRecursiveByDepth(n.getLeft());
			printRecursiveByDepth(n.getRight());

		}

	}

	/**
	 * Prints tree in post order using a recursive method.
	 * 
	 * @param n splay node 
	 **/
	public void printPostOrderRecursively(SplayNode n) {
		if (n != null) {
			printPostOrderRecursively(n.getLeft());
			printPostOrderRecursively(n.getRight());
			System.out.println(n.getElement());
		}

	}

	/**
	 * Pushes the node to be played to the top of the tree.
	 * 
	 * @param x splay node to be splayed 
	 **/
	// just like the power point
	public void splay(SplayNode<E> x) {
		while (x.getParent() != null) {
			SplayNode<E> Parent = x.getParent();
			SplayNode<E> GrandParent = Parent.getParent();
			// zig
			if (GrandParent == null) {
				if (x == Parent.getLeft())
					x.makeLeftChildParent(x, Parent);
				else
					x.makeRightChildParent(x, Parent);
			} // go to next slide
			else {
				if (x == Parent.getLeft()) {
					// left-left child zig zig
					if (Parent == GrandParent.getLeft()) {
						Parent.makeLeftChildParent(Parent, GrandParent);
						x.makeLeftChildParent(x, Parent);
					} else { // zig-zag
						Parent.makeLeftChildParent(x, Parent);
						x.makeRightChildParent(x, x.getParent());
					}
				} else {// zig-zag
					if (Parent == GrandParent.getLeft()) {
						x.makeRightChildParent(x, Parent);
						x.makeLeftChildParent(x, x.getParent());
					} else {// zig-zig
						Parent.makeRightChildParent(Parent, GrandParent);
						x.makeRightChildParent(x, Parent);
					}
				}
			}
		} // ends while
		root = x;
	}

}