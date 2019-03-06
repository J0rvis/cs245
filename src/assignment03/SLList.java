package assignment03;

import java.util.*;
import java.lang.*;

/**
 * File: SLList.java
 * 
 * @author: Hannah Borreson
 * 
 * @version 3/27/2017
 * 
 *          Description: The main purpose of the SLList program is to create,
 *          maintain, and manipulate nodes of a singly linked list that is used
 *          in each sequence in Bioinfo and Sequence. 
 * 
 */
public class SLList<E> implements List<E> {
	private Node<E> head;

	private int size = 0;// Size of list

	/** Constructors */
	public SLList(E it) {
		head = new Node<E>(it); // Create head
		size++;
	}

	public SLList() {
		head = null; // Create head
		size = 0;
	}

	/** Make a new head 
	 * 
	 * @param it**/
	public void addFirst(E it) {
		head = new Node<E>(it, head);
		size++;
	}

	/** walk the list converting data to Strings **/
	public String toString() {
		Node<E> nodeRef = head;
		String result = "";
		while (nodeRef != null) {
			result = result + nodeRef.getElement().toString();

			if (nodeRef.getNext() != null) {
				result = result + " ==> ";
			}
			nodeRef = nodeRef.getNext();
		}
		return result;
	}

	/**
	 * reverses the list
	 */
	// email
	public void reverse() {
		Node<E> nodeRef = head;
		String result = "";

		if (nodeRef == null || nodeRef.getNext() == null) {
			return;
		}
		Node<E> prev = nodeRef.getNext();
		Node<E> curr = prev.getNext();
		prev.setNext(nodeRef);
		nodeRef.setNext(null);

		while (curr != null) {
			Node<E> next = curr.getNext();
			curr.setNext(prev);
			prev = curr;
			curr = next;
		}
		head = prev;
	}

	/** This method empties the list. */
	@Override
	public void clear() {
		while (head != null) {
			removeFirst();
		}

	}

	@Override
	// Power Point
	/** This method inserts an item at a given index. 
	 * 
	 * @param index
	 * @param item
	 * */
	public void insert(int index, E item) {
		if (index < 0 || index > size) {
			throw new IndexOutOfBoundsException(Integer.toString(index));
		}
		if (index == 0) {
			addFirst(item);
		} else {
			Node<E> node = getNode(index - 1);
			addAfter(node, item);
		}
	}

	/** This method adds an item to the end of a list.
	 * 
	 *  @param item
	 *  */
	@Override
	public void add(E item) {
		if (size == 0) {
			addFirst(item);
		} else {
			Node<E> node = getNode(size - 1);
			addAfter(node, item);
		}
	}

	/** This method removes the node at a given index. 
	 * 
	 * @param index
	 * */
	@Override
	public void remove(int index) {
		Node<E> nodeRef = head;
		if (index == 0) {
			removeFirst();
		}
		if (size != 0) {
			while (getNode(index) != nodeRef) {
				nodeRef = nodeRef.getNext();
			}
			if (getNode(index) == nodeRef && getNode(index + 1) != null) {
				getNode(index - 1).setNext(getNode(index + 1));
				size--;
			} else {
				getNode(index - 1).setNext(null);
				size--;
			}
		}

	}

	/** This method returns the previous node of the given index. 
	 * 
	 * @param index
	 * */
	@Override
	public E prev(int index) {
		return getNode(index - 1).getElement();
	}

	/** This method returns the next node of the given index. 
	 * 
	 * @param index
	 * */
	@Override
	public E next(int index) {
		return getNode(index + 1).getElement();
	}

	/** This method returns the length of the list. */
	@Override
	public int length() {
		return size;
	}

	/** This method returns the content of the node at the given index. 
	 * 
	 * @param index
	 * */
	@Override
	public E getValue(int index) {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException(Integer.toString(index));
		}
		Node<E> node = getNode(index);
		return node.getElement();

	}

	/** Returns the node at the given index. 
	 * 
	 * @param index
	 * **/
	// Power Point
	Node<E> getNode(int index) {
		Node<E> node = head;
		for (int i = 0; i < index && node != null; i++) {
			node = node.getNext();
		}
		return node;
	}

	/** Adds the given item after the given node. 
	 * 
	 * @param node
	 * @param item
	 * **/
	// Power Point
	private void addAfter(Node<E> node, E item) {
		node.setNext(new Node<E>(item, node.getNext()));
		size++;
	}

	/** Returns the last node. */
	public Node getLast() {
		return getNode(size - 1);

	}

	/** Gets the head node of the list. */
	public Node getHead() {
		return getNode(0);
	}

	/** Inserts list at the given index. 
	 * 
	 * @param stringList2
	 * @param i
	 * **/
	public void insertList(SLList<E> stringList2, int i) {
		// append after index specified
		// add each node after the last
		// set nodeRef to be current
		Node<E> nodeRef = getNode(i);
		SLList<E> stringList = (SLList<E>) stringList2;
		if (size == i) {

			for (int j = 0; j < stringList2.length(); j++) {
				add(stringList.getValue(j));
			}
			return;
		}

		for (int j = 0; j < stringList2.length(); j++) {
			insert(i + 1, stringList.getValue(j));
			i++;
		}

	}

	/** Removes after specified node. 
	 * 
	 * @param node
	 * */
	public E removeAfter(Node<E> node) {
		Node<E> temp = node.getNext();// make temp the node after
		if (temp != null)// make sure that there is something there
		{
			node.setNext(temp.getNext());
			size--;
			return temp.getElement();
		} else {
			return null;
		}
	}

	/** Removes head.*/
	public E removeFirst() {
		Node<E> temp = head;
		if (head != null) {
			head = head.getNext();
		}
		// return data at the old head or null if the list is empty
		if (temp != null) {
			size--;
			return temp.getElement();
		} else {
			return null;
		}
	}

	/**
	 * Turns the given list into a printable string and allows the user to see
	 * every element in the list.
	 * 
	 * @param list
	 * @param index
	 **/
	public static String toSimpleString(SLList list, int index) {
		Node nodeRef = list.getNode(index);
		String result = "";
		for (int i = index; i < list.length(); i++) {
			while (nodeRef != null) {
				result = result + nodeRef.getElement().toString().toUpperCase();
				nodeRef = nodeRef.getNext();
			}
			return result;
		}
		return result;
	}

	/** Makes a copy of the specified SLList. 
	 * 
	 * @param list
	 * */
	public SLList copy(SLList list) {
		Node nodeRef = list.getHead();

		SLList copy = new SLList();
		String result = "";
		for (int i = 0; i < list.length(); i++) {
			copy.add(list.getValue(i));
		}
		return copy;
	}

}
