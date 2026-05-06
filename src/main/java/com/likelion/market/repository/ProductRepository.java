package com.likelion.market.repository;

import com.likelion.market.entity.Product;
import com.likelion.market.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * ✅ 완성된 파일 — 수정하지 않아도 돼요!
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    // 특정 유저가 등록한 상품 목록 조회
    List<Product> findBySeller(User seller);

    // 판매 중인 상품만 조회
    List<Product> findByStatus(Product.Status status);
}
