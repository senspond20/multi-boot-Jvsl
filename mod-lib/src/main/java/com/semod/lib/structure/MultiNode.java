package com.semod.lib.structure;

//import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Getter
@ToString(exclude = "parent") // parent 를 toString에서 제외.
//@EqualsAndHashCode(of="id")
public class MultiNode<T> {
//    private int id;
    private T data;
    private MultiNode<T> parent;         // 부모 노드
    private List<MultiNode<T>> children; // 자식 노드들

    /**
     * 기본생성자
     */
    private MultiNode(){}

    /**
     * 생성자
     * @param data
     */
    public MultiNode(T data){
        this.data = data;
        this.parent = null;
        this.children = null;
    }

    /**
     * 생성자(자식 생성용)
     * @param data
     * @param parent
     */
    private MultiNode(T data, MultiNode<T> parent){
        this.data = data;
        this.parent = parent;
        this.children = new ArrayList<>();
    }
    /**
     * 자식노드를 추가/삭제 한다.(메소드 체이닝 - 같은 부모에 자식을 이어서 추가할때 사용가능 )
     * @param data
     * @return this
     */
    public MultiNode<T> add(T data){
        MultiNode<T> childNode = new MultiNode<T>(data, this);
        if(this.children == null){
            this.children = new ArrayList<>();
        }
        this.children.add(childNode);
        return this;
    }

    public MultiNode<T> remove(T data){
        MultiNode<T> childNode = new MultiNode<T>(data, this);
        if(this.children != null){
            this.children.remove(childNode);
        }
        return this;
    }

    /**
     * 자식 노드를 추가한다 (메소드 체이닝 - 자식의 자식의 자식의 ... 이어서 추가할때 사용가능)
     * @param data
     * @return childNode
     */
    public MultiNode<T> addDown(T data){
        MultiNode<T> childNode = new MultiNode<T>(data, this);
        if(this.children == null){
            this.children = new ArrayList<>();
        }
        this.children.add(childNode);
        return childNode;
    }

    /**
     * 자식노드에서 data를 찾아 노드객체를 반환 ( 현재노드의 자식노드에서 검색)
     * @param data
     * @return
     */
    public MultiNode<T> findChild(T data) {
        // Optional null일수도 있는 Wrapper클래스
        Optional<MultiNode<T>> first = this.getChildren()
                                           .stream()
                                           .filter(n -> data == n.getData())
                                           .findFirst();
        if (!first.isPresent()) return null;
        return first.get();
    }

    /**
     * 현재 노드의 모든 하위노드에서 data를 검색해 노드객체를 반환
     * @param data
     * @return
     */
    public MultiNode<T> find(T data){

        return null;
    }

}
