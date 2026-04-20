package com.tmdt.m4s3b6.service;

import com.tmdt.m4s3b6.dto.ProductDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductClientService {

    private final RestTemplate restTemplate;

    private final String productServiceUrl = "http://localhost:8081/api/products/";

    /**
     * Gọi Product Service lấy giá + kiểm tra tồn kho
     * Nếu lỗi → fallback
     */
    public ProductDto getProductForOrder(Long productId) {
        String url = productServiceUrl + productId;

        try {
            ResponseEntity<ProductDto> response = restTemplate.getForEntity(url, ProductDto.class);
            if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
                ProductDto product = response.getBody();
                if (product.getStock() <= 0) {
                    throw new RuntimeException("Sản phẩm hết hàng");
                }
                return product;
            }
            throw new RuntimeException("Không lấy được thông tin sản phẩm");
        } catch (Exception e) {
            log.error("Lỗi khi gọi Product Service cho productId {}: {}", productId, e.getMessage());
            return getProductFallback(productId, e);   // Fallback
        }
    }

    /**
     * Fallback: Trả về dữ liệu mặc định hoặc throw ApiResponseError
     */
    private ProductDto getProductFallback(Long productId, Exception e) {
        log.warn("Sử dụng fallback cho productId: {}. Lý do: {}", productId, e.getClass().getSimpleName());

        // Nâng cao: Trả về sản phẩm mặc định (để luồng Order không bị ngắt hoàn toàn)
        ProductDto defaultProduct = new ProductDto();
        defaultProduct.setId(productId);
        defaultProduct.setName("Sản phẩm tạm thời không khả dụng");
        defaultProduct.setPrice(BigDecimal.ZERO);
        defaultProduct.setStock(0);

        return defaultProduct;   // Hoặc throw new BusinessException(...) nếu muốn ép lỗi
    }
}