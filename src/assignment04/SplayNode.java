package assignment04;

/**
 * File: SplayNode.java
 * 
 * @author: Hannah Borreson
 * 
 * @version 5/5/2017
 * 
 *          Description: The main purpose of the SplayNode program is to create
 *          a fully functioning node for a splay tree.
 * 
 */
public class SplayNode<E> {

	private E element; // Value for this node

	private SplayNode<E> left; // reference to left child

	private SplayNode<E> right; // reference to right child

	private SplayNode<E> parent; // reference to parent

	// Constructors

	public SplayNode(E it, SplayNode<E> l, SplayNode<E> r)

	{

		element = it;

		left = l;

		right = r;

	}

	public SplayNode(E it, SplayNode<E> l, SplayNode<E> r, SplayNode<E> p)

	{

		element = it;

		left = l;

		right = r;
		parent = p;

	}

	public SplayNode(E it) {

		element = it;

		left = null;

		right = null;

	}

	// have the appropriate getters and setters
	/**
	 * Returns the left child of node.
	 * **/
	public SplayNode<E> getLeft() {
		return left;

	}
	/**
	 * Sets the left child of node.
	 * @param l node to be set as left child 
	 * **/
	public SplayNode<E> setLeft(SplayNode<E> l) {

		return left = l;

	}
	/**
	 * Returns the right child of node.
	 * **/
	public SplayNode<E> getRight() {
		return right;

	}
	/**
	 * Sets the right child of node.
	 * @param r node to be set as the right child
	 * **/
	public SplayNode<E> setRight(SplayNode<E> r) {
		return right = r;

	}

	/**
	 * Returns the element of the node.
	 * **/
	public E getElement() {
		return element;

	}

	/**
	 * Sets the element of the node.
	 * @param it item to be set as the element of the node
	 * **/
	public E setElement(E it) {
		return element = it;

	}

	// also have a method to determine if the node is a leaf or has children

	/**
	 * Returns true if the node has no children.
	 * **/
	public boolean isLeaf() {

		return left == null && right == null;
	}


	/**
	 * Returns true if node has a left child.
	 * **/
	public boolean hasLeft() {
		return left != null;
	}
	
	/**
	 * Returns true if node has a right child.
	 * **/
	public boolean hasRight() {
		return right != null;
	}
	
	/**
	 * Returns the node's element as a String.
	 * **/
	public String toString() {

		return element.toString();

	}

	/**
	 * Returns true if node has a parent.
	 * **/
	public boolean hasParent() {
		return parent != null;
	}

	/**
	 * Returns the parent of the node.
	 * **/
	public SplayNode<E> getParent() {
		return parent;
	}

	/**
	 * Sets the parent of the node.
	 * 
	 * @param p node to be set as parent 
	 * **/
	public SplayNode<E> setParent(SplayNode<E> p) {
		return parent = p;

	}

	/**
	 * Swaps the grandparent and left child.
	 *@param c child node to be set as parent 
	 *@param p parent node to be set as left child 
	 * **/
	public void makeLeftChildParent(SplayNode<E> c, SplayNode<E> p) {
		if (p.getParent() != null) {
			if (p == p.getParent().getLeft()) {
				p.getParent().setLeft(c);
			} else {
				p.getParent().setRight(c);
			}
		}
		if (c.getRight() != null) {
			c.getRight().setParent(p);
		}
		c.setParent(p.getParent());
		p.setParent(c);
		p.setLeft(c.getRight());
		c.setRight(p);
	}
	/**
	 * Swaps the grandparent and right child.
	 *@param c child node to be set as parent
	 *@param p parent node to be set as right child
	 * **/
	public void makeRightChildParent(SplayNode<E> c, SplayNode<E> p) {
		if (p.getParent() != null) {
			if (p == p.getParent().getRight()) {
				p.getParent().setRight(c);
			} else {
				p.getParent().setLeft(c);
			}
		}
		if (c.getLeft() != null) {
			c.getLeft().setParent(p);
		}
		c.setParent(p.getParent());
		p.setParent(c);
		p.setRight(c.getLeft());
		c.setLeft(p);
	}

}