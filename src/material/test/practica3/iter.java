package material.test.practica3;

import material.Position;
import material.tree.narytree.LinkedTree;

import java.util.ArrayDeque;

public class iter {
    public static void main(String[] args) {
        LinkedTree<String> tree = new LinkedTree<>();
        String li = "A B C D E F G H I J K L M N";
        String[] letras = li.split(" ");
        Position<String> raiz = tree.addRoot(letras[0]);

        Position<String> b = tree.add(letras[1], raiz);
        Position<String> c = tree.add(letras[2], raiz);
        Position<String> d = tree.add(letras[3], raiz);

        Position<String> e = tree.add(letras[4], b);
        Position<String> f = tree.add(letras[5], b);

        Position<String> g = tree.add(letras[6], c);
        Position<String> h = tree.add(letras[7], c);
        Position<String> i = tree.add(letras[8], c);

        Position<String> j = tree.add(letras[9], d);
        Position<String> k = tree.add(letras[10], d);

        Position<String> l = tree.add(letras[11], g);
        Position<String> m = tree.add(letras[12], g);

        Position<String> n = tree.add(letras[13], k);

        traversalPreorder(tree);


    }

    public static void traversalPreorder(LinkedTree<String> t) {
        ArrayDeque<Position<String>> stack = new ArrayDeque<>();
        Position<String> node;
        stack.push(t.root());
        while(!stack.isEmpty()){
            node = stack.pop();
            System.out.println(node.getElement());
            for(Position<String> child: t.children(node)){
                stack.push(child);
            }
        }
    }
}
