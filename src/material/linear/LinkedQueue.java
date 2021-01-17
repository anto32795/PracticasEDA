package material.linear;


public class LinkedQueue<E> implements Queue<E>{
    private class Node<E>{
        private E element;
        private Node<E> next;

        public E getElement() {
            return element;
        }

        public void setElement(E element) {
            this.element = element;
        }

        public Node<E> getNext() {
            return next;
        }

        public void setNext(Node<E> next) {
            this.next = next;
        }
    }

    /** Metodos clase LinkedQueue*/
    private int size;
    private Node<E> bucket, front_ptr, back_ptr;

    public LinkedQueue(){
        this.size = 0;
        this.bucket = new Node<>();
        this.front_ptr = null;
        this.back_ptr = null;
    }

    public Node<E> getBucket() {
        return bucket;
    }

    public void setBucket(Node<E> bucket) {
        this.bucket = bucket;
    }

    public Node<E> getFront_ptr() {
        return front_ptr;
    }

    public void setFront_ptr(Node<E> front_ptr) {
        this.front_ptr = front_ptr;
    }

    public Node<E> getBack_ptr() {
        return back_ptr;
    }

    public void setBack_ptr(Node<E> back_ptr) {
        this.back_ptr = back_ptr;
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
    public E front() {
        return this.front_ptr.element;
    }

    @Override
    /**
     * Varios casos:
     * 1 - Cola vacia
     * 2 - Cola no vacia
     * */
    public void enqueue(Object element) {
        if(isEmpty()){
            this.bucket.setElement((E)element);
            this.bucket.setNext(null);
            this.size++;
            this.front_ptr = this.bucket;
            this.back_ptr = this.bucket;
        }
        else{
            Node<E> new_node = new Node<>();
            new_node.setElement((E)element);
            new_node.setNext(this.back_ptr);
            this.size++;
            this.back_ptr = new_node;
        }
    }

    @Override
    public E dequeue() {
        if(isEmpty()) throw new RuntimeException("The Queue is empty.");
        else{
            Node<E> node_aux = this.front_ptr;
            this.front_ptr = this.front_ptr.next;
            this.size--;
            return node_aux.element;
        }
    }
}
