package material.maps;

import java.util.*;

/**
 * Separate chaining table implementation of hash tables. Note that all
 * "matching" is based on the equals method.
 *
 * @author A. Duarte, J. Vélez, J. Sánchez-Oro, Sergio Pérez
 */
public class HashTableMapSC<K, V> implements Map<K, V> {
    //TODO: Ejercicio para los alumnos, implementar todo

    private class HashEntry<T, U> implements Entry<T, U> {

        protected T key;
        protected U value;

        public HashEntry(T k, U v) {
            key = k;
            value = v;
        }

        @Override
        public U getValue() {
            return value;
        }

        @Override
        public T getKey() {
            return key;
        }

        public U setValue(U val) {
            U old = this.value;
            this.value = val;
            return old;
        }

        @Override
        public boolean equals(Object o) {
            if(o == null) return false;
            else if(o == this) return true;
            HashEntry<T,U> aux = (HashEntry<T, U>) o;
            return (aux.key.equals(this.key) && aux.value.equals(this.value));
        }

        /**
         * Entry visualization.
         */
        @Override
        public String toString() {
            return "(" + key + "," + value + ")";
        }
    }

    private class HashTableMapIterator<T, U> implements Iterator<Entry<T, U>> {
// TODO: THIS
        private List<HashEntry<T, U>> [] bucket;
        private int pos_bucket;
        private int pos_list;

        public HashTableMapIterator(List<HashEntry<T, U>>[] map, int numElems) {
            bucket = map;
            pos_bucket = numElems;
            pos_list = 0;
        }


        @Override
        public boolean hasNext() {
            return ((this.pos_bucket < this.bucket.length) || (pos_list < bucket[pos_bucket].size()));
        }

        @Override
        public Entry<T, U> next() {
            if (hasNext()) {
                goToNextBucket(pos_bucket);
                return this.bucket[pos_bucket].get(pos_list);
            } else {
                throw new IllegalStateException("The map has not more elements");
            }
        }

        @Override
        public void remove() {
            //NO ES NECESARIO IMPLEMENTARLO
            throw new UnsupportedOperationException("Not implemented.");

        }

        /**
         * Returns the index of the next position starting starting from a given index.
         * (if the parameter is already a valid position then does nothing)
         */
        private void goToNextBucket(int i) {
            if(bucket[pos_bucket].get(pos_list+1) != null)
                pos_list++;
            else {
                pos_list = 0;
                while(pos_bucket < bucket.length && (bucket[pos_bucket] == null || bucket[pos_bucket].isEmpty())){
                    pos_bucket++;
                }
            }
        }
    }

    private class HashTableMapKeyIterator<T, U> implements Iterator<T> {
        public HashTableMapIterator<T, U> it;

        public HashTableMapKeyIterator(HashTableMapIterator<T, U> it) {
            this.it = it;
        }

        @Override
        public T next() {
            return it.next().getKey();
        }

        @Override
        public boolean hasNext() {
            return it.hasNext();
        }

        @Override
        public void remove() {
            //NO ES NECESARIO IMPLEMENTARLO
            throw new RuntimeException("Not yet implemented");
        }
    }

    private class HashTableMapValueIterator<T, U> implements Iterator<U> {
        public HashTableMapIterator<T, U> it;

        public HashTableMapValueIterator(HashTableMapIterator<T, U> it) {
            this.it = it;
        }

        @Override
        public U next() {
            return it.next().getValue();
        }

        @Override
        public boolean hasNext() {
            return it.hasNext();
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("Not implemented.");
        }
    }


    /**
     * The array of Lists.
     */
    private LinkedList<HashEntry<K,V>>[] bucket;
    private int capacity, prime, n;
    private int a,b; //random numbers
    private Random rand;

    /**
     * Creates a hash table with prime factor 109345121 and capacity 1000.
     */
    public HashTableMapSC() {
        this.capacity = 1000;
        this.prime = 109345121;
        this.bucket = new LinkedList[capacity];
        for(int i=0 ; i<capacity; i++){
            bucket[i] = new LinkedList<>();
        }
        this.n = 0;
        rand = new Random();
        a = Math.abs(rand.nextInt()) % this.prime;
        b = Math.abs(rand.nextInt()) % this.prime;

    }

    /**
     * Creates a hash table with prime factor 109345121 and given capacity.
     *
     * @param cap initial capacity
     */
    public HashTableMapSC(int cap) {
        this.capacity = cap;
        this.prime = 109345121;
        this.bucket = new LinkedList[capacity];
        for(int i=0 ; i<capacity; i++){
            bucket[i] = new LinkedList<>();
        }
        this.n = 0;
        rand = new Random();
        a = Math.abs(rand.nextInt()) % this.prime;
        b = Math.abs(rand.nextInt()) % this.prime;
    }

    /**
     * Creates a hash table with the given prime factor and capacity.
     *
     * @param p   prime number
     * @param cap initial capacity
     */
    public HashTableMapSC(int p, int cap) {
        this.capacity = cap;
        this.prime = p;
        this.bucket = new LinkedList[capacity];
        for(int i=0 ; i<capacity; i++){
            bucket[i] = new LinkedList<>();
        }
        this.n = 0;
        rand = new Random();
        a = Math.abs(rand.nextInt()) % this.prime;
        b = Math.abs(rand.nextInt()) % this.prime;
    }

    /**
     * Hash function applying MAD method to default hash code.
     *
     * @param key Key
     * @return the hash value
     */
    protected int hashValue(K key) { return ((key.hashCode() * a + b) % this.prime ) % this.capacity; }


    @Override
    public int size() {
        return this.n;
    }

    @Override
    public boolean isEmpty() {
        return this.n == 0;
    }

    @Override
    public V get(K key){
        checkKey(key);
        int pos_bucket = this.hashValue(key);
        List<HashEntry<K,V>> lista_pos = this.bucket[pos_bucket];
        if(lista_pos == null || lista_pos.isEmpty())
            throw new RuntimeException("There is no value for that key.");
        for(HashEntry<K,V> entry: lista_pos){
            if(entry.key.equals(key)){
                return entry.value;
            }
        }
        throw new RuntimeException("There is no value for that key.");
    }
//    @Override
//    public V get(K key) {
//        checkKey(key);
//        LinkedList<HashEntry<K,V>> entry_list = this.bucket[hashValue(key)];
//        if(entry_list == null){
//            return null;
//        }
//        else if(entry_list.size() == 1){
//            return entry_list.getFirst().value;
//        }
//        else {
//            for(HashEntry<K,V> entry: entry_list){
//                if(entry.key.equals(key)){
//                    return entry.value;
//                }
//            }
//        }
//        return null;
//    }

    @Override
    public V put(K key, V value){
        if(n / capacity >= 0.75)
            rehash(this.capacity*2);

        checkKey(key);
        HashEntry<K,V> newEntry = new HashEntry<>(key, value);
        LinkedList<HashEntry<K,V>> entry_list = this.bucket[this.hashValue(key)];
        entry_list.add(newEntry);
        this.n++;
        return null;
    }
//
//    @Override
//    public V put(K key, V value) {
//        if(n / capacity >= 0.75)
//            rehash(this.capacity*2);
//        checkKey(key);
//        HashEntry<K,V> newEntry = new HashEntry<>(key, value);
//        LinkedList<HashEntry<K,V>> entry_list = this.bucket[this.hashValue(key)];
//        System.out.println("Insertando par ["+key.toString()+", "+value.toString()+"] en pos: "+this.hashValue(key));
//        if(entry_list == null){
//            entry_list = new LinkedList<>();
//        }
//        entry_list.add(newEntry);
//        this.n++;
//        if(entry_list.size() == 1){
//            return null; // no previous entry
//        }else{
//            return entry_list.getFirst().value;
//        }
//
//    }
    @Override
    public V remove(K key){
        checkKey(key);
        List<HashEntry<K,V>> entry_list = this.bucket[this.hashValue(key)];
        HashEntry<K,V> refToDelete = null;
        if(entry_list.isEmpty())
            throw new RuntimeException("No entry to remove with key: "+key.toString());
        for(HashEntry<K,V> entry: entry_list){
            if(entry.key.equals(key)){
                refToDelete = entry;
                break;
            }
        }
        if(refToDelete == null){
            throw new RuntimeException("No entry to remove with key: "+key.toString());
        }
        else{
            entry_list.remove(refToDelete);
            this.n--;
        }
        return null;
    }

//
//    @Override
//    public V remove(K key) {
//        checkKey(key);
//        System.out.println("Borrando key ["+key.toString()+"] en pos: "+this.hashValue(key));
//        LinkedList<HashEntry<K,V>> entry_list = this.bucket[this.hashValue(key)];
//        HashEntry<K,V> found_entry = null;
//        if(entry_list == null)
//            return null;
//        else if(entry_list.size() == 1){
//            V toReturn = entry_list.getFirst().value;
//            entry_list.removeFirst();
//            this.n--;
//            return toReturn;
//        }
//        else {
//            for(HashEntry<K,V> entry: entry_list){
//                if(entry.key.equals(key)){
//                    found_entry = entry;
//                    break;
//                }
//            }
//            if(found_entry == null) {
//                return null;
//            }
//            else {
//                V toreturn = found_entry.getValue();
//                entry_list.remove(found_entry);
//                this.n--;
//                return toreturn;
//            }
//        }
//    }


    @Override
    public Iterator<Entry<K, V>> iterator() {
        throw new RuntimeException("Not yet implemented");

    }

    @Override
    public Iterable<K> keys() {
        throw new RuntimeException("Not yet implemented");
    }

    @Override
    public Iterable<V> values() {
        throw new RuntimeException("Not yet implemented");
    }

    @Override
    public Iterable<Entry<K, V>> entries() {
        throw new RuntimeException("Not yet implemented");
    }

    /**
     * Determines whether a key is valid.
     *
     * @param k Key
     */
    protected void checkKey(K k) {
        if(k == null) throw new RuntimeException("Invalid key: null");
    }


    /**
     * Increase/reduce the size of the hash table and rehashes all the entries.
     */
    protected void rehash(int newCap) {
        this.capacity = newCap;
        LinkedList<HashEntry<K,V>>[] old_bucket = this.bucket;
        this.bucket = new LinkedList[newCap];

        for(LinkedList<HashEntry<K,V>> entry_list: old_bucket){
            if(entry_list != null){
                for(HashEntry<K,V> entry: entry_list){
                    if(entry != null)
                        this.put(entry.getKey(), entry.getValue());
                }
            }
        }
    }
}
