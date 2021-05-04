package com.semod.lib.structure;

import com.semod.lib.structure.MultiNode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MultiNodeTest {
    private MultiNode<Integer> root;

    @BeforeEach
    public void setUp() {
        root = new MultiNode<Integer>(1);
        root.add(2)
            .add(3)
            .addDown(4)
                .add(5)
                .add(6)
                .add(7)
                .remove(5)
            .getParent()
            .add(8);
    }
    @Test
    void showTest(){
        System.out.println(root);
    }

    @Test
    void findChildTest(){
        System.out.println(root.findChild(3));
        System.out.println(root.findChild(7));
    }


    @Test
    void findParentTest(){

        System.out.println(root.findChild(111));
        System.out.println(root.findChild(2).getParent());

        System.out.println(root.findChild(4)
                               .findChild(6)
                               .getParent());

    }

    @Test
    void findChildAllTest(){

    }
}
