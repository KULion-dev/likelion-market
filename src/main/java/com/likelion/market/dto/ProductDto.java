package com.likelion.market.dto;

import com.likelion.market.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class ProductDto {

    // ── 상품 등록 요청 ─────────────────────────────
    @Getter
    @Setter
    @NoArgsConstructor
    public static class CreateRequest {
        private String title;
        private String description;
        private int price;
    }

    // ── 상품 응답 ──────────────────────────────────
    @Getter
    @AllArgsConstructor
    public static class Response {
        private Long id;
        private String title;
        private String description;
        private int price;
        private String status;
        private String sellerNickname;

        // Product 엔티티 → Response DTO 변환
        public static Response from(Product product) {
            return new Response(
                    product.getId(),
                    product.getTitle(),
                    product.getDescription(),
                    product.getPrice(),
                    product.getStatus().name(),
                    product.getSeller().getNickname()
            );
        }
    }
}
