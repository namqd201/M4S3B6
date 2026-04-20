package com.tmdt.m4s3b6.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {


    @PostMapping("/orders")
    public ResponseEntity<?> createOrder(@RequestBody OrderRequest request) {
        try {
            // Gọi Product
            ProductDto product = productClientService.getProductForOrder(request.getProductId());

            // Tiếp tục logic tạo Order (tính tiền, kiểm tra stock từ fallback nếu cần)...

            return ResponseEntity.ok(new ApiResponseSuccess("Tạo đơn hàng thành công", order));

        } catch (Exception e) {
            log.error("Lỗi tạo đơn hàng: ", e);
            ApiResponseError error = new ApiResponseError(
                    "Dịch vụ sản phẩm hiện không khả dụng, vui lòng thử lại sau",
                    HttpStatus.SERVICE_UNAVAILABLE.value()
            );
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(error);
        }
    }
}
