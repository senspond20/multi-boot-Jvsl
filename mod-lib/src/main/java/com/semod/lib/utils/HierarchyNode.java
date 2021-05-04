package com.semod.lib.utils;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Getter
public class HierarchyNode<T> {
    private T data;
    private List<HierarchyNode<T>> children;

    private HierarchyNode(){}

    public HierarchyNode(T data){
        this.data = data;
        this.children = new ArrayList<>();
    }

    /**
     * 자식 노드를 추가한다
     * @param data
     * @return
     */
    public HierarchyNode<T> addChildNode(T data){
        HierarchyNode<T> childNode = new HierarchyNode<T>(data);
        return childNode;
    }

    public HierarchyNode<T> findChild(T data) {
        Optional<HierarchyNode<T>> first = this.getChildren().stream().filter(n -> data == n.getData()).findFirst();
        if (!first.isPresent()) return null;
        return first.get();
    }

}
