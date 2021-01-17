package material.test.practica2;

import material.Position;
import material.tree.binarytree.BinaryTree;
import material.tree.binarytree.LinkedBinaryTree;
import material.tree.narytree.LinkedTree;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import usecase.practica2.LevelIsComplete;
import usecase.practica2.exam;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LevelIsCompleteTest {
    LinkedBinaryTree<Integer> t = new LinkedBinaryTree<>();

    @BeforeEach
    void setUp() {
        t.addRoot(1);
        Position<Integer> a,b,c,d,e,f,g,h;
        a = t.root();
        b = t.insertLeft(a,2);
        c = t.insertRight(a,3);

        d = t.insertRight(b,4);
        e = t.insertLeft(c,5);
        f = t.insertRight(c,6);

        g = t.insertLeft(e,7);
        h = t.insertRight(e,8);

    }

    @Test
    void testExam_(){
        LevelIsComplete los = new LevelIsComplete();
        List<Integer> xd= los.levelsIncomplete(t);
        List<Integer> resul = new LinkedList<>();
        resul.add(2);

        assertEquals(resul, xd);
    }
}
