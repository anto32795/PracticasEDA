package usecase.practica4;

import java.util.Objects;

public class Product {
    private String product_name;
    private Integer product_year;

    public Product(String product_name, Integer product_year) {
        this.product_name = product_name;
        this.product_year = product_year;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public Integer getProduct_year() {
        return product_year;
    }

    public void setProduct_year(Integer product_year) {
        this.product_year = product_year;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return product_name.equals(product.product_name) && product_year.equals(product.product_year);
    }

    @Override
    public int hashCode() {
        return Objects.hash(product_name, product_year);
    }
    @Override
    public String toString(){ return product_name+" "+product_year; }
}
