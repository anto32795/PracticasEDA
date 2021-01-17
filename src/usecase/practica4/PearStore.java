package usecase.practica4;

import java.util.Hashtable;
import java.util.Map;
import java.util.Objects;

public class PearStore {
    private String store_name;
    private Integer store_id;
    private Map<Product, Integer> unitsSoldByProduct;
    private Map<Product, Double> puntuationByProduct;

    public PearStore(String store_name, Integer store_id) {
        this.store_name = store_name;
        this.store_id = store_id;
        unitsSoldByProduct = new Hashtable<>();
        puntuationByProduct = new Hashtable<>();
    }

    public String getStore_name() {
        return store_name;
    }

    public void setStore_name(String store_name) {
        this.store_name = store_name;
    }

    public Integer getStore_id() {
        return store_id;
    }

    public void setStore_id(Integer store_id) {
        this.store_id = store_id;
    }

    public void addSoldsToProduct(int solds, Product product){
        //Integer val = unitsSoldByProduct.get(product)+solds;
        unitsSoldByProduct.put(product, solds);
    }

    public void addPuntuationToProduct(double puntuation, Product product){
        puntuationByProduct.put(product, puntuation);
    }

    public double getPuntuationByProduct(Product p){
        return this.puntuationByProduct.get(p);
    }

    public int getSoldsByProduct(Product p){
        return this.unitsSoldByProduct.get(p);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PearStore pearStore = (PearStore) o;
        //return Objects.equals(store_name, pearStore.store_name) && Objects.equals(store_id, pearStore.store_id) && Objects.equals(unitsSoldByProduct, pearStore.unitsSoldByProduct) && Objects.equals(puntuationByProduct, pearStore.puntuationByProduct);
        return this.store_id.equals(pearStore.store_id) && this.store_name.equals(pearStore.store_name);
    }

    @Override
    public int hashCode() {
        return store_id;
    }

    @Override
    public String toString(){ return store_name+" "+store_id;}
}
