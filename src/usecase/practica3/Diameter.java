package usecase.practica3;

import material.Position;
import material.tree.binarytree.BinaryTree;
import java.util.ArrayList;

public class Diameter {
	
	public int evalDiameter(BinaryTree<Integer> tree, Position<Integer> v1, Position<Integer> v2) {
		if(!tree.isLeaf(v1) || !tree.isLeaf(v2)){
			return -1;
		}
		ArrayList<Position<Integer>> a1 = new ArrayList<>(), a2 = new ArrayList<>();
		Position<Integer> aux;
		int d1,d2;

		aux = v1;
		/*while(aux != null) {
			a1.add(aux);
			aux = tree.parent(aux);
		}*/
		try{
			while(true){
				a1.add(aux);
				aux = tree.parent(aux);
			}
		}catch(RuntimeException e){
		}

		aux = v2;
		/*while(aux != null) {
			a2.add(aux);
			aux = tree.parent(aux);
		}*/
		try{
			while(true){
				a2.add(aux);
				aux = tree.parent(aux);
			}
		}catch(RuntimeException e){
		}

		for (d1=0; d1 < a1.size(); d1++){
			for (d2 = 0; d2 < a2.size(); d2++){
				if( a1.get(d1).equals( a2.get(d2)) ){
					return d1+d2+1;
				}
			}
		}

		throw new RuntimeException("No possible way.");
	}
}