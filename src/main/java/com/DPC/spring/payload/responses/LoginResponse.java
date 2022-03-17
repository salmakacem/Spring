package com.DPC.spring.payload.responses;

import com.DPC.spring.entities.ERole;
import com.DPC.spring.entities.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponse {
    @NonNull
    private String token;
    @NotBlank
    private List<Role> roles;


}
