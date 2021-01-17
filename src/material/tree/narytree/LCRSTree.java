package material.tree.narytree;

import material.Position;
import material.tree.iterators.BFSIterator;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * A linked class for a tree where nodes have an arbitrary number of children.
 *
 * @author Raul Cabido, Abraham Duarte, Jose Velez, Jesús Sánchez-Oro
 * @param <E> the elements stored in the tree
 */
public class LCRSTree<E> implements NAryTree<E> {

    private class TreeNode<T> implements Position<T>{
        private T elem;
        private TreeNode<T> parent, sibling, firstSon;
        private LCRSTree<E> myTree;

        public TreeNode(T elem, TreeNode<T> parent, TreeNode<T> sibling, TreeNode<T> firstSon, LCRSTree<E> t) {
            this.elem = elem;
            this.parent = parent;
            this.sibling = sibling;
            this.firstSon = firstSon;
            this.myTree = t;
        }

        public T getElem() {
            return elem;
        }

        public void setElem(T elem) {
            this.elem = elem;
        }

        public TreeNode<T> getParent() {
            return parent;
        }

        public void setParent(TreeNode<T> parent) {
            this.parent = parent;
        }

        public TreeNode<T> getSibling() {
            return sibling;
        }

        public void setSibling(TreeNode<T> sibling) {
            this.sibling = sibling;
        }

        public TreeNode<T> getFirstSon() {
            return firstSon;
        }

        public void setFirstSon(TreeNode<T> firstSon) {
            this.firstSon = firstSon;
        }

        public LCRSTree<E> getMyTree() {
            return myTree;
        }

        public void setMyTree(LCRSTree<E> myTree) {
            this.myTree = myTree;
        }

        @Override
        public T getElement() {
            return elem;
        }
    }

    /**
     *  LCRSTree methods and atributes
     * */
    private TreeNode<E> root;
    private int size;

    public LCRSTree() {
        root = null;
        size = 0;
    }


    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    public Position<E> root() throws RuntimeException {
        return this.root;
    }

    @Override
    public Position<E> parent(Position<E> v) throws RuntimeException {
        TreeNode<E> node = checkPosition(v);
        return node.getParent();
    }

    @Override
    public Iterable<? extends Position<E>> children(Position<E> v) {
        TreeNode<E> parent = checkPosition(v);
        List<Position<E>> children = new LinkedList<>();
        Position<E> node = parent.getFirstSon();

        while(node != null){
            children.add(node);
            node = ((TreeNode<E>)node).sibling;
        }
        return children;
    }

    @Override
    public boolean isInternal(Position<E> v) {
        return !(isLeaf(v));
    }

    @Override
    public boolean isLeaf(Position<E> v) throws RuntimeException {
        return checkPosition(v).firstSon == null;
    }

    @Override
    public boolean isRoot(Position<E> v) {
        return checkPosition(v).parent == null;
    }

    @Override
    public Position<E> addRoot(E e) throws RuntimeException {
        if(this.root() != null)
            throw new RuntimeException("Tree already has a root.");
        TreeNode<E> node = new TreeNode<>(e, null, null, null, this);
        this.root = node;
        return node;
    }

    @Override
    public Iterator<Position<E>> iterator() {
        return new BFSIterator<>(this);
    }

    @Override
    public E replace(Position<E> p, E e) {
        TreeNode<E> pos = checkPosition(p);
        E rtn = pos.getElem();
        pos.setElem(e);
        return rtn;
    }

    @Override
    public void swapElements(Position<E> p1, Position<E> p2) {
        TreeNode<E> n1,n2;
        E e1,e2;
        n1 = checkPosition(p1);
        n2 = checkPosition(p2);

        e1 = n1.getElem();
        e2 = n2.getElem();

        n1.setElem(e2);
        n2.setElem(e1);
    }

    /**
     *  1.- No tiene ningun hijo
     *  2. - Tiene hijo(s):
     *          iterar hasta que no haya sibling
     * */
    @Override
    public Position<E> add(E element, Position<E> p) {
        TreeNode<E> node = checkPosition(p);
        TreeNode<E> newNode = new TreeNode<>(element, node, null, null, this);
        List<TreeNode<E>> childrenPos = ( List<TreeNode<E>> ) children(p); // casting exp
        if(childrenPos.isEmpty()){  // 1
            node.setFirstSon(newNode);
        }
        else{       // 2
            for(TreeNode<E> n: childrenPos){
                if(n.getSibling() == null){
                    n.setSibling(newNode);
                }
            }
        }
        this.size++;
        return newNode;
    }

    @Override
    public void remove(Position<E> p) {
        TreeNode<E> node = checkPosition(p);
        if(isRoot(p)){
            this.root = null;
            this.size = 0;
            return;
        }
        // First we need the size of the subtree to delete..
        BFSIterator<E> it = new BFSIterator<>(this, p);
        int subTreeSize = 0;
        while(it.hasNext()){
            subTreeSize++;
        }

        // Begin to delete the node
        boolean onlyChild = false;
        if(node.getParent().getFirstSon() == node){
            onlyChild = true;
        }

        if(onlyChild){ // only delete from parent
            node.getParent().setFirstSon(null);
        }
        else {      // only delete from his previous sibling
            List<TreeNode<E>> childrenPos = ( List<TreeNode<E>> ) children(node.getParent());
            for(TreeNode<E> n: childrenPos){
                TreeNode<E> sib = n.getSibling();
                if(sib == node){
                    n.setSibling(null);
                }
            }
        }
        this.size -= subTreeSize;
    }

    @Override
    public void moveSubtree(Position<E> pOrig, Position<E> pDest) throws RuntimeException {
        TreeNode<E> origen = checkPosition(pOrig);
        TreeNode<E> destino = checkPosition(pDest);
        Position<E> aux;
        BFSIterator<E> it = new BFSIterator<>(this, pOrig);
        boolean isOnlyChild_dest = false, isOnlyChild_orig = false;

        while(it.hasNext()){
            aux = it.next();
            if(aux == pDest){
                throw new RuntimeException("Destination cannot be a descendant of Origin");
            }
        }

        List<TreeNode<E>> childrenDest = (List<TreeNode<E>>) children(pDest);
        List<TreeNode<E>> childrenOrig = (List<TreeNode<E>>) children(pOrig);
        //Iterator<TreeNode<E>> it2;


        // check if we have to insert to fathers first son or to last sibling
        if(childrenDest.isEmpty()){
            isOnlyChild_dest = true;
        }
        // check if we have to unlink either from parent or sibling
        if(childrenOrig.size() == 1){
            isOnlyChild_orig = true;
        }

        // deleting origins reference
        if(isOnlyChild_orig){
            origen.getParent().setFirstSon(null);
        }else{
            TreeNode<E> aux2 = origen.getParent().getFirstSon();
            while( aux2 != null){
                if(aux2.getSibling() == origen){
                    aux2.setSibling(null);
                    break;
                }
            }
        }

        // origen's new parent is pDest
        origen.setParent(destino);

        // adding new son to parent/sibling
        if(isOnlyChild_dest){
            destino.setFirstSon(origen);
        }else{
            TreeNode<E> aux2 = destino.getFirstSon();
            while( aux2 != null){
                if(aux2.getSibling() == null){
                    aux2.setSibling(origen);
                    break;
                }
            }
        }
    }

    // check position method

    private TreeNode<E> checkPosition(Position<E> p) throws IllegalStateException {
        if (p == null || !(p instanceof TreeNode)) {
            throw new IllegalStateException("The position is invalid");
        }
        TreeNode<E> aux = (TreeNode<E>) p;

        if (aux.getMyTree() != this) {
            throw new IllegalStateException("The node is not from this tree");
        }
        return aux;
    }
}
