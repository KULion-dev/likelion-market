package com.likelion.market.controller;

import com.likelion.market.dto.ProductDto;
import com.likelion.market.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 🔧 TODO가 있는 파일 — 빈칸을 채워보세요!
 *
 * ProductController: 상품 API를 담당하는 컨트롤러예요.
 *
 * 💡 @AuthenticationPrincipal 이란?
 *   JwtFilter가 SecurityContext에 저장한 유저 정보를 꺼내는 어노테이션이에요.
 *   로그인한 사람이 누구인지 Controller에서 바로 알 수 있어요!
 *
 * Postman에서 테스트할 API:
 *   GET  http://localhost:8080/api/products        (전체 목록, 로그인 불필요)
 *   POST http://localhost:8080/api/products        (상품 등록, 로그인 필요)
 *   GET  http://localhost:8080/api/products/mine   (내 상품, 로그인 필요)
 */
@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    /**
     * 전체 상품 목록 조회
     * GET /api/products
     * ✅ 완성된 메서드!
     */
    @GetMapping
    public ResponseEntity<List<ProductDto.Response>> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    /**
     * 상품 등록 (로그인 필요!)
     * POST /api/products
     *
     * TODO ①: productService.createProduct() 호출하고 201 반환
     *   힌트: String email = userDetails.getUsername();
     *         ProductDto.Response response = productService.createProduct(request, email);
     *         return ResponseEntity.status(201).body(response);
     *
     * @param userDetails @AuthenticationPrincipal로 주입된 로그인 유저 정보
     */
    @PostMapping
    public ResponseEntity<?> createProduct(
            @RequestBody ProductDto.CreateRequest request,
            @AuthenticationPrincipal UserDetails userDetails  // 로그인한 유저 정보
    ) {
        // TODO ① — 여기를 채워보세요
        return ResponseEntity.ok("TODO: 아직 구현 안 됨");
    }

    /**
     * 내가 등록한 상품 목록 (로그인 필요!)
     * GET /api/products/mine
     *
     * TODO ②: productService.getMyProducts() 호출하고 반환
     *   힌트: String email = userDetails.getUsername();
     *         return ResponseEntity.ok(productService.getMyProducts(email));
     */
    @GetMapping("/mine")
    public ResponseEntity<?> getMyProducts(
            @AuthenticationPrincipal UserDetails userDetails
    ) {
        // TODO ② — 여기를 채워보세요
        return ResponseEntity.ok("TODO: 아직 구현 안 됨");
    }
}
