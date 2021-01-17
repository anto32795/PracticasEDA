package material.test.practica3;

import material.Position;
import usecase.practica3.Diameter;
import material.tree.binarytree.LinkedBinaryTree;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DiameterTest {
    LinkedBinaryTree<Integer> t = new LinkedBinaryTree<>();
    Position<Integer> n1,n2;

    @BeforeEach
    void setUp() {
        t.addRoot(1);
        Position<Integer>a,b,c,d,e,f,g,h;
        a = t.root();
        b = t.insertLeft(a,2);
        c = t.insertRight(a,3);

        d = t.insertRight(b, 4);
        e = t.insertLeft(c, 5);
        f = t.insertRight(c, 6);

        g = t.insertLeft(e, 7);
        h = t.insertRight(e, 8);

        n1 = d;
        n2 = g;
    }

    @Test
    void testDiamenter(){
        Diameter diametro = new Diameter();
        int resultado_metodo = diametro.evalDiameter(t, n1, n2);

        assertEquals(6, resultado_metodo);
    }
}
