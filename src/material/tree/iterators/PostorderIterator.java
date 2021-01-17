package material.tree.iterators;

import material.Position;
import material.tree.Tree;
import material.tree.narytree.LinkedTree;


import java.util.ArrayDeque;
import java.util.Iterator;
import java.util.function.Predicate;

/**
 * Generic preorder iterator for trees.
 *
 * @param <E>
 * @author A. Duarte, J. Vélez, J. Sánchez-Oro, JD. Quintana
 */
public class PostorderIterator<E> implements Iterator<Position<E>> {

    private ArrayDeque<Position<E>> nodeStack;
    private Tree<E> tree;

    public PostorderIterator(Tree<E> tree) {
        nodeStack = new ArrayDeque<>();
        nodeStack.push(tree.root());
        this.tree = tree;
    }

    public PostorderIterator(Tree<E> tree, Position<E> start) {
        nodeStack = new ArrayDeque<>();
        nodeStack.push(start);
        this.tree = tree;
    }

    public PostorderIterator(Tree<E> tree, Position<E> start, Predicate<Position<E>> predicate) {
        throw new RuntimeException("Not yet implemented");
    }


    @Override
    public boolean hasNext() {
        return !nodeStack.isEmpty();
    }

    @Override
    public Position<E> next() {

        for (Position<E> node : tree.children(nodeStack.peek())) {
            nodeStack.push(node);
        }
        Position<E> aux = nodeStack.pop();
        return aux;
    }

    @Override
    public void remove(){
        throw new RuntimeException("Remove is not supported");
    }

}
