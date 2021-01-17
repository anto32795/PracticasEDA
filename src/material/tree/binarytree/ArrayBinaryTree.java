package material.tree.binarytree;

import material.Position;
import material.tree.narytree.LCRSTree;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ArrayBinaryTree<E> implements BinaryTree<E> {
    private class BTNode<E> implements Position<E>{
        private E element;
        private int pos;
        private ArrayBinaryTree<E> myTree;

        @Override
        public E getElement() {
            return element;
        }
        public int getPos(){
            return pos;
        }
        public ArrayBinaryTree<E> getMyTree() { return myTree; }

        public BTNode(E element, int pos, ArrayBinaryTree<E> myTree) {
            this.element = element;
            this.pos = pos;
            this.myTree = myTree;
        }

        public void setElement(E element) {
            this.element = element;
        }

        public void setPos(int pos) {
            this.pos = pos;
        }

        public void setMyTree(ArrayBinaryTree<E> myTree) {
            this.myTree = myTree;
        }
    }
    /**
     *  Methods and atributes for ArrayBinaryTree
     * */
    private ArrayList<BTNode<E>> bucket;
    private int size;

    public ArrayBinaryTree() {
        this.bucket = new ArrayList<>(10);
        this.size = 0;
    }

    public ArrayBinaryTree(ArrayList<BTNode<E>> bucket, int size) {
        this.bucket = bucket;
        this.size = size;
    }

    public ArrayList<BTNode<E>> getBucket() {
        return bucket;
    }

    public void setBucket(ArrayList<BTNode<E>> bucket) {
        this.bucket = bucket;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    @Override
    public Position<E> left(Position<E> p) throws RuntimeException {
        BTNode<E> node = checkPosition(p);
        if(isLeaf(p))
            throw new RuntimeException("Node is leaf, has no left");
        return this.bucket.get(node.getPos()*2);

    }

    @Override
    public Position<E> right(Position<E> p) throws RuntimeException {
        BTNode<E> node = checkPosition(p);
        if(isLeaf(p)) throw new RuntimeException("Node is leaf, has no right");
        return this.bucket.get(node.getPos()*2 +1);
    }

    @Override
    public boolean hasLeft(Position<E> p) {
        BTNode<E> node = checkPosition(p);
        return this.bucket.get((node.getPos()*2)) != null;
    }

    @Override
    public boolean hasRight(Position<E> p) {
        BTNode<E> node = checkPosition(p);
        return this.bucket.get(node.getPos()*2+1) != null;
    }

    @Override
    public E replace(Position<E> p, E e) {
        BTNode<E> node = checkPosition(p);
        E rtn = node.getElement();
        node.setElement(e);
        return rtn;
    }

    /**
     * Los nodo par son hijo izq
     * */
    @Override
    public Position<E> sibling(Position<E> p) throws RuntimeException {
        BTNode<E> node = checkPosition(p);
        int pos_node = node.getPos();
        int pos_sibling, pos_parent = pos_node/2;

        if(pos_node%2==0){
            pos_sibling = pos_node +1;
        }
        else{
           pos_sibling = pos_node - 1;
        }
        Position<E> rtn = this.bucket.get(pos_sibling);
        if(rtn == null) throw new RuntimeException("Node has no sibling");
        return rtn;
    }

    @Override
    public Position<E> insertLeft(Position<E> p, E e) throws RuntimeException {
        BTNode<E> parent = checkPosition(p);
        int pos_parent = parent.getPos();
        if(this.bucket.get(pos_parent*2) != null){
            throw new RuntimeException("Node already has a left child");
        }
        BTNode<E> new_node = new BTNode<>(e, pos_parent*2, this);
        this.bucket.add(pos_parent*2, new_node);
        this.size++;
        return new_node;
    }

    @Override
    public Position<E> insertRight(Position<E> p, E e) throws RuntimeException {
        BTNode<E> parent = checkPosition(p);
        int pos_parent = parent.getPos();
        if(this.bucket.get(pos_parent*2+1) != null){
            throw new RuntimeException("Node already has a left child");
        }
        BTNode<E> new_node = new BTNode<>(e, pos_parent*2+1, this);
        this.bucket.add(pos_parent*2+1, new_node);
        this.size++;
        return new_node;
    }

    @Override
    public E remove(Position<E> p) throws RuntimeException {
        BTNode<E> node = checkPosition(p);
        int pos_node = node.getPos();
        if(hasLeft(p) && hasRight(p)) throw new RuntimeException("Node has two children");
        else if(isLeaf(p)){
            this.bucket.add(pos_node, null);
            this.size--;
        }
        else{
            return null;
        }
        // TODO: REO
        return null;
    }

    @Override
    public void swap(Position<E> p1, Position<E> p2) {
        return;
    }

    @Override
    public BinaryTree<E> subTree(Position<E> v) {
        ArrayBinaryTree<BTNode<E>> new_tree;
        BTNode<E> newRoot = checkPosition(v);
        int pos = newRoot.getPos();
        ArrayList<BTNode<E>> newBucket = new ArrayList<>();
        BTNode<E> aux = newRoot;
        int rel_pos = 1;
        /*
        while(aux != null){
            try{
                newBucket.add(rel_pos, aux);
                rel_pos*=2;
                if(hasLeft(aux)){
                    newBucket.add(rel_pos, )
                }
            }
        }*/
        //Iterator<BTNode<E>> it = iterator(v);
        return null;
    }

    @Override
    public void attachLeft(Position<E> p, BinaryTree<E> tree) throws RuntimeException {
        BTNode<E> parent_node = checkPosition(p);
        if(! (tree instanceof ArrayBinaryTree)){
            throw new RuntimeException("Tree is not a ArrayBinaryTree");
        }
        else if(hasLeft(p)){
            throw new RuntimeException("Tree has already a Left Child");
        }
        else if(tree.isEmpty())
            throw new RuntimeException("Tree is empty. Nothing to attach");
        // Check if p is already in tree to attach
        Position<E> new_tree_root = tree.root(), aux_parent, aux_left, aux_right;
        Iterator<Position<E>> it = iterator();
        while(it.hasNext()){
            if(it.next() == new_tree_root)
                throw new RuntimeException("Tree to attach is already in original tree");
        }

        // Changing all node positions relative to original
        it = tree.iterator();
        BTNode<E> raiz = ((BTNode<E>) it.next());
        raiz.setPos(((BTNode<E>) p).getPos()*2);
        while(it.hasNext()){
            aux_parent = it.next();
            int aux_pos = ((BTNode<E>)aux_parent).getPos();
            aux_right = tree.right(aux_parent);
            aux_left = tree.left(aux_parent);

            if(aux_left != null)((BTNode<E>)aux_left).setPos(aux_pos*2);
            if(aux_right != null)((BTNode<E>)aux_right).setPos(aux_pos*2 +1);
        }

        for(BTNode<E> child: ((ArrayBinaryTree<E>) tree).bucket){
            this.bucket.add(child.getPos(), child);
        }

        this.size+=tree.size();
    }

    @Override
    public void attachRight(Position<E> p, BinaryTree<E> tree) throws RuntimeException {
        BTNode<E> parent_node = checkPosition(p);
        if(! (tree instanceof ArrayBinaryTree)){
            throw new RuntimeException("Tree is not a ArrayBinaryTree");
        }
        else if(hasLeft(p)){
            throw new RuntimeException("Tree has already a Left Child");
        }
        else if(tree.isEmpty())
            throw new RuntimeException("Tree is empty. Nothing to attach");
        // Check if p is already in tree to attach
        Position<E> new_tree_root = tree.root(), aux_parent, aux_left, aux_right;
        Iterator<Position<E>> it = iterator();
        while(it.hasNext()){
            if(it.next() == new_tree_root)
                throw new RuntimeException("Tree to attach is already in original tree");
        }

        // Changing all node positions relative to original
        it = tree.iterator();
        BTNode<E> raiz = ((BTNode<E>) it.next());
        raiz.setPos(((BTNode<E>) p).getPos()*2 +1);
        while(it.hasNext()){
            aux_parent = it.next();
            int aux_pos = ((BTNode<E>)aux_parent).getPos();
            aux_right = tree.right(aux_parent);
            aux_left = tree.left(aux_parent);

            if(aux_left != null)((BTNode<E>)aux_left).setPos(aux_pos*2);
            if(aux_right != null)((BTNode<E>)aux_right).setPos(aux_pos*2 +1);
        }

        for(BTNode<E> child: ((ArrayBinaryTree<E>) tree).bucket){
            this.bucket.add(child.getPos(), child);
        }

        this.size+=tree.size();
    }

    @Override
    public boolean isComplete() {
        Iterator<Position<E>> it = this.iterator();
        Position<E> node;
        while(it.hasNext()){
            node = it.next();
            if ( ( hasLeft(node) && !hasRight(node) ) || !hasLeft(node) && hasRight(node) ){
                return false;
            }
        }
        return true;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return this.size==0;
    }

    @Override
    public Position<E> root() throws RuntimeException {
        return this.bucket.get(1);
    }

    @Override
    public Position<E> parent(Position<E> v) throws RuntimeException {
        BTNode<E> node = checkPosition(v);
        return this.bucket.get(node.getPos()/2);
    }

    @Override
    public Iterable<? extends Position<E>> children(Position<E> v) {
        List<Position<E>> rtn = new ArrayList<>(2);
        BTNode<E> parent = checkPosition(v);
        int pos_padre = parent.getPos();
        rtn.add(0, this.left(v));
        rtn.add(1, this.right(v));
        return rtn;
    }

    @Override
    public boolean isInternal(Position<E> v) {
        return !isLeaf(v);
    }

    @Override
    public boolean isLeaf(Position<E> v) throws RuntimeException {
        BTNode<E> node = checkPosition(v);
        if(left(v) != null || right(v) != null)
            return false;
        return true;
    }

    @Override
    public boolean isRoot(Position<E> v) {
        BTNode<E> node = checkPosition(v);
        return node.getPos() == 1;
    }

    @Override
    public Position<E> addRoot(E e) throws RuntimeException {
        if(bucket.get(0) != null)
            throw new RuntimeException("Tree has already root");
        BTNode<E> root = new BTNode<>(e, 1, this);
        bucket.add(1, root);
        this.size++;
        return root;
    }

    @Override
    public Iterator<Position<E>> iterator() {
        return null;
    }

    private BTNode<E> checkPosition(Position<E> p) throws IllegalStateException {
        if (p == null || !(p instanceof BTNode)) {
            throw new IllegalStateException("The position is invalid");
        }
        BTNode<E> aux = (BTNode<E>) p;

        if (aux.getMyTree() != this) {
            throw new IllegalStateException("The node is not from this tree");
        }
        return aux;
    }
}
