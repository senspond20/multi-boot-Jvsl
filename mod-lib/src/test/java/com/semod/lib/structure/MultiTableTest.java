package com.semod.lib.structure;

import com.semod.lib.utils.JsonUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MultiTableTest {

    List<MultiTable<String>> list = new ArrayList<>();
    @BeforeEach
    void setup(){

        list.add(new MultiTable<String>(7,4,"카테고리111"));
        list.add(new MultiTable<String>(8,4,"카테고리112"));
        list.add(new MultiTable<String>(9,6,"카테고리211"));
        list.add(new MultiTable<String>(10,6,"카테고리212"));
        list.add(new MultiTable<String>(1,0,"카테고리1"));
        list.add(new MultiTable<String>(4,1,"카테고리11"));
        list.add(new MultiTable<String>(5,1,"카테고리12"));
        list.add(new MultiTable<String>(2,0,"카테고리2"));
        list.add(new MultiTable<String>(3,0,"카테고리3"));
        list.add(new MultiTable<String>(6,2,"카테고리21"));

//        System.out.println(list);

        list.stream()
                .sorted((o1, o2)-> o1.getParentId() - o2.getParentId())
                .collect(Collectors.toList()).forEach(System.out::println);
//        System.out.println(JsonUtils.getPrettyJsonString(list));

    }

    @Test
    void test(){
        MultiNode<String> node = new MultiTable<String>().convertTreeNode(list);
        System.out.println(node);
//        System.out.println(JsonUtils.getPrettyJsonString(node));


    }
    @Test
    void test2(){
//        List node2 = new MultiTable<String>().convert(list);
//        System.out.println(node2);
    }
}
