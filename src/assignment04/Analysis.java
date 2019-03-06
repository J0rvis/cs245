package assignment04;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Stack;

/**
 * File: Analysis.java
 * 
 * @author: Hannah Borreson
 * 
 * @version 5/5/2017
 * 
 *          Description: This file holds a class that takes in two arguments
 *          from the command line and reads one of the files into a splay tree
 *          and then uses the second file to search for 100 specific places. The
 *          class counts the comparisons made and prints out how many
 *          comparisons were made for each place searched for. The class also
 *          has similar helper methods to do the same same thing but with a
 *          binary search tree.
 * 
 */
public class Analysis {

	public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {

		if (args.length < 1) {
			System.err.print("Not enough information has been entered to satisfy the parameters of the problem.");
			System.exit(0);
		}

		System.out.println("******** SPLAY TREE ********");
		PlacesSplay classSplay = new PlacesSplay();
		SplayTree<Place> splay = classSplay.read(new File(args[0]));
		SplayTree<Place> origSplay = classSplay.read(new File(args[1]));
		System.out.println(splay.getSize());
		System.out.println(splay.getHeight());
		findComparisonsSplay(splay, origSplay);

		System.out.println("******** BINARY SEARCH TREE ********");

		PlacesBST classBST = new PlacesBST();
		BinarySearchTree<Place> binaryTree = classBST.read(new File(args[0]));
		BinarySearchTree<Place> origBST = classBST.read(new File(args[1]));
		System.out.println(binaryTree.getSize());
		System.out.println(binaryTree.getHeight());
		findComparisonsBST(binaryTree, origBST);

	}

	/**
	 * Prints out the number of comparisons it took to find each node specified
	 * and also prints out the town and state searched for. For organizational
	 * purposes the file containing the 100 searchable Places was turned into a
	 * splay tree and searched for in this method.
	 * 
	 * @param splay splay tree containing items to search for
	 * @param origSplay the splay tree items are searched for in
	 **/
	public static void findComparisonsSplay(SplayTree<Place> splay, SplayTree<Place> origSplay)
			throws FileNotFoundException, UnsupportedEncodingException {
		
		PlacesSplay classSplay = new PlacesSplay();
		Stack<SplayNode> S = new Stack<SplayNode>();
		SplayNode node = splay.getRoot();
		splay.pushLeftNodesToStack(S, node);
		while (!S.empty()) {
			SplayNode<Place> n = S.pop();
			SplayNode<Place> nodeSplayed = classSplay.search(origSplay, n.getElement().getTownAndState());
			System.out.println(/* "I found: " + */ " " + n.getElement().getTownAndState());
			n = n.getRight();
			splay.pushLeftNodesToStack(S, n);
		}

	}

	/**
	 * Prints out the number of comparisons it took to find each node specified
	 * and also prints out the town and state searched for. For organizational
	 * purposes the file containing the 100 searchable Places was turned into a
	 * binary search tree and searched for in this method.
	 * 
	 * @param binaryTree binary search tree containing items to search for
	 * @param origBST the binary search tree items are searched for in
	 **/
	public static void findComparisonsBST(BinarySearchTree<Place> binaryTree, BinarySearchTree<Place> origBST)
			throws FileNotFoundException, UnsupportedEncodingException {
	
		PlacesBST classBST = new PlacesBST();
		Stack<BSTNode> S = new Stack<BSTNode>();
		BSTNode node = binaryTree.getRoot();
		binaryTree.pushLeftNodesToStack(S, node);
		while (!S.empty()) {
			BSTNode<Place> n = S.pop();
			BSTNode<Place> nodeSplayed = classBST.search(origBST, n.getElement().getTownAndState());
			System.out.println(/* "I found: " + */ " " + n.getElement().getTownAndState());
			n = n.getRight();
			binaryTree.pushLeftNodesToStack(S, n);
		}

	}

}
