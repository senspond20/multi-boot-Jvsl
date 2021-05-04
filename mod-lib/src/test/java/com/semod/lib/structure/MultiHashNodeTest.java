package com.semod.lib.structure;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

//import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

public class MultiHashNodeTest {

    private MultiHashNode<Integer> root;

    @BeforeEach
    void setup(){
        root = new MultiHashNode<Integer>(1);
        root.add(2).add(3).add(3).add(1).addDown(4).addDown(3);
    }

    // true 가 나와야한다
    @Test
    void equalsTest() {
        MultiHashNode<String> node1 = new MultiHashNode<>("안녕하세요");
        MultiHashNode<String> node2 = new MultiHashNode<>("안녕하세요");
        boolean bool = node1.equals(node2);
        System.out.println(bool);
        assumeTrue(bool);
    }

    /**
     * 중복된 데이터를 제외하고 add 되었는지 테스트
     */
    @Test
    void test(){
        System.out.println(root);
    }
}
