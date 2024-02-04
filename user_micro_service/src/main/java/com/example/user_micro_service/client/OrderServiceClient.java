package com.example.user_micro_service.client;

import com.example.user_micro_service.vo.ResponseOrder;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name="order-service")
public interface OrderServiceClient{

    @GetMapping("/order-service/{userId}/orders")
//    @GetMapping("/order-service/{userId}/orders_ng") // 예외 테스트용
    List<ResponseOrder> getOrders(@PathVariable String userId);

}
