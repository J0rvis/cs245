package assignment04;

/**
	 * File: Node.java
	 * 
	 * @author: Hannah Borreson
	 * 
	 * @version 5/5/2017
	 * 
	 *          Description: The main purpose of the Node program is to
	 *          create a functioning Binary Search Tree node that can be used in a binary search tree.
	 * 
	 */

public class Node<E> {
	  private E element;        // Value for this node
	  private Node<E> next;     // Pointer to next node in list
	  // Constructors
	  public Node(E it, Node<E> nextval)
	  { 
	  	element = it;  
	  	next = nextval; 
	  }
	    
	  public Node(E it){
	  	  element = it;
	  	  next = null;
	  }
	  
	  public Node(){
	  	  element = null;
	  	  next = null;
	 }
	  
	  
	  public Node(Node<E> nextval) { 
	  	next = nextval; 
	  }
	 
	 /**
	  * Gets the next node in a singularly linked list.
	  * */
	public Node<E> getNext() { 
	 	return next; 
	 }  
	 

	 /**
	  * Sets the next node in a singularly linked list.
	  * 
	  * @param nextval node to be set to the next node
	  * */
	 public Node<E> setNext(Node<E> nextval){
	 	return next = nextval; 
	 }     // Return element field
	  
	 /**
	  * Gets the element of the specified node.
	  * */
	 
	 public E getElement() { 
	 	return element; 
	 }  // Set element field
	  
	 /**
	  * Sets the element of the node in a singularly linked list.
	  * 
	  * @param it item to be set as the node's element
	  * */
	 public E setElement(E it) {
	 	return element = it; 
	 }
}
