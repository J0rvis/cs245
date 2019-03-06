package assignment03;

/**
 * File: Node.java
 * 
 * @author: Hannah Borreson
 * 
 * @version 3/27/2017
 * 
 *          Description: The main purpose of the Node program is to create,
 *          maintain, and manipulate nodes using constructors, getters, and
 *          setters. The nodes are used to make a singly linked list in SLList.
 * 
 */
public class Node<E> {
	private E element; // Value for this node
	private Node<E> next; // Pointer to next node in list
	// Constructors

	public Node(E it, Node<E> nextval) {
		element = it;
		next = nextval;
	}

	public Node(E it) {
		element = it;
		next = null;
	}

	public Node() {
		element = null;
		next = null;
	}

	public Node(Node<E> nextval) {
		next = nextval;
	}

	/** Method gets next node. */
	public Node<E> getNext() {
		return next;
	}

	/**Method sets next node
	 * 
	 * @param nextval*/
	public Node<E> setNext(Node<E> nextval) {
		return next = nextval;
	} // Return element field

	/**Method gets the element in the node.*/
	public E getElement() {
		return element;
	} // Set element field

	/**Method sets element to the specified object.
	 * 
	 * @param it*/
	public E setElement(E it) {
		return element = it;
	}
}
