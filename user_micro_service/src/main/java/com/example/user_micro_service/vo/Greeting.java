package com.example.user_micro_service.vo;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Data
@Component
public class Greeting {
    @Value(value = "${greeting.message}")
    private String message;
}
