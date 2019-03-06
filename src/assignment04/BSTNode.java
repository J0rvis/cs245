package assignment04;


public class BSTNode<E> {
	/**
	 * File: BSTNode.java
	 * 
	 * @author: Hannah Borreson
	 * 
	 * @version 5/5/2017
	 * 
	 *          Description: The main purpose of the BSTNode program is to
	 *          create a functioning Binary Search Tree node that can be used in a binary search tree.
	 * 
	 */

	private E element; // Value for this node

	private BSTNode <E> left; // reference to left child

	private BSTNode <E> right; // reference to right child

	// Constructors

	public BSTNode(E it, BSTNode <E> l, BSTNode <E> r)

	{

		element = it;

		left = l;

		right = r;

	}

	public BSTNode(E it) {

		element = it;

		left = null;

		right = null;

	}

	// have the appropriate getters and setters
	/**
	 *Retrieves and returns left child of BST Node.
	
	 **/

	public BSTNode <E>  getLeft() {
		return left;

	}

	/**
	 * Sets left child of BST Node to specified node.
	 * @param l node to be set as left child
	 **/
	public BSTNode <E> setLeft(BSTNode <E> l) {

		return left = l;

	}

	/**
	 *Retrieves and returns right child of BST Node.
	 **/
	public BSTNode  <E>getRight() {
		return right;

	}

	/**
	 * Sets right child of BST Node to specified node.
	 * @param r node to be set as right child
	 **/
	public BSTNode <E> setRight(BSTNode <E> r) {
		return right = r;

	}

	/**
	 *Retrieves and returns element of BST Node.
	
	 **/
	public E getElement() {
		return element;

	}

	/**
	 * Sets element of BST Node to specified element.
	 * @param it item to be set in node
	 **/
	public E setElement(E it) {
		return element = it;

	}

	// also have a method to determine if the node is a leaf or has children

	/**
	 * Returns true if node doesn't have any children and is a leaf.
	 * 
	 **/
	public boolean  isLeaf() {
		
		return left == null && right == null && element == null;
	}

	/**
	 * Returns true if node has a left child.
	 * 
	 **/
	public boolean hasLeft() {
		return left != null;
	}

	/**
	 * Returns true if node has a right child.
	 * 
	 **/
	public boolean hasRight() {
		return right != null;
	}

	/**
	 * Returns the element as a string.
	 * 
	 **/
	public String toString() {

		return element.toString();

	}

}