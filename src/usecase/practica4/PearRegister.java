package usecase.practica4;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class PearRegister {
    private Map<Product, List<PearStore>> storesWithProduct;

    public PearRegister() {
        this.storesWithProduct = new Hashtable<>();
    }


    public void loadFile(String pathToFile) {
        File file = new File(pathToFile);
        int num_prods = 0, i,j;
        Product producto;
        String[] store, prod;
        try{
            Scanner sc = new Scanner(file);
            LinkedList<PearStore> lista_tiendas;
            while(sc.hasNextLine()){
                num_prods = sc.nextInt();
                sc.nextLine();
                //System.out.println("num prods: "+num_prods);
                for(i=0; i<num_prods;i++){
                    prod = sc.nextLine().split(" ");
                    //System.out.println(sc.nextLine());
                    producto = new Product(prod[0], Integer.parseInt(prod[1]));
                    //System.out.println("Producto: "+producto);
                    lista_tiendas = new LinkedList<>();
                    for(j=0; j<Integer.parseInt(prod[2]); j++){
                        store = sc.nextLine().split(" ");
                        PearStore ps = new PearStore(store[0], Integer.parseInt(store[1]));
                        //System.out.println("Tienda: "+ps);
                        ps.addPuntuationToProduct(Double.parseDouble(store[3]), producto);
                        ps.addSoldsToProduct(Integer.parseInt(store[2]), producto);
                        lista_tiendas.add(ps);
                    }
                    //System.out.println("Lista tiendas: "+lista_tiendas);
                    storesWithProduct.put(producto, lista_tiendas);
                }
            }
        }catch (FileNotFoundException ioe){
            ioe.getMessage();
        }
    }

    public void addProduct(Product producto, Iterable<PearStore> stores){
        List<PearStore> tiendas = this.storesWithProduct.get(producto);
        /*if(tiendas == null) tiendas = new LinkedList<>();
        for(PearStore s: stores){
            tiendas.add(s);
        }*/
        if(tiendas == null){
            tiendas = new LinkedList<>();
            for(PearStore s: stores){
                tiendas.add(s);
            }
            storesWithProduct.put(producto, tiendas);
        }
        else{
            for(PearStore s: stores){
                tiendas.add(s);
            }
        }
    }

    public void addSalesInPearStore(Product producto, PearStore store, int units, double score){
        store.addSoldsToProduct(units, producto);
        store.addPuntuationToProduct(score, producto);
    }

    public double getScoreOfProduct(Product producto){
        List<PearStore> stores = storesWithProduct.get(producto);
        double score = 0.0;
        for(PearStore s: stores){
            score += s.getPuntuationByProduct(producto);
        }
        return score / stores.size();
    }

    public PearStore getGreatestSeller(Product producto){
        List<PearStore> stores = storesWithProduct.get(producto);
        int max = -1;
        PearStore bestStore = null;
        //if(stores == null) return null;
        for(PearStore s: stores){
            int val = s.getSoldsByProduct(producto);
            if(max < val){
                max = val;
                bestStore = s;
            }
        }
        return bestStore;
    }

    public int getUnits(Product producto){
        List<PearStore> stores = storesWithProduct.get(producto);
        int units = 0;
        for(PearStore s: stores){
            units += s.getSoldsByProduct(producto);
        }
        return units;
    }

    public boolean productExists(Product product){
        List<PearStore> stores = storesWithProduct.get(product);
        if(stores == null) return false;
        else if(stores.isEmpty()) return false;
        else return true;
    }
}
