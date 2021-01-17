package material.test.practica2;

import material.Position;
import material.tree.binarytree.LinkedBinaryTree;
import material.tree.narytree.LinkedTree;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import usecase.practica2.exam;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class examTest {
    LinkedTree<Integer> t = new LinkedTree<>();
    int level = 2;

    @BeforeEach
    void setUp() {
        t.addRoot(1);
        Position<Integer>a,b,c,d,e,f,g,h;
        a = t.root();
        b = t.add(2,a);
        c = t.add(3,a);

        d = t.add(4,b);
        e = t.add(5,c);
        f = t.add(6,c);

        g = t.add(7,e);
        h = t.add(8,e);

    }

    @Test
    void testExam(){
        exam test = new exam(t);
        List<Integer> li = test.getNodesByLevel(this.level);
        List<Integer> res = new LinkedList<>();
        res.add(2);
        res.add(3);

        assertEquals(res, li);
    }
}
