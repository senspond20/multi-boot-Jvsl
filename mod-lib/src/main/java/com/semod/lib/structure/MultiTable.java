package com.semod.lib.structure;

import com.fasterxml.jackson.annotation.JsonIdentityReference;
import lombok.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Getter
@EqualsAndHashCode(of="id")
@NoArgsConstructor
@AllArgsConstructor
//@ToString(exclude = "node")
@ToString
//@JsonIdentityReference(alwaysAsId = true) //직렬화시 id로만 출력된다
public class MultiTable<T> {
    private int id;
    private int parentId;
    private T data;
//    private List<MultiTable<T>> items;

    /**
     * 생성자
     * @param id
     * @param data
     */
    public MultiTable(int id, T data){
        this(id,0,data);
    }
    public MultiNode<T> convertTreeNode(List<MultiTable<T>> list){
        MultiNode<T> root = new MultiNode<T>(null);
        String key = "NodeKey";
        Map<Integer, MultiNode<T>> map = new HashMap<>();

//      parentId 오름차순으로 정렬
        List<MultiTable<T>> sortList = list.stream()
            .sorted((o1, o2)-> o1.getParentId() - o2.getParentId())
            .collect(Collectors.toList());

        for(MultiTable<T> item : sortList){
//            System.out.println(map);
            if(item.getParentId() < 1){
                MultiNode<T> childNode = root.addDown(item.getData());
                map.put(item.getId(), childNode);

            }else {
                MultiNode<T> nNode = map.get(item.getParentId());
                if(nNode !=null){
                    MultiNode<T> nChildNode = nNode.addDown(item.getData());
                    map.put(item.getId(), nChildNode);
                }

            }
        }
        map.clear();
        return root;
    }


//    public List<Object> convert(List<MultiTable<T>> list){
//
//        List<Object> map = new ArrayList<>();
//
//        for(MultiTable<T> item : list){
//            item.items = new ArrayList<>();
//
////            list.stream().filter(item.parentId == )
//
//        }
//        return map;
//    }

}
