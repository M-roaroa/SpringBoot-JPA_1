package jpabook.jpashop.domain.Item;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.FetchType.*;

@Entity
@Getter
@Setter
public class Category {

    @Id
    @GeneratedValue
    @Column(name = "category")
    private Long id;

    private String name;

    @ManyToMany
    @JoinTable(name = "category_item", // 중간 테이블 (조인테이블), JoinTable => 실무에서는 사용하지 x, 실습이므로 진행
        joinColumns = @JoinColumn(name = "category_id"), // 카테고리에 들어가는
        inverseJoinColumns = @JoinColumn(name = "item_id")) // 아이템 쪽에 들어가는
    private List<Item> items = new ArrayList<>();


    //NOTE: 셀프로 양방향 연관관계 매핑
    //내 부모가 내 타입
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "parent_id")
    private Category parent;

    @OneToMany(mappedBy = "parent")
    private List<Category> child = new ArrayList<>();
}
