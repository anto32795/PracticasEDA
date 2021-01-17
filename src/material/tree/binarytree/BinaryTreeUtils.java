package material.tree.binarytree;

import material.Position;
import material.tree.binarytree.BinaryTree;
import material.tree.iterators.BFSIterator;

import java.util.ArrayDeque;
import java.util.Iterator;

public class BinaryTreeUtils<E> {
	
	private BinaryTree<E> tree;
	
	public BinaryTreeUtils(BinaryTree<E> tree) {
		this.tree = tree;
	}
	
	/** 
	  * Given a tree the method returns a new tree where all left children 
	  * become right children and vice versa
	 *
	 * 	*/

	public BinaryTree<E> mirror() {
		BinaryTree<E> reverse_tree = new LinkedBinaryTree<>();
		Position<E> root = this.tree.root();
		//reverse_tree.addRoot(root.getElement());
		ArrayDeque<Position<E>> queue_nodes = new ArrayDeque<>(), queue_parents = new ArrayDeque<>();
		BFSIterator<E> it = new BFSIterator<>(this.tree);
		Position<E> aux, izq, der;

		// parents queue
		while(it.hasNext()){
			queue_parents.add(it.next());
		}

		// nodes queue
		queue_nodes = queue_parents.clone();

		//reverse_tree.addRoot.getElement());
		aux = queue_parents.remove();
		queue_nodes.remove();
		while(!queue_parents.isEmpty()){
			izq = queue_nodes.remove();
			der = queue_nodes.remove();

			reverse_tree.insertLeft(aux, der.getElement()); // sea null o no
			reverse_tree.insertRight(aux, izq.getElement());

			aux = queue_parents.remove();
		}
		return reverse_tree;
	}

	/** 
	  * Determines whether the element e is the tree or not
	 *
	 * Tree has no ordered nodes, so O(n)
	*/
	public boolean contains (E e) {
		Iterator<Position<E>> it = this.tree.iterator();
		Position<E> aux;
		while(it.hasNext()){
			aux = it.next();
			if(aux.getElement().equals(e))
				return true;
		}
		return false;
	}
	/** 
	  * Determines the level of a node in the tree (root is located at level 1)
	*/
	public int level(Position<E> p) {
		return calculateLevel(1, p, p);
	}

	private int calculateLevel(int currentLevel, Position<E> node, Position<E> currentNode){

		if(currentNode != null) {
			if(node.getElement().equals(currentNode.getElement()))
				return currentLevel;

			calculateLevel(currentLevel+1, node, tree.left(currentNode));
			calculateLevel(currentLevel+1, node, tree.right(currentNode));
		}
		return -1;
	}
}
