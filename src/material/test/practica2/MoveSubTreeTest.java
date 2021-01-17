package material.test.practica2;
import material.Position;
import material.tree.iterators.BFSIterator;
import material.tree.narytree.LinkedTree;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import usecase.practica2.GameOfThrones;

import static org.junit.jupiter.api.Assertions.*;

public class MoveSubTreeTest {
    private LinkedTree<String> tree;
    private Position<String> ori, dest;

    @BeforeEach
    void setUp() {
        tree = new LinkedTree<>();
        String li2 = "A C D B E F G H I J K L M N";
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

        ori = b;
        dest = n;
    }


    @Test
    void testMoveSubtree() {
        String expected = "ACDGHIJKLMNBEF";
        tree.moveSubtree(ori, dest);
        String output = "";
        BFSIterator<String> it = new BFSIterator<>(tree, tree.root());
        while(it.hasNext()){
            String n = it.next().getElement();
            output+=n;
        }

        assertEquals(expected, output);
    }

}
