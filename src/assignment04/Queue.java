package assignment04;

/**
 * File: Queue.java
 * 
 * @author: Hannah Borreson
 * 
 * @version 5/5/2017
 * 
 *          Description: The main purpose of the Queue file is to be an
 *          interface for different kinds of queues.
 * 
 */

public interface Queue<E> {

	/**
	 * Returns true if the stack is empty; otherwise false
	 * 
	 * @return true is stack is empty otherwise false
	 */
	public boolean isEmpty();

	/**
	 * Returns the object at the front of the queue without removing it
	 * 
	 * @return the object at the front of the queue
	 * @throws NoSuchElementException
	 */
	public E front();

	/**
	 * Returns the object at the front of the queue and removes it so stack is
	 * one smaller
	 * 
	 * @return the object at the front of the queque
	 * @throws NoSuchElementException
	 */
	public E dequeue();

	/**
	 * Pushes an item onto the rear of the queue
	 * 
	 * @param it
	 *            The object to be inserted at the rear
	 */
	public void enqueue(E it);

	/**
	 * Dumps the queue - clears it of its contents
	 */
	public void clear();

	/**
	 * Returns the number of elements in the queue
	 * 
	 * @return size - the number of elements on the queue
	 */
	public int size();
}
