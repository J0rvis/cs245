package assignment04;


import java.util.EmptyStackException;


/**
 * File: StackList.java
 * 
 * @author: Hannah Borreson
 * 
 * @version 5/5/2017
 * 
 *          Description: The main purpose of the stack list program is to create
 *          a fully functioning stack list.
 * 
 */
public class StackList<E> implements Stack<E> {

	private Node top; // Pointer to top of stack.

	private int size = 0;// Size of list

	public StackList(E it) {

		top = new Node(it); // Create top that stores it

		size++;

	}

	public StackList() {

		top = new Node(); // Create an empty top

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
	 * @param it item to be pushed to stack 
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
		Node<E> nodeRef = top;
		while(top != null){
				top = top.getNext();
				size= 0;
			
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
