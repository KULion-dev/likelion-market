package com.likelion.market.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * ✅ 완성된 파일 — 수정하지 않아도 돼요!
 *
 * Product 엔티티: 중고 상품 정보를 저장하는 테이블이에요.
 * 2회차에서 만든 Product와 비슷하지만, User와 연관관계가 추가됐어요.
 *
 * @ManyToOne → "상품 여러 개가 한 유저에게 속한다" (N:1 관계)
 */
@Entity
@Table(name = "products")
@Getter
@Setter
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;       // 상품명

    @Column(length = 1000)
    private String description; // 상품 설명

    @Column(nullable = false)
    private int price;          // 가격

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status = Status.SELLING;  // 판매 상태

    // 이 상품을 등록한 유저 (외래키: user_id)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User seller;

    public Product(String title, String description, int price, User seller) {
        this.title = title;
        this.description = description;
        this.price = price;
        this.seller = seller;
        this.status = Status.SELLING;
    }

    public enum Status {
        SELLING,    // 판매 중
        RESERVED,   // 예약 중
        SOLD        // 판매 완료
    }
}
