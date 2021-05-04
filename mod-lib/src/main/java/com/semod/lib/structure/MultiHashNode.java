package com.semod.lib.structure;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.*;

/**
 * 동일한 값을 가지는 데이터를 가진 노드가 여러개 존재할 수 있는 경우를 고려하여
 * 노드를 추가할때 데이터의 중복을 허용하지 않도록 설계 (TreeSet 중복허용X)
 * 그리고 @EqualsAndHashCode로 동일한 data를 가지는 객체는 서로 같은 객체로 정의
 * @param <T>
 */
@Getter
@ToString(exclude = "parent") // parent 를 toString에서 제외.
@EqualsAndHashCode(of="data")
public class MultiHashNode<T> {
    //    private int id;
    private T data;
    private MultiHashNode<T> parent;         // 부모 노드
    private Set<MultiHashNode<T>> children; // 자식 노드들

    /**
     * 기본생성자
     */
    private MultiHashNode() {
    }

    /**
     * 생성자
     *
     * @param data
     */
    public MultiHashNode(T data) {
        this.data = data;
        this.parent = null;
        this.children = null;
    }

    /**
     * 생성자(자식 생성용)
     * @param data
     * @param parent
     */
    private MultiHashNode(T data, MultiHashNode<T> parent) {
        this.data = data;
        this.parent = parent;
        this.children = new HashSet<>();
    }

    /**
     * 자식노드를 추가/삭제 한다.(메소드 체이닝 - 같은 부모에 자식을 이어서 추가할때 사용가능 )
     *
     * @param data
     * @return this
     */
    public MultiHashNode<T> add(T data) {
        MultiHashNode<T> childNode = new MultiHashNode<T>(data, this);
        if (this.children == null) {
            this.children = new HashSet<>();
        }
        if(this.getData() != data)
            this.children.add(childNode);
        return this;
    }

    public MultiHashNode<T> remove(T data) {
        if (this.children != null) {
            this.children.remove(data);
        }
        return this;
    }

    /**
     * 자식 노드를 추가한다 (메소드 체이닝 - 자식의 자식의 자식의 ... 이어서 추가할때 사용가능)
     * @param data
     * @return childNode
     */
    public MultiHashNode<T> addDown(T data) {
        MultiHashNode<T> childNode = new MultiHashNode<T>(data, this);
        if (this.children == null) {
            this.children = new HashSet<>();
        }
        if(this.getData() != data)
            this.children.add(childNode);
        return childNode;
    }
}
