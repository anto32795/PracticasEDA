package material.tree.iterators;

import material.Position;
import material.tree.Tree;

import java.util.ArrayDeque;
import java.util.Iterator;
import java.util.function.Predicate;

/**
 * Generic preorder iterator for trees.
 *
 * @param <E>
 * @author A. Duarte, J. Vélez, J. Sánchez-Oro, JD. Quintana
 */
public class PreorderIterator<E> implements Iterator<Position<E>> {

    private ArrayDeque<Position<E>> nodeStack;
    private Tree<E> tree;
    Predicate<Position<E>> node_predicate;

    public PreorderIterator(Tree<E> tree) {
        this.tree = tree;
        this.nodeStack = new ArrayDeque<>();
        nodeStack.push(tree.root());
    }

    public PreorderIterator(Tree<E> tree, Position<E> start) {
        this.tree = tree;
        this.nodeStack = new ArrayDeque<>();
        nodeStack.push(start);
    }

    public PreorderIterator(Tree<E> tree, Position<E> start, Predicate<Position<E>> predicate) {
        this.tree = tree;
        this.nodeStack = new ArrayDeque<>();
        nodeStack.push(start);
        this.node_predicate = predicate;
    }


    @Override
    public boolean hasNext() {
        return !this.nodeStack.isEmpty();
    }

    @Override
    public Position<E> next() {
        Position<E> aux = nodeStack.pop();
        for (Position<E> node : tree.children(aux)) {
            nodeStack.push(node);
        }
        return aux;
    }

    private void lookForward() {
        throw new RuntimeException("Not yet implemented");
    }

    private void pushChildrenInReverseOrder(Tree<E> tree, Position<E> pop) {
        throw new RuntimeException("Not yet implemented");
    }

}
