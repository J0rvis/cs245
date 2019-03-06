package assignment05;

/**
 * File: Stack.java
 * 
 * @author: Hannah Borreson
 * 
 * @version 4/29/2017
 * 
 *          Description: The main purpose of the Stack file is to be an
 *          interface for different kinds of stacks.
 * 
 */



/** Stack ADT */
public interface Stack<E> {
	
  /**	Returns true if the stack is empty; otherwise false
		@return true is stack is empty otherwise false
	*/
	public boolean isEmpty();

	/** Returns the object at the top of the stack without removing it
		@return the object at the top of the stack
		@throws EmptyStackException
	*/		
	public E peek();


	/** Returns the object at the top of the stack and removes it
		so stack is one smaller
		@return the object at the top of the stack
		@throws EmptyStackException
	*/
	public E pop();
	
	


	/** Pushes an item onto the top of the stack
		@param it The object to be inserted at the top
	*/
	public void push(E it);


	/** Dumps the stack - clears it of its contents 
	* I know that this should be clear - but I love alliteration
	*/
	public void puke();




	/** Returns the number of elements on the stack
		@return size - the number of elements on the stack
	*/
	public int size();


}

