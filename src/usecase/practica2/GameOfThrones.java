package usecase.practica2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import material.Position;


import material.Position;
import material.tree.narytree.LinkedTree;

public class GameOfThrones {
    List<FamilyMember> listaFamiliares;

    public GameOfThrones() {
        this.listaFamiliares = new LinkedList<>();
    }

    public void loadFile(String pathToFile){
        String id, name, surname, genre;
        Integer age;
        String linea = "";
        String[] linea_separada;
        FamilyMember familiar;

        try{
            File fichero = new File(pathToFile);
            Scanner sc = new Scanner(fichero);

            while(sc.hasNextLine()){
                linea = sc.nextLine();
                if(linea.contains("--")){
                    break;
                }
                linea_separada = linea.split(" ");
                id = linea_separada[0];
                name = linea_separada[2];
                surname = linea_separada[3];
                genre = linea_separada[4];
                age = Integer.parseInt(linea_separada[5]);

                System.out.println("Familiar recibido: "+id+name+surname+genre+linea_separada[5]);

                familiar = new FamilyMember(id, age, name,surname,genre);
                listaFamiliares.add(familiar );

                // Caputurados todos los nodos

            }
            int num_familias = Integer.parseInt(sc.nextLine());
            System.out.println("Num fam : "+num_familias);
            ArrayList<FamilyMember> roots = new ArrayList<>(num_familias);
            FamilyMember fam_member = null;
            String[] roots_string = new String[num_familias];
            String id_fam_member;

            for(int i=0; i<num_familias;i++){
                id_fam_member = sc.nextLine();
                for(FamilyMember fm: listaFamiliares){
                    if(fm.getId().equals(id_fam_member)){
                        roots.add( new FamilyMember(id_fam_member, fm.getEdad(), fm.getNombre(),
                                fm.getApellido(), fm.getGenero()));
                        break;
                    }
                }
            }
            if(roots.size() != num_familias){
                throw new RuntimeException("GOT_Families has a bad format.");
            }

            // Relaciones padre-hijo ...

            // Arraylist con todos los arboles y su raiz.
            ArrayList<LinkedTree<FamilyMember>> family_trees = new ArrayList<>(num_familias);
            for(FamilyMember f: roots){
                LinkedTree<FamilyMember> newTree = new LinkedTree<>();
                newTree.addRoot(f);
                family_trees.add(newTree);
            }

            while(sc.hasNextLine()){
                String[] rel = new String[2];
                Position<FamilyMember> pos_padre, pos_hijo;
                LinkedTree<FamilyMember> family_tree = null;
                linea = sc.nextLine();
                rel = linea.split(" -> ");

                // find family tree
                for(LinkedTree<FamilyMember> ft: family_trees){
                    //if(ft.root().getElement().getApellido().equals())
                }
            }


            sc.close();
        }catch (FileNotFoundException e){
            System.err.println("File not found..");
            e.getMessage();
        }
    }

    public LinkedTree<FamilyMember> getFamily(String surname){
        throw new RuntimeException("Not yet implemented");
    }

    public String findHeir(String surname){
        throw new RuntimeException("Not yet implemented");
    }

    public void changeFamily(String memberName, String newParent){
        throw new RuntimeException("Not yet implemented");
    }

    public boolean areFamily(String name1, String name2){
        throw new RuntimeException("Not yet implemented");
    }
}
