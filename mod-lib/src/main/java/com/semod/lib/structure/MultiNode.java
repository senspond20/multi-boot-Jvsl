package com.semod.lib.structure;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * 직렬화시 순환참초가 발생할 수 있다. => @JsonBackReference 조치
 * @param <T>
 */
@Getter
@ToString(exclude = "parent") // parent 를 toString에서 제외.
//@EqualsAndHashCode(of="data")
public class MultiNode<T> implements Serializable {
    private static final long serialVersionUID = -4865946674835353945L;

    @JsonManagedReference // 참조가 되는 앞부분을 의미하며, 정상적으로 직렬화를 수행한다.
    private T data;

    @JsonBackReference // 참조가 되는 뒷부분을 의미하며, 직렬화를 수행하지 않는다.
    private MultiNode<T> parent;         // 부모 노드

    @JsonManagedReference
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
     * 최상단 루트노드를 리턴한다.
     * @return
     */
    public MultiNode<T> findRoot(){
        return findRoot(this);
    }
    private MultiNode<T> findRoot(MultiNode<T> current){
        MultiNode<T> node = current.getParent();
        if(node.getParent() != null){
            return this.findRoot(node);
        }else{
            return node;
        }
    }
    /**
     * 자식노드에서 data를 찾아 노드객체를 반환 ( 현재노드의 자식노드에서 검색)
     * ( 자식노드에 동일한 값을 가지는 데이터가 여러개 존재하는 경우를 생각 )
     * @param data
     * @return
     */
    public Stream<MultiNode<T>> findChild(T data) {
        Stream<MultiNode<T>> stream = this.getChildren()
                                        .stream()
                                        .filter(n -> data == n.getData());
        return stream;
    }

    /**
     * 자식노드에서 data를 찾아 노드객체를 반환 ( 현재노드의 자식노드에서 검색)
     * @param data
     * @return
     */
    public MultiNode<T> findFirstChild(T data) {
        // Optional null일수도 있는 Wrapper클래스
        Optional<MultiNode<T>> first = this.findChild(data).findFirst();
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
