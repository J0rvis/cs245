package assignment05;
/**
 * File: Node.java
 * 
 * @author: Hannah Borreson
 * 
 * @version 4/29/2017
 * 
 *          Description: The main purpose of the Node file is to create a fully
 *          functional node to be used in lists.
 * 
 */

public class Node<E> {
	private E element; // Value for this node
	private Node<E> next; // Pointer to next node in list
	// Constructors

	/**
	 * Constructor.
	 *
	 * @param it
	 *            is the item the node will contain
	 * @param nextval
	 *            is the next node in the list
	 **/
	public Node(E it, Node<E> nextval) {
		element = it;
		next = nextval;
	}

	/**
	 * Constructor.
	 *
	 * @param it
	 *            is the item the node will contain
	 **/
	public Node(E it) {
		element = it;
		next = null;
	}

	/**
	 * Constructor.
	 **/
	public Node() {
		element = null;
		next = null;
	}

	/**
	 * Constructor.
	 * 
	 * @param nextval
	 *            is the next node in the list
	 **/
	public Node(Node<E> nextval) {
		next = nextval;
	}

	/**
	 * Returns the next node
	 **/
	public Node<E> getNext() {
		return next;
	}

	/**
	 * Sets the next node
	 * 
	 * @param nextval
	 *            is what is to be the next node in the list
	 **/
	public Node<E> setNext(Node<E> nextval) {
		return next = nextval;
	} // Return element field

	/**
	 * Retrieves the element of specified node
	 **/
	public E getElement() {
		return element;
	} // Set element field

	/**
	 * Set the element of the specified node
	 * 
	 * @param it
	 *            the element that the specified node will contain
	 **/
	public E setElement(E it) {
		return element = it;
	}
}
