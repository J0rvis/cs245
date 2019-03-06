package assignment04;


import java.util.EmptyStackException;

/**
 * File: QueueList.java
 * 
 * @author: Hannah Borreson
 * 
 * @version 5/5/2017
 * 
 *          Description: The main purpose of the QueueList program is to make a
 *          fully functional queue structured as a singularly linked list.
 * 
 */
public class QueueList<E> implements Queue<E> {

	private Node front; // Reference to front.

	private Node rear; // Reference to rear

	private int size = 0;// Size of queue

	/** Constructors **/

	public QueueList(E it) {

		front = rear = new Node(it); // Create top that stores it

		size++;

	}

	public QueueList() {

		front = rear = new Node();// Create an empty front & rear

		size = 0;

	}

	/**
	 * Returns the topElement of the queue without changing list.
	 */
	public E front() {
		// TODO Auto-generated method stub
		if (isEmpty()) {
			System.out.println("Stack is empty");
			throw new EmptyStackException();
		} else {
			E topElement = (E) front.getElement();
			return topElement;
		}
	}
	/**
	 * Deletes the first node in the list. 
	 */
	public E dequeue() {
		// TODO Auto-generated method stub
		if (isEmpty()) {
			System.out.println("Stack is empty");
			throw new EmptyStackException();
		} else {
			E topElement = (E) front.getElement();
			front = front.getNext();
			size--;
			return topElement;
		}
	}

	/**
	 * Returns true if the list is empty.
	 */
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return size == 0;
	}

	/**
	 * Deletes the all of the nodes in the list. 
	 */
	public void clear() {
		// TODO Auto-generated method stub
		Node<E> nodeRef = front;
		while (front != null) {
			front = front.getNext();
			size = 0;

		}

	}
	/**
	 * Adds the specified node in the list at the end of it.
	 * 
	 *  @param it item to be added to list
	 */
	@Override
	public void enqueue(E it) {
		if (isEmpty()) {
			Node node = new Node(it);
			front = node;
			rear = front;
			size++;
		} else {
			Node node = new Node(it);
			rear = rear.setNext(node);
			size++;
		}
	}

	/**
	 * Returns the size of the list.
	 */
	@Override
	public int size() {
		return size;
	}

	// was used to make sure my enqueue worked
	/**
	 * Returns the last element in list. 
	 */
	public E rear() {
		// TODO Auto-generated method stub
		if (isEmpty()) {
			System.out.println("Stack is empty");
			throw new EmptyStackException();
		} else {
			E topElement = (E) rear.getElement();
			return topElement;
		}
	}

}
