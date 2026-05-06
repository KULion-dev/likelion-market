package com.likelion.market.service;

import com.likelion.market.dto.ProductDto;
import com.likelion.market.entity.Product;
import com.likelion.market.entity.User;
import com.likelion.market.repository.ProductRepository;
import com.likelion.market.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 🔧 TODO가 있는 파일 — 빈칸을 채워보세요!
 *
 * ProductService: 상품 관련 비즈니스 로직이에요.
 * 2회차 ProductService랑 비슷하지만, 인증된 유저 정보가 추가됐어요!
 */
@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    /**
     * 전체 상품 목록 조회 (로그인 불필요)
     * ✅ 완성된 메서드 — 수정하지 않아도 돼요!
     */
    public List<ProductDto.Response> getAllProducts() {
        return productRepository.findAll()
                .stream()
                .map(ProductDto.Response::from)
                .collect(Collectors.toList());
    }

    /**
     * 상품 등록 (로그인 필요!)
     *
     * TODO ①: email로 유저 조회
     *   힌트: User seller = userRepository.findByEmail(email)
     *         .orElseThrow(() -> new RuntimeException("유저를 찾을 수 없습니다."));
     *
     * TODO ②: Product 객체 생성 후 저장
     *   힌트: Product product = new Product(
     *             request.getTitle(),
     *             request.getDescription(),
     *             request.getPrice(),
     *             seller
     *         );
     *         Product saved = productRepository.save(product);
     *         return ProductDto.Response.from(saved);
     *
     * @param email 현재 로그인한 유저 이메일 (JwtFilter가 SecurityContext에서 꺼내줘요)
     */
    public ProductDto.Response createProduct(ProductDto.CreateRequest request, String email) {
        // TODO ① — 유저 조회


        // TODO ② — 상품 저장 후 반환
        return null; // ← null 지우고 실제 반환값 작성
    }

    /**
     * 내가 등록한 상품 목록 (로그인 필요!)
     *
     * TODO ③: email로 유저 찾고, 그 유저의 상품 목록 반환
     *   힌트: User user = userRepository.findByEmail(email).orElseThrow(...);
     *         return productRepository.findBySeller(user)
     *                .stream().map(ProductDto.Response::from).collect(Collectors.toList());
     */
    public List<ProductDto.Response> getMyProducts(String email) {
        // TODO ③ — 여기를 채워보세요
        return null;
    }
}
