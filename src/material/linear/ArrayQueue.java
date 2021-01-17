package material.linear;


// cola circular
public class ArrayQueue<E> implements Queue<E>{
    /*
    * Atributos: size = elementos insertados en la cola
    *   front = apunta al elemento a desacolar
    *   capacity = capacidad de la cola
    * */
    private int front, size, capacity;
    private Object[] bucket;

    public ArrayQueue(int capacity) {
        this.front = -1;
        this.size = 0;
        this.capacity = capacity;
        this.bucket = new Object[capacity];
    }

    public ArrayQueue() {
        this.front = -1;
        this.size = 0;
        this.capacity = 3;
        this.bucket = new Object[capacity];
    }

    public int getFront() {
        return front;
    }

    public void setFront(int front) {
        this.front = front;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public Object[] getBucket() {
        return bucket;
    }

    public void setBucket(Object[] bucket) {
        this.bucket = bucket;
    }
    public void setBucketByCapacity(int cap){
        if(cap <= 0) throw new RuntimeException("Capacity must be greater than zero.");
        this.bucket = new Object[cap];
    }


    public int size() {
        return this.size;
    }

    public boolean isEmpty() {
        return this.size==this.capacity;
    }

    public E front() {
        if(isEmpty()) throw new RuntimeException("Queue is empty.");
        Object elem = this.bucket[this.front];
        return (E)elem;
    }


    public void enqueue(E element) {
        if(element == null)
            throw new RuntimeException("Element is null");
        /* Cola no esta llena*/
        else if(this.front < this.capacity){
            int new_front = (this.size+1)%this.capacity;
            this.bucket[new_front] = element;
            this.front = new_front;
            this.size++;
        }
        /*Cola LLENA
        *   Se duplica cap inicial
        *   Se extraen cada uno de los elementos y se insertan sobre la marcha en una nueva ArrayQueue
        *   Convertimos la nueva Queue en la actual (this)
        * */
        else{
            ArrayQueue<E> cola_aux = new ArrayQueue<>(this.capacity*2);
            Object extraido;
            while(!cola_aux.isEmpty()){
                extraido = cola_aux.dequeue();
                cola_aux.enqueue((E) extraido);
            }

            this.size = cola_aux.size;
            this.capacity = cola_aux.capacity;
            this.front = cola_aux.capacity;
            this.bucket = cola_aux.bucket;

            // Inserto normal element
            int new_front = (this.front+size)%this.capacity;
            this.bucket[new_front] = element;
            this.front = new_front;
            this.size++;
        }
    }

    public E dequeue() {
        if(isEmpty()) throw new RuntimeException("Queue is empty.");
        E rtn = (E)this.bucket[this.front];
        this.front = (this.front+1)%this.capacity;
        this.size--;
        return rtn;
    }
}
