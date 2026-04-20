package com.tmdt.m4s3b6.controller;

import com.tmdt.m4s3b6.dto.ApiResponseError;
import com.tmdt.m4s3b6.dto.OrderRequest;
import com.tmdt.m4s3b6.dto.ProductDto;
import com.tmdt.m4s3b6.service.ProductClientService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
@Slf4j
public class OrderController {

    private final ProductClientService productClientService;

    public OrderController(ProductClientService productClientService) {
        this.productClientService = productClientService;
    }

    @PostMapping("/orders")
    public ResponseEntity<?> createOrder(@RequestBody OrderRequest request) {
        try {
            ProductDto product = productClientService.getProductForOrder(request.getProductId());

            return ResponseEntity.ok("Tao don hang thanh cong");

        } catch (Exception e) {
            log.error("Loi tao don hang: ", e);

            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                    .body(new ApiResponseError(
                            "Dich vu san pham hien khong kha dung, vui long thu lai sau",
                            HttpStatus.SERVICE_UNAVAILABLE.value()
                    ));
        }
    }
}
