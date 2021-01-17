package usecase.practica2;

import material.Position;
import material.tree.binarytree.BinaryTree;

import java.util.LinkedList;
import java.util.List;

public class LevelIsComplete {

    public List<Integer> levelsIncomplete(BinaryTree<Integer> t){
        List<Integer> lista = new LinkedList<>();//1
        aux(t, 1, t.root(), lista);
        return lista;
    }

    private void aux(BinaryTree<Integer> tree, int level, Position<Integer> node,  List<Integer> l){
        if(tree.hasLeft(node)){
            aux(tree, level+1, tree.left(node), l);
        }
        else if(tree.hasRight(node)){
            aux(tree, level+1, tree.right(node), l);
        }

        if(tree.isInternal(node)){
            if((tree.hasLeft(node) && !tree.hasRight(node)) || (!tree.hasLeft(node) && tree.hasRight(node))){
                if(!l.contains(level))
                    l.add(level);
            }
        }
    }
}
