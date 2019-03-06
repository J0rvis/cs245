package assignment05;

/**
 * File: StackList.java
 * 
 * @author: Hannah Borreson
 * 
 * @version 4/29/2017
 * 
 *          Description: The main purpose of the StackList program is to make a
 *          fully functional stack structured as a singularly linked list.
 * 
 */

import java.util.EmptyStackException;

public class StackList<E> implements Stack<E> {

	private Node <E> top; // Pointer to top of stack.

	private int size = 0;// Size of list

	/**
	 * Constructor.
	 *
	 * @param it
	 *            is the first item in the new stack list
	 **/

	public StackList(E it) {

		top = new Node <E>(it); // Create top that stores it

		size++;

	}

	/**
	 * Constructor.
	 **/
	public StackList() {

		top = new Node <E>(); // Create an empty top

		size = 0;

	}

	/**
	 * Returns true if the list is empty.
	 */
	public boolean isEmpty() {
		return size == 0;
	}

	/**
	 * Returns the top element in the list.
	 */
	public E peek() {
		if (isEmpty()) {
			System.out.println("Stack is empty");
			throw new EmptyStackException();
		} else {
			E topElement = (E) top.getElement();
			return topElement;
		}

	}

	/**
	 * Deletes the top Element of the Stack.
	 */
	@Override
	public E pop() {
		if (isEmpty()) {
			System.out.println("Stack is empty");
			throw new EmptyStackException();
		} else {
			E topElement = (E) top.getElement();
			top = top.getNext();
			size--;
			return topElement;
		}
	}

	/**
	 * Adds the element to the top of the stack.
	 * 
	 * @param it
	 *            item to be inserted into stack
	 */
	@Override
	public void push(E it) {
		// TODO Auto-generated method stub
		top = new Node<E>(it, top);
		size++;
	}

	/**
	 * Clears the stack list.
	 */
	@Override
	public void puke() {
		// TODO Auto-generated method stub
		//Node<E> nodeRef = top;
		while (top != null) {
			top = top.getNext();
			size = 0;

		}

	}

	/**
	 * Returns the size of the stack list.
	 */
	@Override
	public int size() {
		// TODO Auto-generated method stub
		return size;
	}

}
