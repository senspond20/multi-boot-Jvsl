package com.semod.lib.structure;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MultiNodeTest2 {

    private MultiNode<Integer> root;

    @BeforeEach
    void setUp() {
        root = new MultiNode<Integer>(1);
        root.add(1).add(2).add(3).add(3).add(4).addDown(1).add(4).addDown(3);
    }
    @Test
    void test(){
//        System.out.println();
        root.findChild(3).forEach(System.out::println);

    }

    @Test
    void equalsTest(){
        MultiNode<Integer> node1 = new MultiNode<Integer>(5);
        MultiNode<Integer> node2 = new MultiNode<Integer>(5);
        System.out.println(node1.equals(node2));
    }

    @Test
    void findRoot(){
       MultiNode<Integer> root = new MultiNode<>(1);
       MultiNode<Integer> node = root.add(1).add(2).addDown(1).add(2).addDown(3).addDown(4).add(5).addDown(6).add(7).addDown(8);
       System.out.println(node.findRoot());
       System.out.println(node.findRoot().getParent()); // null

    }
}
