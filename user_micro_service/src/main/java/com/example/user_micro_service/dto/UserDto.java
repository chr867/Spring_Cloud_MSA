package com.example.user_micro_service.dto;

import com.example.user_micro_service.vo.ResponseOrder;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.Date;
import java.util.List;

@Data
public class UserDto {
    private String email;
    private String name;
    private String pwd;
    private String userId;
    private Date createdAt;

    private String encryptedPwd;
    private List<ResponseOrder> orders;

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }
}
